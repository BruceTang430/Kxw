package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Ty_changePwd.LogUser;

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

public class Third_changePwd extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/third_changepwd.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    EditText pwd,repwd;
    int success=0;
	JSONParser jsonParser = new JSONParser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_changepwd);
		ActivityCollector.getInstance().addActivity(this);
		
		pwd=(EditText)findViewById(R.id.edit_pwd);
	    repwd=(EditText)findViewById(R.id.edit_repwd);
	    //"确认按钮监听"
		Button button=(Button)findViewById(R.id.btn_submit);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){				
				if(pwd.getText().toString().equals(repwd.getText().toString()))
					new LogUser().execute();
				else Toast.makeText(Third_changePwd.this, "两次输入密码不一致！", Toast.LENGTH_SHORT).show();
			}
		});
			
	}
	
	//访问数据库
	class LogUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Third_changePwd.this); 
			pDialog.setMessage("Wait.."); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub	
			String pwd2=pwd.getText().toString(); 
			
			Intent intent=getIntent();
		    Bundle bun = intent.getExtras();
		    String username=bun.getString("username");
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("username", username)); 
			para.add(new BasicNameValuePair("pwd", pwd2)); 
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
			if(success==0) 
				Toast.makeText(getApplicationContext(), "您输入的信息错误！", Toast.LENGTH_SHORT).show();
			if(success==1){ 
				Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_SHORT).show();
				Intent it= new Intent();
				it.setClass(Third_changePwd.this,Third_login.class);
			    startActivity(it);
			}
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
