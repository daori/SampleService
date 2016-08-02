package com.example.daori.servicegale;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.daori.starterservice.ServiceMaster;
import com.example.daori.starterservice.ServiceRunner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button mStartServiceA;
    Button mStartServiceB;
    Button mStopService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Declare the start service button
        mStartServiceA = (Button) findViewById(R.id.btn_start_service_a);
        mStartServiceB = (Button) findViewById(R.id.btn_start_service_b);


        // Declare the stop service button
        mStopService = (Button) findViewById(R.id.btn_stop_service);

        // Add event listener to each button
        ServiceRunner serviceRunner = new ServiceRunner(this);
        mStartServiceA.setOnClickListener(new StartServiceClickListener(ServiceA.class, serviceRunner));
        mStartServiceB.setOnClickListener(new StartServiceClickListener(ServiceB.class, serviceRunner));

        mStopService.setOnClickListener(new StopServiceClickListener(serviceRunner));
    }



//    @Override
//    public void onClick(View v) {
//        // TODO Auto-generated method stub
//        // User click start button
//        if (v.getId() == R.id.btn_start_service) {
//            // Display the Toast Message
//            startService(new Intent(getBaseContext(), ServiceA.class));
//        } else { // User click stop button
//
//            // Stop the service
//            stopService(new Intent(getBaseContext(), ServiceA.class));
//        }
//    }

    private class StartServiceClickListener<B extends ServiceMaster> implements View.OnClickListener {

        private ServiceRunner serviceRunner;
        private Class<B> serviceName;

        public StartServiceClickListener(Class<B> service, ServiceRunner serviceRunner) {
            this.serviceName = service;
            this.serviceRunner = serviceRunner;
        }

        @Override
        public void onClick(View v) {
            serviceRunner.run(serviceName);
        }
    }

    private class StopServiceClickListener implements View.OnClickListener {
        private ServiceRunner serviceRunner;

        public StopServiceClickListener(ServiceRunner serviceRunner) {
            this.serviceRunner = serviceRunner;
        }

        @Override
        public void onClick(View v) {
            List<Class> listServiceMaster = new ArrayList<>();
            listServiceMaster.add(ServiceA.class);
            listServiceMaster.add(ServiceB.class);
            serviceRunner.stop(listServiceMaster);
        }
    }
}