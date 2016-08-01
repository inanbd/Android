package com.example.kaiza.dxball_1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

/*
 * Created by kaizar on 4/11/2016.
 */
public class Brick {

    public static final String TAG="Bick POS:";

    private float left=0;
    private  float top=0;
    private  float right=0;
    private  float bottom=0;
    private  int color;
    int type;

    float screen_width;
    float screen_height;

    private Paint paint;

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
    public int getType(){
        return type;
    }
    public void setType(int t){
        this.type=t;
    }

    /*
    * type 1: life -1, color: red;
    * type 2: life -2, color:yellow;
    * type 3: life -3 color: magenta;
    * */

    public Brick(Canvas canvas){

        left=100;
        top=100;
        right=200;
        bottom = 200;
        color= Color.RED;
        paint = new Paint();


        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(left,top,right,bottom,paint);

        //canvas.drawRect(100, 100, 200, 200, paint);

    }

    //overloaded function for every input
    public Brick(float left,float top,float right , float bottom ,int color){
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom =bottom;

        this.color= color;
        paint = new Paint();

        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

       // canvas.drawRect(left,top,right,bottom,paint);

    }

    public Brick(int BrickPos,int type,Canvas canvas){
        this.type=type;

        BrickPos--;

        screen_width=canvas.getWidth();
        screen_height=canvas.getHeight();

        if(type==1){
            this.color=Color.RED;
        }else if(type==2){
            this.color=Color.YELLOW;
        }else if(type==3){
            this.color=Color.MAGENTA;
        }else if(type==4){
            this.color=Color.BLUE;
        }

        int col=BrickPos%5;
        int row=BrickPos/5;

        Log.d(TAG, "col: "+col);
        Log.d(TAG, "row: "+row);

        if(col==0){
            this.left=0;
            this.right=screen_width/5;
            right--;
            left++;
        }else if(col==1){
            this.left=screen_width/5;
            this.right = screen_width/2.5f;
            right--;
            left++;

        }else if(col==2){
            this.left=screen_width/2.5f;
            this.right = screen_width/1.7f;
            right--;
            left++;

        }else if(col==3){

            this.left=screen_width/1.7f;
            this.right = screen_width/1.25f;
            right--;
            left++;

        }else if(col==4){
            this.left = screen_width/1.25f;
            this.right=screen_width;
            right--;
            left++;
        }

        if(row==0){
            this.top=screen_height/12;
            this.bottom=screen_height/6;
            bottom--;
        }else if(row==1){
            this.top=screen_height/6;
            this.bottom=screen_height/4;
            bottom--;

        }else if(row==2){
            this.top=screen_height/4;
            this.bottom=screen_height/3;
            bottom--;

        }else if(row==3){
            this.top=screen_height/3;
            this.bottom=screen_height/2.4f;
            bottom--;

        }else if(row==4){
            this.top=screen_height/2.4f;
            this.bottom=screen_height/2;
            bottom--;

        }
        this.paint=new Paint();

        this.paint.setColor(color);
        this.paint.setStyle(Paint.Style.FILL);

    }


    public  void  onDraw(Canvas canvas){
        if(type==1){
            this.color=Color.RED;
        }else if(type==2){
            this.color=Color.YELLOW;
        }else if(type==3){
            this.color=Color.MAGENTA;
        }else if(type==4){
            this.color=Color.BLUE;
        }
        this.paint.setColor(color);
        this.paint.setStyle(Paint.Style.FILL);

        canvas.drawRect(left,top,right,bottom,paint);

    }

}
