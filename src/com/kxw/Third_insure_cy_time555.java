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
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.utils.DistanceUtil;
import com.kxw.Third_insure_cy_cash3.Upload;
import com.kxw.Third_insure_cy_time55.Third;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Third_insure_cy_time555 extends Activity implements OnClickListener {
	private static String url_create_product = "http://120.27.26.219/kxw/android/third_insure_cy_time.php"; 
	private static String actionUrl = "http://120.27.26.219/kxw/android/third_cy_upload.php";
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog,pDialog2; 
	JSONParser jsonParser = new JSONParser();
	int success=1,userid,usertype,p=0;
	String insure_name,insured_tel,insured_address,insured_name,fhorg,shorg,
	goods_name,goods_weight,carnum,driver_name,driver_tel,driver_license,cyorg,cyaddr,
	carnum2,fbnum,fhaddress,ddaddress,ddcity,ddcounty,m_compens,ddprovince,insurance_num,goods_value2,
	fhtel,shtel,srcPath,uploadFile;
	RadioButton r,r2;
	double fh_lat,fh_lon,dd_lat,dd_lon;
	GeoCoder mSearch = null;
	RadioGroup pay,add;
	double insuremon,addit,time,sumtime,coe,dis_coe,loc_coe,balance_time;
	Button submit,selectImage;
    private ImageView imageView;     
    private String picPath = null; 
    public static final int UPLOAD=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_insure_cy_time55);
		ActivityCollector.getInstance().addActivity(this);
		

		//����ȫ�ֱ���
		Kxw_Application id=((Kxw_Application)getApplicationContext());
		userid=id.get_third_userid();
		time=id.get_third_time();
		
        
		fh_lat=id.get_third_fh_lat();
		fh_lon=id.get_third_fh_lon();
		dd_lat=id.get_third_dd_lat();
		dd_lon=id.get_third_dd_lon();
		
		Intent it=getIntent();
		Bundle bun = it.getExtras();
		fhaddress=bun.getString("fhaddress");
		ddaddress=bun.getString("ddaddress");
		ddcity=bun.getString("ddcity");
		ddcounty=bun.getString("ddcounty");
		ddprovince=bun.getString("ddprovince");
		goods_value2=bun.getString("goods_value");
		insured_name=bun.getString("insured_name");
		insured_tel=bun.getString("insured_tel");
		insured_address=bun.getString("insured_address");
		goods_name=bun.getString("goods_name");
		goods_weight=bun.getString("goods_weight");
		driver_name=bun.getString("driver_name");
		driver_tel=bun.getString("driver_tel");
		driver_license=bun.getString("driver_license");
		carnum=bun.getString("carnum");
		fhorg=bun.getString("fhorg");
		shorg=bun.getString("shorg");
		cyorg=bun.getString("cyorg");
		cyaddr=bun.getString("cyaddr");
		fhtel=bun.getString("fhtel");
		shtel=bun.getString("shtel");
		
		pay=(RadioGroup)findViewById(R.id.radgroup);
		add=(RadioGroup)findViewById(R.id.radgroup2);
		
		LatLng p1 = new LatLng(fh_lat, fh_lon);
		LatLng p2 = new LatLng(dd_lat, dd_lon);
		double d=DistanceUtil. getDistance(p1, p2);
        double d2=Math.floor(d/1000);
        
        if(d2<500){
        	dis_coe=0.75;
        }else if(d2>2500){
        	dis_coe=1.5;
        }else dis_coe=1;
        String d3=String.valueOf(dis_coe);
		Log.d("dis_coe", d3);
		
		String address=ddprovince+ddcity;
		if(address.equals("��������")||address.equals("������֥")||ddprovince.equals("����")||ddprovince.equals("����")||ddprovince.equals("�½�")||ddprovince.equals("�ຣ")){
			loc_coe=2;
		}else loc_coe=1;
		coe=loc_coe*dis_coe;
		Log.d("coe", String.valueOf(coe));
		
  		submit=(Button)this.findViewById(R.id.btn_submit);
  	    //�����ύ��ť��ɫ
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
	       switch (v.getId()) {  
	        case R.id.btn_selectImage:  
	            /*** 
	             * ����ǵ���android���õ�intent��������ͼƬ�ļ� ��ͬʱҲ���Թ��������� 
	             */  
	            Intent intent = new Intent();  
	            intent.setType("image/*");  
	            intent.setAction(Intent.ACTION_GET_CONTENT);  
	            startActivityForResult(intent, 1);  
	            break;  
	        case R.id.btn_submit:  
				for(int i=0;i<pay.getChildCount();i++){
					r=(RadioButton)pay.getChildAt(i);
					if(r.isChecked()){
						r.getText();
						break;
					}
				}
				if(r.getText().equals("50��")) {
					insuremon=0.5;
					m_compens="50��";
				}
				else if(r.getText().equals("100��")) {
					insuremon=1;
					m_compens="100��";
				}else if(r.getText().equals("150��")) {
					insuremon=1.5;
					m_compens="150��";
				}else if(r.getText().equals("200��")) {
					insuremon=2;
					m_compens="200��";
				}
				
				for(int i=0;i<add.getChildCount();i++){
					r2=(RadioButton)add.getChildAt(i);
					if(r2.isChecked()){
						addit=0.5;
						break;
					}else addit=0;					
				}
				sumtime=insuremon*coe+addit;
				
				if(time<sumtime) Toast.makeText(Third_insure_cy_time555.this, "����", Toast.LENGTH_LONG).show();
				else{
					AlertDialog alert=new AlertDialog.Builder(Third_insure_cy_time555.this).create();
				    alert.setTitle("ϵͳ��ʾ:");
				    alert.setMessage("��֧��������:"+sumtime);
				    //���"ȷ��"��ť
				    alert.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ��֧��", new DialogInterface.OnClickListener(){
				    	@Override
				    	public void onClick(DialogInterface dialog,int which){
				    		new Third().execute();
				    	}
				    });
				    alert.show();
					
				}
				break;
	       }
	}
	       @Override  
	       protected void onActivityResult(int requestCode, int resultCode, Intent data) {  
	           if (resultCode == Activity.RESULT_OK) {  
	               /**  
	                * ��ѡ���ͼƬ��Ϊ�յĻ����ڻ�ȡ��ͼƬ��;��  
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
	                        * ���������һ���ж���Ҫ��Ϊ�˵����������ѡ�񣬱��磺ʹ�õ��������ļ��������Ļ�����ѡ����ļ��Ͳ�һ����ͼƬ�ˣ� 
	                        * �����Ļ��������ж��ļ��ĺ�׺�� �����ͼƬ��ʽ�Ļ�����ô�ſ��� 
	                        */  
//	                       if (srcPath.endsWith("jpg") || srcPath.endsWith("png")) {  
//	                           picPath = srcPath;  
	                           Bitmap bitmap = BitmapFactory.decodeStream(cr  
	                                   .openInputStream(uri));  
	                           imageView.setImageBitmap(bitmap);  
//	                       } else {  
//	                           alert();  
//	                       }  
	                   } else {  
	                       alert();  
	                   }  
	      
	               } catch (Exception e) {  
	               }  
	           }  
	      
	           super.onActivityResult(requestCode, resultCode, data);  
	       }  
	      
	       private void alert() {  
	           Dialog dialog = new AlertDialog.Builder(this).setTitle("��ʾ")  
	                   .setMessage("��ѡ��Ĳ�����Ч��ͼƬ")  
	                   .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {  
	                       public void onClick(DialogInterface dialog, int which) {  
	                           picPath = null;  
	                       }  
	                   }).create();  
	           dialog.show();  
	       }  	       
	//�������ݿ�
	class Third extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Third_insure_cy_time555.this); 
			pDialog.setMessage("�ύ��"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			
			String str1=insured_tel.substring(0, 3);
			String str2="****";
			String str3=insured_tel.substring(7, 11);
			
			long now = System.currentTimeMillis();
			fbnum=str1+str2+str3+String.valueOf(now);
			
			double time2=time-sumtime;
			Log.d("time", String.valueOf(time2));
			
			Kxw_Application id=((Kxw_Application)getApplicationContext());
			insurance_num=id.get_third_insurance_num();
			Log.d("insurance", insurance_num);
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("fh_address", fhaddress));
			para.add(new BasicNameValuePair("dd_address", ddaddress));
			para.add(new BasicNameValuePair("goods_name", goods_name));
			para.add(new BasicNameValuePair("goods_weight", goods_weight));
			para.add(new BasicNameValuePair("insured_tel", insured_tel));
			para.add(new BasicNameValuePair("insured_address", insured_address));
			para.add(new BasicNameValuePair("insured_name", insured_name));
			para.add(new BasicNameValuePair("userid", Integer.toString(userid)));
			para.add(new BasicNameValuePair("fbnum", fbnum));
			para.add(new BasicNameValuePair("carnum", carnum));
			para.add(new BasicNameValuePair("driver_name", driver_name));
			para.add(new BasicNameValuePair("driver_tel", driver_tel));
			para.add(new BasicNameValuePair("driver_license", driver_license));
			para.add(new BasicNameValuePair("insurance_num", insurance_num));
			para.add(new BasicNameValuePair("balance_time", String.valueOf(time2)));
			para.add(new BasicNameValuePair("pay_time", String.valueOf(sumtime)));
			para.add(new BasicNameValuePair("goods_value", goods_value2));
			para.add(new BasicNameValuePair("usertype", "3"));
			para.add(new BasicNameValuePair("insurance_type", "1"));
			para.add(new BasicNameValuePair("fhorg", fhorg));
			para.add(new BasicNameValuePair("shorg", shorg));
			para.add(new BasicNameValuePair("cyorg", cyorg));
			para.add(new BasicNameValuePair("cyaddr", cyaddr));
			para.add(new BasicNameValuePair("m_compens", m_compens));
			para.add(new BasicNameValuePair("fhtel", fhtel));
			para.add(new BasicNameValuePair("shtel", shtel));

			// getting JSON Object 
			// Note that create product url accepts POST method 
			JSONObject json = jsonParser.makeHttpRequest(url_create_product, 
			"POST", para); 
			// check log cat fro response 
			Log.d("Create Response", json.toString()); 
			// check for success tag 
			try{ 
			success = json.getInt(TAG_SUCCESS); 
			if(success == 1) {
				
				
				} else{ 
				// failed to create product 
					//Toast.makeText(getApplicationContext(), " 111",Toast.LENGTH_SHORT).show();
				} 		
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
				if(p==1){
					new Upload().execute();
				}
				Toast.makeText(getApplicationContext(), "֧���ɹ�", Toast.LENGTH_SHORT).show();
				Intent it= new Intent();
			    it.setClass(Third_insure_cy_time555.this,Third_myInsurance.class);	
			    startActivity(it);
			    finish();
				break;
				}
			
			case 0:{
				Toast.makeText(getApplicationContext(), "���ִ���", Toast.LENGTH_SHORT).show();
				break;
			}
			}
			pDialog.dismiss();
		}
	
	}
	//�������ݿ�
	class Upload extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog2 = new ProgressDialog(Third_insure_cy_time555.this); 
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
		        // ����ÿ�δ��������С��������Ч��ֹ�ֻ���Ϊ�ڴ治�����
		        // �˷���������Ԥ�Ȳ�֪�����ݳ���ʱ����û�н����ڲ������ HTTP �������ĵ�����
//		      httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
		        // �������������
		        httpURLConnection.setDoInput(true);
		        httpURLConnection.setDoOutput(true);
		        httpURLConnection.setUseCaches(false);
		        // ʹ��POST����
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
		        // ��ȡ�ļ�
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
//			Toast.makeText(getApplicationContext(), "�ϴ��ɹ���", Toast.LENGTH_SHORT).show();

		pDialog2.dismiss();
		}	
	}	
	  @Override
	  protected void onDestroy() {
	      // TODO Auto-generated method stub
	      try{
	          pDialog.dismiss();
	          pDialog2.dismiss();
	      }catch (Exception e) {
	          System.out.println("myDialogȡ����ʧ�ܣ�");
	          // TODO: handle exception
	      }
	      super.onDestroy();
	  }
//���ϽǷ�����һ��
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
