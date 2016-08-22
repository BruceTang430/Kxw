package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cy_driver_set_mod extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_driver_set_mod.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;
	JSONParser jsonParser = new JSONParser();
	String driver_tel,driver_name,car_num,company_name,car_type,driver_idcard,driver_bankcard,username;
	EditText driver_name2,driver_tel2,driver_idcard2,driver_bankcard2,car_num2,car_type2,company_name2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_driver_set_mod);
		ActivityCollector.getInstance().addActivity(this);

		 Kxw_Application cy=((Kxw_Application)getApplicationContext());
		 company_name=cy.get_cy_driver_company();
		 driver_name=cy.get_cy_driver_name();
		 driver_tel=cy.get_cy_driver_tel();
		 driver_idcard=cy.get_cy_driver_idcard();
		 driver_bankcard=cy.get_cy_driver_bankcard();
		 car_num=cy.get_driver_carnum();
		 car_type=cy.get_cy_driver_cartype();
		 username=cy.get_cy_driver_username();
		 
		driver_name2=(EditText)findViewById(R.id.edit_driver_name);
		driver_name2.setText(driver_name);
		driver_tel2=(EditText)findViewById(R.id.edit_driver_tel);
		driver_tel2.setText(driver_tel);
		driver_idcard2=(EditText)findViewById(R.id.edit_driver_idcard);
		driver_idcard2.setText(driver_idcard);
		driver_bankcard2=(EditText)findViewById(R.id.edit_driver_bankcard);
		driver_bankcard2.setText(driver_bankcard);
		car_num2=(EditText)findViewById(R.id.edit_car_num);
		car_num2.setText(car_num);
		car_type2=(EditText)findViewById(R.id.edit_car_type);
		car_type2.setText(car_type);
		company_name2=(EditText)findViewById(R.id.edit_company_name);
		company_name2.setText(company_name);
		
		//确认提交按钮监听
		Button button2=(Button)findViewById(R.id.btn_submit);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
  				if(!"".equals(driver_name2)&&!"".equals(driver_tel2)&&!"".equals(driver_idcard2)&&!"".equals(driver_bankcard2)&&!"".equals(car_num2)&&!"".equals(car_type2)&&!"".equals(company_name2)){
  					if(!isMobileNO(driver_tel)){
  					    Toast.makeText(Cy_driver_set_mod.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_driver_tel)).setText("");
					    ((EditText)findViewById(R.id.edit_driver_tel)).requestFocus();	
					}else new Modif().execute();
				}	
			}
		});

	}
	//访问数据库
	class Modif extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Cy_driver_set_mod.this); 
			pDialog.setMessage("处理中"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub		
			String driver_name=driver_name2.getText().toString();
			String driver_tel=driver_tel2.getText().toString();
			String driver_idcard=driver_idcard2.getText().toString();
			String driver_bankcard=driver_bankcard2.getText().toString();
			String car_num=car_num2.getText().toString();
			String car_type=car_type2.getText().toString();
			String company_name=company_name2.getText().toString();
							
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("username", username)); 
			para.add(new BasicNameValuePair("driver_name", driver_name));
			para.add(new BasicNameValuePair("driver_tel", driver_tel));
			para.add(new BasicNameValuePair("driver_idcard", driver_idcard));
			para.add(new BasicNameValuePair("driver_bankcard", driver_bankcard));
			para.add(new BasicNameValuePair("car_num", car_num));
			para.add(new BasicNameValuePair("car_type", car_type));
			para.add(new BasicNameValuePair("company_name", company_name));

			// getting JSON Object 
			// Note that create product url accepts POST method 
			JSONObject json = jsonParser.makeHttpRequest(url_create_product, 
			"POST", para); 
			// check log cat fro response 
			Log.d("Create Response", json.toString()); 
			// check for success tag 
			try{ 
			success = json.getInt(TAG_SUCCESS); 
						
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
				Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent();
				intent.setClass(Cy_driver_set_mod.this, Cy_driver_set.class);
				startActivity(intent);
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
	
	//手机号码正则
	public static boolean isMobileNO(String mobiles) {  
			String telRegex = "[1][3458]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
			if (TextUtils.isEmpty(mobiles)) return false;  
			else return mobiles.matches(telRegex);  
	}
	
	//左上角返回
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




