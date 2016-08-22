package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_driver_myInsurance_detail.Pay;

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

public class Cy_company_myInsurance_detail extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_company_pay_online.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0,userid;
	JSONParser jsonParser = new JSONParser();
	
	private static String url_create_product2 = "http://120.27.26.219/kxw/android/cy_company_pay_time.php"; 
    private static final String TAG_SUCCESS2 = "success"; 
    private ProgressDialog pDialog2; 
    int success2=0;
	JSONParser jsonParser2 = new JSONParser();
	
	TextView fbnum,insure_name,insured_tel,insured_address,insured_name,insuremoney,goods_name,
    goods_weight,goods_loadaddr,goods_destination,insurance_num2,carnumber,delivertime,license,
    driver_tel,driver_license,driver_name,fhorg,shorg;
	String time,ispay,insurance_num,premium,fbnum2,serial_num;
	double balance,premium2,time2,balance_time;
    
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.cy_myinsurance_detail);
ActivityCollector.getInstance().addActivity(this);


Intent intent=getIntent();
Bundle bun = intent.getExtras();

time=bun.getString("time");

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

fhorg=(TextView)findViewById(R.id.txt_fhorg);
fhorg.setText(bun.getString("fhorg"));

shorg=(TextView)findViewById(R.id.txt_shorg);
shorg.setText(bun.getString("shorg"));

premium=bun.getString("premium");
serial_num=bun.getString("serial_num");

Kxw_Application i=((Kxw_Application)getApplicationContext());
balance_time=i.get_cy_company_time();
balance=i.get_cy_company_balance();

//"����Ԥ��"��ť����
Button button22=(Button)findViewById(R.id.btn_preview22);
//���ð�ť��ɫ
GradientDrawable myGrad2 = (GradientDrawable)button22.getBackground();
myGrad2.setColor(0xFF4f94cd);
button22.setOnClickListener(new OnClickListener(){
	@Override
	public void onClick(View v){
		Intent intent=new Intent();
		intent.setClass(getApplicationContext(), Cy_company_myInsurance_preview.class);
		startActivity(intent);
	}		 	
});	

Button button=(Button)findViewById(R.id.btn_pay);
Button button2=(Button)findViewById(R.id.btn_cc);

if(insurance_num.equals("δ����")){
}else{
	button.setVisibility(View.GONE);
	button2.setVisibility(View.GONE);
}
if(premium.equals("")){
button.setVisibility(View.GONE);
time2=Double.valueOf(time);
}else {
	button2.setVisibility(View.GONE);
	premium2=Double.valueOf(premium);
}
//���֧����ť
GradientDrawable myGrad4 = (GradientDrawable)button.getBackground();
myGrad4.setColor(0xFF4f94cd);	
button.setOnClickListener(new View.OnClickListener(){
	@Override
	public void onClick(View v){
		if(balance<premium2){
			AlertDialog alert=new AlertDialog.Builder(Cy_company_myInsurance_detail.this).create();
		    alert.setTitle("ϵͳ��ʾ:");
		    alert.setMessage("����");
		    //���"ȷ��"��ť
		    alert.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    	}
		    });
		    alert.show();
		}else {
			AlertDialog alert2=new AlertDialog.Builder(Cy_company_myInsurance_detail.this).create();
		    alert2.setTitle("ϵͳ��ʾ:");
		    alert2.setMessage("��֧��:"+premium);
		    //���"ȷ��"��ť
		    alert2.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    		new Pay().execute();
		    	}
		    });
		    alert2.setButton(DialogInterface.BUTTON_NEGATIVE, "ȡ��", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    	}
		    });
		    alert2.show();
		}
		
	}
});		

//����֧����ť
GradientDrawable myGrad = (GradientDrawable)button2.getBackground();
myGrad.setColor(0xFF4f94cd);	
button2.setOnClickListener(new View.OnClickListener(){
	@Override
	public void onClick(View v){
		if(balance_time<time2){
			AlertDialog alert=new AlertDialog.Builder(Cy_company_myInsurance_detail.this).create();
		    alert.setTitle("ϵͳ��ʾ:");
		    alert.setMessage("����");
		    //���"ȷ��"��ť
		    alert.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    	}
		    });
		    alert.show();
		}else {
			AlertDialog alert2=new AlertDialog.Builder(Cy_company_myInsurance_detail.this).create();
		    alert2.setTitle("ϵͳ��ʾ:");
		    alert2.setMessage("��֧������:"+time2);
		    //���"ȷ��"��ť
		    alert2.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    		new Time().execute();
		    	}
		    });
		    alert2.setButton(DialogInterface.BUTTON_NEGATIVE, "ȡ��", new DialogInterface.OnClickListener(){
		    	@Override
		    	public void onClick(DialogInterface dialog,int which){
		    	}
		    });
		    alert2.show();
		}
		
	}
});	


}

//���֧��
class Pay extends AsyncTask<String, String, String> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog = new ProgressDialog(Cy_company_myInsurance_detail.this); 
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
		String username=i.get_cy_company_username();
		int userid=i.get_cy_company_userid();
		
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
		it.setClass(Cy_company_myInsurance_detail.this,Cy_company_myInsurance.class);
		startActivity(it);
		finish();
		} else{ 
		// failed to create product 
			//Toast.makeText(getApplicationContext(), " ֧��ʧ��",Toast.LENGTH_SHORT).show();
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
			Toast.makeText(getApplicationContext(), "֧���ɹ���", Toast.LENGTH_SHORT).show();
		else Toast.makeText(getApplicationContext(), "���ִ���", Toast.LENGTH_SHORT).show();
		pDialog.dismiss();
		
	}

}

//����֧��
class Time extends AsyncTask<String, String, String> {

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pDialog2 = new ProgressDialog(Cy_company_myInsurance_detail.this); 
		pDialog2.setMessage("Loging.."); 
		pDialog2.setIndeterminate(false); 
		pDialog2.setCancelable(true); 
		pDialog2.show(); 
	}
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub	

		double t=balance_time-time2;
		
		Kxw_Application i=((Kxw_Application)getApplicationContext());
		String username=i.get_cy_company_username();
		int userid=i.get_cy_company_userid();
		
		List<NameValuePair> para = new ArrayList<NameValuePair>(); 
		para.add(new BasicNameValuePair("time", Double.toString(t)));  
		para.add(new BasicNameValuePair("username", username));
		para.add(new BasicNameValuePair("userid", String.valueOf(userid)));
		para.add(new BasicNameValuePair("fbnum", fbnum2));
		// getting JSON Object 
		// Note that create product url accepts POST method 
		JSONObject json = jsonParser2.makeHttpRequest(url_create_product2, 
		"POST", para); 
		// check log cat fro response 
		Log.d("Create Response", json.toString()); 
		// check for success tag 
		try{ 
		success2 = json.getInt(TAG_SUCCESS2); 
		if(success2 == 1) {
		
		Intent it= new Intent();							
		it.setClass(Cy_company_myInsurance_detail.this,Cy_company_myInsurance.class);
		startActivity(it);
		finish();
		} else{ 
		// failed to create product 
			//Toast.makeText(getApplicationContext(), " ֧��ʧ��",Toast.LENGTH_SHORT).show();
		} 
		} catch(JSONException e) { 
		e.printStackTrace(); 
		} 					
		return null;
	}

	@Override
	protected void onPostExecute(String fil_url) {
		// TODO Auto-generated method stub
		if(success2==1) 
			Toast.makeText(getApplicationContext(), "֧���ɹ���", Toast.LENGTH_SHORT).show();
		else Toast.makeText(getApplicationContext(), "���ִ���", Toast.LENGTH_SHORT).show();
		pDialog2.dismiss();
	}

}

//���ϽǷ�����һ��
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

