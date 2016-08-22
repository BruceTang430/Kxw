package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Third_myInsurance_detail.Pay;

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

public class Third_myInsurance_freight_detail extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/third_pay_online.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;
	JSONParser jsonParser = new JSONParser();
	
	TextView car_num,car_numtype,engine_num,carframe_num,car_character,driver_name,firstdate,
    business_insure,jq_insure,car_typename,jq_money,damage_money,steal_money,three_money,
    driverseat_money,pasgerseat_money,time,serial_num,insurance_num,idcard,transfer_date;
    
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.third_myinsurance_freight_detail);
ActivityCollector.getInstance().addActivity(this);

Intent intent=getIntent();
Bundle bun = intent.getExtras();

idcard=(TextView)findViewById(R.id.txt_idcard);
idcard.setText(bun.getString("idcard"));

transfer_date=(TextView)findViewById(R.id.txt_transfer_date);
transfer_date.setText(bun.getString("transfer_date"));

serial_num=(TextView)findViewById(R.id.txt_serial_num);
serial_num.setText(bun.getString("serial_num"));

insurance_num=(TextView)findViewById(R.id.txt_insurance_num);
insurance_num.setText(bun.getString("insurance_num"));

car_num=(TextView)findViewById(R.id.txt_car_num);
car_num.setText(bun.getString("car_num"));

car_numtype=(TextView)findViewById(R.id.txt_car_numtype);
car_numtype.setText(bun.getString("car_numtype"));

engine_num=(TextView)findViewById(R.id.txt_engine_num);
engine_num.setText(bun.getString("engine_num"));

carframe_num=(TextView)findViewById(R.id.txt_carframe_num);
carframe_num.setText(bun.getString("carframe_num"));

car_character=(TextView)findViewById(R.id.txt_car_character);
car_character.setText(bun.getString("car_character"));

driver_name=(TextView)findViewById(R.id.txt_driver_name);
driver_name.setText(bun.getString("driver_name"));

firstdate=(TextView)findViewById(R.id.txt_firstdate);
firstdate.setText(bun.getString("firstdate"));


business_insure=(TextView)findViewById(R.id.txt_business_insure);
business_insure.setText(bun.getString("business_insure"));

jq_insure=(TextView)findViewById(R.id.txt_jq_insure);
jq_insure.setText(bun.getString("jq_insure"));

car_typename=(TextView)findViewById(R.id.txt_car_typename);
car_typename.setText(bun.getString("car_typename"));

jq_money=(TextView)findViewById(R.id.txt_jq_money);
jq_money.setText(bun.getString("jq_money"));

damage_money=(TextView)findViewById(R.id.txt_damage_money);
damage_money.setText(bun.getString("damage_money"));

steal_money=(TextView)findViewById(R.id.txt_steal_money);
steal_money.setText(bun.getString("steal_money"));

three_money=(TextView)findViewById(R.id.txt_three_money);
three_money.setText(bun.getString("three_money"));

driverseat_money=(TextView)findViewById(R.id.txt_driverseat_money);
driverseat_money.setText(bun.getString("driverseat_money"));

pasgerseat_money=(TextView)findViewById(R.id.txt_pasgerseat_money);
pasgerseat_money.setText(bun.getString("pasgerseat_money"));

time=(TextView)findViewById(R.id.txt_time);
time.setText(bun.getString("time"));



////"保单预览"按钮监听
//Button button22=(Button)findViewById(R.id.btn_preview22);
////设置按钮颜色
//GradientDrawable myGrad2 = (GradientDrawable)button22.getBackground();
//myGrad2.setColor(0xFF4f94cd);
//button22.setOnClickListener(new OnClickListener(){
//	@Override
//	public void onClick(View v){
//		Intent intent=new Intent();
//		intent.setClass(getApplicationContext(), Third_myInsurance_preview.class);
//		startActivity(intent);
//	}		 	
//});	

//Button button=(Button)findViewById(R.id.btn_pay);
//if(premium.equals("")){
//	button.setVisibility(View.GONE);
//}else premium2=Double.valueOf(premium);
//设置按钮颜色
//GradientDrawable myGrad4 = (GradientDrawable)button.getBackground();
//myGrad4.setColor(0xFF4f94cd);	
//button.setOnClickListener(new View.OnClickListener(){
//	@Override
//	public void onClick(View v){
//
//		if(balance<premium2){
//			AlertDialog alert=new AlertDialog.Builder(Third_myInsurance_detail.this).create();
//		    alert.setTitle("系统提示:");
//		    alert.setMessage("余额不足"+String.valueOf(balance));
//		    //添加"确定"按钮
//		    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
//		    	@Override
//		    	public void onClick(DialogInterface dialog,int which){
//		    	}
//		    });
//		    alert.show();
//		}else {
//			AlertDialog alert2=new AlertDialog.Builder(Third_myInsurance_detail.this).create();
//		    alert2.setTitle("系统提示:");
//		    alert2.setMessage("需支付:"+premium);
//		    //添加"确定"按钮
//		    alert2.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
//		    	@Override
//		    	public void onClick(DialogInterface dialog,int which){
//		    		new Pay().execute();
//		    	}
//		    });
//		    alert2.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener(){
//		    	@Override
//		    	public void onClick(DialogInterface dialog,int which){
//		    	}
//		    });
//		    alert2.show();
//		}
//		
//	}
//});		

}

//访问数据库
//class Pay extends AsyncTask<String, String, String> {
//
//@Override
//protected void onPreExecute() {
//	// TODO Auto-generated method stub
//	super.onPreExecute();
//	pDialog = new ProgressDialog(Third_myInsurance_detail.this); 
//	pDialog.setMessage("Loging.."); 
//	pDialog.setIndeterminate(false); 
//	pDialog.setCancelable(true); 
//	pDialog.show(); 
//}
//
//@Override
//protected String doInBackground(String... args) {
//	// TODO Auto-generated method stub	
//
//	double b=balance-premium2;
//	
//	Kxw_Application i=((Kxw_Application)getApplicationContext());
//	String username=i.get_third_username();
//	int userid=i.get_third_userid();
//	
//	List<NameValuePair> para = new ArrayList<NameValuePair>(); 
//	para.add(new BasicNameValuePair("balance", Double.toString(b)));  
//	para.add(new BasicNameValuePair("username", username));
//	para.add(new BasicNameValuePair("userid", String.valueOf(userid)));
//	para.add(new BasicNameValuePair("fbnum", fbnum2));
//	para.add(new BasicNameValuePair("usertype", String.valueOf(usertype)));
//	// getting JSON Object 
//	// Note that create product url accepts POST method 
//	JSONObject json = jsonParser.makeHttpRequest(url_create_product, 
//	"POST", para); 
//	// check log cat fro response 
//	Log.d("Create Response", json.toString()); 
//	// check for success tag 
//	try{ 
//	success = json.getInt(TAG_SUCCESS); 
//	if(success == 1) {
//	
//	Intent it= new Intent();							
//	it.setClass(Third_myInsurance_detail.this,Third_myInsurance.class);
//	startActivity(it);
//	finish();
//	} else{ 
//	// failed to create product 
//		Toast.makeText(getApplicationContext(), " 支付失败",Toast.LENGTH_SHORT).show();
//	} 
//	} catch(JSONException e) { 
//	e.printStackTrace(); 
//	} 					
//	return null;
//}
//
//@Override
//protected void onPostExecute(String fil_url) {
//	// TODO Auto-generated method stub
//	if(success==1) 
//		Toast.makeText(getApplicationContext(), "支付成功！", Toast.LENGTH_SHORT).show();
//	pDialog.dismiss();
//}
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

