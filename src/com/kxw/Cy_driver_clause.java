package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class Cy_driver_clause extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clause);
		ActivityCollector.getInstance().addActivity(this);

			Button button=(Button)findViewById(R.id.btn_back);
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					Intent intent=new Intent();
					intent.setClass(Cy_driver_clause.this, Cy_driver_myNote.class);
					startActivity(intent);
					finish();
					}			
			});
			Button button2=(Button)findViewById(R.id.btn_next);
			button2.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					RadioButton r=(RadioButton)findViewById(R.id.radbtn_agree);
					if(r.isChecked()){
						Intent intent=new Intent();
						intent.setClass(Cy_driver_clause.this, Cy_driver_txbxd_cash.class);
						startActivity(intent);
						finish();
					}else Toast.makeText(Cy_driver_clause.this, "未同意《投保协议》！", Toast.LENGTH_SHORT).show();
					
					}			
			});
	}


	
	//左上角返回
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


