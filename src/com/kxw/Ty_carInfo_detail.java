package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Ty_carInfo_detail extends Activity {
	TextView load_address,destination_address,car_type,car_number,car_length,car_weight,car_vol,
	go_time,time,tel,remark;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_carinfo_detail);
		ActivityCollector.getInstance().addActivity(this);

		Intent intent=getIntent();
	    Bundle bun = intent.getExtras();
		
		load_address=(TextView)findViewById(R.id.txt_load_address);
		load_address.setText(bun.getString("load_address"));
		
		destination_address=(TextView)findViewById(R.id.txt_destination_address);
		destination_address.setText(bun.getString("destination_address"));
		
		car_type=(TextView)findViewById(R.id.txt_car_type);
		car_type.setText(bun.getString("car_type"));
		
		car_number=(TextView)findViewById(R.id.txt_car_number);
		car_number.setText(bun.getString("car_number"));
		
		car_length=(TextView)findViewById(R.id.txt_car_length);
		car_length.setText(bun.getString("car_length"));
		
		car_weight=(TextView)findViewById(R.id.txt_car_weight);
		car_weight.setText(bun.getString("car_weight"));
		
		car_vol=(TextView)findViewById(R.id.txt_car_vol);
		car_vol.setText(bun.getString("car_vol"));

		go_time=(TextView)findViewById(R.id.txt_go_time);
		go_time.setText(bun.getString("go_time"));

		tel=(TextView)findViewById(R.id.txt_tel);
		tel.setText(bun.getString("tel"));

		remark=(TextView)findViewById(R.id.txt_remark);
		remark.setText(bun.getString("remark"));

		time=(TextView)findViewById(R.id.txt_time);
		time.setText(bun.getString("time"));
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

