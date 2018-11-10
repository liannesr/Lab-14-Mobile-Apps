package com.techexchange.mobileapps.lab14;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import java.util.ArrayList;
import java.util.Random;

public class MultiBouncingBallView extends View{
    private static final String TAG = "MultiBouncingBallView";
    private int delayMillis = 30;
    private int ovalX;
    private int ovalY;
    private int ovalH;
    private int ovalW;
    private int xDistance;
    private int yDistance;
    private int screenWidth;
    private int screenHeight;
    private ArrayList<Particle> particleList= new ArrayList<>();
    private static int NUM_BALLS = 2;



    // Having this constructor is necessary.
    public MultiBouncingBallView(Context context) {
        super(context);
        ovalH=200;
        ovalW=200;
        xDistance=5;
        yDistance=5;
        Random random = new Random();
        Random number = new Random();
        for(int i =0; i<NUM_BALLS;i++){
            ovalX = number.nextInt(10000);
            ovalY = number.nextInt(10000);
            int r = random.nextInt(255);
            int g = random.nextInt(255);
            int b = random.nextInt(255);
            particleList.add(new Particle(ovalX,ovalY,ovalH,ovalW, new Paint(),xDistance,yDistance, Color.rgb(r, g, b)));
        }

    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Particle currentParticle;
        // This is where we'll be drawing things...
        for(int j=0;j<NUM_BALLS;j++){
            Particle currentParticle = particleList.get(j);
            canvas.drawOval(currentParticle.getOvalX(),currentParticle.getOvalY(),currentParticle.getOvalX()+currentParticle.getOvalW(),
                    currentParticle.getOvalY()+currentParticle.getOvalH(), currentParticle.getPaint());
            for(int k=0;k<NUM_BALLS;k++){
                if(j!=k){
                    detectCollision(currentParticle, particleList.get(k));
                }
            }
            update(currentParticle);
        }

        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException ex) {
            Log.e(TAG, "Sleep interrupted!", ex);
        }

        invalidate(); // Force a redraw.
    }

    private void update(Particle particle) {
        if(particle.getOvalX()+particle.getOvalW()>screenWidth){
            int d = particle.getOvalX()+particle.getOvalW()-screenWidth;
            particle.setxDistance(-particle.getxDistance());
            //xDistance = -xDistance;
            particle.setOvalX(particle.getOvalX()-2*d);
            particle.setOvalY(particle.getOvalY()+particle.getyDistance());

        }
        else if(particle.getOvalX()<0){
            int d = -particle.getOvalX();
            particle.setOvalX(particle.getOvalX()+2*d);
            particle.setxDistance(-particle.getxDistance());
           // xDistance = -xDistance;
            particle.setOvalY(particle.getOvalY()+particle.getyDistance());
        }
        else if(particle.getOvalY()+particle.getOvalH()>screenHeight){
            int d =  particle.getOvalY()+particle.getOvalH()-screenHeight;
            particle.setyDistance(-particle.getyDistance());
            //yDistance = -yDistance;
            particle.setOvalY(particle.getOvalY()-2*d);
            particle.setOvalX(particle.getOvalX()+particle.getxDistance());
        }
        else if(particle.getOvalY()<0){
            particle.setyDistance(-particle.getyDistance());
            //yDistance = -yDistance;
            int d = -particle.getOvalY();
            particle.setOvalY(particle.getOvalY()+2*d);
            particle.setOvalX(particle.getOvalX()+particle.getxDistance());
        }
        else{
            particle.setOvalX(particle.getOvalX()+particle.getxDistance());
            particle.setOvalY(particle.getOvalY()+particle.getyDistance());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenHeight = h;
        screenWidth = w;
    }

    public boolean detectCollision(Particle particleOne, Particle particleTwo){
        int sumOfradiuses = particleOne.getOvalW()/2 + particleTwo.getOvalW()/2;
        int xVector =  (particleOne.getOvalX() + particleOne.getOvalW()/2) - (particleTwo.getOvalX() + particleTwo.getOvalW()/2);
        int yVector =  (particleOne.getOvalY() + particleOne.getOvalH()/2) - (particleTwo.getOvalY() + particleTwo.getOvalH()/2);
        double distance = Math.sqrt((xVector*xVector)+(yVector*yVector));

        if(distance<sumOfradiuses){
            //Log.d(TAG, "RADIOUS: "+sumOfradiuses)Log.d(TAG, "DISTANCE: "+ distance);
            Log.d(TAG, "COLLISION!");
            return true;
        }
        return false;
    }

}
