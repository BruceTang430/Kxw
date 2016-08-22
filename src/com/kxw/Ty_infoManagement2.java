package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.TextView;
import android.widget.Toast;

public class Ty_infoManagement2 extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_tyd_delete.php"; 
	private static String url_create_product2 = "http://120.27.26.219/kxw/android/ty_tyd_rechoose.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog,pDialog2; 
    int success=0,success2=0;
	JSONParser jsonParser = new JSONParser();
	JSONParser jsonParser2 = new JSONParser();
	String isinsure,pay,order,fbnum,hw_name,hw_gg,hw_number,hw_weight,hw_vol,hw_remark,hw_bz,fh_address,
	dd_address,fhr_name,fhr_tel,fh_xxaddress,dd_xxaddress,shr_name,shr_tel,shr_address,shr_xxaddress;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.ty_infomanagement2);
	    ActivityCollector.getInstance().addActivity(this);
	    
	    Intent intent=getIntent();
	    Bundle bun = intent.getExtras();
	    
	    TextView fbnum2=(TextView)findViewById(R.id.txt_fbnum);
	    fbnum2.setText(bun.getString("fbnum"));	    
	    TextView isorder2=(TextView)findViewById(R.id.txt_state);
	    order=bun.getString("isorder");
	    isorder2.setText(order);
	    TextView ispay2=(TextView)findViewById(R.id.txt_cost);
	    pay=bun.getString("ispay");	    
	    ispay2.setText(pay);
	    TextView carriage2=(TextView)findViewById(R.id.txt_carriage);
	    carriage2.setText(bun.getString("carriage"));
	    TextView cyusername2=(TextView)findViewById(R.id.txt_cyusername);
	    cyusername2.setText(bun.getString("cyusername"));
	    TextView hwname2=(TextView)findViewById(R.id.txt_hwname);
	    hwname2.setText(bun.getString("hw_name"));
	    TextView time2=(TextView)findViewById(R.id.txt_time);
	    time2.setText(bun.getString("time"));
	    TextView isinsure2=(TextView)findViewById(R.id.txt_isinsure);
	    isinsure=bun.getString("ty_isinsure");
	    isinsure2.setText(isinsure);
	    
	    TextView cy_tel2=(TextView)findViewById(R.id.txt_cy_tel);
	    cy_tel2.setText(bun.getString("cy_tel"));
	    
	    
	    hw_name= bun.getString("hw_name");
	    fh_address= bun.getString("fh_address");
        dd_address = bun.getString("dd_address");
        fhr_name = bun.getString("fhr_name");
        fhr_tel = bun.getString("fhr_tel");
        fh_xxaddress= bun.getString("fh_xxaddress");
        dd_xxaddress= bun.getString("dd_xxaddress");
        shr_name= bun.getString("shr_name");
        shr_tel= bun.getString("shr_tel");
        shr_address= bun.getString("shr_address");
        shr_xxaddress= bun.getString("shr_xxaddress");
        hw_gg= bun.getString("hw_gg");
        hw_number= bun.getString("hw_number");
        hw_weight= bun.getString("hw_weight");
        hw_vol= bun.getString("hw_vol");
        hw_bz= bun.getString("hw_bz");
        hw_remark= bun.getString("hw_remark");
        fbnum= bun.getString("fbnum");
	    
	    //"托运单详情"按钮监听
		Button button=(Button)findViewById(R.id.btn_tydxq);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent in=new Intent();
				in.setClass(Ty_infoManagement2.this, Ty_infoManagement_note.class);
				in.putExtra("hw_name", hw_name);
		        in.putExtra("fh_address", fh_address);
		        in.putExtra("dd_address", dd_address);
		        in.putExtra("fhr_name", fhr_name);
		        in.putExtra("fhr_tel", fhr_tel);
		        in.putExtra("fh_xxaddress", fh_xxaddress);
		        in.putExtra("dd_xxaddress", dd_xxaddress);
		        in.putExtra("shr_name", shr_name);
		        in.putExtra("shr_tel", shr_tel);
		        in.putExtra("shr_address", shr_address);
		        in.putExtra("shr_xxaddress", shr_xxaddress);
		        in.putExtra("hw_gg", hw_gg);
		        in.putExtra("hw_number", hw_number);
		        in.putExtra("hw_weight", hw_weight);
		        in.putExtra("hw_vol", hw_vol);
		        in.putExtra("hw_bz", hw_bz);
		        in.putExtra("hw_remark", hw_remark);
				startActivity(in);
			}
		});
		
		//"删除"按钮监听
		Button button2=(Button)findViewById(R.id.btn_gs);
		//设置按钮颜色
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFF4f94cd);
		button2.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(order.equals("已接单")){
					//button5.setVisibility(View.INVISIBLE);
					Toast.makeText(Ty_infoManagement2.this, "已接单，不能删除！", Toast.LENGTH_SHORT).show();
				}else {
				    AlertDialog alert =new AlertDialog.Builder(Ty_infoManagement2.this).create();
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
			}
		});
		
		//"重选承运人"按钮监听
		Button button4=(Button)findViewById(R.id.btn_cxcyr);
		//设置按钮颜色
		GradientDrawable myGrad4 = (GradientDrawable)button4.getBackground();
		myGrad4.setColor(0xFF4f94cd);	
		button4.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(order.equals("已接单")){
				    AlertDialog alert =new AlertDialog.Builder(Ty_infoManagement2.this).create();
				    alert.setTitle("系统提示:");
				    alert.setMessage("确认重选承运人?");
				    //添加"取消"按钮
				    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener(){
				    	@Override
				    	public void onClick(DialogInterface dialog,int which){
				    		Toast.makeText(Ty_infoManagement2.this, "已取消", Toast.LENGTH_SHORT).show();
				    	}
				    });
				    //添加"确定"按钮
				    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
				    	@Override
				    	public void onClick(DialogInterface dialog,int which){
				    		new ReChoose().execute();
				    	}
				    });
				    alert.show();
				}else Toast.makeText(Ty_infoManagement2.this, "未接单", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		//"去缴费"按钮监听
		Button button5=(Button)findViewById(R.id.btn_qjf);
		//设置按钮颜色
		GradientDrawable myGrad5 = (GradientDrawable)button5.getBackground();
		myGrad5.setColor(0xFF4f94cd);	
		button5.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
//				if(isinsure.equals("已保险")){
//					//button5.setVisibility(View.INVISIBLE);
//					Toast.makeText(Ty_infoManagement2.this, "已保险！", Toast.LENGTH_SHORT).show();
//				}else if(isinsure.equals("未保险")){
				    Intent intent=new Intent();
				    intent.setClass(Ty_infoManagement2.this, Ty_insure_clause.class);
				    intent.putExtra("fh_address", fh_address);
			        intent.putExtra("dd_address", dd_address);
			        intent.putExtra("fbnum", fbnum);
			        intent.putExtra("hw_name", hw_name);
			        intent.putExtra("hw_weight", hw_weight);
				    startActivity(intent);
//				}
			}
		});
		
		//设置按钮可见性
//		if(order.equals("未接单")){
//			//button3.setVisibility(View.INVISIBLE);
//			button4.setVisibility(View.INVISIBLE);
//		}
//		if(pay.equals("已缴费")){
//			button5.setVisibility(View.INVISIBLE);
//		}
	    
	}
	//删除托运单
	class Delete extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Ty_infoManagement2.this); 
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
				it.setClass(Ty_infoManagement2.this,Ty_infoManagement.class);
				startActivity(it);
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
	//重选承运人
	class ReChoose extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog2 = new ProgressDialog(Ty_infoManagement2.this); 
			pDialog2.setMessage("Loging.."); 
			pDialog2.setIndeterminate(false); 
			pDialog2.setCancelable(true); 
			pDialog2.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub	
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("fbnum", fbnum)); 
			// getting JSON Object 
			// Note that create product url accepts POST method 
			JSONObject json = jsonParser2.makeHttpRequest(url_create_product2, 
			"POST", para); 
			// check log cat fro response 
			Log.d("Create Response", json.toString()); 
			// check for success tag 
			try{ 
			success2 = json.getInt(TAG_SUCCESS); 
			if(success2 == 1) {
				Intent it= new Intent();							
				it.setClass(Ty_infoManagement2.this,Ty_infoManagement.class);
				startActivity(it);
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
			if(success2==1) 
				Toast.makeText(getApplicationContext(), "操作成功！", Toast.LENGTH_SHORT).show();
			pDialog2.dismiss();
		}
	
	}	
	//左上角返回
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

