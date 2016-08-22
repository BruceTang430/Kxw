package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.kxw.Cy_company_txbxd_time.Cy_insure;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Third_insure_cy_time55 extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/third_insure_cy_time.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1,userid,usertype;
	String insure_name,insured_tel,insured_address,insured_name,fhorg,shorg,
	goods_name,goods_weight,carnum,driver_name,driver_tel,driver_license,cyorg,cyaddr,
	carnum2,fbnum,fhaddress,ddaddress,ddcity,ddcounty,m_compens,ddprovince,insurance_num,goods_value2,
	fhtel,shtel;
	RadioButton r,r2;
	double fh_lat,fh_lon,dd_lat,dd_lon;
	GeoCoder mSearch = null;
	RadioGroup pay,add;
	double insuremon,addit,time,sumtime,coe,dis_coe,loc_coe,balance_time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_insure_cy_time55);
		ActivityCollector.getInstance().addActivity(this);
		

		//调用全局变量
		Kxw_Application id=((Kxw_Application)getApplicationContext());
		userid=id.get_third_userid();
		time=id.get_third_time();
		
        
		fh_lat=id.get_third_fh_lat();
		fh_lon=id.get_third_fh_lon();
		dd_lat=id.get_third_dd_lat();
		dd_lon=id.get_third_dd_lon();
		
		Intent it=getIntent();
		Bundle bun = it.getExtras();
		fhaddress=bun.getString("fhaddress");
		ddaddress=bun.getString("ddaddress");
		ddcity=bun.getString("ddcity");
		ddcounty=bun.getString("ddcounty");
		ddprovince=bun.getString("ddprovince");
		goods_value2=bun.getString("goods_value");
		insured_name=bun.getString("insured_name");
		insured_tel=bun.getString("insured_tel");
		insured_address=bun.getString("insured_address");
		goods_name=bun.getString("goods_name");
		goods_weight=bun.getString("goods_weight");
		driver_name=bun.getString("driver_name");
		driver_tel=bun.getString("driver_tel");
		driver_license=bun.getString("driver_license");
		carnum=bun.getString("carnum");
		fhorg=bun.getString("fhorg");
		shorg=bun.getString("shorg");
		cyorg=bun.getString("cyorg");
		cyaddr=bun.getString("cyaddr");
		fhtel=bun.getString("fhtel");
		shtel=bun.getString("shtel");
		
		pay=(RadioGroup)findViewById(R.id.radgroup);
		add=(RadioGroup)findViewById(R.id.radgroup2);
		
		LatLng p1 = new LatLng(fh_lat, fh_lon);
		LatLng p2 = new LatLng(dd_lat, dd_lon);
		double d=DistanceUtil. getDistance(p1, p2);
        double d2=Math.floor(d/1000);
        
        if(d2<500){
        	dis_coe=0.75;
        }else if(d2>2500){
        	dis_coe=1.5;
        }else dis_coe=1;
        String d3=String.valueOf(dis_coe);
		Log.d("dis_coe", d3);
		
		String address=ddprovince+ddcity;
		if(address.equals("西藏拉萨")||address.equals("西藏灵芝")||ddprovince.equals("甘肃")||ddprovince.equals("宁夏")||ddprovince.equals("新疆")||ddprovince.equals("青海")){
			loc_coe=2;
		}else loc_coe=1;
		coe=loc_coe*dis_coe;
		Log.d("coe", String.valueOf(coe));
		
		//"提交保单"按钮监听
		Button button=(Button)findViewById(R.id.btn_submit);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				
				
				for(int i=0;i<pay.getChildCount();i++){
					r=(RadioButton)pay.getChildAt(i);
					if(r.isChecked()){
						r.getText();
						break;
					}
				}
				if(r.getText().equals("50万")) {
					insuremon=0.5;
					m_compens="50万";
				}
				else if(r.getText().equals("100万")) {
					insuremon=1;
					m_compens="100万";
				}else if(r.getText().equals("150万")) {
					insuremon=1.5;
					m_compens="150万";
				}else if(r.getText().equals("200万")) {
					insuremon=2;
					m_compens="200万";
				}
				
				for(int i=0;i<add.getChildCount();i++){
					r2=(RadioButton)add.getChildAt(i);
					if(r2.isChecked()){
						addit=0.5;
						break;
					}else addit=0;					
				}
				sumtime=insuremon*coe+addit;
				
				if(time<sumtime) Toast.makeText(Third_insure_cy_time55.this, "余额不足", Toast.LENGTH_LONG).show();
				else{
					AlertDialog alert=new AlertDialog.Builder(Third_insure_cy_time55.this).create();
				    alert.setTitle("系统提示:");
				    alert.setMessage("需支付车次数:"+sumtime);
				    //添加"确定"按钮
				    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定支付", new DialogInterface.OnClickListener(){
				    	@Override
				    	public void onClick(DialogInterface dialog,int which){
				    		new Third().execute();
				    	}
				    });
				    alert.show();
					
				}
			}
		});
		
	}

	//访问数据库
	class Third extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Third_insure_cy_time55.this); 
			pDialog.setMessage("提交中"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			
			String str1=insured_tel.substring(0, 3);
			String str2="****";
			String str3=insured_tel.substring(7, 11);
			
			long now = System.currentTimeMillis();
			fbnum=str1+str2+str3+String.valueOf(now);
			
			double time2=time-sumtime;
			Log.d("time", String.valueOf(time2));
			
			Kxw_Application id=((Kxw_Application)getApplicationContext());
			insurance_num=id.get_third_insurance_num();
			Log.d("insurance", insurance_num);
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("fh_address", fhaddress));
			para.add(new BasicNameValuePair("dd_address", ddaddress));
			para.add(new BasicNameValuePair("goods_name", goods_name));
			para.add(new BasicNameValuePair("goods_weight", goods_weight));
			para.add(new BasicNameValuePair("insured_tel", insured_tel));
			para.add(new BasicNameValuePair("insured_address", insured_address));
			para.add(new BasicNameValuePair("insured_name", insured_name));
			para.add(new BasicNameValuePair("userid", Integer.toString(userid)));
			para.add(new BasicNameValuePair("fbnum", fbnum));
			para.add(new BasicNameValuePair("carnum", carnum));
			para.add(new BasicNameValuePair("driver_name", driver_name));
			para.add(new BasicNameValuePair("driver_tel", driver_tel));
			para.add(new BasicNameValuePair("driver_license", driver_license));
			para.add(new BasicNameValuePair("insurance_num", insurance_num));
			para.add(new BasicNameValuePair("balance_time", String.valueOf(time2)));
			para.add(new BasicNameValuePair("pay_time", String.valueOf(sumtime)));
			para.add(new BasicNameValuePair("goods_value", goods_value2));
			para.add(new BasicNameValuePair("usertype", "3"));
			para.add(new BasicNameValuePair("insurance_type", "1"));
			para.add(new BasicNameValuePair("fhorg", fhorg));
			para.add(new BasicNameValuePair("shorg", shorg));
			para.add(new BasicNameValuePair("cyorg", cyorg));
			para.add(new BasicNameValuePair("cyaddr", cyaddr));
			para.add(new BasicNameValuePair("m_compens", m_compens));
			para.add(new BasicNameValuePair("fhtel", fhtel));
			para.add(new BasicNameValuePair("shtel", shtel));

			// getting JSON Object 
			// Note that create product url accepts POST method 
			JSONObject json = jsonParser.makeHttpRequest(url_create_product, 
			"POST", para); 
			// check log cat fro response 
			Log.d("Create Response", json.toString()); 
			// check for success tag 
			try{ 
			success = json.getInt(TAG_SUCCESS); 
			if(success == 1) {
				
				
				} else{ 
				// failed to create product 
					//Toast.makeText(getApplicationContext(), " 111",Toast.LENGTH_SHORT).show();
				} 		
			} catch(JSONException e) { 
			e.printStackTrace(); 
			} 
			
			
			return null;
		}

		@Override
		protected void onPostExecute(String fil_url) {
			// TODO Auto-generated method stub
			switch (success){
			case 1:{
				Toast.makeText(getApplicationContext(), "支付成功", Toast.LENGTH_SHORT).show();
				Intent it= new Intent();
			    it.setClass(Third_insure_cy_time55.this,Third_myInsurance.class);	
			    startActivity(it);
			    finish();
				break;
				}
			
			case 0:{
				Toast.makeText(getApplicationContext(), "出现错误", Toast.LENGTH_SHORT).show();
				break;
			}
			}
			pDialog.dismiss();
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


}

