package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Ty_set_myInfo extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_set_myinfo);
		ActivityCollector.getInstance().addActivity(this);

		 Kxw_Application ty=((Kxw_Application)getApplicationContext());
		 String username=ty.get_ty_login_username();
		 String tel=ty.get_ty_tel();
		 String truename=ty.get_ty_truename();
		 String idcard=ty.get_ty_idcard();
		 String insure_address=ty.get_ty_insure_address();
		 int userid=ty.get_ty_userid();
		 
		TextView username2=(TextView)findViewById(R.id.txt_username);
		username2.setText(username);
		TextView userid2=(TextView)findViewById(R.id.txt_userid);
		userid2.setText(String.valueOf(userid));
		TextView tel2=(TextView)findViewById(R.id.txt_tel);
		tel2.setText(tel);
		TextView truename2=(TextView)findViewById(R.id.txt_truename);
		truename2.setText(truename);
		TextView idcard2=(TextView)findViewById(R.id.txt_idcard);
		idcard2.setText(idcard);
		TextView insure_address2=(TextView)findViewById(R.id.txt_insure_address);
		insure_address2.setText(insure_address);
	}

	//×óÉÏ½Ç·µ»Ø
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

