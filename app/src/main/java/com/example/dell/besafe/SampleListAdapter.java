 package com.example.dell.besafe;

 import android.content.Context;
 import android.speech.tts.TextToSpeech;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.BaseAdapter;
 import android.widget.TextView;

 import java.util.ArrayList;
 import java.util.HashMap;

 public class
 SampleListAdapter extends BaseAdapter{
     TextToSpeech t1;
     Context b;
     LayoutInflater lf;
     Search sec;

     ArrayList<HashMap<String, String>> ll = new ArrayList<HashMap<String, String>>();
     HashMap<String, String> hash = new HashMap<String, String>();

     public SampleListAdapter(ArrayList<HashMap<String, String>> result,
             Context context) {
         this.b = context;
         this.ll = result;
           sec=new Search();
     }

     @Override
     public int getCount() {
         // TODO Auto-generated method stub




         return ll.size();
     }

     @Override
     public Object getItem(int position) {

         return ll.get(position);
     }

     @Override
     public long getItemId(int position) {
         // TODO Auto-generated method stub
         return 0;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
         lf = (LayoutInflater) b
                 .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         convertView = lf.inflate(R.layout.demo, null);

         TextView vname = (TextView) convertView.findViewById(R.id.name);
         TextView vmob = (TextView) convertView.findViewById(R.id.mobiles);
         TextView vlocation = (TextView) convertView.findViewById(R.id.locations);


         hash = ll.get(position);
         String astr1 = hash.get("name");
         String astr2 = hash.get("mobile");
         String astr3 = hash.get("location");





         vname.setText(astr1);
         vmob.setText(astr2);
         vlocation.setText(astr3);



         return convertView;
     }

     public class ViewHolder {

         public TextView name,mobile,location;

     }


 }

