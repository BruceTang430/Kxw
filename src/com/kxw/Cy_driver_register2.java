package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Cy_driver_register2 extends Activity {
	private TextView kxw_title;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_driver_register2);

		//预览按钮监听
		Button button=(Button)findViewById(R.id.btn_ylzcxx);
		ActivityCollector.getInstance().addActivity(this);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				//获取编辑框内容
				String weight=((EditText)findViewById(R.id.edit_carweight)).getText().toString();
				String zztj=((EditText)findViewById(R.id.edit_zztj)).getText().toString();
				Spinner cllx=((Spinner)findViewById(R.id.spin_cllx));
				String cllx2=cllx.getSelectedItem().toString();
				String jsyxm=((EditText)findViewById(R.id.edit_jsyxm)).getText().toString();
				String jsytel=((EditText)findViewById(R.id.edit_jsytel)).getText().toString();
				String jsyid=((EditText)findViewById(R.id.edit_jsyid)).getText().toString();
				String yhkh=((EditText)findViewById(R.id.edit_yhkh)).getText().toString();
				String yhorg=((EditText)findViewById(R.id.edit_yhorg)).getText().toString();
				//判断编辑框是否全部填写
				if(!"".equals(yhorg)&&!"".equals(weight)&&!"".equals(zztj)&&!"".equals(cllx)&&!"".equals(jsyxm)&&!"".equals(jsytel)&&!"".equals(jsyid)&&!"".equals(yhkh)){			
				        Intent intent=new Intent();
				        //调用ApplicationContext，把编辑框内容写进set()函数
						Kxw_Application hcsj=((Kxw_Application)getApplicationContext());
						hcsj.sethcsj_carweight(weight);
						hcsj.sethcsj_zztj(zztj);
						hcsj.sethcsj_cllx(cllx2);
						hcsj.sethcsj_jsyxm(jsyxm);
						hcsj.sethcsj_jsytel(jsytel);
						hcsj.sethcsj_jsyid(jsyid);
						hcsj.sethcsj_yhkh(yhkh);	
						hcsj.sethcsj_yhorg(yhorg);
				    if(!isMobileNO(jsytel)){
  					    Toast.makeText(Cy_driver_register2.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_jsytel)).setText("");
					    ((EditText)findViewById(R.id.edit_jsytel)).requestFocus();	
				    }else if(!isIdNO(jsyid)){
  					        Toast.makeText(Cy_driver_register2.this, "身份证号码不正确", Toast.LENGTH_LONG).show();
  					        ((EditText)findViewById(R.id.edit_jsyid)).setText("");
					        ((EditText)findViewById(R.id.edit_jsyid)).requestFocus();
				    }
				    else{
  				    	    intent.setClass(Cy_driver_register2.this, Cy_driver_registerYl.class);
  					    	startActivity(intent);
  					    	
  				         }
				    	
				}else{
					Toast.makeText(Cy_driver_register2.this, "请将注册信息输入完整", Toast.LENGTH_LONG).show();
				}
			}
			
		});

	}
	//手机号码正则
	public static boolean isMobileNO(String mobiles) {  
			String telRegex = "[1][3458]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
			if (TextUtils.isEmpty(mobiles)) return false;  
			else return mobiles.matches(telRegex);  
	} 
	//身份证号码正则
	public static boolean isIdNO(String id) {  		    
		    String isIDCard="(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
			if (TextUtils.isEmpty(id)) return false;  
			else return id.matches(isIDCard);  
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
