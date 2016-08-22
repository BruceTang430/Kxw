package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.igexin.sdk.PushManager;
import com.kxw.Ty_register.NewUser;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
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

public class Ty_txtydYl extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_tyd.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1, userid;
	String cid,ty_tel;
	TextView fhr3,fhrnum3,fhaddress3,xxaddress22,ddaddress3,ddxxaddress3,shr3,shrtel3,shraddress3,
	xxaddress33,hwname3,hwnum3,hwgg3,hwweight3,hwvol3,hwbz3,remark3,ty_ispay3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_txtydyl);
		ActivityCollector.getInstance().addActivity(this);

		//调用get()函数，把第一页编辑框内容写进预览界面
		Kxw_Application txtyd=((Kxw_Application)getApplicationContext());
		
		TextView fhr2=(TextView)findViewById(R.id.textView1);
		fhr2.setText("发货人：");
		fhr3=(TextView)findViewById(R.id.textView2);
		fhr3.setText(txtyd.gettxtyd_fhr());
		
		TextView fhrnum2=(TextView)findViewById(R.id.textView3);
		fhrnum2.setText("发货人电话：");
		fhrnum3=(TextView)findViewById(R.id.textView4);
		fhrnum3.setText(txtyd.gettxtyd_fhrnum());
		
		TextView fhaddress2=(TextView)findViewById(R.id.textView5);
		fhaddress2.setText("发货地址：");
		fhaddress3=(TextView)findViewById(R.id.textView6);
		fhaddress3.setText(txtyd.getprovince()+","+txtyd.getcity11()+","+txtyd.getcounty());
		
		TextView xxaddress2=(TextView)findViewById(R.id.textView7);
		xxaddress2.setText("详细地址：");
		xxaddress22=(TextView)findViewById(R.id.textView8);
		xxaddress22.setText(txtyd.getxxaddress());
		
		TextView ddaddress2=(TextView)findViewById(R.id.textView9);
		ddaddress2.setText("到达地：");
		ddaddress3=(TextView)findViewById(R.id.textView10);
		ddaddress3.setText(txtyd.getprovince2()+","+txtyd.getcity2()+","+txtyd.getcounty2());
		
		TextView ddxxaddress2=(TextView)findViewById(R.id.textView11);
		ddxxaddress2.setText("详细地址：");
		ddxxaddress3=(TextView)findViewById(R.id.textView12);
		ddxxaddress3.setText(txtyd.getxxaddress2());
		
		//第二页信息
		TextView shr2=(TextView)findViewById(R.id.textView13);
		shr2.setText("收货人：");
		shr3=(TextView)findViewById(R.id.textView14);
		shr3.setText(txtyd.gettxtyd_shr());
		
		TextView shrtel2=(TextView)findViewById(R.id.textView15);
		shrtel2.setText("收货人电话：");
		shrtel3=(TextView)findViewById(R.id.textView16);
		shrtel3.setText(txtyd.gettxtyd_shrtel());
		
		TextView shraddress2=(TextView)findViewById(R.id.textView17);
		shraddress2.setText("收货人地址：");
		shraddress3=(TextView)findViewById(R.id.textView18);
		shraddress3.setText(txtyd.getprovince3()+","+txtyd.getcity3()+","+txtyd.getcounty3());
		
		TextView xxaddress3=(TextView)findViewById(R.id.textView19);
		xxaddress3.setText("详细地址：");
		xxaddress33=(TextView)findViewById(R.id.textView20);
		xxaddress33.setText(txtyd.getxxaddress3());
		
		TextView hwname2=(TextView)findViewById(R.id.textView21);
		hwname2.setText("品名：");
		hwname3=(TextView)findViewById(R.id.textView22);
		hwname3.setText(txtyd.gettxtyd_hwname());
		
		TextView hwgg2=(TextView)findViewById(R.id.textView23);
		hwgg2.setText("规格：");
		hwgg3=(TextView)findViewById(R.id.textView24);
		hwgg3.setText(txtyd.gettxtyd_hwgg());
		
		TextView hwnum2=(TextView)findViewById(R.id.textView25);
		hwnum2.setText("数量(件)：");
		hwnum3=(TextView)findViewById(R.id.textView26);
		hwnum3.setText(txtyd.gettxtyd_hwnum());
		
		TextView hwweight2=(TextView)findViewById(R.id.textView27);
		hwweight2.setText("重量(吨)：");
		hwweight3=(TextView)findViewById(R.id.textView28);
		hwweight3.setText(txtyd.gettxtyd_hwweight());
		
		TextView hwvol2=(TextView)findViewById(R.id.textView29);
		hwvol2.setText("体积(m³)：");
		hwvol3=(TextView)findViewById(R.id.textView30);
		hwvol3.setText(txtyd.gettxtyd_hwvol());
		
		TextView hwbz2=(TextView)findViewById(R.id.textView31);
		hwbz2.setText("包装：");
		hwbz3=(TextView)findViewById(R.id.textView32);
		hwbz3.setText(txtyd.gettxtyd_hwbz());
		
		TextView remark2=(TextView)findViewById(R.id.textView33);
		remark2.setText("备注：");
		remark3=(TextView)findViewById(R.id.textView34);
		remark3.setText(txtyd.gettxtyd_remark());
		
		TextView ty_ispay2=(TextView)findViewById(R.id.textView35);
		ty_ispay2.setText("付运费方式");
		ty_ispay3=(TextView)findViewById(R.id.textView36);
		ty_ispay3.setText(txtyd.get_ty_ispay());
		
		//登陆时set
		userid=txtyd.get_ty_userid();
		ty_tel=txtyd.get_ty_tel();
		
		cid=PushManager.getInstance().getClientid(getApplicationContext());
		
		//提交按钮监听
		Button button=(Button)findViewById(R.id.btn_submit);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				new Ty_tyd().execute();
			}		
		});

	}
	
	//访问数据库
		class Ty_tyd extends AsyncTask<String, String, String> {

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				pDialog = new ProgressDialog(Ty_txtydYl.this); 
				pDialog.setMessage("提交中"); 
				pDialog.setIndeterminate(false); 
				pDialog.setCancelable(true); 
				pDialog.show(); 
			}
			
			@Override
			protected String doInBackground(String... args) {
				// TODO Auto-generated method stub

				String fhr_name=fhr3.getText().toString();
				String fhr_tel=fhrnum3.getText().toString();
				String fh_address=fhaddress3.getText().toString();
				String fh_xxaddress=xxaddress22.getText().toString();
				String dd_address=ddaddress3.getText().toString();
				String dd_xxaddress=ddxxaddress3.getText().toString();
				String shr_name=shr3.getText().toString();
				String shr_tel=shrtel3.getText().toString();
				String shr_address=shraddress3.getText().toString();
				String shr_xxaddress=xxaddress33.getText().toString();
				String hw_name=hwname3.getText().toString();
				String hw_gg=hwgg3.getText().toString();
				String hw_number=hwnum3.getText().toString();
				String hw_weight=hwweight3.getText().toString();
				String hw_vol=hwvol3.getText().toString();
				String hw_bz=hwbz3.getText().toString();
				String hw_remark=remark3.getText().toString();
				String ty_ispay=ty_ispay3.getText().toString();
				
				List<NameValuePair> para = new ArrayList<NameValuePair>(); 
				para.add(new BasicNameValuePair("userid", Integer.toString(userid)));
				para.add(new BasicNameValuePair("fhr_name", fhr_name)); 
				para.add(new BasicNameValuePair("fhr_tel", fhr_tel)); 
				para.add(new BasicNameValuePair("fh_address", fh_address));
				para.add(new BasicNameValuePair("fh_xxaddress", fh_xxaddress));
				para.add(new BasicNameValuePair("dd_address", dd_address));
				para.add(new BasicNameValuePair("dd_xxaddress", dd_xxaddress));
				para.add(new BasicNameValuePair("shr_name", shr_name));
				para.add(new BasicNameValuePair("shr_tel", shr_tel));
				para.add(new BasicNameValuePair("shr_address", shr_address));
				para.add(new BasicNameValuePair("shr_xxaddress", shr_xxaddress));
				para.add(new BasicNameValuePair("hw_name", hw_name));
				para.add(new BasicNameValuePair("hw_gg", hw_gg));
				para.add(new BasicNameValuePair("hw_number", hw_number));
				para.add(new BasicNameValuePair("hw_weight", hw_weight));
				para.add(new BasicNameValuePair("hw_vol", hw_vol));
				para.add(new BasicNameValuePair("hw_bz", hw_bz));
				para.add(new BasicNameValuePair("hw_remark", hw_remark));
				para.add(new BasicNameValuePair("cid", cid));
				para.add(new BasicNameValuePair("ty_tel", ty_tel));
				para.add(new BasicNameValuePair("ty_ispay", ty_ispay));
				// getting JSON Object 
				// Note that create product url accepts POST method 
				JSONObject json = jsonParser.makeHttpRequest(url_create_product, 
				"POST", para); 
				// check log cat fro response 
				Log.d("Create Response", json.toString()); 
				// check for success tag 
				try{ 
				success = json.getInt(TAG_SUCCESS); 
							
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
					Intent intent=new Intent();
				    intent.setClass(Ty_txtydYl.this, Ty_mainActivity.class);
				    startActivity(intent);
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
