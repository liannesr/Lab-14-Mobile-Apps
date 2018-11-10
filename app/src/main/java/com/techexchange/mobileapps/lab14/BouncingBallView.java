package com.techexchange.mobileapps.lab14;
import android.graphics.Color;
import android.view.View;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.graphics.Paint;

public class BouncingBallView extends View  {

    private static final String TAG = "BouncingBallView";
    private int delayMillis = 30;
    private int ovalX;
    private int ovalY;
    private int ovalH;
    private int ovalW;
    private int xDistance;
    private int yDistance;
    private int screenWidth;
    private int screenHeight;
    private static Paint paintComponent = new Paint();


    // Having this constructor is necessary.
    public BouncingBallView(Context context) {
        super(context);
        ovalX=0;
        ovalY=100;
        ovalH=300;
        ovalW=300;
        xDistance=5;
        yDistance=5;
        paintComponent.setColor(Color.GREEN);


    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // This is where we'll be drawing things...
        canvas.drawOval(ovalX,ovalY,ovalX+ovalW, ovalY+ovalH, paintComponent);
        update();
        try {
            Thread.sleep(delayMillis);
        } catch (InterruptedException ex) {
            Log.e(TAG, "Sleep interrupted!", ex);
        }

        invalidate(); // Force a redraw.
    }

    private void update() {
        // Perform physics simulations here...
        if(ovalX+ovalW>screenWidth){
            int d = ovalX+ovalW-screenWidth;
            xDistance = -xDistance;
            ovalX-=2*d;
            ovalY+=yDistance;

        }
        else if(ovalX<0){
            int d = -ovalX;
            ovalX += 2*d;
            xDistance = -xDistance;
            ovalY+=yDistance;
        }
        else if(ovalY+ovalH>screenHeight){
            int d = ovalY+ovalH-screenHeight;
            yDistance = -yDistance;
            ovalY-=2*d;
            ovalX+=xDistance;
        }
        else if(ovalY<0){
            yDistance = -yDistance;
            int d = -ovalY;
            ovalY += 2*d;
            ovalX+=xDistance;
        }
        else{
            ovalX+=xDistance;
            ovalY+=yDistance;
        }



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenHeight = h;
        screenWidth = w;
    }
}
