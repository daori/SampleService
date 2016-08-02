package com.example.daori.starterservice;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.List;

/**
 * Created by daori on 8/2/16.
 */
public class ServiceRunner {

    private Context context;

    public ServiceRunner(Context context) {
        this.context = context;
    }

    public void run(Class serviceClass){
        context.startService(new Intent(context, serviceClass));
    }

    public void stop(List<Class> listOfService){
        for (Class serviceClass : listOfService){
            if(isServiceRunning(serviceClass)) {
                Log.d("Service", "Stop Service : " +serviceClass.getName());
                context.stopService(new Intent(context, serviceClass));
            }
        }
    }

    private boolean isServiceRunning(Class<?> serviceClass){
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo serviceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)){
            if (serviceClass.getName().equals(serviceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
