package com.example.dell.besafe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Preferedcontacts extends Activity {


    public static String url ="http://cyberstudents.in/android/1617Staff/RK/Geo/Service.asmx";
    private static final String Soap_Name = "http://tempuri.org/";
    private static String Soap_Url = "";
    private static String Soap_Method = "";
    String name, id, month,percen ;
    HashMap<String, String> data;
    String myUnameintent = "4";
    final Context context = this;
    ListView list;

    String value;
SQLiteDatabase db;
    String item;
    String myid, myname, mymonth,mypercen;
    ArrayList<HashMap<String, String>> myArraylist = new ArrayList<HashMap<String, String>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferedcontacts);
        value=getIntent().getExtras().getString("loginname");
        db=openOrCreateDatabase("BeSafety",context.MODE_PRIVATE, null);


        //SoapGetmobno();
        myArraylist = SoapGetmobno();
        SampleListAdapterone sample = new SampleListAdapterone(myArraylist,
                getApplicationContext());
        list= (ListView) findViewById(R.id.precontact);
        list.setAdapter(sample);
        list.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1,
                                    int position, long id) {



                final View v = arg1;



                final TextView aprod1=(TextView)v.findViewById(R.id.contactnumber);
                item = aprod1.getText().toString();

//
//				String a[]=xyz.split(":");
//		         item=a[0];


                // TODO Auto-generated method stub


                // Log.e("value", result);


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        context);

                alertDialogBuilder.setTitle("Women Safety App");


                alertDialogBuilder
                        .setMessage("Do u want Delete this Contact?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // if this button is clicked, close
                                // current activity

                                deletecontact();

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





    public ArrayList<HashMap<String, String>> SoapGetmobno() {

        ArrayList<HashMap<String, String>> lista = new ArrayList<HashMap<String, String>>();

//
        Cursor c=db.rawQuery("SELECT * FROM AddContacts where uname='"+value+"'" , null);
        if(c.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"No Prefered Contacts",Toast.LENGTH_SHORT).show();
        }
        else{
            while (c.moveToNext()) {

                data = new HashMap<String, String>();
                data.put("id", c.getString(1));
                data.put("name", c.getString(2));
                Log.e("asdfg",c.getString(1));
                Log.e("asdfg",c.getString(2));

                lista.add(data);



            }

        }


        return lista;




    }
    public void deletecontact()
    {


        db.execSQL("delete from AddContacts where mobile='"+item+"'");
        Log.e("jkk",item);
        Toast.makeText(getApplicationContext(), "Prefered Contact Deleted", Toast.LENGTH_LONG).show();
        myArraylist = SoapGetmobno();
        SampleListAdapterone sample = new SampleListAdapterone(myArraylist,
                getApplicationContext());

        list.setAdapter(sample);



    }

}
