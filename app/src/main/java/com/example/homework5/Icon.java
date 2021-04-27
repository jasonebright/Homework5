package com.example.homework5;

import android.graphics.Bitmap;

public class Icon {
    Bitmap img;
    float x,y;
    public Icon(Bitmap img, float x, float y){
        this.img = img;
        this.x = x;
        this.y = y;

    }

    public Bitmap getImg() {
        return img;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
