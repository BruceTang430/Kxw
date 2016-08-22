package com.kxw;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Cy_company_myInsurance_preview extends Activity {
	private WebView webView;
	String url;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_myinsurance_preview);
		ActivityCollector.getInstance().addActivity(this);
		
		Kxw_Application i=((Kxw_Application)getApplicationContext());
		int insurance_id=i.get_company_insurance_id();
		int insurance_type=i.get_company_insurance_type();
		
		String id=String.valueOf(insurance_id);
		
		if(String.valueOf(insurance_type).equals("0")){
			url="http://120.27.26.219/kxw/gc_pdf.php?gc_id="+id;
		}else url="http://120.27.26.219/kxw/cc_pdf.php?cc_id="+id;
		
		webView=(WebView)findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
		webView.loadUrl(url);
		WebSettings webSettings = webView.getSettings();
		webSettings.setUseWideViewPort(true);
		webSettings.setLoadWithOverviewMode(true);
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


