package com.example.kaiza.autoprofilemanager;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    Intent intent;

    Sensor proximity ;
    Sensor accelerometer;
    //Boolean flag=true;
    EditText et1,et2,et3,et4;

    SensorManager sensorManager;
    Sensor s1 ;
    Sensor s2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        s1 =sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        s2= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        et1=(EditText) findViewById(R.id.editText1);
        et2=(EditText) findViewById(R.id.editText2);
        et3=(EditText) findViewById(R.id.editText3);
        et4=(EditText) findViewById(R.id.editText4);


    }

    @Override
    protected void onResume() {
        super.onResume();


        sensorManager.registerListener(this,s1,SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,s2,SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void startSrvc(View v){

        intent = new Intent(MainActivity.this,Profile.class);
        startService(intent);
    }
    public void stopSrvc(View v){

        stopService(intent);

    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if(sensor.getType()==Sensor.TYPE_PROXIMITY){
            et1.setText("P:"+event.values[0]);
        }
        else if(sensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            et2.setText("x:" + event.values[0]);
            et3.setText("y:" + event.values[1]);
            et4.setText("z:" + event.values[2]);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
