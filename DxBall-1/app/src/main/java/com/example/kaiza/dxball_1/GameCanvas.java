package com.example.kaiza.dxball_1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class GameCanvas extends View{

    public static boolean newLife;
    public static boolean gameOver;

    private int speedDivisor;

    Paint paint;
    float screenSizeX=0, screenSizeY=0;
    int ballSpeed,lvl=0,totalLvl=0,nextLvl=1;
    Ball b1;
    Brick br1,br2,br3;
    Bar bar;
    public static int life,score=0;

    int level[][]={ //upto 20  in each row..(1-4 each)
            {1,1,1,1,1},
            {4,3,1,2,3,4,3,2,2,2,3,3,1,1,3,2,4,2,3,4},
            {1,2,3,3,2,1,4,4,4}

    };
//    int level2[]={4,3,1,2,3,4,3,2,2,2,3,3,1,1,3,2,4,2,3,4};

    ArrayList<Brick> bricks=new ArrayList<Brick>();


    private static final String TAG = "MyActivity";


    boolean firstTime=true,job=true;


    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        life=3;
        MainActivity.gameStarted=true;
        MainActivity.score=0;
        speedDivisor=90;
    }

    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        if(firstTime)
        {
            score=0;
            totalLvl=level.length;

            firstTime=false;

            gameOver=false;

            ballSpeed = (int)canvas.getHeight()/speedDivisor;
            //ballSpeed=4;
            screenSizeX = canvas.getWidth();
            screenSizeY = canvas.getHeight();
            bar = new Bar(canvas);


            //ball
           // b1 = new Ball(canvas.getWidth()/2,canvas.getHeight()/2,Color.RED,canvas.getWidth()/25);

            b1=new Ball((int)bar.getMiddle(),(int) bar.getTop()-canvas.getWidth()/25 -10,canvas.getWidth()/25);
            b1.bounce(canvas);
            b1.setDx(ballSpeed);
            b1.setDy(-ballSpeed);

            //bricks
            for(int i=1;i<=level[lvl].length;i++){
                if(level[lvl][i-1]>=0&&level[lvl][i-1]<=4){
                    bricks.add(new Brick(i,level[lvl][i-1],canvas));
                }
            }
            //bar


        }
        if(lvl==nextLvl && gameOver==false){ //new Level
            nextLvl++;
            b1.setBall((int)bar.getMiddle(),(int) bar.getTop()-canvas.getWidth()/25 -10);

            speedDivisor-=10;

            ballSpeed = (int)canvas.getHeight()/speedDivisor;
            b1.setDy(ballSpeed);
            b1.setDy(-ballSpeed);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            for(int i=1;i<=level[lvl].length;i++){
                if(level[lvl][i-1]>=0&&level[lvl][i-1]<=4){
                    bricks.add(new Brick(i,level[lvl][i-1],canvas));
                }
            }

        }
        if(newLife){

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            newLife = false;
            //new ball

           // b1=new Ball((int)bar.getMiddle(),(int) bar.getTop()+canvas.getWidth()/25,canvas.getWidth()/25);
           // b1.setBall((int)bar.getMiddle(),(int) Math.floor(bar.getTop()+canvas.getWidth()/25));
            //b1.setBall(canvas.getWidth()/2,canvas.getHeight()-50);
            b1.setBall((int)bar.getMiddle(),(int) bar.getTop()-canvas.getWidth()/25 -10);

            b1.setDx(ballSpeed);
            b1.setDy(-ballSpeed);


        }


        canvas.drawRGB(255, 255, 255);

        b1.onDraw(canvas);

        this.ballBrickCollision(bricks,b1,canvas);
        this.ballBarCollision(bar,b1, canvas);
        b1.ballBoundaryCheck(canvas);

        b1.move();


        //br1.onDraw(canvas);br2.onDraw(canvas);br3.onDraw(canvas);

        for(int i=0;i<bricks.size();i++){
            bricks.get(i).onDraw(canvas);

        }

        //score
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Score: "+score,10,40,paint);

        //level
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Level: "+lvl,(canvas.getWidth()/2)-50,40,paint);

        //life
        paint.setTextSize(30);
        paint.setFakeBoldText(true);
        canvas.drawText("Life: "+life,canvas.getWidth()-110,40,paint);



        bar.onDraw(canvas);

        if(gameOver){


            paint.setColor(Color.WHITE);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);

            paint.setColor(Color.RED);
            paint.setTextSize(40);
            paint.setFakeBoldText(true);
            canvas.drawText("GAME OVER",canvas.getWidth()/2-110,canvas.getHeight()/2,paint);
            paint.setColor(Color.BLUE);
            canvas.drawText("SCORE: "+score,canvas.getWidth()/2-100,canvas.getHeight()/2+60,paint);
            ballSpeed =0;
            //gameOver = false;

            MainActivity.score= score;

        }

        invalidate();
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int X = (int) event.getX();

        bar.x=X;

       // Log.d(TAG, "onTouchEvent: "+event.getX());

        return true; //by returning true it will give continious touch values...
        //return super.onTouchEvent(event);
    }

    public void ballBarCollision(Bar myBar,Ball myBall,Canvas canvas){
        if(((myBall.getY()+myBall.getRadius())>=myBar.getTop())&&((myBall.getY()+myBall.getRadius())<=myBar.getBottom())&& ((myBall.getX()+myBall.getRadius())>=myBar.getLeft())&& ((myBall.getX()+myBall.getRadius())<=myBar.getRight())) {
            myBall.setDy(-(myBall.getDy()));

        }

    }
    public void ballBrickCollision(ArrayList<Brick> br ,Ball myBall,Canvas canvas){
        for(int i=0;i<br.size();i++) {
            if (((myBall.getY() - myBall.getRadius()) <= br.get(i).getBottom()) && ((myBall.getY() + myBall.getRadius()) >= br.get(i).getTop()) && ((myBall.getX()) >= br.get(i).getLeft()) && ((myBall.getX()) <= br.get(i).getRight())) {

                if(br.get(i).getType() > 1){
                    br.get(i).setType(br.get(i).getType()-1);
                }
                else
                    br.remove(i);

                if(br.size()==0){
                    lvl++;
                    if(lvl==totalLvl){
                        gameOver = true;
                    }

                }
                score+=1;
                MainActivity.score+=1;
                myBall.setDy(-(myBall.getDy()));
            }
        }

    }



}
