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
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Ty_myInsurance extends ListActivity implements OnRefreshListener {
	private static String url_all_products = "http://120.27.26.219/kxw/android/ty_myinsurance.php"; 
	private static final String TAG_SUCCESS = "success"; 
	
	
	private ProgressDialog pDialog; 	
	JSONParser jParser = new JSONParser(); 
	ArrayList<HashMap<String, String>> infoList; 
	
	JSONArray jsinfo = null; 
	int userid;String insurance_num,ispay;
	
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
               // mDatas.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap")); 
            	mAdapter.notifyDataSetChanged();
            	//infoList.remove(position);
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
		setContentView(R.layout.ty_myinsurance2);
		ActivityCollector.getInstance().addActivity(this);
		
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.id_swipe_ly);  
        
        mSwipeLayout.setOnRefreshListener(this);  
        mSwipeLayout.setColorScheme(android.R.color.holo_blue_bright, android.R.color.holo_green_light,  
                android.R.color.holo_orange_light, android.R.color.holo_red_light);

		//调用get()函数，提取用户id
		Kxw_Application id=((Kxw_Application)getApplicationContext());
		userid=id.get_ty_userid();
		
		
		infoList = new ArrayList<HashMap<String, String>>(); 
		new LoadAllInfo().execute(); 
		lv = getListView(); 
		lv.setOnItemClickListener(new OnItemClickListener() {  
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) { 
                
	        	//从列表中提取数据打包传入下个activity
		        String fbnum = ((TextView) view.findViewById(R.id.txt_fbnum)).getText().toString();
		        String insure_name = ((TextView) view.findViewById(R.id.txt_insure_name)).getText().toString();
		        String insured_tel = ((TextView) view.findViewById(R.id.txt_insured_tel)).getText().toString();		        
		        String insured_address= ((TextView) view.findViewById(R.id.txt_insured_address)).getText().toString();
		        String insured_name = ((TextView) view.findViewById(R.id.txt_insured_name)).getText().toString();
		        String insuremoney = ((TextView) view.findViewById(R.id.txt_insuremoney)).getText().toString();
		        String goods_name = ((TextView) view.findViewById(R.id.txt_goods_name)).getText().toString();
		        String goods_weight= ((TextView) view.findViewById(R.id.txt_goods_weight)).getText().toString();		      
		        String goods_loadaddr= ((TextView) view.findViewById(R.id.txt_goods_loadaddr)).getText().toString();
		        String goods_destination= ((TextView) view.findViewById(R.id.txt_goods_destination)).getText().toString();
		        String insurance_num= ((TextView) view.findViewById(R.id.txt_insurance_num)).getText().toString();
		        String delivertime= ((TextView) view.findViewById(R.id.txt_delivertime)).getText().toString();		        
		        String premium= ((TextView) view.findViewById(R.id.txt_premium)).getText().toString();
		        String serial_num= ((TextView) view.findViewById(R.id.txt_serial_num)).getText().toString();
		        String insurance_type= ((TextView) view.findViewById(R.id.txt_insurance_type)).getText().toString();
		        String insurance_id= ((TextView) view.findViewById(R.id.txt_insurance_id)).getText().toString();
		        
         		Kxw_Application i=((Kxw_Application)getApplicationContext());
        		i.set_ty_insurance_id(Integer.valueOf(insurance_id));
        		i.set_ty_insurance_type(Integer.valueOf(insurance_type));
        		
		        Intent in = new Intent(getApplicationContext(),Ty_myInsurance_detail.class); 

		        in.putExtra("fbnum", fbnum);
		        in.putExtra("insure_name",insure_name);
		        in.putExtra("insured_tel", insured_tel);
		        in.putExtra("insured_address", insured_address);
		        in.putExtra("insured_name", insured_name);
		        in.putExtra("insuremoney", insuremoney);
		        in.putExtra("goods_name", goods_name);
		        in.putExtra("goods_weight", goods_weight);
		        in.putExtra("goods_loadaddr", goods_loadaddr);
		        in.putExtra("goods_destination", goods_destination);
		        in.putExtra("insurance_num", insurance_num);
		        in.putExtra("delivertime", delivertime);
		        in.putExtra("premium", premium);
		        in.putExtra("serial_num", serial_num);

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
		     pDialog = new ProgressDialog(Ty_myInsurance.this); 
		     pDialog.setMessage("Loading. Please wait..."); 
		     pDialog.setIndeterminate(false); 
		     pDialog.setCancelable(false); 
		     pDialog.show(); 
		 } 
		 
		 protected String doInBackground(String... args) { 
			 List<NameValuePair> para = new ArrayList<NameValuePair>(); 
				para.add(new BasicNameValuePair("userid", Integer.toString(userid))); 
	 
		     JSONObject json = jParser.makeHttpRequest(url_all_products, "POST", para); 
		     Log.d("All Products: ", json.toString()); 
		     //从php提取数据
		     try{ 
		         int success = json.getInt(TAG_SUCCESS); 
		         if(success == 1) { 
		             jsinfo = json.getJSONArray("ty_myinsurance"); 
		             for(int i = 0; i < jsinfo.length(); i++) { 
		                 JSONObject c = jsinfo.getJSONObject(i); 
		                 String fbnum = c.getString("fbnum"); 
		                 String insure_name = c.getString("insure_name"); 
		                 String insured_tel = c.getString("insured_tel");		                 
		                 String insured_address = c.getString("insured_address");
		                 String insured_name = c.getString("insured_name");
		                 String insuremoney = c.getString("goods_value");
		                 String goods_name = c.getString("goods_name");
		                 String goods_weight = c.getString("goods_weight");
		                 String goods_loadaddr = c.getString("goods_loadaddr");
		                 String goods_destination = c.getString("goods_destination");
		                 insurance_num = c.getString("insurance_num");		                 
		                 String address=goods_loadaddr+' '+"——> "+goods_destination;
		                 String delivertime=c.getString("delivertime");
		                 String premium=c.getString("premium");
		                 String serial_num=c.getString("serial_num");
		                 int insurance_type=c.getInt("insurance_type");
		                 int insurance_id=c.getInt("id");
		                 int ispay2 = c.getInt("ispay");
		                 ispay=String.valueOf(ispay2);
		                 

		        		
		                 if(insurance_num.equals("null")&&ispay.equals("0")){
		                	 insurance_num="未付款";
		                 }else if(insurance_num.equals("null")&&ispay.equals("1")){
		                	 insurance_num="审核中";
		                 }
		                 //导入哈希表
		                 HashMap<String, String> map = new HashMap<String, String>(); 
		                 map.put("fbnum", fbnum); 
		                 map.put("insure_name",insure_name); 
		                 map.put("insured_tel",insured_tel);
		                 map.put("address",address);
		                 map.put("insured_address",insured_address);
		                 map.put("insured_name",insured_name);
		                 map.put("insuremoney",insuremoney);
		                 map.put("goods_name",goods_name);
		                 map.put("goods_weight",goods_weight);
		                 map.put("goods_loadaddr",goods_loadaddr);
		                 map.put("goods_destination",goods_destination);
		                 map.put("insurance_num",insurance_num);
		                 map.put("delivertime",delivertime);
		                 map.put("premium",premium);
		                 map.put("serial_num",serial_num);
		                 map.put("insurance_type",String.valueOf(insurance_type));
		                 map.put("insurance_id",String.valueOf(insurance_id));

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
								Ty_myInsurance.this, infoList, 
							    R.layout.list_ty_insurance, new String[] { "fbnum","insure_name","insured_tel","insured_address","insured_name",
							    		"insuremoney","goods_name","goods_weight","goods_loadaddr","goods_destination",
							    		"insurance_num","address","delivertime","insurance_num","premium","serial_num","insurance_type","insurance_id"}, 
								new int[] { R.id.txt_fbnum,R.id.txt_insure_name,R.id.txt_insured_tel,R.id.txt_insured_address,
										R.id.txt_insured_name,R.id.txt_insuremoney,R.id.txt_goods_name,R.id.txt_goods_weight,
										R.id.txt_goods_loadaddr,R.id.txt_goods_destination,R.id.txt_insurance_num,R.id.txt_address,
										R.id.txt_delivertime,R.id.txt_insurance_num,R.id.txt_premium,R.id.txt_serial_num,R.id.txt_insurance_type,R.id.txt_insurance_id}); 
			                    setListAdapter(mAdapter); 
			          } 
			      }); 
			 }
    }
	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);

	}

}
