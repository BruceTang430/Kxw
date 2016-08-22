package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_company_txbxd_time2.Cy_insure;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Cy_company_txbxd_time extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_company_insure_time.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1,userid;
	EditText goods_value2,insured_tel,insured_address,insured_name,insuremoney,driver_name,driver_tel,
	driver_license,carnum,fhorg,shorg,cyorg,cyaddr;
	RadioButton r,r2;
	String premium3,fbnum,fhaddress,ddaddress,hw_name,hw_weight,goods_value,serial_num,insurance_num;
	RadioGroup pay,add;
	double insuremon,addit,time,sumtime,coe;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_txbxd_time);
		ActivityCollector.getInstance().addActivity(this);

		//调用全局变量
		Kxw_Application tyd=((Kxw_Application)getApplicationContext());
		userid=tyd.get_cy_company_userid();
		fbnum=tyd.get_tyd_fbnum();
	    fhaddress=tyd.get_tyd_fh_address();
		ddaddress=tyd.get_tyd_dd_address();
		hw_name=tyd.get_tyd_hw_name();
		hw_weight=tyd.get_tyd_hw_weight();
		coe=tyd.get_coe();
		insurance_num=tyd.get_cy_company_insurance_num();
		
		userid=tyd.get_cy_company_userid();
		
		fhorg=(EditText)findViewById(R.id.edit_fhorg);
		shorg=(EditText)findViewById(R.id.edit_shorg);
		insured_tel=(EditText)findViewById(R.id.edit_insured_tel);
		insured_address=(EditText)findViewById(R.id.edit_insured_address);
		insured_name=(EditText)findViewById(R.id.edit_insured_name);
		driver_name=(EditText)findViewById(R.id.edit_driver_name);
		driver_tel=(EditText)findViewById(R.id.edit_driver_tel);
		driver_license=(EditText)findViewById(R.id.edit_driver_license);
		carnum=(EditText)findViewById(R.id.edit_carnum);	
		goods_value2=(EditText)findViewById(R.id.edit_goods_value);
		cyorg=(EditText)findViewById(R.id.edit_cyorg);
		cyaddr=(EditText)findViewById(R.id.edit_cyaddr);
		
		pay=(RadioGroup)findViewById(R.id.radgroup);
		add=(RadioGroup)findViewById(R.id.radgroup2);
				
	
	//"确定提交"按钮监听
	Button button=(Button)findViewById(R.id.btn_submit);
	//设置按钮颜色
	GradientDrawable myGrad = (GradientDrawable)button.getBackground();
	myGrad.setColor(0xFF4f94cd);
	button.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){
			
			//获取车次余额
			Kxw_Application ii=((Kxw_Application)getApplicationContext());
			time=ii.get_cy_company_time();
			for(int i=0;i<pay.getChildCount();i++){
				r=(RadioButton)pay.getChildAt(i);
				if(r.isChecked()){
					r.getText();
					break;
				}
			}
			if(r.getText().equals("50万")) {
				insuremon=0.5;
				goods_value="50万";
			}
			else if(r.getText().equals("100万")) {
				insuremon=1;
				goods_value="100万";
			}else if(r.getText().equals("150万")) {
				insuremon=1.5;
				goods_value="150万";
			}else if(r.getText().equals("200万")) {
				insuremon=2;
				goods_value="200万";
			}
			
			for(int i=0;i<add.getChildCount();i++){
				r2=(RadioButton)add.getChildAt(i);
				if(r2.isChecked()){
					addit=0.5;
					break;
				}else addit=0;
			}
			sumtime=insuremon*coe+addit;

			String tel=insured_tel.getText().toString();
			String driver_tel2=driver_tel.getText().toString();
			String carnum2=carnum.getText().toString();
		if(cyorg.length()!=0&&cyaddr.length()!=0&&goods_value2.length()!=0&&fhorg.length()!=0&&shorg.length()!=0&&carnum.length()!=0&&insured_tel.length()!=0&&insured_address.length()!=0){	
			if(!isMobileNO(tel)){
				    Toast.makeText(Cy_company_txbxd_time.this, "手机号码不正确", Toast.LENGTH_LONG).show();
				    ((EditText)findViewById(R.id.edit_insured_tel)).setText("");
			    ((EditText)findViewById(R.id.edit_insured_tel)).requestFocus();	
			}else if(!isMobileNO(driver_tel2)){
				    Toast.makeText(Cy_company_txbxd_time.this, "手机号码不正确", Toast.LENGTH_LONG).show();
				    ((EditText)findViewById(R.id.edit_driver_tel)).setText("");
			    ((EditText)findViewById(R.id.edit_driver_tel)).requestFocus();						
			}else if(time<sumtime) Toast.makeText(Cy_company_txbxd_time.this, "余额不足", Toast.LENGTH_LONG).show();
				else{
					AlertDialog alert=new AlertDialog.Builder(Cy_company_txbxd_time.this).create();
				    alert.setTitle("系统提示:");
				    alert.setMessage("需支付车次数:"+sumtime);
				    //添加"确定"按钮
				    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定支付", new DialogInterface.OnClickListener(){
				    	@Override
				    	public void onClick(DialogInterface dialog,int which){
				    		new Cy_insure().execute();
				    	}
				    });
				    alert.show();
					
				}
			}else Toast.makeText(Cy_company_txbxd_time.this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
		}			
	}); 

}
//访问数据库
class Cy_insure extends AsyncTask<String, String, String> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog = new ProgressDialog(Cy_company_txbxd_time.this); 
		pDialog.setMessage("提交中"); 
		pDialog.setIndeterminate(false); 
		pDialog.setCancelable(true); 
		pDialog.show(); 
	}
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		String insured_tel2=((EditText)findViewById(R.id.edit_insured_tel)).getText().toString();
		String insured_address2=((EditText)findViewById(R.id.edit_insured_address)).getText().toString();
		String insured_name2=((EditText)findViewById(R.id.edit_insured_name)).getText().toString();
		String carnum2=carnum.getText().toString();
		String driver_name2=driver_name.getText().toString();
		String driver_tel2=driver_tel.getText().toString();
		String driver_license2=driver_license.getText().toString();
		String fhorg2=fhorg.getText().toString();
		String shorg2=shorg.getText().toString();
		String goods_value3=goods_value2.getText().toString();
		String cyorg2=cyorg.getText().toString();
		String cyaddr2=cyaddr.getText().toString();
		
	    double time2=time-sumtime;
		
		String str1=insured_tel2.substring(0, 3);
		String str2="****";
		String str3=insured_tel2.substring(7, 11);
		
		long now = System.currentTimeMillis();
		serial_num=str1+str2+str3+String.valueOf(now);
		
		List<NameValuePair> para = new ArrayList<NameValuePair>(); 
		para.add(new BasicNameValuePair("fhorg", fhorg2));
		para.add(new BasicNameValuePair("shorg", shorg2));
		para.add(new BasicNameValuePair("fbnum", fbnum));
		para.add(new BasicNameValuePair("insured_tel", insured_tel2));
		para.add(new BasicNameValuePair("insured_address", insured_address2));
		para.add(new BasicNameValuePair("insured_name", insured_name2));
		para.add(new BasicNameValuePair("cy_userid", Integer.toString(userid))); 
		para.add(new BasicNameValuePair("carnum", carnum2));
		para.add(new BasicNameValuePair("fh_address", fhaddress));
		para.add(new BasicNameValuePair("dd_address", ddaddress));
		para.add(new BasicNameValuePair("hw_name", hw_name));
		para.add(new BasicNameValuePair("hw_weight", hw_weight));
		para.add(new BasicNameValuePair("driver_name", driver_name2));
		para.add(new BasicNameValuePair("driver_tel", driver_tel2));
		para.add(new BasicNameValuePair("driver_license", driver_license2));
		para.add(new BasicNameValuePair("goods_value", goods_value3));
		para.add(new BasicNameValuePair("balance_time", String.valueOf(time2)));
		para.add(new BasicNameValuePair("pay_time", String.valueOf(sumtime)));
		para.add(new BasicNameValuePair("serial_num", serial_num));
		para.add(new BasicNameValuePair("insurance_num", insurance_num));
		para.add(new BasicNameValuePair("max", goods_value));
		para.add(new BasicNameValuePair("cyorg", cyorg2));
		para.add(new BasicNameValuePair("cyaddr", cyaddr2));
		
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
		    it.setClass(Cy_company_txbxd_time.this,Cy_company_myInsurance.class);	
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
		
		//手机号码正则
		public static boolean isMobileNO(String mobiles) {  
				String telRegex = "[1][3458]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
				if (TextUtils.isEmpty(mobiles)) return false;  
				else return mobiles.matches(telRegex);  
		} 
		//车牌号正则
		public static boolean isCarnumberNO(String carnumber) {
			String carnumRegex = "([\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5})|([\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}[\u4e00-\u9fa5]{2}[A-Z]{1}[A-Z_0-9]{5})";
			if (TextUtils.isEmpty(carnumber)) return false;
			else return carnumber.matches(carnumRegex);
			}	
}