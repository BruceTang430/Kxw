package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_company_txbxd_cash.Cy_insure;

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
import android.text.TextWatcher;
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

public class Cy_company_txbxd_time2 extends Activity implements TextWatcher {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_insure_time.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1,userid;
	EditText insure_name,insure_tel,insure_address,insured_name,carnum;
	RadioButton r,r2;
	String fbnum;
	RadioGroup pay,add;
	double insuremon,addit,time,sumtime,coe;
	TextView cy_time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_txbxd_time);
		ActivityCollector.getInstance().addActivity(this);

		//调用全局变量
		Kxw_Application tyd=((Kxw_Application)getApplicationContext());
	    TextView fbnum2=(TextView)findViewById(R.id.txt_fbnum);
	    fbnum2.setText(tyd.get_tyd_fbnum());
	    TextView fhaddress3=(TextView)findViewById(R.id.txt_fhaddress);
		fhaddress3.setText(tyd.get_tyd_fh_address());
		TextView ddaddress3=(TextView)findViewById(R.id.txt_ddaddress);
		ddaddress3.setText(tyd.get_tyd_dd_address());
		coe=tyd.get_coe();
		
		userid=tyd.get_cy_company_userid();
		
		insure_name=(EditText)findViewById(R.id.edit_insure_name);
		insure_tel=(EditText)findViewById(R.id.edit_insured_tel);
		insure_address=(EditText)findViewById(R.id.edit_insured_address);
		insured_name=(EditText)findViewById(R.id.edit_insured_name);
		carnum=(EditText)findViewById(R.id.edit_carnum);
		
		cy_time=(TextView)findViewById(R.id.txt_time);
		
		pay=(RadioGroup)findViewById(R.id.radgroup);
		add=(RadioGroup)findViewById(R.id.radgroup2);
		
		//获取车次余额
		Kxw_Application i=((Kxw_Application)getApplicationContext());
		time=i.get_cy_company_time();
		
	
	//"确定提交"按钮监听
	Button button=(Button)findViewById(R.id.btn_submit);
	//设置按钮颜色
	GradientDrawable myGrad = (GradientDrawable)button.getBackground();
	myGrad.setColor(0xFF4f94cd);
	button.setOnClickListener(new OnClickListener(){
		@Override
		public void onClick(View v){

			String tel=insure_tel.getText().toString();
			if(insure_name.length()!=0&&insure_tel.length()!=0&&insure_address.length()!=0&&carnum.length()!=0){
				if(!isMobileNO(tel)){
					    Toast.makeText(Cy_company_txbxd_time2.this, "手机号码不正确", Toast.LENGTH_LONG).show();
					    ((EditText)findViewById(R.id.edit_insured_tel)).setText("");
				    ((EditText)findViewById(R.id.edit_insured_tel)).requestFocus();	
				}else if(time<sumtime) Toast.makeText(Cy_company_txbxd_time2.this, "余额不足", Toast.LENGTH_LONG).show();
				else{
					new Cy_insure().execute();
				}
			}else Toast.makeText(Cy_company_txbxd_time2.this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
		}			
	}); 

}
//访问数据库
class Cy_insure extends AsyncTask<String, String, String> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog = new ProgressDialog(Cy_company_txbxd_time2.this); 
		pDialog.setMessage("提交中"); 
		pDialog.setIndeterminate(false); 
		pDialog.setCancelable(true); 
		pDialog.show(); 
	}
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		String insure_name2=((EditText)findViewById(R.id.edit_insure_name)).getText().toString();
		String insure_tel2=((EditText)findViewById(R.id.edit_insured_tel)).getText().toString();
		String insure_address2=((EditText)findViewById(R.id.edit_insured_address)).getText().toString();
		String insured_name2=((EditText)findViewById(R.id.edit_insured_name)).getText().toString();
		String insuremoney2=((EditText)findViewById(R.id.edit_insuremoney)).getText().toString();
		String carnum2=((EditText)findViewById(R.id.edit_carnum)).getText().toString();
		
		if(insured_name2.equals("")){
			insured_name2=insure_name2;
		}

		Kxw_Application tyd2=((Kxw_Application)getApplicationContext());
	    fbnum=tyd2.get_tyd_fbnum();
	    String username=tyd2.get_cy_company_username();
	    double time2=time-sumtime;
		
		List<NameValuePair> para = new ArrayList<NameValuePair>(); 
		para.add(new BasicNameValuePair("fbnum", fbnum));
		para.add(new BasicNameValuePair("insure_name", insure_name2));
		para.add(new BasicNameValuePair("insure_tel", insure_tel2));
		para.add(new BasicNameValuePair("insure_address", insure_address2));
		para.add(new BasicNameValuePair("insured_name", insured_name2));
		para.add(new BasicNameValuePair("cy_userid", Integer.toString(userid))); 
		para.add(new BasicNameValuePair("carnum", carnum2));
		para.add(new BasicNameValuePair("cy_username", username));
		para.add(new BasicNameValuePair("time", String.valueOf(time2)));
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
	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		for(int i=0;i<pay.getChildCount();i++){
			r=(RadioButton)pay.getChildAt(i);
			if(r.isChecked()){
				r.getText();
				break;
			}
		}
		if(r.getText().equals("50万")) insuremon=0.5;
		else if(r.getText().equals("100万")) insuremon=1;
		
		for(int i=0;i<add.getChildCount();i++){
			r2=(RadioButton)add.getChildAt(i);
			if(r2.isChecked()){
				addit=0.5;
				break;
			}else addit=0;
		}
		sumtime=insuremon*coe+addit;
		cy_time.setText(String.valueOf(sumtime));

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

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

}
