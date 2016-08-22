package com.kxw;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Ty_txtyd3 extends Activity implements OnGetGeoCoderResultListener {
	RadioButton r,r2;
	String ty_ispay;
	
	String fhcity2,fhcounty2;
	double fh_lat,fh_lon;
	
	GeoCoder mSearch = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_txtyd3);
		ActivityCollector.getInstance().addActivity(this);
		
		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		
		Kxw_Application txtyd=((Kxw_Application)getApplicationContext());
		String geo_fh_city=txtyd.getcity11();
		String geo_fh_county=txtyd.getcounty()+"政府";
		
		// Geo搜索
		mSearch.geocode(new GeoCodeOption().city(geo_fh_city).address(geo_fh_county));	
		

		final RadioGroup pay=(RadioGroup)findViewById(R.id.radgroup);
		//final RadioGroup pay2=(RadioGroup)findViewById(R.id.radgroup2);
		//预览按钮监听
		Button button2=(Button)findViewById(R.id.btn_yltyd);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				String hwnum=((EditText)findViewById(R.id.edit_hwnum)).getText().toString();
				String hwweight=((EditText)findViewById(R.id.edit_hwweight)).getText().toString();
				String hwvol=((EditText)findViewById(R.id.edit_hwvol)).getText().toString();
				String hwbz=((EditText)findViewById(R.id.edit_hwbz)).getText().toString();
				String remark=((EditText)findViewById(R.id.edit_remark)).getText().toString();
				for(int i=0;i<pay.getChildCount();i++){
					r=(RadioButton)pay.getChildAt(i);
					if(r.isChecked()){
						ty_ispay=r.getText().toString();
						break;
					}
				}
				if(r.isChecked()&&!"".equals(hwbz)&&!"".equals(hwnum)&&!"".equals(hwweight)&&!"".equals(hwvol)){
				        Intent intent=new Intent();
				        //调用ApplicationContext，把编辑框内容写进set()函数
						Kxw_Application txtyd=((Kxw_Application)getApplicationContext());
						txtyd.settxtyd_hwnum(hwnum);
						txtyd.settxtyd_hwweight(hwweight);
						txtyd.settxtyd_hwvol(hwvol);
						txtyd.settxtyd_hwbz(hwbz);
						txtyd.settxtyd_remark(remark);
						txtyd.set_ty_ispay(ty_ispay);
						txtyd.set_fh_lat(fh_lat);
						txtyd.set_fh_lon(fh_lon);
						intent.setClass(Ty_txtyd3.this, Ty_txtydYl3.class);
						startActivity(intent);	
						finish();
				}else{
					Toast.makeText(Ty_txtyd3.this, "请将注册信息输入完整", Toast.LENGTH_LONG).show();
				}
			}			
		});
	}
	//actionBar
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
		fh_lat=result.getLocation().latitude;
		fh_lon=result.getLocation().longitude;

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
		// TODO Auto-generated method stub

	}

}
