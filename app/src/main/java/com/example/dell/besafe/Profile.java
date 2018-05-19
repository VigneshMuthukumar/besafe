package com.example.dell.besafe;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Profile extends Activity {

    String val;
    TextView pro;

    GPSTracker gps;
    Button sub;
    double latitude,longitude;

    public static String url ="http://cyberstudents.in/android/1617Staff/RK/Geo/Service.asmx";
    private static final String Soap_Name = "http://tempuri.org/";
    private static String Soap_Url = "";
    private static String Soap_Method = "";
    String name, id, month,percen ;
    HashMap<String, String> data;
    String myUnameintent = "4";
    String lat,log;
    ListView list;
    String myid, myname, mymonth,mypercen;
    ImageView send;
SQLiteDatabase db;
    String username;
    ArrayList<HashMap<String, String>> myArraylist = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

//        Typeface tf = Typeface.createFromAsset(getAssets(),
//                "fonts/ANDROID ROBOT.ttf");
        send=(ImageView)findViewById(R.id.panicbutton);
        db=openOrCreateDatabase("BeSafety", Context.MODE_PRIVATE, null);


        username=getIntent().getExtras().getString("key");
        gps=new GPSTracker(Profile.this);


        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            lat=String.valueOf(latitude);
            log=String.valueOf(longitude);

            Log.e("log", log);
            Log.e("lat", lat);


        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }


        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                SoapGetmobno();

            }
        });



        send.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // TODO Auto-generated method stub



//			  if(event.getAction() == MotionEvent.ACTION_UP) {
//	                send.setBackgroundColor(Color.TRANSPARENT);
//
//
//
//			  }
                return false;
            }
        }) ;



    }
    public ArrayList<HashMap<String, String>> SoapGetmobno() {


//
        ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();

        SmsManager smsManager = SmsManager.getDefault();
        StringBuffer smsBody = new StringBuffer();
        smsBody.append("http://maps.google.com/?q=");
        smsBody.append(lat);
        smsBody.append(",");
        smsBody.append(log);

        Cursor c=db.rawQuery("SELECT * FROM AddContacts where uname='"+username+"'" , null);
        if(c.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"No Prefered Contacts",Toast.LENGTH_SHORT).show();
        }
        else{
            while (c.moveToNext()) {


                myname= c.getString(2);
                Log.e("logsms ", log);
                Log.e("lat sms", lat);
                smsManager.sendTextMessage(myname, null, "Its me "+username +"  Please help!!!  I am in danger and need assistance. Please Track my Location"+smsBody.toString(), null, null);






            }
            Toast.makeText(getApplicationContext(), "Alert Foward To your Guardians", Toast.LENGTH_SHORT).show();


        }
        return lista;

    }


}
