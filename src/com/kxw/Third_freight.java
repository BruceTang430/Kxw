package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Third_insure_cy_cash.Third;

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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class Third_freight extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/third_freight.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1,userid,usertype;
	EditText carnum,fdjnum,cjnum,drivername,firstdate,syqb,jqqb,carname,idcard;
	String carnum2,carnumtype2,fdjnum2,cjnum2,character2,drivername2,firstdate2,syqb2,jqqb2,carname2,
	jqmoney2,csmoney2,dqmoney2,szmoney2,sjzwmoney2,ckzwmoney2,idcard2,transfer_date2;
	Spinner carnumtype,character;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_freight);
		ActivityCollector.getInstance().addActivity(this);

		//调用全局变量
		Kxw_Application id=((Kxw_Application)getApplicationContext());
		userid=id.get_third_userid();
		usertype=id.get_usertype();
		
		carnum=(EditText)findViewById(R.id.edit_carnum);
		carnumtype=(Spinner)findViewById(R.id.spin_carnumtype);		
		fdjnum=(EditText)findViewById(R.id.edit_fdjnum);
		cjnum=(EditText)findViewById(R.id.edit_cjnum);
		character=(Spinner)findViewById(R.id.spin_character);		
		drivername=(EditText)findViewById(R.id.edit_drivername);
		firstdate=(EditText)findViewById(R.id.edit_firstdate);
		syqb=(EditText)findViewById(R.id.edit_syqb);
		jqqb=(EditText)findViewById(R.id.edit_jqqb);
		carname=(EditText)findViewById(R.id.edit_carname);
		idcard=(EditText)findViewById(R.id.edit_idcard);
		
		//"提交保单"按钮监听
		Button button=(Button)findViewById(R.id.btn_submit);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				carnumtype2=carnumtype.getSelectedItem().toString();
				character2=character.getSelectedItem().toString();
								
				carnum2=carnum.getText().toString();				
				fdjnum2=fdjnum.getText().toString();
				cjnum2=cjnum.getText().toString();
				drivername2=drivername.getText().toString();
				firstdate2=firstdate.getText().toString();
				syqb2=syqb.getText().toString();
				jqqb2=jqqb.getText().toString();
				carname2=carname.getText().toString();
				idcard2=idcard.getText().toString();
				
				jqmoney2=((EditText)findViewById(R.id.edit_jqmoney)).getText().toString();
				csmoney2=((EditText)findViewById(R.id.edit_csmoney)).getText().toString();
				dqmoney2=((EditText)findViewById(R.id.edit_dqmoney)).getText().toString();
				szmoney2=((EditText)findViewById(R.id.edit_szmoney)).getText().toString();
				sjzwmoney2=((EditText)findViewById(R.id.edit_sjzwmoney)).getText().toString();
				ckzwmoney2=((EditText)findViewById(R.id.edit_ckzwmoney)).getText().toString();
				transfer_date2=((EditText)findViewById(R.id.edit_transfer_date)).getText().toString();
				
				if(jqmoney2.equals(""))jqmoney2="0";
				if(csmoney2.equals(""))csmoney2="0";
				if(dqmoney2.equals(""))dqmoney2="0";
				if(szmoney2.equals(""))szmoney2="0";
				if(sjzwmoney2.equals(""))sjzwmoney2="0";
				if(ckzwmoney2.equals(""))ckzwmoney2="0";
				if(transfer_date2.equals(""))transfer_date2="未过户";

				if(idcard.length()!=0&&carnum.length()!=0&&fdjnum.length()!=0&&cjnum.length()!=0&&drivername.length()!=0
						&&firstdate.length()!=0&&syqb.length()!=0&&jqqb.length()!=0&&carname.length()!=0){
						new Third().execute();
				}else Toast.makeText(Third_freight.this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
				
			}			
		}); 
	}
	
	//访问数据库
	class Third extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Third_freight.this); 
			pDialog.setMessage("提交中"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			
			String str1=carnum2.substring(2, 7);			
			long now = System.currentTimeMillis();
			String serial_num=str1+String.valueOf(now);

			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("userid", String.valueOf(userid)));
			para.add(new BasicNameValuePair("usertype", String.valueOf(usertype)));
			para.add(new BasicNameValuePair("car_num", carnum2));
			para.add(new BasicNameValuePair("car_numtype", carnumtype2));
			para.add(new BasicNameValuePair("engine_num", fdjnum2));
			para.add(new BasicNameValuePair("carframe_num", cjnum2));
			para.add(new BasicNameValuePair("car_character", character2));
			para.add(new BasicNameValuePair("driver_name", drivername2));
			para.add(new BasicNameValuePair("firstdate", firstdate2));
			para.add(new BasicNameValuePair("business_insure", syqb2));
			para.add(new BasicNameValuePair("jq_insure", jqqb2));
			para.add(new BasicNameValuePair("car_typename", carname2));
			para.add(new BasicNameValuePair("jq_money", jqmoney2));
			para.add(new BasicNameValuePair("damage_money", csmoney2)); 
			para.add(new BasicNameValuePair("steal_money", dqmoney2));
			para.add(new BasicNameValuePair("three_money", szmoney2));
			para.add(new BasicNameValuePair("driverseat_money", sjzwmoney2));
			para.add(new BasicNameValuePair("pasgerseat_money", ckzwmoney2));
			para.add(new BasicNameValuePair("serial_num", serial_num));
			para.add(new BasicNameValuePair("idcard", idcard2));
			para.add(new BasicNameValuePair("transfer_date", transfer_date2));

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
				Toast.makeText(getApplicationContext(), "提交成功！", Toast.LENGTH_SHORT).show();
			    Intent it= new Intent();
				it.setClass(Third_freight.this,Third_myInsurance_freight.class);	
				startActivity(it);
				finish();
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
}


