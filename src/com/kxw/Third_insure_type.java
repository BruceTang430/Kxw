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

public class Third_insure_type extends Activity {
	private TextView kxw_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_insure_type);
		ActivityCollector.getInstance().addActivity(this);
		
		//���ñ�������
		Typeface face = Typeface.createFromAsset(getAssets(),"fonts/fzlxjt.ttf");
		kxw_title = (TextView) findViewById(R.id.txt_wybx);
		kxw_title.setTypeface(face);
		//�ֽ�֧����ť���
		Button button=(Button)findViewById(R.id.btn_ty);
		//���ð�ť��ɫ
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Third_insure_type.this, Third_insure_ty3.class);
				startActivity(intent);
			}		 	
		});
		//���εֿ۰�ť���
		Button button2=(Button)findViewById(R.id.btn_cy);
		//���ð�ť��ɫ
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFF4f94cd);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Third_insure_type.this, Third_insure_cy_type.class);
				startActivity(intent);
			}		 	
		});
	}
	//���ϽǷ�����һ��
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
