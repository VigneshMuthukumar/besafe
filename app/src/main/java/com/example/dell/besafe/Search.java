package com.example.dell.besafe;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class Search extends Activity  {

    EditText sr;
    ImageView sub;
SQLiteDatabase db;

    TextToSpeech t1;
    String cont="";

    String listval;


    String name,mobile,location;

    HashMap<String, String> data;
    final Context context = this;
    ListView list;
    String myUnameintent = "4";
    String myname,mymobile,mylocation;
    public static String username;



    public String item,item1,item2;
    ArrayList<HashMap<String, String>> myArraylist = new ArrayList<HashMap<String, String>>();
    ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        db=openOrCreateDatabase("BeSafety", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS AddContacts(uname varchar,  contact varchar, mobile varchar);");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        username=getIntent().getExtras().getString("loginname");

        Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();

        sr=(EditText)findViewById(R.id.searchbox);

        sub=(ImageView)findViewById(R.id.malesearch);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.US);
                }
            }
        });




        sub.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                cont=sr.getText().toString();

                SoapGetmobno();
                myArraylist = SoapGetmobno();
                SampleListAdapter sample = new SampleListAdapter(myArraylist,
                        getApplicationContext());
                list = (ListView) findViewById(R.id.listView1);
                list.setAdapter(sample);

                Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();




                list.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View arg1,
                                            int position, long id) {



                        final View v = arg1;


                        final TextView aprod=(TextView)v.findViewById(R.id.name);

                        final TextView aprod1=(TextView)v.findViewById(R.id.mobiles);
                        item = aprod.getText().toString();
                        item1=aprod1.getText().toString();
//
//						String a[]=xyz.split(":");
//				         item=a[0];


                        // TODO Auto-generated method stub


                        // Log.e("value", result);


                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                                context);

                        alertDialogBuilder.setTitle("Women Safety App");


                        alertDialogBuilder
                                .setMessage("Do u want add Prefered Contact?")
                                .setCancelable(false)
                                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, close
                                        // current activity

                                        check();

                                    }
                                })
                                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = alertDialogBuilder.create();

                        alertDialog.show();


                    }
                });

            }
        });




    }



    public ArrayList<HashMap<String, String>> SoapGetmobno() {
        lista.clear();

//
//where name='"+sr+"'"
        ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();
String val=sr.getText().toString().trim();
//
        Cursor c=db.rawQuery("SELECT * FROM ContactDetails where name='"+val+"'" , null);
        if(c.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"Invalid Name",Toast.LENGTH_SHORT).show();
        }
        else{
            while (c.moveToNext()) {

                data = new HashMap<String, String>();
                data.put("name", c.getString(0));
                data.put("mobile", c.getString(2));
                data.put("location", c.getString(3));
                Log.e("asdfg",c.getString(0));
                Log.e("asdfg",c.getString(2));
                Log.e("asdfg",c.getString(3));
                lista.add(data);



            }

        }


        return lista;

    }


    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();


    }



    public void check()
    {



        Cursor c=db.rawQuery("SELECT * FROM AddContacts where uname='"+username+"' and contact='"+item+"' and mobile='"+item1+"'", null);
        if(c.getCount()==0)
        {
            rk();


        }
        else {


            Toast.makeText(getApplicationContext(), "Already added In Prefered Contact",
                        Toast.LENGTH_LONG).show();

        }





    }


    public void rk()
    {



        String str="insert into AddContacts values('"+username+"','"+item+"','"+item1+"')";
        db.execSQL(str);
        Toast.makeText(getApplicationContext(),"Added in Prefered Contacts",Toast.LENGTH_SHORT).show();





    }
}




