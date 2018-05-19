package com.example.dell.besafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class Infopage extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infopage);
        new rk().start();
    }
    public class rk extends Thread
    {
        public void run()
        {
            try{
                Thread.sleep(3000);
                Intent in=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(in);
            }
            catch(Exception e)
            {
                e.printStackTrace();
                Log.e("error", e.toString());

            }
        }
    }
}