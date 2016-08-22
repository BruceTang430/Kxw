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

public class Cy_driver_carinfo extends Activity {
	private TextView kxw_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_driver_carinfo);
		ActivityCollector.getInstance().addActivity(this);
		
		//设置标题字体
		Typeface face = Typeface.createFromAsset(getAssets(),"fonts/fzlxjt.ttf");
		kxw_title = (TextView) findViewById(R.id.txt_wybx);
		kxw_title.setTypeface(face);
		//发布信息按钮监控
		Button button=(Button)findViewById(R.id.btn_publish);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Cy_driver_carinfo.this, Cy_driver_publish.class);
				startActivity(intent);
			}		 	
		});
		//信息管理按钮监控
		Button button2=(Button)findViewById(R.id.btn_manage);
		//设置按钮颜色
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFF4f94cd);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Cy_driver_carinfo.this, Cy_driver_manage.class);
				startActivity(intent);
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
}

