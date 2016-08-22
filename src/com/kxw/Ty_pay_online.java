package com.kxw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Ty_loginActivity.LogUser;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ty_pay_online extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_pay_online.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0,userid;
	JSONParser jsonParser = new JSONParser();
	double balance,premium;
	BigDecimal data1,data2;
	String fbnum,serial_num;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_pay_online);
		ActivityCollector.getInstance().addActivity(this);
		
		//获取保费
		Intent it=getIntent();
		Bundle bun = it.getExtras();
		String premium2=bun.getString("premium");		
		premium=Double.valueOf(premium2);
		fbnum=bun.getString("fbnum");
		serial_num=bun.getString("serial_num");
		
		//获取余额
		Kxw_Application i=((Kxw_Application)getApplicationContext());
		balance=i.get_ty_balance();
		userid=i.get_ty_userid();
		
		TextView bal=(TextView)findViewById(R.id.txt_balance);
		String balance2=String.valueOf(balance);
		bal.setText(balance2);
		
		//"提交"按钮监听
		Button button=(Button)findViewById(R.id.btn_confirm);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){				
				new Pay().execute();
			}
		});		
	}
	//访问数据库
		class Pay extends AsyncTask<String, String, String> {

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				pDialog = new ProgressDialog(Ty_pay_online.this); 
				pDialog.setMessage("Loging.."); 
				pDialog.setIndeterminate(false); 
				pDialog.setCancelable(true); 
				pDialog.show(); 
			}
			
			@Override
			protected String doInBackground(String... args) {
				// TODO Auto-generated method stub	
 
				double b=balance-premium;
				
				Kxw_Application i=((Kxw_Application)getApplicationContext());
				String username=i.get_ty_login_username();
				
				List<NameValuePair> para = new ArrayList<NameValuePair>(); 
				para.add(new BasicNameValuePair("balance", Double.toString(b)));  
				para.add(new BasicNameValuePair("username", username));
				para.add(new BasicNameValuePair("userid", String.valueOf(userid)));
				para.add(new BasicNameValuePair("fbnum", String.valueOf(fbnum)));
				para.add(new BasicNameValuePair("serial_num", serial_num));
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
				
				Intent it= new Intent();							
				it.setClass(Ty_pay_online.this,Ty_myInsurance.class);
				startActivity(it);
				finish();
				} else{ 
				// failed to create product 
					Toast.makeText(getApplicationContext(), " 支付失败",Toast.LENGTH_SHORT).show();
				} 
				} catch(JSONException e) { 
				e.printStackTrace(); 
				} 					
				return null;
			}

			@Override
			protected void onPostExecute(String fil_url) {
				// TODO Auto-generated method stub
				if(success==1) 
					Toast.makeText(getApplicationContext(), "支付成功！", Toast.LENGTH_SHORT).show();
				pDialog.dismiss();
			}
		
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
