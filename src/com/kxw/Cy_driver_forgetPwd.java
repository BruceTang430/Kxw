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
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cy_driver_forgetPwd extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_driver_forgetpwd.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    EditText username,tel,idcard;
    int success=0;
	JSONParser jsonParser = new JSONParser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_forgetpwd);
		ActivityCollector.getInstance().addActivity(this);
		
		username=(EditText)findViewById(R.id.edit_username);
	    tel=(EditText)findViewById(R.id.edit_tel);
	    idcard=(EditText)findViewById(R.id.edit_idcard);
	    //"确认按钮监听"
		Button button=(Button)findViewById(R.id.btn_confirm);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){				
				if(username.length()!=0&&tel.length()!=0&&idcard.length()!=0)
					new LogUser().execute();
				else Toast.makeText(Cy_driver_forgetPwd.this, "请输入完整信息！", Toast.LENGTH_SHORT).show();
			}
		});
			
	}
	
	//访问数据库
	class LogUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Cy_driver_forgetPwd.this); 
			pDialog.setMessage("Wait.."); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub	
			String username2=username.getText().toString(); 
			String tel2=tel.getText().toString();
			String idcard2=idcard.getText().toString();
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("username", username2)); 
			para.add(new BasicNameValuePair("tel", tel2)); 
			para.add(new BasicNameValuePair("idcard", idcard2)); 
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
			it.putExtra("username", username2);
			it.setClass(Cy_driver_forgetPwd.this,Cy_driver_changePwd.class);
			startActivity(it);
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
			if(success==0) 
				Toast.makeText(getApplicationContext(), "您输入的信息错误！", Toast.LENGTH_SHORT).show();
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
