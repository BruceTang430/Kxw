package com.kxw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Third_myInsurance_freight extends ListActivity implements OnRefreshListener {
	private static String url_all_products = "http://120.27.26.219/kxw/android/third_myinsurance_freight.php"; 
	private static final String TAG_SUCCESS = "success";
	
	private ProgressDialog pDialog; 	
	JSONParser jParser = new JSONParser(); 
	ArrayList<HashMap<String, String>> infoList; 
	
	JSONArray jsinfo = null; 
	int userid,usertype;
	String ispay,premium;
	
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
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_myinsurance);
		ActivityCollector.getInstance().addActivity(this);

		//调用get()函数，提取用户id
		Kxw_Application id=((Kxw_Application)getApplicationContext());
		userid=id.get_third_userid();
		usertype=id.get_usertype();
		
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
                
	        	//从列表中提取数据打包传入下个activity
		        String car_num = ((TextView) view.findViewById(R.id.txt_car_num)).getText().toString();
		        String car_numtype = ((TextView) view.findViewById(R.id.txt_car_numtype)).getText().toString();
		        String engine_num = ((TextView) view.findViewById(R.id.txt_engine_num)).getText().toString();		        
		        String carframe_num= ((TextView) view.findViewById(R.id.txt_carframe_num)).getText().toString();
		        String car_character = ((TextView) view.findViewById(R.id.txt_car_character)).getText().toString();
		        String driver_name = ((TextView) view.findViewById(R.id.txt_driver_name)).getText().toString();
		        String firstdate = ((TextView) view.findViewById(R.id.txt_firstdate)).getText().toString();
		        String business_insure= ((TextView) view.findViewById(R.id.txt_business_insure)).getText().toString();
		        String jq_insure= ((TextView) view.findViewById(R.id.txt_jq_insure)).getText().toString();
		        String car_typename= ((TextView) view.findViewById(R.id.txt_car_typename)).getText().toString();
		        String jq_money= ((TextView) view.findViewById(R.id.txt_jq_money)).getText().toString();
		        String damage_money= ((TextView) view.findViewById(R.id.txt_damage_money)).getText().toString();
		        String three_money= ((TextView) view.findViewById(R.id.txt_three_money)).getText().toString();
		        String steal_money= ((TextView) view.findViewById(R.id.txt_steal_money)).getText().toString();
		        String driverseat_money= ((TextView) view.findViewById(R.id.txt_driverseat_money)).getText().toString();
		        String pasgerseat_money= ((TextView) view.findViewById(R.id.txt_pasgerseat_money)).getText().toString();
		        String time= ((TextView) view.findViewById(R.id.txt_time)).getText().toString();
		        String ispay= ((TextView) view.findViewById(R.id.txt_ispay)).getText().toString();
		        String premium= ((TextView) view.findViewById(R.id.txt_premium)).getText().toString();
		        String serial_num= ((TextView) view.findViewById(R.id.txt_serial_num)).getText().toString();
		        String insurance_num= ((TextView) view.findViewById(R.id.txt_insurance_num)).getText().toString();
		        String idcard= ((TextView) view.findViewById(R.id.txt_idcard)).getText().toString();
		        String transfer_date= ((TextView) view.findViewById(R.id.txt_transfer_date)).getText().toString();
        		
		        Intent in = new Intent(getApplicationContext(),Third_myInsurance_freight_detail.class); 

		        in.putExtra("car_num", car_num);
		        in.putExtra("car_numtype",car_numtype);
		        in.putExtra("engine_num", engine_num);
		        in.putExtra("carframe_num", carframe_num);
		        in.putExtra("car_character", car_character);
		        in.putExtra("driver_name", driver_name);
		        in.putExtra("firstdate", firstdate);
		        in.putExtra("business_insure", business_insure);
		        in.putExtra("jq_insure", jq_insure);
		        in.putExtra("car_typename", car_typename);
		        in.putExtra("jq_money", jq_money);
		        in.putExtra("damage_money", damage_money);
		        in.putExtra("three_money", three_money);
		        in.putExtra("steal_money", steal_money);
		        in.putExtra("driverseat_money", driverseat_money);
		        in.putExtra("pasgerseat_money", pasgerseat_money);
		        in.putExtra("time", time);
		        in.putExtra("ispay", ispay);
		        in.putExtra("premium", premium);
		        in.putExtra("serial_num", serial_num);
		        in.putExtra("insurance_num", insurance_num);
		        in.putExtra("idcard", idcard);
		        in.putExtra("transfer_date", transfer_date);
		        
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
		     pDialog = new ProgressDialog(Third_myInsurance_freight.this); 
		     pDialog.setMessage("Loading. Please wait..."); 
		     pDialog.setIndeterminate(false); 
		     pDialog.setCancelable(false); 
		     pDialog.show(); 
		 } 
		 
		 protected String doInBackground(String... args) { 
			 List<NameValuePair> para = new ArrayList<NameValuePair>(); 
				para.add(new BasicNameValuePair("userid", Integer.toString(userid)));
				para.add(new BasicNameValuePair("usertype", Integer.toString(usertype)));
	 
		     JSONObject json = jParser.makeHttpRequest(url_all_products, "POST", para); 
		     Log.d("All Products: ", json.toString()); 
		     //从php提取数据
		     try{ 
		         int success = json.getInt(TAG_SUCCESS); 
		         if(success == 1) { 
		             jsinfo = json.getJSONArray("third_myinsurance"); 
		             for(int i = 0; i < jsinfo.length(); i++) { 
		                 JSONObject c = jsinfo.getJSONObject(i); 
		                 String car_num = c.getString("car_num"); 
		                 String car_numtype = c.getString("car_numtype"); 
		                 String engine_num = c.getString("engine_num");		                 
		                 String carframe_num = c.getString("carframe_num");
		                 String car_character = c.getString("car_character");
		                 String driver_name = c.getString("driver_name");
		                 String firstdate = c.getString("firstdate");
		                 String business_insure = c.getString("business_insure");
		                 String jq_insure = c.getString("jq_insure");
		                 String car_typename = c.getString("car_typename");	                        
		                 String jq_money = c.getString("jq_money");
		                 String damage_money = c.getString("damage_money");
		                 String steal_money = c.getString("steal_money");
		                 String three_money = c.getString("three_money");
		                 String driverseat_money = c.getString("driverseat_money");
		                 String pasgerseat_money = c.getString("pasgerseat_money");
		                 String time = c.getString("time");
		                 double premium2 = c.getDouble("premium");
		                 int ispay2 = c.getInt("ispay");
		                 String serial_num = c.getString("serial_num");
		                 String insurance_num = c.getString("insurance_num");
		                 String idcard = c.getString("idcard");
		                 String transfer_date = c.getString("transfer_date");
		                 
		                 ispay=String.valueOf(ispay2);
		                 premium=String.valueOf(premium2);
		                 if(premium.equals("0.0"))premium="待计算";
		                 if(ispay.equals("0"))ispay="未缴费";
		                 else ispay="已缴费";
		                 if(insurance_num.equals(""))insurance_num="待审核";
		                 
		                 HashMap<String, String> map = new HashMap<String, String>(); 
		                 map.put("car_num", car_num); 
		                 map.put("car_numtype",car_numtype); 
		                 map.put("engine_num",engine_num);		                 
		                 map.put("carframe_num",carframe_num);
		                 map.put("car_character",car_character);
		                 map.put("driver_name",driver_name);
		                 map.put("firstdate",firstdate);
		                 map.put("business_insure",business_insure);
		                 map.put("jq_insure",jq_insure);
		                 map.put("car_typename",car_typename);
		                 map.put("jq_money",jq_money);
		                 map.put("damage_money",damage_money);
		                 map.put("steal_money",steal_money);
		                 map.put("three_money",three_money);
		                 map.put("driverseat_money",driverseat_money);
		                 map.put("pasgerseat_money",pasgerseat_money);
		                 map.put("time",time);
		                 map.put("ispay",ispay);
		                 map.put("premium",premium);
		                 map.put("serial_num",serial_num);
		                 map.put("insurance_num",insurance_num);
		                 map.put("idcard",idcard);
		                 map.put("transfer_date",transfer_date);

		                 infoList.add(map); 
		             }
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
						  //将数据赋值在列表
						  mAdapter = new SimpleAdapter( 
								Third_myInsurance_freight.this, infoList, 
							    R.layout.list_third_myinsurance_freight, new String[] { "car_num","car_numtype","engine_num","carframe_num","car_character",
							    		"driver_name","firstdate","business_insure","jq_insure","car_typename","jq_money","damage_money",
							    		"steal_money","three_money","driverseat_money","pasgerseat_money","time","ispay","premium","serial_num","insurance_num",
							    		"idcard","transfer_date"}, 
								new int[] { R.id.txt_car_num,R.id.txt_car_numtype,R.id.txt_engine_num,R.id.txt_carframe_num,
										R.id.txt_car_character,R.id.txt_driver_name,R.id.txt_firstdate,R.id.txt_business_insure,
										R.id.txt_jq_insure,R.id.txt_car_typename,R.id.txt_jq_money,R.id.txt_damage_money,
										R.id.txt_steal_money,R.id.txt_three_money,R.id.txt_driverseat_money,R.id.txt_pasgerseat_money,R.id.txt_time,
										R.id.txt_ispay,R.id.txt_premium,R.id.txt_serial_num,R.id.txt_insurance_num,R.id.txt_idcard,
										R.id.txt_transfer_date}); 
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
