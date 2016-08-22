package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Ty_pay_offline extends Activity {
	String tele="18981992918";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_pay_offline);
		ActivityCollector.getInstance().addActivity(this);
		
		Button tel=(Button)findViewById(R.id.btn_tel);
		tel.setText(tele);
		tel.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + tele));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				finish();
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
