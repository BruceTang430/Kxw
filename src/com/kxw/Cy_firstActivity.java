package com.kxw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Cy_firstActivity extends Activity {
	private TextView kxw_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_firstactivity);
		ActivityCollector.getInstance().addActivity(this);
		//���ñ�������
		Typeface face = Typeface.createFromAsset(getAssets(),"fonts/fzlxjt.ttf");
		kxw_title = (TextView) findViewById(R.id.kxw);
		kxw_title.setTypeface(face);
		//˾�����
		Button button=(Button)findViewById(R.id.btn_sjrk);
		//���ð�ť��ɫ
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		//˾����ť������
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Cy_firstActivity.this, Cy_driver_login.class);
				startActivity(intent);
				finish();
			}
			
		});
		//��˾���
		Button button2=(Button)findViewById(R.id.btn_gsrk);
		//���ð�ť��ɫ
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFFe0eee0);
		//��˾��ť������
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Cy_firstActivity.this, Cy_company_login.class);
				startActivity(intent);
				finish();
			}
			
		});
	}

}
