package com.techexchange.mobileapps.lab14;

import android.graphics.Color;
import android.graphics.Paint;
import java.util.Random;

public class Particle {
    private int ovalX;
    private int ovalY;
    private int ovalH;
    private int ovalW;
    private int xDistance;
    private int yDistance;
    private  Paint paintComponent;

    public Particle(int ovalX, int ovalY, int ovalH, int ovalW, Paint paintComponent, int xDistance, int yDistance, int color){
        this.ovalX = ovalX;
        this.ovalY = ovalY;
        this.ovalH = ovalH;
        this.ovalW = ovalW;
        this.paintComponent = paintComponent;
        this.paintComponent.setColor(color);
        this.xDistance = xDistance;
        this.yDistance = yDistance;
    }

    public int getOvalH() {
        return ovalH;
    }

    public int getOvalW() {
        return ovalW;
    }

    public int getOvalX() {
        return ovalX;
    }

    public int getOvalY() {
        return ovalY;
    }

    public void setOvalH(int ovalH) {
        this.ovalH = ovalH;
    }

    public void setOvalW(int ovalW) {
        this.ovalW = ovalW;
    }

    public void setOvalX(int ovalX) {
        this.ovalX = ovalX;
    }

    public void setOvalY(int ovalY) {
        this.ovalY = ovalY;
    }

    public Paint getPaint() {
        return paintComponent;
    }

    public int getxDistance() {
        return xDistance;
    }

    public int getyDistance() {
        return yDistance;
    }

    public void setxDistance(int xDistance) {
        this.xDistance = xDistance;
    }

    public void setyDistance(int yDistance) {
        this.yDistance = yDistance;
    }
}
