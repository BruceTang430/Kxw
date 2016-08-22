package com.kxw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_driver_txbxd_cash.Cy_insure;

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

public class Cy_company_txbxd_cash extends Activity implements TextWatcher {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_company_insure.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1,userid;
	EditText insure_name,insured_tel,insured_address,insured_name,insuremoney,driver_name,driver_tel,
	driver_license,carnum;
	TextView cy_premium;
	String premium3,fbnum,fhaddress,ddaddress,hw_name,hw_weight,serial_num;
	double balance,premium,ratio;
	RadioButton r;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_txbxd_cash);
		ActivityCollector.getInstance().addActivity(this);

		//调用全局变量
		Kxw_Application tyd=((Kxw_Application)getApplicationContext());
		
		userid=tyd.get_cy_company_userid();
		fbnum=tyd.get_tyd_fbnum();
	    fhaddress=tyd.get_tyd_fh_address();
		ddaddress=tyd.get_tyd_dd_address();
		hw_name=tyd.get_tyd_hw_name();
		hw_weight=tyd.get_tyd_hw_weight();
		
		insure_name=(EditText)findViewById(R.id.edit_insure_name);
		insured_tel=(EditText)findViewById(R.id.edit_insured_tel);
		insured_address=(EditText)findViewById(R.id.edit_insured_address);
		insured_name=(EditText)findViewById(R.id.edit_insured_name);
		insuremoney=(EditText)findViewById(R.id.edit_insuremoney);
		cy_premium=(TextView)findViewById(R.id.txt_premium);
		driver_name=(EditText)findViewById(R.id.edit_driver_name);
		driver_tel=(EditText)findViewById(R.id.edit_driver_tel);
		driver_license=(EditText)findViewById(R.id.edit_driver_license);
		carnum=(EditText)findViewById(R.id.edit_carnum);
		
		final RadioGroup pay=(RadioGroup)findViewById(R.id.radgroup);
		//计算保费
		insuremoney.addTextChangedListener(this);
		
		//获取余额
		Kxw_Application i=((Kxw_Application)getApplicationContext());
		balance=i.get_cy_company_balance();
		ratio=i.get_cy_company_ratio();
		
		//"确定提交"按钮监听
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
				String tel=insured_tel.getText().toString();
				String driver_tel2=driver_tel.getText().toString();
				String carnum2=carnum.getText().toString();
				String license=driver_license.getText().toString();
				if(carnum.length()!=0&&insure_name.length()!=0&&insured_tel.length()!=0&&insured_address.length()!=0&&insuremoney.length()!=0){
					if(!isMobileNO(tel)){
  					    Toast.makeText(Cy_company_txbxd_cash.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_insured_tel)).setText("");
					    ((EditText)findViewById(R.id.edit_insured_tel)).requestFocus();	
					}else if(!isMobileNO(driver_tel2)){
  					    Toast.makeText(Cy_company_txbxd_cash.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_driver_tel)).setText("");
					    ((EditText)findViewById(R.id.edit_driver_tel)).requestFocus();						
					}else if(!isIdNO(license)){
  					    Toast.makeText(Cy_company_txbxd_cash.this, "驾驶证号不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_driver_license)).setText("");
					    ((EditText)findViewById(R.id.edit_driver_license)).requestFocus();						
					}else{
						new Cy_insure().execute();
					}
				}else Toast.makeText(Cy_company_txbxd_cash.this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
			}			
		}); 

	}
	//访问数据库
	class Cy_insure extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Cy_company_txbxd_cash.this); 
			pDialog.setMessage("提交中"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			String insure_name2=((EditText)findViewById(R.id.edit_insure_name)).getText().toString();
			String insured_tel2=((EditText)findViewById(R.id.edit_insured_tel)).getText().toString();
			String insured_address2=((EditText)findViewById(R.id.edit_insured_address)).getText().toString();
			String insured_name2=((EditText)findViewById(R.id.edit_insured_name)).getText().toString();
			String insuremoney2=((EditText)findViewById(R.id.edit_insuremoney)).getText().toString();
			String carnum2=carnum.getText().toString();
			String driver_name2=driver_name.getText().toString();
			String driver_tel2=driver_tel.getText().toString();
			String driver_license2=driver_license.getText().toString();
			
			if(insured_name2.equals("")){
				insured_name2=insure_name2;
			}
			
			String str1=insured_tel2.substring(0, 3);
			String str2="****";
			String str3=insured_tel2.substring(7, 11);
			
			long now = System.currentTimeMillis();
			serial_num=str1+str2+str3+String.valueOf(now);

			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("fbnum", fbnum));
			para.add(new BasicNameValuePair("insure_name", insure_name2));
			para.add(new BasicNameValuePair("insured_tel", insured_tel2));
			para.add(new BasicNameValuePair("insured_address", insured_address2));
			para.add(new BasicNameValuePair("insured_name", insured_name2));
			para.add(new BasicNameValuePair("insuremoney", insuremoney2));
			para.add(new BasicNameValuePair("cy_userid", Integer.toString(userid))); 
			para.add(new BasicNameValuePair("cy_premium", premium3));
			para.add(new BasicNameValuePair("carnum", carnum2));
			para.add(new BasicNameValuePair("fh_address", fhaddress));
			para.add(new BasicNameValuePair("dd_address", ddaddress));
			para.add(new BasicNameValuePair("hw_name", hw_name));
			para.add(new BasicNameValuePair("hw_weight", hw_weight));
			para.add(new BasicNameValuePair("driver_name", driver_name2));
			para.add(new BasicNameValuePair("driver_tel", driver_tel2));
			para.add(new BasicNameValuePair("driver_license", driver_license2));
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
				if(r.getText().equals("线下支付")){
					Intent it= new Intent();
					it.setClass(Cy_company_txbxd_cash.this,Ty_pay_offline.class);	
					startActivity(it);
					finish();
				}else if(r.getText().equals("余额抵扣")){
					if(balance<premium){
						AlertDialog alert=new AlertDialog.Builder(Cy_company_txbxd_cash.this).create();
					    alert.setTitle("系统提示:");
					    alert.setMessage("余额不足，请线下支付");
					    //添加"确定"按钮
					    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
					    	}
					    });
					    alert.show();
					}else {
					    Intent it= new Intent();
					    it.setClass(Cy_company_txbxd_cash.this,Cy_company_pay_online.class);	
					    it.putExtra("premium", premium3);
					    it.putExtra("fbnum", fbnum);
					    it.putExtra("serial_num", serial_num);
					    startActivity(it);
					    finish();
					}					
				}
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

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		double insuremoney3=Double.parseDouble(insuremoney.getText().toString());
	    //premium=Math.floor(insuremoney3*ratio);
	    BigDecimal b   =   new   BigDecimal(insuremoney3*ratio); 
	    premium   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();  
	    premium3=String.valueOf(premium);
        cy_premium.setText(premium3);

	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

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
	//身份证号码正则
	public static boolean isIdNO(String id) {  		    
		    String isIDCard="(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
			if (TextUtils.isEmpty(id)) return false;  
			else return id.matches(isIDCard);  
	}
}

