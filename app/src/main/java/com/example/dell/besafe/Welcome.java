package com.example.dell.besafe;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.example.dell.besafe.ShakeDetector.*;

public class Welcome extends Activity {

    ImageView profile,search,con,location;
    public String username;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);



        search=(ImageView)findViewById(R.id.search);
        con=(ImageView)findViewById(R.id.contact);


        username=getIntent().getExtras().getString("name");



        search.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent in=new Intent(getApplicationContext(),Search.class);

                in.putExtra("loginname", username);

                startActivity(in);

            }
        });


        con.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent in=new Intent(getApplicationContext(),Preferedcontacts.class);
                in.putExtra("loginname", username);
                startActivity(in);

            }
        });



        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager
                .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new OnShakeListener() {

            @Override
            public void onShake(int count) {

                Intent in=new Intent(getApplicationContext(),Profile.class);

                in.putExtra("key", username);
                startActivity(in);


            }
        });


    }

    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

}
