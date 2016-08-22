package com.kxw;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_driver_txbxd_cash.Cy_insure;
import com.kxw.Ty_infoManagement2.ReChoose;
import com.kxw.Ty_pay_online.Pay;
import com.kxw.Ty_txbxd_cash.Ty_insure;

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

public class Ty_txbxd_cash extends Activity implements TextWatcher {
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_insure.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1,userid;
	String fbnum,fh_address,dd_address,hw_name,hw_number,hw_bz,hw_weight,username,premium3,id;
	EditText insure_name,insured_tel,insured_address,insured_name,insuremoney;
	TextView ty_premium;
	RadioButton r;
	double balance,premium,ratio;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_txbxd_cash);
		ActivityCollector.getInstance().addActivity(this);

		//调用全局变量
		Kxw_Application id=((Kxw_Application)getApplicationContext());
		userid=id.get_ty_userid();
		
		Intent intent=getIntent();
	    Bundle bun = intent.getExtras();
	    
	    fbnum=bun.getString("fbnum");
	    fh_address=bun.getString("fh_address");
	    dd_address=bun.getString("dd_address");
	    hw_name=bun.getString("hw_name");
	    hw_weight=bun.getString("hw_weight");
	    
	    TextView fbnum2=(TextView)findViewById(R.id.txt_fbnum);
	    fbnum2.setText(fbnum);
	    TextView fhaddress3=(TextView)findViewById(R.id.edit_fhaddress);
		fhaddress3.setText(fh_address);
		TextView ddaddress3=(TextView)findViewById(R.id.edit_ddaddress);
		ddaddress3.setText(dd_address);
		
		insure_name=(EditText)findViewById(R.id.edit_insure_name);
		insured_tel=(EditText)findViewById(R.id.edit_insured_tel);
		insured_address=(EditText)findViewById(R.id.edit_insured_address);
		insured_name=(EditText)findViewById(R.id.edit_insured_name);
		insuremoney=(EditText)findViewById(R.id.edit_insuremoney);
		ty_premium=(TextView)findViewById(R.id.txt_premium);
		
		final RadioGroup pay=(RadioGroup)findViewById(R.id.radgroup);
		
		//获取余额
		Kxw_Application i=((Kxw_Application)getApplicationContext());
		balance=i.get_ty_balance();
		ratio=i.get_ty_ratio();
		
		//计算保费
		insuremoney.addTextChangedListener(this);
		//"提交保单"按钮监听
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
				if(insure_name.length()!=0&&insured_tel.length()!=0&&insured_address.length()!=0&&insuremoney.length()!=0){
					if(!isMobileNO(tel)){
  					    Toast.makeText(Ty_txbxd_cash.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_insured_tel)).setText("");
					    ((EditText)findViewById(R.id.edit_insured_tel)).requestFocus();	
					}else{
						new Ty_insure().execute();
					}
				}else Toast.makeText(Ty_txbxd_cash.this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
				
			}			
		}); 
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		//计算保费
		double insuremoney3=Double.parseDouble(insuremoney.getText().toString());
	    //premium=Math.floor(insuremoney3*ratio);
	    BigDecimal b   =   new   BigDecimal(insuremoney3*ratio); 
	    premium   =   b.setScale(1,   BigDecimal.ROUND_HALF_UP).doubleValue();  
	    premium3=String.valueOf(premium);
        ty_premium.setText(premium3);
	}
	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub

	}
	//访问数据库
	class Ty_insure extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Ty_txbxd_cash.this); 
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
			
			if(insured_name2.equals("")){
				insured_name2=insure_name2;
			}
			
			Intent intent=getIntent();
		    Bundle bun = intent.getExtras();				    
		    
		    fbnum=bun.getString("fbnum");
		    hw_name=bun.getString("hw_name");
		    hw_weight=bun.getString("hw_weight");
		    
			String str1=insure_tel2.substring(0, 3);
			String str2="****";
			String str3=insure_tel2.substring(7, 11);
			
			long now = System.currentTimeMillis();
			id=str1+str2+str3+String.valueOf(now);
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("fh_address", fh_address));
			para.add(new BasicNameValuePair("dd_address", dd_address));
			para.add(new BasicNameValuePair("hw_name", hw_name));
			para.add(new BasicNameValuePair("hw_weight", hw_weight));
			para.add(new BasicNameValuePair("fbnum", fbnum));
			para.add(new BasicNameValuePair("insure_name", insure_name2));
			para.add(new BasicNameValuePair("insured_tel", insure_tel2));
			para.add(new BasicNameValuePair("insured_address", insure_address2));
			para.add(new BasicNameValuePair("insured_name", insured_name2));
			para.add(new BasicNameValuePair("insuremoney", insuremoney2));
			para.add(new BasicNameValuePair("ty_userid", Integer.toString(userid))); 
			para.add(new BasicNameValuePair("ty_premium", premium3)); 
			para.add(new BasicNameValuePair("serial_num", id)); 
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
			switch (success){
			case 1:{
				if(r.getText().equals("线下支付")){
					Intent it= new Intent();
					it.setClass(Ty_txbxd_cash.this,Ty_pay_offline.class);	
					startActivity(it);
					finish();
				}else if(r.getText().equals("余额抵扣")){
					if(balance<premium){
						AlertDialog alert=new AlertDialog.Builder(Ty_txbxd_cash.this).create();
					    alert.setTitle("系统提示:");
					    alert.setMessage("余额不足，请线下支付");
					    //添加"确定"按钮
					    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
								Intent it= new Intent();
								it.setClass(Ty_txbxd_cash.this,Ty_mainActivity.class);	
								startActivity(it);
								finish();
					    	}
					    });
					    alert.show();
					}else {
					    Intent it= new Intent();
					    it.setClass(Ty_txbxd_cash.this,Ty_pay_online.class);	
					    it.putExtra("premium", premium3);
					    it.putExtra("fbnum", fbnum);
					    it.putExtra("serial_num", id);
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
	
	//手机号码正则
	public static boolean isMobileNO(String mobiles) {  
			String telRegex = "[1][3458]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
			if (TextUtils.isEmpty(mobiles)) return false;  
			else return mobiles.matches(telRegex);  
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

}
