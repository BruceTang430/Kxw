package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Ty_infoManagement_note extends Activity {
	private TextView kxw_title;
	TextView fhr3,fhrnum3,fhaddress3,xxaddress22,ddaddress3,ddxxaddress3,shr3,shrtel3,shraddress3,xxaddress33,hwname3,hwnum3,hwgg3,hwweight3,hwvol3,hwbz3,remark3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_infomanagement_note);

		Intent intent=getIntent();
	    Bundle bun = intent.getExtras();
		
		fhr3=(TextView)findViewById(R.id.textView2);
		fhr3.setText(bun.getString("fhr_name"));
		
		fhrnum3=(TextView)findViewById(R.id.textView4);
		fhrnum3.setText(bun.getString("fhr_tel"));
		
		fhaddress3=(TextView)findViewById(R.id.textView6);
		fhaddress3.setText(bun.getString("fh_address"));
		
		xxaddress22=(TextView)findViewById(R.id.textView8);
		xxaddress22.setText(bun.getString("fh_xxaddress"));
		
		ddaddress3=(TextView)findViewById(R.id.textView10);
		ddaddress3.setText(bun.getString("dd_address"));
		
		ddxxaddress3=(TextView)findViewById(R.id.textView12);
		ddxxaddress3.setText(bun.getString("dd_xxaddress"));
		
		shr3=(TextView)findViewById(R.id.textView14);
		shr3.setText(bun.getString("shr_name"));

		shrtel3=(TextView)findViewById(R.id.textView16);
		shrtel3.setText(bun.getString("shr_tel"));

		shraddress3=(TextView)findViewById(R.id.textView18);
		shraddress3.setText(bun.getString("shr_address"));

		xxaddress33=(TextView)findViewById(R.id.textView20);
		xxaddress33.setText(bun.getString("shr_xxaddress"));

		hwname3=(TextView)findViewById(R.id.textView22);
		hwname3.setText(bun.getString("hw_name"));

		hwgg3=(TextView)findViewById(R.id.textView24);
		hwgg3.setText(bun.getString("hw_gg"));

		hwnum3=(TextView)findViewById(R.id.textView26);
		hwnum3.setText(bun.getString("hw_number"));

		hwweight3=(TextView)findViewById(R.id.textView28);
		hwweight3.setText(bun.getString("hw_weight"));

		hwvol3=(TextView)findViewById(R.id.textView30);
		hwvol3.setText(bun.getString("hw_vol"));

		hwbz3=(TextView)findViewById(R.id.textView32);
		hwbz3.setText(bun.getString("hw_bz"));

		remark3=(TextView)findViewById(R.id.textView34);
		remark3.setText(bun.getString("hw_remark"));
		
	    //"返回上一页"按钮监听
//		Button button=(Button)findViewById(R.id.btn_pre);
//		//设置按钮颜色
//		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
//		myGrad.setColor(0xFF66ccff);
//		button.setOnClickListener(new OnClickListener(){
//			@Override
//			public void onClick(View v){
//				Intent intent=new Intent();
//				intent.setClass(Ty_fbxxgl_tyd.this, Ty_fbxxgl2.class);
//				startActivity(intent);
//			}
//		});
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
