package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Cy_driver_set_myInfo extends Activity {
	int userid;
	String driver_tel,username,driver_name,company_address,company_xxaddress,car_num,company_name,
	       car_weight,car_vol,car_type,driver_idcard,driver_bankcard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_driver_set_myinfo);
		ActivityCollector.getInstance().addActivity(this);

		 Kxw_Application cy=((Kxw_Application)getApplicationContext());
		 username=cy.get_cy_driver_username();
		 company_name=cy.get_cy_driver_company();
		 company_address=cy.get_cy_driver_address();
		 company_xxaddress=cy.get_cy_driver_xxaddress();
		 driver_name=cy.get_cy_driver_name();
		 userid=cy.get_cy_driver_userid();
		 driver_tel=cy.get_cy_driver_tel();
		 driver_idcard=cy.get_cy_driver_idcard();
		 driver_bankcard=cy.get_cy_driver_bankcard();
		 car_num=cy.get_driver_carnum();
		 car_weight=cy.get_cy_driver_weight();
		 car_vol=cy.get_cy_driver_vol();
		 car_type=cy.get_cy_driver_cartype();
		 
		TextView username2=(TextView)findViewById(R.id.txt_username);
		username2.setText(username);
		TextView userid2=(TextView)findViewById(R.id.txt_userid);
		userid2.setText(String.valueOf(userid));
		TextView tel2=(TextView)findViewById(R.id.txt_driver_tel);
		tel2.setText(driver_tel);
		TextView driver_name2=(TextView)findViewById(R.id.txt_driver_name);
		driver_name2.setText(driver_name);
		TextView driver_idcard2=(TextView)findViewById(R.id.txt_driver_idcard);
		driver_idcard2.setText(driver_idcard);
		TextView driver_bankcard2=(TextView)findViewById(R.id.txt_driver_bankcard);
		driver_bankcard2.setText(driver_bankcard);
		TextView company_name2=(TextView)findViewById(R.id.txt_company_name);
		company_name2.setText(company_name);
		TextView company_address2=(TextView)findViewById(R.id.txt_company_address);
		company_address2.setText(company_address);
		TextView company_xxaddress2=(TextView)findViewById(R.id.txt_company_xxaddress);
		company_xxaddress2.setText(company_xxaddress);
		TextView car_num2=(TextView)findViewById(R.id.txt_car_num);
		car_num2.setText(car_num);
		TextView car_type2=(TextView)findViewById(R.id.txt_car_type);
		car_type2.setText(car_type);
		TextView car_weight2=(TextView)findViewById(R.id.txt_car_weight);
		car_weight2.setText(car_weight);
		TextView car_vol2=(TextView)findViewById(R.id.txt_car_vol);
		car_vol2.setText(car_vol);

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



