package com.kxw;


import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cy_driver_registerYl extends Activity {
	private TextView kxw_title;
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_driver_register2.php"; 
	// JSON Node names 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser(); int success=0;
	TextView user3,carnum3,carorg3,address3,xxaddress3,carweight3,zztj3,cllx3,drivername3,drivertel3,
	driverid3,bankcard3,bankorg3;
	String pwd3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_driver_registeryl);
		ActivityCollector.getInstance().addActivity(this);

		//����get()�������ѵ�һҳ�༭������д��Ԥ������
		Kxw_Application hcsj=((Kxw_Application)getApplicationContext());
		
		TextView user2=(TextView)findViewById(R.id.textView1);
		user2.setText("�û�����");
		user3=(TextView)findViewById(R.id.textView2);
		user3.setText(hcsj.gethcsj_user());
		
		pwd3=hcsj.getdriver_pwd();
		
		TextView carnum2=(TextView)findViewById(R.id.textView3);
		carnum2.setText("�������ƺţ�");
		carnum3=(TextView)findViewById(R.id.textView4);
		carnum3.setText(hcsj.get_driver_carnum());
		
		TextView carorg2=(TextView)findViewById(R.id.textView5);
		carorg2.setText("����������λ��");
		carorg3=(TextView)findViewById(R.id.textView6);
		carorg3.setText(hcsj.gethcsj_carorg());
		
		TextView address2=(TextView)findViewById(R.id.textView7);
		address2.setText("������λ��ַ��");
		address3=(TextView)findViewById(R.id.textView8);
		address3.setText(hcsj.getprovince()+","+hcsj.getcity11()+","+hcsj.getcounty());
		
		TextView xxaddress2=(TextView)findViewById(R.id.textView9);
		xxaddress2.setText("��ϸ��ַ��");
		xxaddress3=(TextView)findViewById(R.id.textView10);
		xxaddress3.setText(hcsj.getxxaddress());
		
		TextView carweight2=(TextView)findViewById(R.id.textView11);
		carweight2.setText("��������");
		carweight3=(TextView)findViewById(R.id.textView12);
		carweight3.setText(hcsj.gethcsj_carweight());
		
		TextView zztj2=(TextView)findViewById(R.id.textView13);
		zztj2.setText("װ�������");
		zztj3=(TextView)findViewById(R.id.textView14);
		zztj3.setText(hcsj.gethcsj_zztj());
		
		TextView cllx2=(TextView)findViewById(R.id.textView15);
		cllx2.setText("�������ͣ�");
		cllx3=(TextView)findViewById(R.id.textView16);
		cllx3.setText(hcsj.gethcsj_cllx());
		
		TextView drivername2=(TextView)findViewById(R.id.textView17);
		drivername2.setText("��ʻԱ������");
		drivername3=(TextView)findViewById(R.id.textView18);
		drivername3.setText(hcsj.gethcsj_jsyxm());
		
		TextView drivertel2=(TextView)findViewById(R.id.textView19);
		drivertel2.setText("��ʻԱ�ֻ��ţ�");
		drivertel3=(TextView)findViewById(R.id.textView20);
		drivertel3.setText(hcsj.gethcsj_jsytel());
		
		TextView driverid2=(TextView)findViewById(R.id.textView21);
		driverid2.setText("��ʻԱ���֤��");
		driverid3=(TextView)findViewById(R.id.textView22);
		driverid3.setText(hcsj.gethcsj_jsyid());
		
		TextView bankcard2=(TextView)findViewById(R.id.textView23);
		bankcard2.setText("���п��ţ�");
		bankcard3=(TextView)findViewById(R.id.textView24);
		bankcard3.setText(hcsj.gethcsj_yhkh());

		TextView bankorg2=(TextView)findViewById(R.id.textView25);
		bankorg2.setText("���е�λ��");
		bankorg3=(TextView)findViewById(R.id.textView26);
		bankorg3.setText(hcsj.gethcsj_yhorg());
		
		//�ύ��ť����
		Button button=(Button)findViewById(R.id.btn_submit);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				new NewUser().execute();
			}
					
		});

	}
	//�������ݿ�
	class NewUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Cy_driver_registerYl.this); 
			pDialog.setMessage("ע����..."); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub		
			String name = user3.getText().toString(); 
			String carnum = carnum3.getText().toString();
			String company = carorg3.getText().toString();
			String address = address3.getText().toString();
			String xxaddress = xxaddress3.getText().toString();
			String weight = carweight3.getText().toString();
			String vol = zztj3.getText().toString();
			String cartype=cllx3.getText().toString();
			String drivername = drivername3.getText().toString();
			String drivertel = drivertel3.getText().toString();
			String driverid = driverid3.getText().toString();
			String bankcard = bankcard3.getText().toString();
			String bankorg = bankorg3.getText().toString();
			String pwd=getMD5String(pwd3);
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("name", name)); 
			para.add(new BasicNameValuePair("pwd", pwd)); 
			para.add(new BasicNameValuePair("carnum", carnum));
			para.add(new BasicNameValuePair("company", company));
			para.add(new BasicNameValuePair("address", address));
			para.add(new BasicNameValuePair("xxaddress", xxaddress));
			para.add(new BasicNameValuePair("weight", weight));
			para.add(new BasicNameValuePair("vol", vol));
			para.add(new BasicNameValuePair("cartype", cartype));
			para.add(new BasicNameValuePair("drivername", drivername));
			para.add(new BasicNameValuePair("drivertel", drivertel));
			para.add(new BasicNameValuePair("driverid", driverid));
			para.add(new BasicNameValuePair("bankcard", bankcard));		
			para.add(new BasicNameValuePair("bankorg", bankorg));
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
			switch(success){
			case 1:{
				Toast.makeText(getApplicationContext(), "ע��ɹ���", Toast.LENGTH_SHORT).show();
				Intent intent2=new Intent();
				intent2.setClass(Cy_driver_registerYl.this, Cy_driver_login.class);
				startActivity(intent2);
				finish();
				break;
			}
			case 2:{
				Toast.makeText(getApplicationContext(), "�û����Ѵ���", Toast.LENGTH_SHORT).show();
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
	//MD5����
    public final static String getMD5String(String s) {  
		char hexDigits[] = { '0', '1', '2', '3', '4',  
		        '5', '6', '7', '8', '9',  
		        'A', 'B', 'C', 'D', 'E', 'F' };  
		try {  
		    byte[] btInput = s.getBytes();  
		    //���MD5ժҪ�㷨�� MessageDigest ����  
		    MessageDigest mdInst = MessageDigest.getInstance("MD5");  
		    //ʹ��ָ�����ֽڸ���ժҪ  
		    mdInst.update(btInput);  
		    //�������  
	        byte[] md = mdInst.digest();  
	        //������ת����ʮ�����Ƶ��ַ�����ʽ  
		    int j = md.length;  
		    char str[] = new char[j * 2];  
		    int k = 0;  
		    for (int i = 0; i < j; i++) {  
		        byte byte0 = md[i];  
		        str[k++] = hexDigits[byte0 >>> 4 & 0xf];  
		        str[k++] = hexDigits[byte0 & 0xf];  
		    }  
		    return new String(str);  
		}  
		catch (Exception e) {  
		   e.printStackTrace();  
		   return null;  
		}  
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
