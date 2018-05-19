package com.example.dell.besafe;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup extends Activity {

    EditText one,two,three,four;
    TextView signup;

    String uname,pass,mail,mobile;

    public static String url ="http://cyberstudents.in/android/1617Staff/RK/Geo/Service.asmx";
    private static final String Soap_Name = "http://tempuri.org/";
    private static String Soap_Url = "";
    private static String Soap_Method= "";
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        one=(EditText)findViewById(R.id.Uname);
        two=(EditText)findViewById(R.id.pass);
        three=(EditText)findViewById(R.id.mail);
        four=(EditText)findViewById(R.id.mobile);
        signup=(TextView)findViewById(R.id.sinfemale);
        db=openOrCreateDatabase("BeSafety", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS Register(uname varchar, pass varchar, mail varchar, mobile varchar);");


        signup.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                uname=one.getText().toString();
                pass=two.getText().toString();
                mail=three.getText().toString();
                mobile=four.getText().toString();


                if(!uname.equals("")&& !pass.equals("")&& !mail.equals("")&& !mobile.equals(""))
                {


                    String str="insert into Register values('"+uname+"','"+pass+"','"+mail+"','"+mobile+"')";
                    db.execSQL(str);
                    one.setText("");
                    two.setText("");
                    three.setText("");
                    four.setText("");
                   Toast.makeText(getApplicationContext(),"Stored Successfull",Toast.LENGTH_SHORT).show();


                }

                else{

                    Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_SHORT).show();
                    one.setError("");
                    two.setError("");
                    three.setError("");
                    four.setError("");








                }

            }
        });





    }


}
