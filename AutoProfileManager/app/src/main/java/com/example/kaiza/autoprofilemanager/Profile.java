package com.example.kaiza.autoprofilemanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kaizar on 5/15/2016.
 */
public class Profile extends Service implements SensorEventListener {


    SensorManager sensorManager;
    Sensor s1,s2;
    AudioManager audioManager;


    int status=0;
    float prox;
    Boolean flag=true;
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onCreate() {

        super.onCreate();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        s1 = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        s2= sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this,s1, sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this,s2, sensorManager.SENSOR_DELAY_NORMAL);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();;
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        sensorManager.unregisterListener(this);
        Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();;
    }


    void offVribrate(){
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        //int x=myAudioManager.getStreamMaxVolume(AudioManager.STREAM_RING);
        //Log.d("My", "ans"+x);

        //myAudioManager.adjustVolume(myAudioManager.ADJUST_RAISE, myAudioManager.FLAG_PLAY_SOUND);
        audioManager.setStreamVolume(AudioManager.STREAM_RING, audioManager.getStreamMaxVolume(AudioManager.STREAM_RING), AudioManager.FLAG_PLAY_SOUND);
        Vibrator myVibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        myVibrator.cancel();
    }



    public void ringing()
    {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        audioManager.setStreamVolume(AudioManager.STREAM_RING,audioManager.getStreamMaxVolume(AudioManager.STREAM_RING), AudioManager.FLAG_PLAY_SOUND);
        SystemClock.sleep(500);
    }
    public void vibrate()
    {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        Vibrator myVibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        SystemClock.sleep(500);
        myVibrator.cancel();
    }
    public void silent()
    {
        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        Vibrator myVibrator= (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        myVibrator.cancel();
    }






    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor ss=event.sensor;
        if(ss.getType()==Sensor.TYPE_PROXIMITY){
            prox=event.values[0];
        }

        if(ss.getType()==Sensor.TYPE_ACCELEROMETER){
            //if((event.values[0]< 0.16 && event.values[1]<(0) && event.values[2] >10))
            if(prox>0&&event.values[2]>6)
            {
                if(status!=1){
                    ringing();
                    Toast.makeText(getApplicationContext(), "Ring", Toast.LENGTH_SHORT).show();
                    status=1;
                }
            }
            //else if((event.values[0]< 0.4 && event.values[1]<0.4 && event.values[2] >(-11)))
            else if(prox<1 && event.values[2]<6&&event.values[2]>-5)
            {
                if(status!=2){
                    vibrate();
                    Toast.makeText(getApplicationContext(), "Vibrate", Toast.LENGTH_SHORT).show();
                    status=2;
                }
            }
            else if(prox<1 &&event.values[2]<-5){
               if(status!=3){
                   silent();
                   Toast.makeText(getApplicationContext(), "Silent", Toast.LENGTH_SHORT).show();
                   status=3;

               }


            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // TODO Auto-generated method stub

    }






}
