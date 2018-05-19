package com.example.dell.besafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.dell.besafe.R.drawable.spinner;




public class MainActivity extends Activity {


    Spinner sp;
    Button submit;
    ImageView im;
    ArrayAdapter<String> spinnerAdapter;

    String selected_val;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        im=(ImageView)findViewById(R.id.admin);
        submit=(Button)findViewById(R.id.Login);
        sp=(Spinner)findViewById(R.id.Spiner);


//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.gen, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        sp.setAdapter(adapter);
        spinnerAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(spinnerAdapter);

        spinnerAdapter.add("Add Contacts");
        spinnerAdapter.add("Log In");


        spinnerAdapter.notifyDataSetChanged();






        sp.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                // TODO Auto-generated method stub


                selected_val = sp.getSelectedItem().toString();


                //Toast.makeText(getApplicationContext(), selected_val, Toast.LENGTH_LONG).show();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });





        submit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                if(selected_val.equalsIgnoreCase("Add Contacts"))
                {
                    Intent in=new Intent(getApplicationContext(),MaleLogin.class);

                    startActivity(in);


                }

                else if(selected_val.equals("Log In"))
                {
                    Intent in=new Intent(getApplicationContext(),FemaleLogin.class);

                    startActivity(in);


                }
            }
        });



    }


}
