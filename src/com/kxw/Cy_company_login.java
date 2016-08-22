package com.kxw;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_driver_login.LogUser;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cy_company_login extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_company_login.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    EditText username,pwd;
    int success=0;
	JSONParser jsonParser = new JSONParser();
	
	private SharedPreferences pref;
	private SharedPreferences.Editor editor;
	private CheckBox rememberPass;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_login);
		ActivityCollector.getInstance().addActivity(this);
		
		username=(EditText)findViewById(R.id.editText1);
	    pwd=(EditText)findViewById(R.id.editText2);
		pref=PreferenceManager.getDefaultSharedPreferences(this);
		rememberPass=(CheckBox)findViewById(R.id.remember);
	    
	    boolean isRemember=pref.getBoolean("remember", false);
	    if(isRemember){
	    	String username2=pref.getString("username", "");
	    	String pwd2=pref.getString("pwd", "");
	    	username.setText(username2);
	    	pwd.setText(pwd2);
	    	rememberPass.setChecked(true);
	    }

		//登陆按钮监听
		Button button=(Button)findViewById(R.id.btn_login);
		//设置登陆按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				if(username.length()!=0&&pwd.length()!=0)
					new LogUser().execute();
				else Toast.makeText(Cy_company_login.this, "请输入用户名或密码！", Toast.LENGTH_SHORT).show();
			}
		});
		//注册按钮监听
		Button button2=(Button)findViewById(R.id.btn_register);
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFF4f94cd);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Cy_company_login.this, Cy_company_register3.class);
				startActivity(intent);
				}			
		});
		//"忘记密码"按钮监听
		Button button3=(Button)findViewById(R.id.btn_forget);
		//设置按钮颜色
		GradientDrawable myGrad3 = (GradientDrawable)button2.getBackground();
		myGrad3.setColor(0xFF4f94cd);
		button3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Cy_company_login.this, Cy_company_forgetPwd.class);
				startActivity(intent);
				}			
		});			
	}
	//访问数据库
		class LogUser extends AsyncTask<String, String, String> {

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				pDialog = new ProgressDialog(Cy_company_login.this); 
				pDialog.setMessage("Loging.."); 
				pDialog.setIndeterminate(false); 
				pDialog.setCancelable(true); 
				pDialog.show(); 
			}
			
			@Override
			protected String doInBackground(String... args) {
				// TODO Auto-generated method stub	
				String username2=username.getText().toString(); 
				String pwd3=getMD5String(pwd.getText().toString());
				String pwd2=pwd.getText().toString();
				
				List<NameValuePair> para = new ArrayList<NameValuePair>(); 
				para.add(new BasicNameValuePair("username", username2)); 
				para.add(new BasicNameValuePair("pwd", pwd3)); 
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
					
					editor=pref.edit();
					if(rememberPass.isChecked()){
						editor.putBoolean("remember", true);
						editor.putString("username", username2);
						editor.putString("pwd", pwd2);
					}else{
						editor.clear();
					}
					editor.commit();
					
				int userid = json.getInt("userid");
				//double time = json.getDouble("time");
				String time = json.getString("time");
				String insurance_num=json.getString("insurance_num");
				double balance = json.getDouble("balance");
				double ratio = json.getDouble("ratio");
				String cy_company_tel=json.getString("cy_company_tel");
				String cy_company_name=json.getString("cy_company_name");
				String cy_company_address=json.getString("cy_company_address");
				String cy_company_xxaddress=json.getString("cy_company_xxaddress");
				String cy_company_creditnum=json.getString("cy_company_creditnum");
				int isidentify=json.getInt("isidentify");
				
				Kxw_Application id3=((Kxw_Application)getApplicationContext());
				id3.set_cy_company_userid(userid);
				id3.set_cy_company_username(username2);
				id3.set_cy_company_balance(balance);
				id3.set_cy_company_time(Double.valueOf(time));		
				id3.set_cy_company_tel(cy_company_tel);
				id3.set_cy_company_name(cy_company_name);
				id3.set_cy_company_address(cy_company_address);
				id3.set_cy_company_xxaddress(cy_company_xxaddress);
				id3.set_cy_company_creditnum(cy_company_creditnum);
				id3.set_cy_company_ratio(ratio);
				id3.set_cy_company_insurance_num(insurance_num);
				id3.set_company_isidentify(isidentify);

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
					Toast.makeText(getApplicationContext(), "您输入的用户名或密码错误！", Toast.LENGTH_SHORT).show();
				else {
					Intent it= new Intent();							
					it.setClass(Cy_company_login.this,Cy_company_admin_notice.class);
					startActivity(it);
					finish();
				}
				pDialog.dismiss();
			}
		
		}
		
		//MD5加密
	    public final static String getMD5String(String s) {  
			char hexDigits[] = { '0', '1', '2', '3', '4',  
			        '5', '6', '7', '8', '9',  
			        'A', 'B', 'C', 'D', 'E', 'F' };  
			try {  
			    byte[] btInput = s.getBytes();  
			    //获得MD5摘要算法的 MessageDigest 对象  
			    MessageDigest mdInst = MessageDigest.getInstance("MD5");  
			    //使用指定的字节更新摘要  
			    mdInst.update(btInput);  
			    //获得密文  
		        byte[] md = mdInst.digest();  
		        //把密文转换成十六进制的字符串形式  
			    int j = md.length;  
			    char str[] = new char[j * 2];  
			    int k = 0;  
			    for (int i = 0; i < j; i++) {  
			        byte byte0 = md[i];  
			        str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
			        str[k++] = hexDigits[byte0 & 0xf];  
			    }  
			    return new String(str);  
			}  
			catch (Exception e) {  
			   e.printStackTrace();  
			   return null;  
			}  
	    }  		
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
