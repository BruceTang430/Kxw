package com.kxw;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.igexin.sdk.PushManager;
import com.kxw.Ty_register4.NewUser;
import com.kxw.Ty_register4.Upload;
import com.kxw.Ty_txtydYl2.Ty_tyd;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Ty_txtydYl3 extends Activity implements OnGetGeoCoderResultListener, OnClickListener {
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_tyd.php"; 
	private static String actionUrl = "http://120.27.26.219/kxw/android/ty_tyd_upload.php";
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog,pDialog2; 
	JSONParser jsonParser = new JSONParser();
	int success=1, userid,p=0;
	String cid,ty_tel,srcPath,uploadFile,fbnum;
	TextView fhr3,fhrnum3,fhaddress3,xxaddress22,ddaddress3,ddxxaddress3,shr3,shrtel3,shraddress3,
	xxaddress33,hwname3,hwnum3,hwgg3,hwweight3,hwvol3,hwbz3,remark3,ty_ispay3;
	
	String ddcity2,ddcounty2,distance;
	double dd_lat,dd_lon,fh_lat,fh_lon,dis_coe,loc_coe,coe;
	
	GeoCoder mSearch = null;
	Button submit,selectImage;
    private ImageView imageView;     
    private String picPath = null; 
    public static final int UPLOAD=1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_txtydyl);
		ActivityCollector.getInstance().addActivity(this);
		
		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);

		//调用get()函数，把第一页编辑框内容写进预览界面
		Kxw_Application txtyd=((Kxw_Application)getApplicationContext());
		
		TextView fhr2=(TextView)findViewById(R.id.textView1);
		fhr2.setText("发货人：");
		fhr3=(TextView)findViewById(R.id.textView2);
		fhr3.setText(txtyd.gettxtyd_fhr());
		
		TextView fhrnum2=(TextView)findViewById(R.id.textView3);
		fhrnum2.setText("发货人电话：");
		fhrnum3=(TextView)findViewById(R.id.textView4);
		fhrnum3.setText(txtyd.gettxtyd_fhrnum());
		
		TextView fhaddress2=(TextView)findViewById(R.id.textView5);
		fhaddress2.setText("发货地址：");
		fhaddress3=(TextView)findViewById(R.id.textView6);
		fhaddress3.setText(txtyd.getprovince()+","+txtyd.getcity11()+","+txtyd.getcounty());
		
		TextView xxaddress2=(TextView)findViewById(R.id.textView7);
		xxaddress2.setText("详细地址：");
		xxaddress22=(TextView)findViewById(R.id.textView8);
		xxaddress22.setText(txtyd.getxxaddress());
		
		TextView ddaddress2=(TextView)findViewById(R.id.textView9);
		ddaddress2.setText("到达地：");
		ddaddress3=(TextView)findViewById(R.id.textView10);
		ddaddress3.setText(txtyd.getprovince2()+","+txtyd.getcity2()+","+txtyd.getcounty2());
		
		TextView ddxxaddress2=(TextView)findViewById(R.id.textView11);
		ddxxaddress2.setText("详细地址：");
		ddxxaddress3=(TextView)findViewById(R.id.textView12);
		ddxxaddress3.setText(txtyd.getxxaddress2());
		
		//第二页信息
		TextView shr2=(TextView)findViewById(R.id.textView13);
		shr2.setText("收货人：");
		shr3=(TextView)findViewById(R.id.textView14);
		shr3.setText(txtyd.gettxtyd_shr());
		
		TextView shrtel2=(TextView)findViewById(R.id.textView15);
		shrtel2.setText("收货人电话：");
		shrtel3=(TextView)findViewById(R.id.textView16);
		shrtel3.setText(txtyd.gettxtyd_shrtel());
		
		TextView shraddress2=(TextView)findViewById(R.id.textView17);
		shraddress2.setText("收货人地址：");
		shraddress3=(TextView)findViewById(R.id.textView18);
		shraddress3.setText(txtyd.getprovince3()+","+txtyd.getcity3()+","+txtyd.getcounty3());
		
		TextView xxaddress3=(TextView)findViewById(R.id.textView19);
		xxaddress3.setText("详细地址：");
		xxaddress33=(TextView)findViewById(R.id.textView20);
		xxaddress33.setText(txtyd.getxxaddress3());
		
		TextView hwname2=(TextView)findViewById(R.id.textView21);
		hwname2.setText("品名：");
		hwname3=(TextView)findViewById(R.id.textView22);
		hwname3.setText(txtyd.gettxtyd_hwname());
		
		TextView hwgg2=(TextView)findViewById(R.id.textView23);
		hwgg2.setText("货物价值：");
		hwgg3=(TextView)findViewById(R.id.textView24);
		hwgg3.setText(txtyd.gettxtyd_hwgg());
		
		TextView hwnum2=(TextView)findViewById(R.id.textView25);
		hwnum2.setText("数量：");
		hwnum3=(TextView)findViewById(R.id.textView26);
		hwnum3.setText(txtyd.gettxtyd_hwnum());
		
		TextView hwweight2=(TextView)findViewById(R.id.textView27);
		hwweight2.setText("重量：");
		hwweight3=(TextView)findViewById(R.id.textView28);
		hwweight3.setText(txtyd.gettxtyd_hwweight());
		
		TextView hwvol2=(TextView)findViewById(R.id.textView29);
		hwvol2.setText("体积：");
		hwvol3=(TextView)findViewById(R.id.textView30);
		hwvol3.setText(txtyd.gettxtyd_hwvol());
		
		TextView hwbz2=(TextView)findViewById(R.id.textView31);
		hwbz2.setText("包装：");
		hwbz3=(TextView)findViewById(R.id.textView32);
		hwbz3.setText(txtyd.gettxtyd_hwbz());
		
		TextView remark2=(TextView)findViewById(R.id.textView33);
		remark2.setText("备注：");
		remark3=(TextView)findViewById(R.id.textView34);
		remark3.setText(txtyd.gettxtyd_remark());
		
		TextView ty_ispay2=(TextView)findViewById(R.id.textView35);
		ty_ispay2.setText("付运费方式");
		ty_ispay3=(TextView)findViewById(R.id.textView36);
		ty_ispay3.setText(txtyd.get_ty_ispay());
		
		//登陆时set
		userid=txtyd.get_ty_userid();
		ty_tel=txtyd.get_ty_tel();
		
		fh_lat=txtyd.get_fh_lat();
		fh_lon=txtyd.get_fh_lon();
		dd_lat=txtyd.get_dd_lat();
		dd_lon=txtyd.get_dd_lon();	
		
		LatLng p1 = new LatLng(fh_lat, fh_lon);
		LatLng p2 = new LatLng(dd_lat, dd_lon);
		double d=DistanceUtil. getDistance(p1, p2);
        double d2=Math.floor(d/1000);
        distance=String.valueOf(d2);
        
        if(d2<500){
        	dis_coe=0.75;
        }else if(d2>2500){
        	dis_coe=1.5;
        }else dis_coe=1;
		
		String dd_province=txtyd.getprovince2();
		String dd_city=txtyd.getcity2();
		String address=dd_province+dd_city;
		if(address.equals("西藏拉萨")||address.equals("西藏灵芝")||dd_province.equals("甘肃")||dd_province.equals("宁夏")||dd_province.equals("新疆")||dd_province.equals("青海")){
			loc_coe=2;
		}else loc_coe=1;
		coe=loc_coe*dis_coe;
		
		cid=PushManager.getInstance().getClientid(getApplicationContext());
		
		//提交按钮监听
		submit=(Button)findViewById(R.id.btn_submit);
  	    //设置提交按钮颜色
  		GradientDrawable myGrad = (GradientDrawable)submit.getBackground();
		myGrad.setColor(0xFF4f94cd);
				
		selectImage=(Button)this.findViewById(R.id.btn_selectImage);
		
        selectImage.setOnClickListener(this);  
        submit.setOnClickListener(this); 
        
        imageView = (ImageView) this.findViewById(R.id.imageView);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	       switch (v.getId()) {  
	        case R.id.btn_selectImage:  
	            /*** 
	             * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 
	             */  
	            Intent intent = new Intent();  
	            intent.setType("image/*");  
	            intent.setAction(Intent.ACTION_GET_CONTENT);  
	            startActivityForResult(intent, 1);  
	            break;  
	        case R.id.btn_submit:  
	        	new Ty_tyd().execute();
			}

	}
    @Override  
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
        if (resultCode == Activity.RESULT_OK) {  
            /**  
             * 当选择的图片不为空的话，在获取到图片的途径  
             */  
            Uri uri = data.getData();  
            //Log.e(TAG, "uri = " + uri);  
            try {  
                String[] pojo = { MediaStore.Images.Media.DATA };  
   
                Cursor cursor = managedQuery(uri, pojo, null, null, null);  
                if (cursor != null) {  
                    ContentResolver cr = this.getContentResolver();  
                    int colunm_index = cursor  
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);  
                    cursor.moveToFirst();  
                    srcPath = cursor.getString(colunm_index); 
                    uploadFile=srcPath;
                    p=1;
                    Log.d("path", srcPath);
                    /*** 
                     * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了， 
                     * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以 
                     */  
//                    if (srcPath.endsWith("jpg") || srcPath.endsWith("png")) {  
//                        picPath = srcPath;  
                        Bitmap bitmap = BitmapFactory.decodeStream(cr  
                                .openInputStream(uri));  
                        imageView.setImageBitmap(bitmap);  
//                    } else {  
//                        alert();  
//                    }  
                } else {  
                    alert();  
                }  
   
            } catch (Exception e) {  
            }  
        }  
   
        super.onActivityResult(requestCode, resultCode, data);  
    }  
   
    private void alert() {  
        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示")  
                .setMessage("您选择的不是有效的图片")  
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int which) {  
                        picPath = null;  
                    }  
                }).create();  
        dialog.show();  
    }  
	//访问数据库
	class Ty_tyd extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Ty_txtydYl3.this); 
			pDialog.setMessage("提交中"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub

			String fhr_name=fhr3.getText().toString();
			String fhr_tel=fhrnum3.getText().toString();
			String fh_address=fhaddress3.getText().toString();
			String fh_xxaddress=xxaddress22.getText().toString();
			String dd_address=ddaddress3.getText().toString();
			String dd_xxaddress=ddxxaddress3.getText().toString();
			String shr_name=shr3.getText().toString();
			String shr_tel=shrtel3.getText().toString();
			String shr_address=shraddress3.getText().toString();
			String shr_xxaddress=xxaddress33.getText().toString();
			String hw_name=hwname3.getText().toString();
			String hw_gg=hwgg3.getText().toString();
			String hw_number=hwnum3.getText().toString();
			String hw_weight=hwweight3.getText().toString();
			String hw_vol=hwvol3.getText().toString();
			String hw_bz=hwbz3.getText().toString();
			String hw_remark=remark3.getText().toString();
			String ty_ispay=ty_ispay3.getText().toString();
			
			String str1=fhr_tel.substring(0, 3);
			String str2="****";
			String str3=fhr_tel.substring(7, 11);
			
			long now = System.currentTimeMillis();
			fbnum=str1+str2+str3+String.valueOf(now);
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("userid", String.valueOf(userid)));
			para.add(new BasicNameValuePair("fhr_name", fhr_name)); 
			para.add(new BasicNameValuePair("fhr_tel", fhr_tel)); 
			para.add(new BasicNameValuePair("fh_address", fh_address));
			para.add(new BasicNameValuePair("fh_xxaddress", fh_xxaddress));
			para.add(new BasicNameValuePair("dd_address", dd_address));
			para.add(new BasicNameValuePair("dd_xxaddress", dd_xxaddress));
			para.add(new BasicNameValuePair("shr_name", shr_name));
			para.add(new BasicNameValuePair("shr_tel", shr_tel));
			para.add(new BasicNameValuePair("shr_address", shr_address));
			para.add(new BasicNameValuePair("shr_xxaddress", shr_xxaddress));
			para.add(new BasicNameValuePair("hw_name", hw_name));
			para.add(new BasicNameValuePair("hw_gg", hw_gg));
			para.add(new BasicNameValuePair("hw_number", hw_number));
			para.add(new BasicNameValuePair("hw_weight", hw_weight));
			para.add(new BasicNameValuePair("hw_vol", hw_vol));
			para.add(new BasicNameValuePair("hw_bz", hw_bz));
			para.add(new BasicNameValuePair("hw_remark", hw_remark));
			para.add(new BasicNameValuePair("cid", cid));
			para.add(new BasicNameValuePair("ty_tel", ty_tel));
			para.add(new BasicNameValuePair("ty_ispay", ty_ispay));
			para.add(new BasicNameValuePair("coe", Double.toString(coe)));
			para.add(new BasicNameValuePair("fbnum", fbnum));
			para.add(new BasicNameValuePair("distance", distance));
			
			// getting JSON Object 
			// Note that create product url accepts POST method 
			JSONObject json = jsonParser.makeHttpRequest(url_create_product, 
			"POST", para); 
			// check log cat fro response 
			Log.d("Create Response", json.toString()); 
			// check for success tag 
			try{ 
			success = json.getInt(TAG_SUCCESS); 
						
			} catch(JSONException e) { 
			e.printStackTrace(); 
			} 
			
			
			return null;
		}

		@Override
		protected void onPostExecute(String fil_url) {
			// TODO Auto-generated method stub
			switch (success){
			case 1:{
				if(p==0){
					Toast.makeText(getApplicationContext(), "提交成功！", Toast.LENGTH_SHORT).show();
					Intent it= new Intent();							
					it.setClass(getApplication(),Ty_mainActivity.class);
					startActivity(it);
					finish();
				}
				else{
					new Upload().execute();
					Toast.makeText(getApplicationContext(), "上传图片中...", Toast.LENGTH_SHORT).show();
				}
			}
			case 0:{
				Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
				break;
			}
			case 2:{
				Toast.makeText(getApplicationContext(), "传输错误", Toast.LENGTH_SHORT).show();
				break;
			}
			}
			pDialog.dismiss();
		}
	
	}
	//访问数据库
	class Upload extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog2 = new ProgressDialog(Ty_txtydYl3.this); 
			pDialog2.setMessage("wait.."); 
			pDialog2.setIndeterminate(false); 
			pDialog2.setCancelable(true); 
			pDialog2.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub	
		     String end = "\r\n";
		      String twoHyphens = "--";
		      String boundary = "******";
		      
		      List<NameValuePair> para = new ArrayList<NameValuePair>(); 
				para.add(new BasicNameValuePair("fbnum", fbnum)); 
				para.add(new BasicNameValuePair("filename",srcPath.substring(srcPath.lastIndexOf("/") + 1)));

				// getting JSON Object 
				// Note that create product url accepts POST method 
				JSONObject json = jsonParser.makeHttpRequest(actionUrl, 
				"POST", para); 
				// check log cat fro response 
				Log.d("Create Response", json.toString());
				
		      try
		      {
		        URL url = new URL(actionUrl);
		        HttpURLConnection httpURLConnection = (HttpURLConnection) url
		            .openConnection();
		        httpURLConnection.setConnectTimeout(8000);
		        httpURLConnection.setReadTimeout(8000);
		        // 设置每次传输的流大小，可以有效防止手机因为内存不足崩溃
		        // 此方法用于在预先不知道内容长度时启用没有进行内部缓冲的 HTTP 请求正文的流。
//		      httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
		        // 允许输入输出流
		        httpURLConnection.setDoInput(true);
		        httpURLConnection.setDoOutput(true);
		        httpURLConnection.setUseCaches(false);
		        // 使用POST方法
		        httpURLConnection.setRequestMethod("POST");
		        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
		        httpURLConnection.setRequestProperty("Charset", "UTF-8");
		        httpURLConnection.setRequestProperty("Content-Type",
		            "multipart/form-data;boundary=" + boundary);

		        DataOutputStream dos = new DataOutputStream(
		            httpURLConnection.getOutputStream());
		        dos.writeBytes(twoHyphens + boundary + end);
		        dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\"; filename=\""
		            + srcPath.substring(srcPath.lastIndexOf("/") + 1)
		            + "\""
		            + end);
		        dos.writeBytes(end);

		        FileInputStream fis = new FileInputStream(srcPath);
		        byte[] buffer = new byte[8192]; // 8k
		        int count = 0;
		        // 读取文件
		        while ((count = fis.read(buffer)) != -1)
		        {
		          dos.write(buffer, 0, count);
		        }
		        fis.close();

		        dos.writeBytes(end);
		        dos.writeBytes(twoHyphens + boundary + twoHyphens + end);
		        dos.flush();

		        InputStream is = httpURLConnection.getInputStream();
		        InputStreamReader isr = new InputStreamReader(is, "utf-8");
		        BufferedReader br = new BufferedReader(isr);
		        String result = br.readLine();

		        //Toast.makeText(this, result, Toast.LENGTH_LONG).show();
		        dos.close();
		        is.close();

		      } catch (Exception e)
		      {
		        e.printStackTrace();
		        setTitle(e.getMessage());
		      }			
				return null;

		}

		@Override
		protected void onPostExecute(String fil_url) {
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "上传成功！", Toast.LENGTH_SHORT).show();
			Intent it= new Intent();							
			it.setClass(getApplication(),Ty_mainActivity.class);
			startActivity(it);
			finish();
//		if(success==1) 
//			Toast.makeText(getApplicationContext(), "上传成功！", Toast.LENGTH_SHORT).show();
//		else if(success==0) Toast.makeText(getApplicationContext(), "上传失败",Toast.LENGTH_SHORT).show();
		pDialog2.dismiss();
		}
	
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

@Override
protected void onDestroy() {
	mSearch.destroy();
	super.onDestroy();
}
	@Override
	public void onGetGeoCodeResult(GeoCodeResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
		// TODO Auto-generated method stub

	}

}
