package com.example.dell.besafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class FemaleLogin extends Activity {

    TextView sign,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_female_login);

        sign=(TextView)findViewById(R.id.sin);
        signup=(TextView)findViewById(R.id.signupforfree);



        sign.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                Intent in=new Intent(getApplicationContext(),Signin.class);

                startActivity(in);



            }
        });


        signup.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub



                Intent in=new Intent(getApplicationContext(),Signup.class);

                startActivity(in);
            }
        });



    }


}
