package com.kxw;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.kxw.Third_insure_cy_cash.Third;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Third_insure_cy_time44 extends Activity implements OnGetGeoCoderResultListener {
	GeoCoder mSearch = null;
	double dd_lat,dd_lon;
	String fhaddress,ddaddress,ddcity,ddcounty,ddprovince;
	EditText insured_tel,insured_address,insured_name,fhorg,shorg,cyorg,cyaddr,
	goods_name,goods_weight,carnum,driver_name,driver_tel,driver_license,goods_value2,
	fhtel,shtel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_insure_cy_time44);
		ActivityCollector.getInstance().addActivity(this);
		
		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		
		Intent it=getIntent();
		Bundle bun = it.getExtras();
		fhaddress=bun.getString("fhaddress");
		ddaddress=bun.getString("ddaddress");
		ddcity=bun.getString("ddcity");
		ddcounty=bun.getString("ddcounty");
		ddprovince=bun.getString("ddprovince");
		
		fhorg=(EditText)findViewById(R.id.edit_fhorg);
		shorg=(EditText)findViewById(R.id.edit_shorg);
		insured_tel=(EditText)findViewById(R.id.edit_insured_tel);
		insured_address=(EditText)findViewById(R.id.edit_insured_address);
		insured_name=(EditText)findViewById(R.id.edit_insured_name);
		goods_name=(EditText)findViewById(R.id.edit_goods_name);
		goods_weight=(EditText)findViewById(R.id.edit_goods_weight);
		carnum=(EditText)findViewById(R.id.edit_carnum);
		driver_name=(EditText)findViewById(R.id.edit_driver_name);
		driver_tel=(EditText)findViewById(R.id.edit_driver_tel);
		driver_license=(EditText)findViewById(R.id.edit_driver_license);
		goods_value2=(EditText)findViewById(R.id.edit_goods_value);
		cyorg=(EditText)findViewById(R.id.edit_cyorg);
		cyaddr=(EditText)findViewById(R.id.edit_cyaddr);
		fhtel=(EditText)findViewById(R.id.edit_fhtel);
		shtel=(EditText)findViewById(R.id.edit_shtel);
		
		// Geo搜索
		mSearch.geocode(new GeoCodeOption().city(ddcity).address(ddcounty+"政府"));

		//下一页按钮监听
		Button button2=(Button)findViewById(R.id.btn_next);
		//设置下一页按钮颜色
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFF4f94cd);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				String insured_tel2=insured_tel.getText().toString();
				String insured_address2=insured_address.getText().toString();
				String insured_name2=insured_name.getText().toString();
				String carnum2=carnum.getText().toString();
				String driver_name2=driver_name.getText().toString();
				String driver_tel2=driver_tel.getText().toString();
				String driver_license2=driver_license.getText().toString();
				String goods_name2=goods_name.getText().toString();
				String goods_weight2=goods_weight.getText().toString();
				String fhorg2=fhorg.getText().toString();
				String shorg2=shorg.getText().toString();
				String goods_value3=goods_value2.getText().toString();
				String cyorg2=cyorg.getText().toString();
				String cyaddr2=cyaddr.getText().toString();
				String fhtel2=fhtel.getText().toString();
				String shtel2=shtel.getText().toString();

				if(fhtel.length()!=0&&shtel.length()!=0&&cyorg.length()!=0&&cyaddr.length()!=0&&fhorg.length()!=0&&shorg.length()!=0&&goods_value2.length()!=0&&insured_tel.length()!=0&&insured_address.length()!=0
						&&goods_name.length()!=0&&goods_weight.length()!=0&&carnum.length()!=0&&driver_name.length()!=0&&driver_tel.length()!=0&&driver_license.length()!=0){
					if(!isMobileNO(insured_tel2)){
  					    Toast.makeText(Third_insure_cy_time44.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_insured_tel)).setText("");
					    ((EditText)findViewById(R.id.edit_insured_tel)).requestFocus();	
					}else if(!isMobileNO(driver_tel2)){
  					    Toast.makeText(Third_insure_cy_time44.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_driver_tel)).setText("");
					    ((EditText)findViewById(R.id.edit_driver_tel)).requestFocus();	
					}else {
						Kxw_Application txtyd=((Kxw_Application)getApplicationContext());
					       txtyd.set_third_dd_lat(dd_lat);
					       txtyd.set_third_dd_lon(dd_lon);
					    Intent intent=new Intent();
	  					intent.setClass(Third_insure_cy_time44.this, Third_insure_cy_time555.class);
	  					intent.putExtra("ddaddress", ddprovince+","+ddcity+","+ddcounty);
	  					intent.putExtra("fhaddress", fhaddress);
	  					intent.putExtra("ddcity", ddcity);
	  					intent.putExtra("ddcounty", ddcounty);
	  					intent.putExtra("ddprovince", ddprovince);
	  					intent.putExtra("insured_name", insured_name2);
	  					intent.putExtra("insured_tel", insured_tel2);
	  					intent.putExtra("insured_address", insured_address2);
	  					intent.putExtra("carnum", carnum2);
	  					intent.putExtra("goods_name", goods_name2);
	  					intent.putExtra("goods_weight", goods_weight2);
	  					intent.putExtra("driver_name", driver_name2);
	  					intent.putExtra("driver_tel", driver_tel2);
	  					intent.putExtra("driver_license", driver_license2);
	  					intent.putExtra("fhorg", fhorg2);
	  					intent.putExtra("shorg", shorg2);
	  					intent.putExtra("cyorg", cyorg2);
	  					intent.putExtra("cyaddr", cyaddr2);
	  					intent.putExtra("fhtel", fhtel2);
	  					intent.putExtra("shtel", shtel2);
	  					intent.putExtra("goods_value", goods_value3);
	  					startActivity(intent);	
	  					finish();
				    }
				}else Toast.makeText(Third_insure_cy_time44.this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();						
  				}				
				
					
		});
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
	protected void onDestroy() {
		mSearch.destroy();
		super.onDestroy();
	}
	
	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		// TODO Auto-generated method stub
		dd_lat=result.getLocation().latitude;
		dd_lon=result.getLocation().longitude;

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
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


}
