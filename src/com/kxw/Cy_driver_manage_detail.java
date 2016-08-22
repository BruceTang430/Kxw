package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Ty_infoManagement2.Delete;

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

public class Cy_driver_manage_detail extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_driver_carinfo_delete.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;
	JSONParser jsonParser = new JSONParser();
	TextView load_address,destination_address,car_type,car_number,car_length,car_weight,car_vol,
	go_time,time,tel,remark,fbnum2;
	String fbnum;
	Bundle bun;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_driver_manage_detail);
		ActivityCollector.getInstance().addActivity(this);

		Intent intent=getIntent();
	    bun = intent.getExtras();

		fbnum2=(TextView)findViewById(R.id.txt_fbnum);
		fbnum=bun.getString("fbnum");
		fbnum2.setText(fbnum);
		
		load_address=(TextView)findViewById(R.id.txt_load_address);
		load_address.setText(bun.getString("load_address"));
		
		destination_address=(TextView)findViewById(R.id.txt_destination_address);
		destination_address.setText(bun.getString("destination_address"));
		
		car_type=(TextView)findViewById(R.id.txt_car_type);
		car_type.setText(bun.getString("car_type"));
		
		car_number=(TextView)findViewById(R.id.txt_car_number);
		car_number.setText(bun.getString("car_number"));
		
		car_length=(TextView)findViewById(R.id.txt_car_length);
		car_length.setText(bun.getString("car_length"));
		
		car_weight=(TextView)findViewById(R.id.txt_car_weight);
		car_weight.setText(bun.getString("car_weight"));
		
		car_vol=(TextView)findViewById(R.id.txt_car_vol);
		car_vol.setText(bun.getString("car_vol"));

		go_time=(TextView)findViewById(R.id.txt_go_time);
		go_time.setText(bun.getString("go_time"));

		tel=(TextView)findViewById(R.id.txt_tel);
		tel.setText(bun.getString("tel"));

		remark=(TextView)findViewById(R.id.txt_remark);
		remark.setText(bun.getString("remark"));

		time=(TextView)findViewById(R.id.txt_time);
		time.setText(bun.getString("time"));
		
		//"修改信息"按钮监听
		Button c=(Button)findViewById(R.id.btn_change);
		//设置按钮颜色
		GradientDrawable myGrad5 = (GradientDrawable)c.getBackground();
		myGrad5.setColor(0xFF4f94cd);	
		c.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent in= new Intent();							
				in.setClass(getApplication(),Cy_driver_manage_change.class);
		        in.putExtra("car_number", bun.getString("car_number"));
		        in.putExtra("car_length", bun.getString("car_length"));
		        in.putExtra("car_weight", bun.getString("car_weight"));
		        in.putExtra("car_vol", bun.getString("car_vol"));
		        in.putExtra("tel", bun.getString("tel"));
		        in.putExtra("go_time", bun.getString("go_time"));
		        in.putExtra("remark", bun.getString("remark"));
		        in.putExtra("fbnum", bun.getString("fbnum"));
				startActivity(in);
				finish();

			}
		});
		
		//"删除信息"按钮监听
		Button e=(Button)findViewById(R.id.btn_delete);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)e.getBackground();
		myGrad.setColor(0xFF4f94cd);	
		e.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
			    AlertDialog alert =new AlertDialog.Builder(Cy_driver_manage_detail.this).create();
			    alert.setTitle("系统提示:");
			    alert.setMessage("确认删除?");
			    //添加"取消"按钮
			    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener(){
			    	@Override
			    	public void onClick(DialogInterface dialog,int which){
			    	}
			    });
			    //添加"确定"按钮
			    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
			    	@Override
			    	public void onClick(DialogInterface dialog,int which){
			    		new Delete().execute();
			    	}
			    });
			    alert.show();

			}
		});
	}
	//删除托运单
	class Delete extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Cy_driver_manage_detail.this); 
			pDialog.setMessage("Loging.."); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub	
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("fbnum", fbnum)); 
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
				it.setClass(Cy_driver_manage_detail.this,Cy_driver_manage.class);
				startActivity(it);
				finish();
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
			if(success==1) 
				Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
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


