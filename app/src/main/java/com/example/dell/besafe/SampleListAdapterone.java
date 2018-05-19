package com.example.dell.besafe;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class SampleListAdapterone extends BaseAdapter {
	Context b;
	LayoutInflater lf;
    Preferedcontacts secc;
	ArrayList<HashMap<String, String>> ll1 = new ArrayList<HashMap<String, String>>();
	HashMap<String, String> hash = new HashMap<String, String>();
	
	public SampleListAdapterone(ArrayList<HashMap<String, String>> result,
			Context context) {
		this.b = context;
		this.ll1 = result;
//		secc= new Preferedcontacts();
//		ll1 = secc.SoapGetmobno();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ll1.size();
	}

	@Override
	public Object getItem(int position) {

		return ll1.get(position);
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

		convertView = lf.inflate(R.layout.demmo, null);
		ImageView im=(ImageView)convertView.findViewById(R.id.imageView1);
		TextView vname = (TextView) convertView.findViewById(R.id.contactname);
		TextView vmonth = (TextView) convertView.findViewById(R.id.contactnumber);
		
		
		

		hash = ll1.get(position);
		String astr1 = hash.get("id");
		String astr2 = hash.get("name");
				
		
		
		
		
	
		vname.setText(astr1);
		vmonth.setText(astr2);
		
		
		
		return convertView;
	}

	public class ViewHolder {

		public TextView name,mobile;

	}


}
