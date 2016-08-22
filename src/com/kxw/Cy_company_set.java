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
		 
			//�������ϰ�ť����
			Button button=(Button)findViewById(R.id.btn_myInfo);
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					Intent intent=new Intent();
					intent.setClass(Cy_company_set.this, Cy_company_set_myInfo.class);
					startActivity(intent);
					}			
			});
			
			//ʵ����֤��ť����
			Button button2=(Button)findViewById(R.id.btn_modInfo);
			button2.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					if(isidentify==1){
						Toast.makeText(getApplicationContext(), "����֤��", Toast.LENGTH_SHORT).show();
					}else{
						AlertDialog alert=new AlertDialog.Builder(Cy_company_set.this).create();
					    alert.setTitle("ϵͳ��ʾ:");
					    alert.setMessage("��֤��������ô�����Ƭ");
					    //���"ȷ��"��ť
					    alert.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ������", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
					    		Intent intent=new Intent();
								intent.setClass(Cy_company_set.this, Cy_company_identify.class);
								startActivity(intent);
					    	}
					    });
					    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "ȡ��", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
					    	}
					    });
					    alert.show();
						
					}
					}		
			});
			
			//����ѯ��ť����
			Button button3=(Button)findViewById(R.id.btn_balance);
			button3.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					AlertDialog alert=new AlertDialog.Builder(Cy_company_set.this).create();
				    alert.setTitle("ϵͳ��ʾ:");
				    alert.setMessage("�����ֽ����:"+String.valueOf(balance)+'\n'+"       �������:"+String.valueOf(time));
				    //���"ȷ��"��ť
				    alert.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��", new DialogInterface.OnClickListener(){
				    	@Override
				    	public void onClick(DialogInterface dialog,int which){
				    	}
				    });
				    alert.show();
					}			
			});
			//���ڰ�ť����
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


	//�ֻ���������
	public static boolean isMobileNO(String mobiles) {  
			String telRegex = "[1][3458]\\d{9}";//"[1]"�����1λΪ����1��"[358]"����ڶ�λ����Ϊ3��5��8�е�һ����"\\d{9}"��������ǿ�����0��9�����֣���9λ��  
			if (TextUtils.isEmpty(mobiles)) return false;  
			else return mobiles.matches(telRegex);  
	}
	
	//���ϽǷ���
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

