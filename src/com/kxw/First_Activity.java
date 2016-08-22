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
		
		//���ñ�������
		Typeface face = Typeface.createFromAsset(getAssets(),"fonts/fzlxjt.ttf");
		kxw_title = (TextView) findViewById(R.id.kxw);
		kxw_title.setTypeface(face);
		//�����˽ӿ�
		Button button=(Button)findViewById(R.id.btn_wyfh);
		//�������˰�ť��ɫ
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		//���˰�ť������
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(First_Activity.this, Ty_loginActivity.class);
				startActivity(intent);
			}
			
		});
		//�����˽ӿ�
		Button button2=(Button)findViewById(R.id.btn_wylh);
		//���ó��˰�ť��ɫ
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFFe0eee0);
		//���˰�ť������
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(First_Activity.this, Cy_firstActivity.class);
				startActivity(intent);
			}
			
		});
		//���սӿ�
		Button button3=(Button)findViewById(R.id.btn_wybx);
		//���ð�ť��ɫ
		GradientDrawable myGrad3 = (GradientDrawable)button3.getBackground();
		myGrad3.setColor(0xFF636363);
		//���˰�ť������
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
