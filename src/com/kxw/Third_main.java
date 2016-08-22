package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Third_main extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_main);
		ActivityCollector.getInstance().addActivity(this);

		Kxw_Application buttonpress=((Kxw_Application)getApplicationContext());
		//购买保险按钮监控
		Button button=(Button)findViewById(R.id.btn_buy);
		buttonpress.setButtonFocusChanged(button);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Third_main.this, Third_clause.class);
				startActivity(intent);
			}		 	
		});
		//我的保单按钮监控
		Button button2=(Button)findViewById(R.id.btn_note);
		buttonpress.setButtonFocusChanged(button);
		button2.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Third_main.this, Third_myInsurance_type.class);
				startActivity(intent);
			}		 	
		});

		//"设置"按钮监听
		final Button button3=(Button)findViewById(R.id.btn_set);
		//按钮按下效果
		buttonpress.setButtonFocusChanged(button3);
		button3.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){			
				Intent intent=new Intent();
				intent.setClass(Third_main.this, Third_set.class);
				startActivity(intent);
			}						
		});
		
		//"货运保险"按钮监听
		final Button button4=(Button)findViewById(R.id.btn_insurance);
		//按钮按下效果
		buttonpress.setButtonFocusChanged(button4);
		button4.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){			
				Intent intent=new Intent();
				intent.setClass(Third_main.this, Third_freight.class);
				startActivity(intent);
			}						
		});
	}
	
	//返回键重写，直接返回到桌面
	@Override
    public void onBackPressed() {
       // TODO Auto-generated method stub
		Intent home = new Intent(Intent.ACTION_MAIN);  
		home.addCategory(Intent.CATEGORY_HOME);   
		startActivity(home);
    }
	   //菜单栏
		@Override
	    protected void onStart() {
	        super.onStart();
	        ActionBar actionBar = this.getActionBar();
	        actionBar.setDisplayHomeAsUpEnabled(true);
	    }
		@Override 
		public boolean onOptionsItemSelected(MenuItem item) {
			switch(item.getItemId())
		    {
		      case R.id.action_exit:
		    	  ActivityCollector.getInstance().exit();
		        return true;
		      case R.id.action_change:
		    	  Intent intent=new Intent();
					intent.setClass(Third_main.this, Third_login.class);
					startActivity(intent);
					finish();
		        return true;
		    }
	        return super.onOptionsItemSelected(item);    
	    }
  
		@Override
		  public boolean onCreateOptionsMenu(Menu menu)
		  {
		    // Inflate the menu; this adds items to the action bar if it is present.
		    getMenuInflater().inflate(R.menu.actionbar, menu);			
			//返回true才会显示overflow按钮
		    return true;
		  }
}

