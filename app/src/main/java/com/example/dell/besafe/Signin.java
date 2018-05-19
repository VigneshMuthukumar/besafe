package com.example.dell.besafe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signin extends Activity {

    TextView signin;
    EditText one,two;
    SQLiteDatabase db;
    String user,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        db=openOrCreateDatabase("BeSafety", Context.MODE_PRIVATE, null);


        one=(EditText)findViewById(R.id.usrusr);
        two=(EditText)findViewById(R.id.pswrd);
        signin=(TextView)findViewById(R.id.signinfemale);


        signin.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                user=one.getText().toString();
                pass=two.getText().toString();

                if(!user.equals("")&& !pass.equals(""))
                {

                    Cursor c=db.rawQuery("SELECT * FROM Register where uname='"+user+"' and pass='"+pass+"'", null);
                    if(c.getCount()==0)
                    {
                        Toast.makeText(getApplicationContext(),"Invalid Username and Password",Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        Intent intent=new Intent(Signin.this,Welcome.class);
                        intent.putExtra("name", user);
                        startActivity(intent);

                    }//

                }
                else{

                    Toast.makeText(getApplicationContext(), "fill all the fields", Toast.LENGTH_LONG).show();

                    one.setError("");
                    two.setError("");
                }

            }
        });
    }


}
