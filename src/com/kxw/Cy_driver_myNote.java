package com.kxw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_driver_myNote.LoadAllInfo;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Cy_driver_myNote extends ListActivity implements OnRefreshListener {
	private ProgressDialog pDialog; 

	JSONParser jParser = new JSONParser(); 
	ArrayList<HashMap<String, String>> infoList; 

	private static String url_all_products = "http://120.27.26.219/kxw/android/cy_mynote.php"; 
	private static final String TAG_SUCCESS = "success"; 

	JSONArray jsinfo = null; 
	
	private static final int REFRESH_COMPLETE = 0X110;
	private SwipeRefreshLayout mSwipeLayout;
	private ListView lv;
	private SimpleAdapter mAdapter;
	
	private Handler mHandler = new Handler()  
			    {  
			        public void handleMessage(android.os.Message msg)  
			        {  
			            switch (msg.what)  
			            {  
			            case REFRESH_COMPLETE:   
			            	mAdapter.notifyDataSetChanged();
			            	infoList.clear();
			            	new LoadAllInfo().execute();			                  
			                mSwipeLayout.setRefreshing(false);  
			                break;  
			            }  
			        };  
			    }; 

	@SuppressLint("InlinedApi") 
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.cy_mynote); 
		ActivityCollector.getInstance().addActivity(this);
		
		
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);   
        mSwipeLayout.setOnRefreshListener(this);  
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
                android.R.color.holo_orange_light, android.R.color.holo_red_light);	
        
		infoList = new ArrayList<HashMap<String, String>>(); 
		new LoadAllInfo().execute(); 
		lv = getListView(); 
		lv.setOnItemClickListener(new OnItemClickListener() { 
			//
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) { 

		        String ty_userid = ((TextView) view.findViewById(R.id.txt_userid)).getText().toString(); 
		        String fhr_name = ((TextView) view.findViewById(R.id.txt_fhrname)).getText().toString();
		        String fhr_tel = ((TextView) view.findViewById(R.id.txt_fhrtel)).getText().toString();
		        String fh_address = ((TextView) view.findViewById(R.id.txt_fhaddress)).getText().toString();
		        String fh_xxaddress = ((TextView) view.findViewById(R.id.txt_fhxxaddress)).getText().toString();
		        String dd_address = ((TextView) view.findViewById(R.id.txt_ddaddress)).getText().toString();
		        String dd_xxaddress = ((TextView) view.findViewById(R.id.txt_ddxxaddress)).getText().toString();
		        String shr_name = ((TextView) view.findViewById(R.id.txt_shrname)).getText().toString();
		        String shr_tel = ((TextView) view.findViewById(R.id.txt_shrtel)).getText().toString();
		        String shr_address = ((TextView) view.findViewById(R.id.txt_shraddress)).getText().toString();
		        String shr_xxaddress = ((TextView) view.findViewById(R.id.txt_shrxxaddress)).getText().toString();
		        String hw_name = ((TextView) view.findViewById(R.id.txt_hwname)).getText().toString();
		        String hw_gg = ((TextView) view.findViewById(R.id.txt_hwgg)).getText().toString();
		        String hw_number = ((TextView) view.findViewById(R.id.txt_hwnumber)).getText().toString();
		        String hw_weight = ((TextView) view.findViewById(R.id.txt_hwweight)).getText().toString();
		        String hw_vol = ((TextView) view.findViewById(R.id.txt_hwvol)).getText().toString();
		        String hw_bz = ((TextView) view.findViewById(R.id.txt_hwbz)).getText().toString();
		        String hw_remark = ((TextView) view.findViewById(R.id.txt_hwremark)).getText().toString();
		        String fb_num = ((TextView) view.findViewById(R.id.txt_fbnum)).getText().toString();
		        String carriage = ((TextView) view.findViewById(R.id.txt_carriage)).getText().toString();
		        String ty_ispay = ((TextView) view.findViewById(R.id.txt_ty_ispay)).getText().toString();
		        String cy_isinsure = ((TextView) view.findViewById(R.id.txt_cy_isinsure)).getText().toString();
		        
		        Intent in = new Intent(getApplicationContext(),Cy_driver_myNote2.class); 
		        in.putExtra("ty_userid", ty_userid);
		        in.putExtra("fhr_name", fhr_name);
		        in.putExtra("fhr_tel", fhr_tel);
		        in.putExtra("fh_address", fh_address);
		        in.putExtra("fh_xxaddress", fh_xxaddress);
		        in.putExtra("dd_address", dd_address);
		        in.putExtra("dd_xxaddress", dd_xxaddress);
		        in.putExtra("shr_name", shr_name);
		        in.putExtra("shr_tel", shr_tel);
		        in.putExtra("shr_address", shr_address);
		        in.putExtra("shr_xxaddress", shr_xxaddress);
		        in.putExtra("hw_name", hw_name);
		        in.putExtra("hw_gg", hw_gg);
		        in.putExtra("hw_number", hw_number);
		        in.putExtra("hw_weight", hw_weight);
		        in.putExtra("hw_vol", hw_vol);
		        in.putExtra("hw_bz", hw_bz);
		        in.putExtra("hw_remark", hw_remark);
		        in.putExtra("fbnum", fb_num);
		        in.putExtra("carriage", carriage);
		        in.putExtra("ty_ispay", ty_ispay);
		        in.putExtra("cy_isinsure", cy_isinsure);
		        startActivityForResult(in, 1); 

	        } 
	    });
	 } 


	 @Override
	 protected void  onActivityResult(int requestCode, int resultCode, Intent data) { 
	     super.onActivityResult(requestCode, resultCode, data); 
	 
	     if(resultCode == 100) { 
		     Intent intent = getIntent(); 
		     finish(); 
		     startActivity(intent); 
	     } 
	 } 

	 class LoadAllInfo extends AsyncTask<String, String, String> { 

	 @Override
	 protected void onPreExecute() { 
	     super.onPreExecute(); 
	     pDialog = new ProgressDialog(Cy_driver_myNote.this); 
	     pDialog.setMessage("Loading. Please wait..."); 
	     pDialog.setIndeterminate(false); 
	     pDialog.setCancelable(false); 
	     pDialog.show(); 
	 } 
	 
	 protected String doInBackground(String... args) { 
		 //提取承运人id
		 Kxw_Application id3=((Kxw_Application)getApplicationContext());
		 String cy_username=id3.get_cy_driver_username();
		 //将承运人id传入php
		 List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("cy_username", cy_username)); 

	     JSONObject json = jParser.makeHttpRequest(url_all_products, "POST", para); 
	     Log.d("All Products: ", json.toString()); 
	     //从php提取数据 
	     try{ 
	         int success = json.getInt(TAG_SUCCESS); 
	         if(success == 1) { 
	             jsinfo = json.getJSONArray("cy_mynote"); 
	             for(int i = 0; i < jsinfo.length(); i++) { 
	                 JSONObject c = jsinfo.getJSONObject(i); 
	                 String hw_name = c.getString("hw_name"); 
	                 String fh_address = c.getString("fh_address"); 
	                 String dd_address = c.getString("dd_address");
	                 String address=fh_address+' '+"——> "+dd_address;
	                 String ty_userid = c.getString("ty_userid");
	                 String fhr_name = c.getString("fhr_name");
	                 String fhr_tel = c.getString("fhr_tel");
	                 String fh_xxaddress = c.getString("fh_xxaddress");
	                 String dd_xxaddress = c.getString("dd_xxaddress");
	                 String shr_name = c.getString("shr_name");
	                 String shr_tel = c.getString("shr_tel");
	                 String shr_address = c.getString("shr_address");
	                 String shr_xxaddress = c.getString("shr_xxaddress");
	                 String hw_gg = c.getString("hw_gg");
	                 String hw_number = c.getString("hw_number");
	                 String hw_weight = c.getString("hw_weight");
	                 String hw_vol = c.getString("hw_vol");
	                 String hw_bz = c.getString("hw_bz");
	                 String hw_remark = c.getString("hw_remark");
	                 String fbnum=c.getString("fbnum");
	                 String carriage=c.getString("carriage");
	                 String ty_ispay=c.getString("ty_ispay");
	                 String cy_isinsure=c.getString("cy_isinsure");
	                 
	                 if(cy_isinsure.equals("1")){
	                	 cy_isinsure="已保险";
	                 }else {
	                	 cy_isinsure="未保险";
	                 }
	                 if(ty_ispay.equals("0")){
	                	 ty_ispay="托运人未缴费";
	                 }else if(ty_ispay.equals("1")){
	                	 ty_ispay="托运人已缴费";
	                 }


	                 HashMap<String, String> map = new HashMap<String, String>(); 
	                 map.put("hw_name", hw_name); 
	                 map.put("address", address);
	                 map.put("fh_address",fh_address); 
	                 map.put("dd_address",dd_address);
	                 map.put("ty_userid",ty_userid);
	                 map.put("fhr_name",fhr_name);
	                 map.put("fhr_tel",fhr_tel);
	                 map.put("fh_xxaddress",fh_xxaddress);
	                 map.put("dd_xxaddress",dd_xxaddress);
	                 map.put("shr_name",shr_name);
	                 map.put("shr_tel",shr_tel);
	                 map.put("shr_address",shr_address);
	                 map.put("shr_xxaddress",shr_xxaddress);
	                 map.put("hw_gg",hw_gg);
	                 map.put("hw_number",hw_number);
	                 map.put("hw_weight",hw_weight);
	                 map.put("hw_vol",hw_vol);
	                 map.put("hw_bz",hw_bz);
	                 map.put("hw_remark",hw_remark);
	                 map.put("fbnum",fbnum);
	                 map.put("carriage",carriage);
	                 map.put("ty_ispay",ty_ispay);
	                 map.put("cy_isinsure",cy_isinsure);
	                 infoList.add(map); 
	             } 
	         } else{ 
	         } 
	      } catch(JSONException e) { 
	            e.printStackTrace(); 
	      } 
	      return null; 
	 } 

	 protected void onPostExecute(String file_url) { 
	 // dismiss the dialog after getting all products 
		  pDialog.dismiss(); 
	 // updating UI from Background Thread 
		  runOnUiThread(new Runnable() { 
			  public void run() { 
				  mAdapter = new SimpleAdapter( 
						Cy_driver_myNote.this, infoList, 
					    R.layout.list_cy_mynote, new String[] { "hw_name", "address","ty_userid","fhr_name","fhr_tel","fh_address","fh_xxaddress",
					    		"dd_address","dd_xxaddress","shr_name","shr_tel","shr_address","shr_xxaddress","hw_gg","hw_number",
					    		"hw_weight","hw_vol","hw_bz","hw_remark","fbnum","carriage","ty_ispay","cy_isinsure"}, 
						new int[] { R.id.txt_hwname, R.id.txt_address,R.id.txt_userid,R.id.txt_fhrname,R.id.txt_fhrtel,R.id.txt_fhaddress,
								R.id.txt_fhxxaddress,R.id.txt_ddaddress,R.id.txt_ddxxaddress,R.id.txt_shrname,R.id.txt_shrtel,R.id.txt_shraddress,
								R.id.txt_shrxxaddress,R.id.txt_hwgg,R.id.txt_hwnumber,R.id.txt_hwweight,R.id.txt_hwvol,R.id.txt_hwbz,
								R.id.txt_hwremark,R.id.txt_fbnum,R.id.txt_carriage,R.id.txt_ty_ispay,R.id.txt_cy_isinsure}); 
	                    setListAdapter(mAdapter); 
	          } 
	      }); 
	 } 
  } 
		//左上角返回上一级
		@Override
	    protected void onStart() {
	        super.onStart();
	        ActionBar actionBar = this.getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }
		@Override 
		public boolean onOptionsItemSelected(MenuItem item) {    
	        return super.onOptionsItemSelected(item);   
	    }
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

	}

}
