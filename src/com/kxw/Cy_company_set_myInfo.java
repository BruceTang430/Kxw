package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Cy_company_set_myInfo extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_company_set_myinfo);
		ActivityCollector.getInstance().addActivity(this);

		 Kxw_Application cy=((Kxw_Application)getApplicationContext());
		 String username=cy.get_cy_company_username();
		 String tel=cy.get_cy_company_tel();
		 String company_name=cy.get_cy_company_name();
		 String company_address=cy.get_cy_company_address();
		 int userid=cy.get_cy_company_userid();
		 String company_xxaddress=cy.get_cy_company_xxaddress();
		 String company_creditnum=cy.get_cy_company_creditnum();
		 
		TextView username2=(TextView)findViewById(R.id.txt_username);
		username2.setText(username);
		TextView userid2=(TextView)findViewById(R.id.txt_userid);
		userid2.setText(String.valueOf(userid));
		TextView tel2=(TextView)findViewById(R.id.txt_tel);
		tel2.setText(tel);
		TextView company_name2=(TextView)findViewById(R.id.txt_company_name);
		company_name2.setText(company_name);
		TextView company_address2=(TextView)findViewById(R.id.txt_address);
		company_address2.setText(company_address);
		TextView company_xxaddress2=(TextView)findViewById(R.id.txt_xxaddress);
		company_xxaddress2.setText(company_xxaddress);
		TextView company_creditnum2=(TextView)findViewById(R.id.txt_creditnum);
		company_creditnum2.setText(company_creditnum);

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


