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
import android.widget.TextView;
import android.widget.Toast;

public class Cy_company_set_mod extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_company_set_mod.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;
	JSONParser jsonParser = new JSONParser();
	String company_tel2,company_name2,company_address2,company_xxaddress2,company_creditnum2,username;
	EditText orgname2,orgtel2,address2,xxaddress2,creditnum2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_company_set_mod);
		ActivityCollector.getInstance().addActivity(this);

		 Kxw_Application cy=((Kxw_Application)getApplicationContext());
		 username=cy.get_cy_company_username();
		 String company_tel=cy.get_cy_company_tel();
		 String company_name=cy.get_cy_company_name();
		 String company_address=cy.get_cy_company_address();
		 String company_xxaddress=cy.get_cy_company_xxaddress();
		 String company_creditnum=cy.get_cy_company_creditnum();
		 
		orgname2=(EditText)findViewById(R.id.edit_orgname);
		orgname2.setText(company_name);
		orgtel2=(EditText)findViewById(R.id.edit_orgtel);
		orgtel2.setText(company_tel);
		address2=(EditText)findViewById(R.id.edit_address);
		address2.setText(company_address);
		xxaddress2=(EditText)findViewById(R.id.edit_xxaddress);
		xxaddress2.setText(company_xxaddress);
		creditnum2=(EditText)findViewById(R.id.edit_creditnum);
		creditnum2.setText(company_creditnum);
		
		//确认提交按钮监听
		Button button2=(Button)findViewById(R.id.btn_submit);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
  				company_name2=orgname2.getText().toString();
  				company_tel2=orgtel2.getText().toString();
  				company_address2=address2.getText().toString();
  				company_xxaddress2=xxaddress2.getText().toString();
  				company_creditnum2=creditnum2.getText().toString();
  				if(!"".equals(company_name2)&&!"".equals(company_tel2)&&!"".equals(company_address2)&&!"".equals(company_xxaddress2)&&!"".equals(company_creditnum2)){
  					if(!isMobileNO(company_tel2)){
  					    Toast.makeText(Cy_company_set_mod.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_orgtel)).setText("");
					    ((EditText)findViewById(R.id.edit_orgtel)).requestFocus();	
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
			pDialog = new ProgressDialog(Cy_company_set_mod.this); 
			pDialog.setMessage("处理中"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub			
							
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("username", username)); 
			para.add(new BasicNameValuePair("company_name", company_name2));
			para.add(new BasicNameValuePair("company_tel", company_tel2));
			para.add(new BasicNameValuePair("company_address", company_address2));
			para.add(new BasicNameValuePair("company_xxaddress", company_xxaddress2));
			para.add(new BasicNameValuePair("company_creditnum", company_creditnum2));

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
				intent.setClass(Cy_company_set_mod.this, Cy_company_set.class);
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



