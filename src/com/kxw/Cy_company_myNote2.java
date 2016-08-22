package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_driver_myNote2.Delete;

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

public class Cy_company_myNote2 extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_tyd_delete.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;
	JSONParser jsonParser = new JSONParser();
	String pay,cy_isinsure,ty_userid,ty_ispay,carriage,isgo,order,fbnum,hw_name,hw_gg,hw_number,hw_weight,
	hw_vol,hw_remark,hw_bz,fh_address,dd_address,fhr_name,fhr_tel,fh_xxaddress,dd_xxaddress,shr_name,shr_tel,
	shr_address,shr_xxaddress,coe;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.cy_mynote2);
	    ActivityCollector.getInstance().addActivity(this);
 
	    Intent intent=getIntent();
	    Bundle bun = intent.getExtras();
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
        fbnum=bun.getString("fbnum");
        order=bun.getString("isorder");
        ty_userid=bun.getString("ty_userid");
        carriage=bun.getString("carriage");
        ty_ispay=bun.getString("ty_ispay");
        cy_isinsure=bun.getString("cy_isinsure");
        coe=bun.getString("coe");
	    
	    TextView hwname2=(TextView)findViewById(R.id.txt_hwname);
	    hwname2.setText(hw_name);
	    
	    TextView fhaddress2=(TextView)findViewById(R.id.txt_fhaddress);
	    fhaddress2.setText(fh_address);
	    
	    TextView ddaddress2=(TextView)findViewById(R.id.txt_ddaddress);
	    ddaddress2.setText(dd_address);
	    
	    TextView ty_userid2=(TextView)findViewById(R.id.txt_ty_userid);
	    ty_userid2.setText(ty_userid);
	    
	    TextView ispay2=(TextView)findViewById(R.id.txt_ty_ispay);	    
	    ispay2.setText(ty_ispay);
	    
	    TextView carriage2=(TextView)findViewById(R.id.txt_carriage);
	    carriage2.setText(carriage);
	    
	    TextView cy_isinsure2=(TextView)findViewById(R.id.txt_cy_isinsure);
	    cy_isinsure2.setText(cy_isinsure);
	    
	    
	    Kxw_Application tyd=((Kxw_Application)getApplicationContext());
		tyd.set_tyd_fh_address(fh_address);
		tyd.set_tyd_dd_address(dd_address);
		tyd.set_tyd_fbnum(fbnum);
		tyd.set_coe(Double.valueOf(coe));
		tyd.set_tyd_hw_name(hw_name);
		tyd.set_tyd_hw_weight(hw_weight);


	    
	    //"托运单详情"按钮监听
		Button button=(Button)findViewById(R.id.btn_wdtyd);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent in=new Intent();
				in.setClass(Cy_company_myNote2.this, Cy_company_myNote_note.class);
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
		Button button2=(Button)findViewById(R.id.btn_delete);
		//设置按钮颜色
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFF4f94cd);
		button2.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				if(cy_isinsure.equals("已保险")){
					//button5.setVisibility(View.INVISIBLE);
					Toast.makeText(Cy_company_myNote2.this, "已保险，不能取消！", Toast.LENGTH_SHORT).show();
				}else {
				    AlertDialog alert =new AlertDialog.Builder(Cy_company_myNote2.this).create();
				    alert.setTitle("系统提示:");
				    alert.setMessage("确认取消?");
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
		
		//"买保险"按钮监听
		final Button button3=(Button)findViewById(R.id.btn_insure);
		//设置按钮颜色
		GradientDrawable myGrad3 = (GradientDrawable)button3.getBackground();
		myGrad3.setColor(0xFF4f94cd);	
		button3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
//				if(cy_isinsure.equals("已保险")){
//					//button5.setVisibility(View.INVISIBLE);
//					Toast.makeText(Cy_company_myNote2.this, "已保险！", Toast.LENGTH_SHORT).show();
//				}else if(cy_isinsure.equals("未保险")){
				Intent intent=new Intent();
				intent.setClass(Cy_company_myNote2.this, Cy_company_clause.class);
				    startActivity(intent);
//				}
			}
		});
		//"上传回单"按钮监听
		final Button button4=(Button)findViewById(R.id.btn_upload);
		//设置按钮颜色
		GradientDrawable myGrad4 = (GradientDrawable)button4.getBackground();
		myGrad4.setColor(0xFF4f94cd);	
		button4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Cy_company_myNote2.this, Cy_uploadNote.class);
				startActivity(intent);
			}
		});	

	    
	}
	//删除托运单
	class Delete extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Cy_company_myNote2.this); 
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
				it.setClass(Cy_company_myNote2.this,Cy_company_myNote.class);
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
