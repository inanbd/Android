package com.example.kaiza.dxball_1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Bar {

    private  float screenSizeX =0;
    private  float screenSizeY =0;
    public   float x=0;

    private float left=0;
    private  float top=0;
    private  float right=0;
    private  float bottom=0;
    //private  float circleRadius=0;
    private  int color;
    private Paint paintRect;
//    private  Paint paintCirc;


    public float getMiddle(){
        if(x<=10||x>=screenSizeX-10){
            x=screenSizeX/2;
        }
        return x;
    }
    public float getTop(){
        return top;
    }
    public float getBottom(){
        return bottom;
    }
    public float getLeft(){
        return left;
    }
    public float getRight(){
        return right;
    }



    private static final String TAG = "MyActivity";

    public  Bar(Canvas canvas){
        screenSizeX=canvas.getWidth();
        screenSizeY=canvas.getHeight();
        paintRect = new Paint();

        x=(int)screenSizeX/2;

        left =x-screenSizeX/6;
        right = x+screenSizeX/6;
       // paintCirc = new Paint();

        bottom = screenSizeY-screenSizeY/60;
        top = screenSizeY- screenSizeY/20;

        paintRect.setColor(Color.BLUE);
        paintRect.setStyle(Paint.Style.FILL);



        //paintCirc.setColor(Color.WHITE);
        //paintCirc.setStyle(Paint.Style.FILL);

        //Log.d(TAG, "Bar: x:"+screenSizeX+"Y: "+screenSizeY);
        //canvas.drawRect(100, 100, 200, 200, paint);
        Log.d(TAG, "left: "+left+" Right: "+right);
       // canvas.drawRect(left,screenSizeY/30,right,screenSizeY/29,paintRect);

    }

    public  void  onDraw(Canvas canvas){
        left =x-screenSizeX/6;
        right = x+screenSizeX/6;
        bottom = screenSizeY-screenSizeY/60;
        top = screenSizeY- screenSizeY/20;
//      canvas.drawRect(left,screenSizeY/30,right,screenSizeY/25,paintRect);
        canvas.drawRect(left,top,right,bottom,paintRect);
    }
    public  void setX(float x){
        this.x = x;

    }

}
