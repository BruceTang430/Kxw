package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Cy_driver_set extends Activity {
	double balance;int userid,isidentify;
	String driver_tel,username,driver_name,company_address,company_xxaddress,car_num,company_name,
	       car_weight,car_vol,car_type,driver_idcard,driver_bankcard;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_set);
		ActivityCollector.getInstance().addActivity(this);
		
		 Kxw_Application cy=((Kxw_Application)getApplicationContext());
		 username=cy.get_cy_driver_username();
		 balance=cy.get_cy_driver_balance();
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
		 isidentify=cy.get_driver_isidentify();
		 
			//个人资料按钮监听
			Button button=(Button)findViewById(R.id.btn_myInfo);
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					Intent intent=new Intent();
					intent.setClass(Cy_driver_set.this, Cy_driver_set_myInfo.class);
					startActivity(intent);
					}			
			});
			
			//实名认证按钮监听
			Button button2=(Button)findViewById(R.id.btn_modInfo);
			button2.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					if(isidentify==1){
						Toast.makeText(getApplicationContext(), "已认证！", Toast.LENGTH_SHORT).show();
					}else{
						AlertDialog alert=new AlertDialog.Builder(Cy_driver_set.this).create();
					    alert.setTitle("系统提示:");
					    alert.setMessage("认证需驾驶证、行驶证、身份证照片");
					    //添加"确定"按钮
					    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定进入", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
					    		Intent intent=new Intent();
								intent.setClass(Cy_driver_set.this, Cy_driver_identify1.class);
								startActivity(intent);
					    	}
					    });
					    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
					    	}
					    });
					    alert.show();
						
					}
					
					}		
			});
			
			//余额查询按钮监听
			Button button3=(Button)findViewById(R.id.btn_balance);
			button3.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					AlertDialog alert=new AlertDialog.Builder(Cy_driver_set.this).create();
				    alert.setTitle("系统提示:");
				    alert.setMessage("您的现金余额:"+String.valueOf(balance));
				    //添加"确定"按钮
				    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
				    	@Override
				    	public void onClick(DialogInterface dialog,int which){
				    	}
				    });
				    alert.show();
					}			
			});
			//关于按钮监听
			Button button4=(Button)findViewById(R.id.btn_about);
			button4.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
						Intent intent=new Intent();
						intent.setClass(Cy_driver_set.this, Ty_pay_offline.class);
						startActivity(intent);	
				}
			});
	}


	//手机号码正则
	public static boolean isMobileNO(String mobiles) {  
			String telRegex = "[1][3458]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
			if (TextUtils.isEmpty(mobiles)) return false;  
			else return mobiles.matches(telRegex);  
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

