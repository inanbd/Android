package com.example.kaiza.dxball_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by kaizar on 5/15/2016.
 */
public class StartGame extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new GameCanvas(this));
       // next();
    }
    public void next(){
        Intent i=new Intent(this,InsertName.class);
        startActivity(i);
    }

    /*
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent i=new Intent(this,InsertName.class);
        startActivity(i);
    }
    */
}
