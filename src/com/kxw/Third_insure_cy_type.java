package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Third_insure_cy_type extends Activity {
	private TextView kxw_title;
	int usertype;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_company_wybx);
		ActivityCollector.getInstance().addActivity(this);
		
		//���ñ�������
		Typeface face = Typeface.createFromAsset(getAssets(),"fonts/fzlxjt.ttf");
		kxw_title = (TextView) findViewById(R.id.txt_wybx);
		kxw_title.setTypeface(face);
		//�ֽ�֧����ť���
		Button button=(Button)findViewById(R.id.btn_cash);
		//���ð�ť��ɫ
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Third_insure_cy_type.this, Third_insure_cy_cash3.class);
				startActivity(intent);
			}		 	
		});
		Kxw_Application id=((Kxw_Application)getApplicationContext());
		usertype=id.get_usertype();
		Log.d("usertype", String.valueOf(usertype));
		//���εֿ۰�ť���
		Button button2=(Button)findViewById(R.id.btn_carnum);
		//���ð�ť��ɫ
		GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
		myGrad2.setColor(0xFF4f94cd);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				if(String.valueOf(usertype).equals("3")){
					Intent intent=new Intent();
					intent.setClass(Third_insure_cy_type.this, Third_insure_cy_time11.class);
					startActivity(intent);					
				}else Toast.makeText(getApplicationContext(), "��������˾�û����ܽ��룡", Toast.LENGTH_SHORT).show();

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

