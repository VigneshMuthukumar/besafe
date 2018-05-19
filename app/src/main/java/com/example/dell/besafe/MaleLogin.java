package com.example.dell.besafe;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import android.database.sqlite.SQLiteDatabaseLockedException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MaleLogin extends Activity {


    EditText one,two,three,four,five,six;
    Button save,search;
    String name,mail,mobile,location,log,lat,address,logval,latval;
    public static String url ="http://cyberstudents.in/android/1617Staff/RK/Geo/Service.asmx";
    private static final String Soap_Name = "http://tempuri.org/";
    private static String Soap_Url = "";
    private static String Soap_Method= "";
   SQLiteDatabase db;
    String lognitude;
    String lattitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        one=(EditText)findViewById(R.id.malename);
        two=(EditText)findViewById(R.id.malemail);
        three=(EditText)findViewById(R.id.malemobile);
        four=(EditText)findViewById(R.id.malelocation);
        five=(EditText)findViewById(R.id.logval);
        six=(EditText)findViewById(R.id.lotval);
        search=(Button)findViewById(R.id.get);
        save=(Button)findViewById(R.id.malesubmit);
        db=openOrCreateDatabase("BeSafety", Context.MODE_PRIVATE, null);
       db.execSQL("CREATE TABLE IF NOT EXISTS ContactDetails(name varchar, mail varchar, mobile varchar, address varchar, logval varchar, latval varchar);");
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                getlocation(view);

            }
        });


            save.setOnClickListener(new OnClickListener() {



                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub


                    name = one.getText().toString();
                    mail = two.getText().toString();
                    mobile = three.getText().toString();
                    address = four.getText().toString();
                    logval = five.getText().toString();
                    latval = six.getText().toString();


                    if (!name.equals("") && !mail.equals("") && !mobile.equals("") && !address.equals("") && !logval.equals("") && !latval.equals("")) {
                  String str="insert into ContactDetails values('"+name+"','"+mail+"','"+mobile+"','"+address+"','"+logval+"','"+latval+"')";
                       db.execSQL(str);


                            one.setText("");
                            two.setText("");
                            three.setText("");
                            four.setText("");
                            five.setText("");
                            six.setText("");
                        Toast.makeText(getApplicationContext(),"Stored Successfull",Toast.LENGTH_SHORT).show();




                    } else {
                        Toast.makeText(getApplicationContext(), "Fill all the fields ", Toast.LENGTH_SHORT).show();

                        one.setError("");
                        two.setError("");
                        three.setError("");
                        four.setError("");
                        five.setError("");
                        six.setError("");
                    }
                }
            });




            }

    public void getlocation(View v)
    {


        address=four.getText().toString();
        GeocodingLocation locationAddress = new GeocodingLocation();
        locationAddress.getAddressFromLocation(address,
                getApplicationContext(), new GeocoderHandler());



    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String lognitude;
            String lattitude;

            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    lognitude = bundle.getString("address");
                    lattitude=bundle.getString("address1");
                    break;
                default:
                    lognitude = null;
                    lattitude=null;
            }

            five.setText(lattitude);
            six.setText(lognitude);
        }
    }





}
