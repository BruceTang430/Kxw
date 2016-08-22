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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Cy_driver_admin_notice extends Activity {
	private WebView webView;
	String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_notice);
		ActivityCollector.getInstance().addActivity(this);	
		
		url="http://120.27.26.219/kxw/bulletin1.php";
		
		webView=(WebView)findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl(url);
		
		WebSettings webSettings = webView.getSettings();
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
		
		//进入主页按钮监听
		Button button=(Button)findViewById(R.id.btn_next);
		//设置登陆按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent=new Intent();
				intent.setClass(Cy_driver_admin_notice.this, Cy_driver_mainActivity.class);
				startActivity(intent);
			}
		});
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
		    	  Intent intent=new Intent(getApplicationContext(), Cy_driver_login.class);
				  startActivity(intent);
				  finish();
		        return true;
		      case R.id.action_next:
		    	  Intent intent2=new Intent(getApplicationContext(), Cy_driver_mainActivity.class);
				  startActivity(intent2);
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



