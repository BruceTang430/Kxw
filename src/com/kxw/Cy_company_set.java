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
import android.widget.Button;
import android.widget.Toast;

public class Cy_company_set extends Activity {
	double balance,time;
	int isidentify;
	String tel,username,company_name,company_address,company_xxaddress,company_creditnum;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_set);
		ActivityCollector.getInstance().addActivity(this);
		
		 Kxw_Application cy=((Kxw_Application)getApplicationContext());
		 username=cy.get_cy_company_username();
		 balance=cy.get_cy_company_balance();
		 time=cy.get_cy_company_time();
		 company_name=cy.get_cy_company_name();
		 company_address=cy.get_cy_company_address();
		 company_xxaddress=cy.get_cy_company_xxaddress();
		 company_creditnum=cy.get_cy_company_creditnum();
		 isidentify=cy.get_company_isidentify();
		 
			//个人资料按钮监听
			Button button=(Button)findViewById(R.id.btn_myInfo);
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					Intent intent=new Intent();
					intent.setClass(Cy_company_set.this, Cy_company_set_myInfo.class);
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
						AlertDialog alert=new AlertDialog.Builder(Cy_company_set.this).create();
					    alert.setTitle("系统提示:");
					    alert.setMessage("认证需社会信用代码照片");
					    //添加"确定"按钮
					    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定进入", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
					    		Intent intent=new Intent();
								intent.setClass(Cy_company_set.this, Cy_company_identify.class);
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
					AlertDialog alert=new AlertDialog.Builder(Cy_company_set.this).create();
				    alert.setTitle("系统提示:");
				    alert.setMessage("您的现金余额:"+String.valueOf(balance)+'\n'+"       车次余额:"+String.valueOf(time));
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
						intent.setClass(Cy_company_set.this, Ty_pay_offline.class);
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

