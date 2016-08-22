package com.kxw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.igexin.sdk.PushManager;

public class First_Activity extends Activity {
	private TextView kxw_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_activity);
		ActivityCollector.getInstance().addActivity(this);
		
		PushManager.getInstance().initialize(this.getApplicationContext());
		SDKInitializer.initialize(getApplicationContext());
		
		//设置标题字体
		Typeface face = Typeface.createFromAsset(getAssets(),"fonts/fzlxjt.ttf");
		kxw_title = (TextView) findViewById(R.id.kxw);
		kxw_title.setTypeface(face);
		//托运人接口
		Button button=(Button)findViewById(R.id.btn_wyfh);
		//设置托运按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		//托运按钮监听器
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(First_Activity.this, Ty_loginActivity.class);
				startActivity(intent);
			}
			
		});
		//承运人接口
		Button button2=(Button)findViewById(R.id.btn_wylh);
		//设置承运按钮颜色
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFFe0eee0);
		//承运按钮监听器
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(First_Activity.this, Cy_firstActivity.class);
				startActivity(intent);
			}
			
		});
		//保险接口
		Button button3=(Button)findViewById(R.id.btn_wybx);
		//设置按钮颜色
		GradientDrawable myGrad3 = (GradientDrawable)button3.getBackground();
		myGrad3.setColor(0xFF636363);
		//承运按钮监听器
		button3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(First_Activity.this, Third_login.class);
				startActivity(intent);
			}
			
		});		
	}

}
