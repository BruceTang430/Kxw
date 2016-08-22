package com.kxw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Ty_infoManagement.LoadAllInfo;

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

public class Ty_carInfo extends ListActivity implements OnRefreshListener {
	private static String url_all_products = "http://120.27.26.219/kxw/android/ty_carinfo.php"; 
	private static final String TAG_SUCCESS = "success"; 
		
	private ProgressDialog pDialog; 	
	JSONParser jParser = new JSONParser(); 
	ArrayList<HashMap<String, String>> infoList; 
	
	JSONArray jsinfo = null; 
	
	private static final int REFRESH_COMPLETE = 0X110;
	private SwipeRefreshLayout mSwipeLayout;
	private ListView lv;
	private SimpleAdapter mAdapter;
	
	//下拉刷新
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
		setContentView(R.layout.ty_carinfo);
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
                
	        	//从列表中提取数据打包传入下个activity
	        	String time = ((TextView) view.findViewById(R.id.txt_time)).getText().toString();		        
		        String load_address= ((TextView) view.findViewById(R.id.txt_load_address)).getText().toString();
		        String destination_address = ((TextView) view.findViewById(R.id.txt_destination_address)).getText().toString();
		        String car_type = ((TextView) view.findViewById(R.id.txt_car_type)).getText().toString();
		        String car_number = ((TextView) view.findViewById(R.id.txt_car_number)).getText().toString();
		        String car_length= ((TextView) view.findViewById(R.id.txt_car_length)).getText().toString();
		        String car_weight= ((TextView) view.findViewById(R.id.txt_car_weight)).getText().toString();
		        String car_vol= ((TextView) view.findViewById(R.id.txt_car_vol)).getText().toString();
		        String go_time= ((TextView) view.findViewById(R.id.txt_go_time)).getText().toString();
		        String tel= ((TextView) view.findViewById(R.id.txt_tel)).getText().toString();
		        String remark= ((TextView) view.findViewById(R.id.txt_remark)).getText().toString();		        			
		        
		        Intent in = new Intent(getApplicationContext(),Ty_carInfo_detail.class); 
		        
		        in.putExtra("time", time);
		        in.putExtra("load_address", load_address);
		        in.putExtra("destination_address", destination_address);
		        in.putExtra("car_type", car_type);
		        in.putExtra("car_number", car_number);
		        in.putExtra("car_length", car_length);
		        in.putExtra("car_weight", car_weight);
		        in.putExtra("car_vol", car_vol);
		        in.putExtra("tel", tel);
		        in.putExtra("go_time", go_time);
		        in.putExtra("remark", remark);
		        
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
		     pDialog = new ProgressDialog(Ty_carInfo.this); 
		     pDialog.setMessage("Loading. Please wait..."); 
		     pDialog.setIndeterminate(false); 
		     pDialog.setCancelable(false); 
		     pDialog.show(); 
		 } 
		 
		 protected String doInBackground(String... args) { 
			 List<NameValuePair> para = new ArrayList<NameValuePair>(); 
	 
		     JSONObject json = jParser.makeHttpRequest(url_all_products, "POST", para); 
		     Log.d("All Products: ", json.toString()); 
		     //从php提取数据
		     try{ 
		         int success = json.getInt(TAG_SUCCESS); 
		         if(success == 1) { 
		             jsinfo = json.getJSONArray("ty_carinfo"); 
		             for(int i = 0; i < jsinfo.length(); i++) { 
		                 JSONObject c = jsinfo.getJSONObject(i); 
		                 String load_address = c.getString("load_address"); 
		                 String destination_address = c.getString("destination_address");
		                 String address=load_address+' '+"——> "+destination_address;
		                 String car_type = c.getString("car_type");
		                 String car_number = c.getString("car_number");
		                 String car_weight = c.getString("car_weight");
		                 String car_length = c.getString("car_length");
		                 String car_vol = c.getString("car_vol");
		                 String go_time = c.getString("go_time");
		                 String remark = c.getString("remark");
		                 String tel = c.getString("tel");
		                 String time = c.getString("time");
		                 		                		                 
		                 //Log.d("isorder: ", isorder); 

		                 //导入哈希表
		                 HashMap<String, String> map = new HashMap<String, String>(); 
		                 map.put("load_address",load_address); 
		                 map.put("destination_address",destination_address);
		                 map.put("address",address);
		                 map.put("car_type",car_type);
		                 map.put("car_length",car_length);
		                 map.put("car_number",car_number);
		                 map.put("car_weight",car_weight);
		                 map.put("car_vol",car_vol);
		                 map.put("go_time",go_time);
		                 map.put("remark",remark);
		                 map.put("tel",tel);
		                 map.put("time",time);

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
						  //将数据赋值在列表
						  mAdapter = new SimpleAdapter( 
								Ty_carInfo.this, infoList, 
							    R.layout.list_ty_carinfo, new String[] { "address","load_address","destination_address","car_type","car_length",
							    		"car_number","car_weight","car_vol","go_time","tel","remark","time"}, 
								new int[] { R.id.txt_address,R.id.txt_load_address,R.id.txt_destination_address,R.id.txt_car_type,
										R.id.txt_car_length,R.id.txt_car_number,R.id.txt_car_weight,R.id.txt_car_vol,R.id.txt_go_time,
										R.id.txt_tel,R.id.txt_remark,R.id.txt_time}); 
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
