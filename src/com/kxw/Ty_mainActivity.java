package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_driver_login.LogUser;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Ty_mainActivity extends Activity {
	private TextView fbxx,xxgl,wdbd,hygz,set,exit;
	private long mExitTime;
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_exit.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;String username;
	JSONParser jsonParser = new JSONParser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
		setContentView(R.layout.ty_mainactivity);
		ActivityCollector.getInstance().addActivity(this);
		
		//获取username
		Kxw_Application user3=((Kxw_Application)getApplicationContext());
		username=user3.get_ty_login_username();
		
		Kxw_Application buttonpress=((Kxw_Application)getApplicationContext());
		//发布信息按钮监听
		final Button button=(Button)findViewById(R.id.btn_fbxx);
		//按钮按下效果		
		buttonpress.setButtonFocusChanged(button);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Ty_mainActivity.this, Ty_txtyd1.class);
				startActivity(intent);
			}
			
		});
		//"信息管理"按钮监听
		final Button button2=(Button)findViewById(R.id.btn_xxgl);
		//按钮按下效果
		buttonpress.setButtonFocusChanged(button2);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){			
				Intent intent=new Intent();
				intent.setClass(Ty_mainActivity.this, Ty_infoManagement.class);
				startActivity(intent);
			}
					
		});
		//"我的保单"按钮监听
		final Button button5=(Button)findViewById(R.id.btn_myinsurance);
		//按钮按下效果
		buttonpress.setButtonFocusChanged(button5);
		button5.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){			
				Intent intent=new Intent();
				intent.setClass(Ty_mainActivity.this, Ty_myInsurance.class);
				startActivity(intent);
			}						
		});		
		//"设置"按钮监听
		final Button button3=(Button)findViewById(R.id.btn_set);
		//按钮按下效果
		buttonpress.setButtonFocusChanged(button3);
		button3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){			
				Intent intent=new Intent();
				intent.setClass(Ty_mainActivity.this, Ty_set.class);
				startActivity(intent);
			}						
		});
		//"退出"按钮监听
		final Button button4=(Button)findViewById(R.id.btn_exit);
		//按钮按下效果
		buttonpress.setButtonFocusChanged(button4);
		button4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){			
				Intent intent=new Intent();
				intent.setClass(Ty_mainActivity.this, Ty_carInfo.class);
				startActivity(intent);
			}						
		});
		
	}
	//访问数据库
			class ExitUser extends AsyncTask<String, String, String> {

				@Override
				protected void onPreExecute() {
					// TODO Auto-generated method stub
					super.onPreExecute();
					pDialog = new ProgressDialog(Ty_mainActivity.this); 
					pDialog.setMessage("Exit.."); 
					pDialog.setIndeterminate(false); 
					pDialog.setCancelable(true); 
					pDialog.show(); 
				}
				
				@Override
				protected String doInBackground(String... args) {
					// TODO Auto-generated method stub					
					List<NameValuePair> para = new ArrayList<NameValuePair>(); 
					para.add(new BasicNameValuePair("username", username));
					// getting JSON Object 
					// Note that create product url accepts POST method 
					JSONObject json = jsonParser.makeHttpRequest(url_create_product, 
					"POST", para); 
					// check log cat fro response 
					//Log.d("Create Response", json.toString()); 
					// check for success tag 
					try{ 
					success = json.getInt(TAG_SUCCESS); 
					if(success == 1) {
						//退出程序
						int currentVersion = android.os.Build.VERSION.SDK_INT;  						           
						  if (currentVersion > android.os.Build.VERSION_CODES.ECLAIR_MR1) { 
						      Intent startMain = new Intent(Intent.ACTION_MAIN); 
						          startMain.addCategory(Intent.CATEGORY_HOME); 
						          startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); 
						          startActivity(startMain); 
						          System.exit(0); 
						  }
						else {// android2.1 
						      ActivityManager am = (ActivityManager) getSystemService(ACTIVITY_SERVICE); 
						      am.restartPackage(getPackageName()); 
						  }
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
						Toast.makeText(getApplicationContext(), "系统出错！", Toast.LENGTH_SHORT).show();
					pDialog.dismiss();
				}
			
			}
			//返回键重写，直接返回到桌面
			@Override
		    public void onBackPressed() {
		       // TODO Auto-generated method stub
				Intent home = new Intent(Intent.ACTION_MAIN);  
				home.addCategory(Intent.CATEGORY_HOME);   
				startActivity(home);
		    }

	    
	   //菜单栏
		@Override
	    protected void onStart() {
	        super.onStart();
	        ActionBar actionBar = this.getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }
		@Override 
		public boolean onOptionsItemSelected(MenuItem item) {
			switch(item.getItemId())
		    {
		      case R.id.action_exit:
		    	  ActivityCollector.getInstance().exit();
		        return true;
		      case R.id.action_change:
		    	  Intent intent=new Intent();
				  intent.setClass(Ty_mainActivity.this, Ty_loginActivity.class);
				  startActivity(intent);
				  finish();
		        return true;
		    }
	        return super.onOptionsItemSelected(item);    
	    }
     
		@Override
		  public boolean onCreateOptionsMenu(Menu menu)
		  {
		    // Inflate the menu; this adds items to the action bar if it is present.
		    getMenuInflater().inflate(R.menu.actionbar, menu);			
			//返回true才会显示overflow按钮
		    return true;
		  }

}
