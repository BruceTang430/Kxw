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


public class Ty_insure_clause extends Activity {
	String fbnum,fh_address,dd_address,hw_name,hw_weight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clause);
		ActivityCollector.getInstance().addActivity(this);

		Intent intent=getIntent();
	    Bundle bun = intent.getExtras();
	    
	    fbnum=bun.getString("fbnum");
	    fh_address=bun.getString("fh_address");
	    dd_address=bun.getString("dd_address");
	    hw_name=bun.getString("hw_name");
	    hw_weight=bun.getString("hw_weight"); 
			Button button=(Button)findViewById(R.id.btn_back);
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					Intent intent=new Intent();
					intent.setClass(Ty_insure_clause.this, Ty_infoManagement.class);
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
						intent.setClass(Ty_insure_clause.this, Ty_txbxd_cash.class);
					    intent.putExtra("fh_address", fh_address);
				        intent.putExtra("dd_address", dd_address);
				        intent.putExtra("fbnum", fbnum);
				        intent.putExtra("hw_name", hw_name);
				        intent.putExtra("hw_weight", hw_weight);
						startActivity(intent);
						finish();
					}else Toast.makeText(Ty_insure_clause.this, "未同意《投保协议》！", Toast.LENGTH_SHORT).show();
					
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

