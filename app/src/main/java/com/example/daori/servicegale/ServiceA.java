package com.example.daori.servicegale;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.example.daori.starterservice.ServiceMaster;

import java.util.Timer;
import java.util.TimerTask;

public class ServiceA extends Service implements ServiceMaster{

    private static final String TAG = "DaoServiceA";
    private final int INTERVAL = 6 * 1000;
    private Timer timer = new Timer();

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onCreate() Called by the system when the service
     * is first created. Do not call this method directly.
     */
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Toast.makeText(this, "Service A is created", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "Service A created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        // Display the Toast Message
        Toast.makeText(this, "Start Service A", Toast.LENGTH_SHORT).show();

        // Execute an action after period time
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                // Print a log
                Log.d(TAG, "Start Service A ");
            }
        }, 0, INTERVAL);
        return super.onStartCommand(intent, flags, startId);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Service#onDestroy() Service is destroyed when user stop
     * the service
     */
    @Override
    public void onDestroy() {
        // Display the Toast Message
        Toast.makeText(this, "Stop Service A", Toast.LENGTH_SHORT).show();
        if (timer != null) {
            timer.cancel();
        }
        super.onDestroy();
    }

    @Override
    public Class getClassName() {
        return ServiceA.class;
    }
}