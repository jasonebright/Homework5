package com.example.homework5;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;


public class MyCanvas extends View {
    HashMap <Integer, Path> activePaths;
    Paint pathPaint;
    private static final int STATE_STILL=0;
    private static final int STATE_MOVING=1;
    private static int DEFAULT_COLOR;

    private int state=0;
    private ArrayList<Paint> paintPenList =new ArrayList<>();
    private Path latestPath;
    private Paint latestPaint;
    private ArrayList<Path> pathPenList =new ArrayList<>();
    //private GetCoordinateCallback callbackForCoordinate;
    private int lineWidth =70;
    private int currentColor;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        pathPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pathPaint.setColor(Color.BLACK);
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setStrokeWidth(70);

        DEFAULT_COLOR = Color.RED;
        currentColor = DEFAULT_COLOR;
        initPaintPen(currentColor);
    }

    private void initPaintPen(int color){
        latestPaint=getNewPaintPen(color);
        latestPath=getNewPathPen();

        paintPenList.add(latestPaint);
        pathPenList.add(latestPath);
    }

    private Path getNewPathPen() {
        Path path = new Path();
        return path;
    }

    private Paint getNewPaintPen(int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(70);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Path path: activePaths.values()){
            canvas.drawPath(path, pathPaint);
        }
        super.onDraw(canvas);
    }

    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }
}
