package com.example.homework5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.provider.MediaStore;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;

public class DrawActivity extends AppCompatActivity implements View.OnClickListener {

    MyCanvas myCanvas;
    TouchListener touchListener;
    Button red,blue, green, undo, clear, done;
    static final int REQUEST_IMAGE= 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        myCanvas = (MyCanvas) findViewById(R.id.myCanvas);
        Bundle b1 = getIntent().getExtras();
        Bitmap thumbnail = (Bitmap) b1.get("photo");
        myCanvas.setBackground(new BitmapDrawable(getResources(), thumbnail));
        touchListener = new TouchListener(this);
        myCanvas.setOnTouchListener(touchListener);

        red = (Button) findViewById(R.id.buttonRed);
        blue = (Button) findViewById(R.id.buttonBlue);
        green = (Button) findViewById(R.id.buttonGreen);
        undo = (Button) findViewById(R.id.buttonUndo);
        clear = (Button) findViewById(R.id.buttonClear);
        done = (Button) findViewById(R.id.buttonDone);

        red.setOnClickListener(this);
        blue.setOnClickListener(this);
        green.setOnClickListener(this);
        undo.setOnClickListener(this);
        clear.setOnClickListener(this);
        done.setOnClickListener(this);


    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) {
        myCanvas.removePath(id);
    }

    public void onDoubleTap(float x, float y) {

        myCanvas.doubleTap(x, y);
    }

    public void onLongPress(float x, float y) {
        myCanvas.longPress( x,  y);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap thumbnail = (Bitmap) extras.get("data");
            myCanvas.setBackground(new BitmapDrawable(getResources(), thumbnail));
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.buttonRed:
                myCanvas.setDrawColor(Color.RED);
                break;
            case R.id.buttonBlue:
                myCanvas.setDrawColor(Color.BLUE);
                break;
            case R.id.buttonGreen:
                myCanvas.setDrawColor(Color.GREEN);
                break;
            case R.id.buttonUndo:
                myCanvas.undo();
                break;
            case R.id.buttonClear:
                myCanvas.clear();
                break;
            case R.id.buttonDone:
                finish();
                break;
        }

    }
}