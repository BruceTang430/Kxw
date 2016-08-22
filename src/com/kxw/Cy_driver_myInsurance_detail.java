package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Ty_myInsurance_detail.Pay;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cy_driver_myInsurance_detail extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_driver_pay_online.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0,userid;
	JSONParser jsonParser = new JSONParser();
	
	TextView fbnum,insure_name,insured_tel,insured_address,insured_name,insuremoney,goods_name,
    goods_weight,goods_loadaddr,goods_destination,insurance_num2,carnumber,delivertime,license,
    driver_tel,driver_license,driver_name;
	String ispay,insurance_num,premium,fbnum2,serial_num;
	double balance,premium2;
    
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.cy_myinsurance_detail);
ActivityCollector.getInstance().addActivity(this);


Intent intent=getIntent();
Bundle bun = intent.getExtras();

fbnum=(TextView)findViewById(R.id.txt_fbnum);
fbnum2=bun.getString("fbnum");
fbnum.setText(fbnum2);

insure_name=(TextView)findViewById(R.id.txt_insure_name);
insure_name.setText(bun.getString("insure_name"));

insured_tel=(TextView)findViewById(R.id.txt_insured_tel);
insured_tel.setText(bun.getString("insured_tel"));

insured_address=(TextView)findViewById(R.id.txt_insured_address);
insured_address.setText(bun.getString("insured_address"));

insured_name=(TextView)findViewById(R.id.txt_insured_name);
insured_name.setText(bun.getString("insured_name"));

insuremoney=(TextView)findViewById(R.id.txt_insuremoney);
insuremoney.setText(bun.getString("insuremoney"));

goods_name=(TextView)findViewById(R.id.txt_goods_name);
goods_name.setText(bun.getString("goods_name"));


goods_weight=(TextView)findViewById(R.id.txt_goods_weight);
goods_weight.setText(bun.getString("goods_weight"));

goods_loadaddr=(TextView)findViewById(R.id.txt_goods_loadaddr);
goods_loadaddr.setText(bun.getString("goods_loadaddr"));

goods_destination=(TextView)findViewById(R.id.txt_goods_destination);
goods_destination.setText(bun.getString("goods_destination"));

insurance_num2=(TextView)findViewById(R.id.txt_insurnum);
insurance_num=bun.getString("insurance_num");
insurance_num2.setText(insurance_num);

carnumber=(TextView)findViewById(R.id.txt_carnum);
carnumber.setText(bun.getString("carnumber"));

delivertime=(TextView)findViewById(R.id.txt_delivertime);
delivertime.setText(bun.getString("delivertime"));

driver_name=(TextView)findViewById(R.id.txt_driver_name);
driver_name.setText(bun.getString("driver_name"));

driver_tel=(TextView)findViewById(R.id.txt_driver_tel);
driver_tel.setText(bun.getString("driver_tel"));

driver_license=(TextView)findViewById(R.id.txt_driver_license);
driver_license.setText(bun.getString("driver_license"));

premium=bun.getString("premium");
premium2=Double.valueOf(premium);

serial_num=bun.getString("serial_num");

Kxw_Application i=((Kxw_Application)getApplicationContext());
balance=i.get_cy_driver_balance();


Button time=(Button)findViewById(R.id.btn_cc);
time.setVisibility(View.GONE);

//"保单预览"按钮监听
Button button22=(Button)findViewById(R.id.btn_preview22);
//设置按钮颜色
GradientDrawable myGrad2 = (GradientDrawable)button22.getBackground();
myGrad2.setColor(0xFF4f94cd);
button22.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v){
		Intent intent=new Intent();
		intent.setClass(getApplicationContext(), Cy_driver_myInsurance_preview.class);
		startActivity(intent);
	}		 	
});	

//if(insurance_num.equals("审核中")){
//button.setVisibility(View.INVISIBLE);
//}
Button cash=(Button)findViewById(R.id.btn_pay);
//设置按钮颜色
GradientDrawable myGrad4 = (GradientDrawable)cash.getBackground();
myGrad4.setColor(0xFF4f94cd);	
cash.setOnClickListener(new View.OnClickListener(){
	@Override
	public void onClick(View v){
		if(balance<premium2){
			AlertDialog alert=new AlertDialog.Builder(Cy_driver_myInsurance_detail.this).create();
		    alert.setTitle("系统提示:");
		    alert.setMessage("余额不足");
		    //添加"确定"按钮
		    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    	}
		    });
		    alert.show();
		}else {
			AlertDialog alert2=new AlertDialog.Builder(Cy_driver_myInsurance_detail.this).create();
		    alert2.setTitle("系统提示:");
		    alert2.setMessage("需支付:"+premium);
		    //添加"确定"按钮
		    alert2.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    		new Pay().execute();
		    	}
		    });
		    alert2.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    	}
		    });
		    alert2.show();
		}
		
	}
});		

}

//访问数据库
class Pay extends AsyncTask<String, String, String> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog = new ProgressDialog(Cy_driver_myInsurance_detail.this); 
		pDialog.setMessage("Loging.."); 
		pDialog.setIndeterminate(false); 
		pDialog.setCancelable(true); 
		pDialog.show(); 
	}
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub	

		double b=balance-premium2;
		
		Kxw_Application i=((Kxw_Application)getApplicationContext());
		String username=i.get_cy_driver_username();
		int userid=i.get_cy_driver_userid();
		
		List<NameValuePair> para = new ArrayList<NameValuePair>(); 
		para.add(new BasicNameValuePair("balance", Double.toString(b)));  
		para.add(new BasicNameValuePair("username", username));
		para.add(new BasicNameValuePair("userid", String.valueOf(userid)));
		para.add(new BasicNameValuePair("fbnum", fbnum2));
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
		it.setClass(Cy_driver_myInsurance_detail.this,Cy_driver_myInsurance.class);
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
