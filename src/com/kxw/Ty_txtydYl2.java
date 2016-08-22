package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.igexin.sdk.PushManager;
import com.kxw.Ty_txtydYl.Ty_tyd;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Ty_txtydYl2 extends Activity implements OnGetGeoCoderResultListener {
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_tyd.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1, userid;
	String cid,ty_tel;
	TextView fhr3,fhrnum3,fhaddress3,xxaddress22,ddaddress3,ddxxaddress3,shr3,shrtel3,shraddress3,
	xxaddress33,hwname3,hwnum3,hwgg3,hwweight3,hwvol3,hwbz3,remark3,ty_ispay3;
	
	String ddcity2,ddcounty2;
	double dd_lat,dd_lon,fh_lat,fh_lon,dis_coe,loc_coe,coe;
	
	GeoCoder mSearch = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_txtydyl);
		ActivityCollector.getInstance().addActivity(this);
		
		// ��ʼ������ģ�飬ע���¼�����
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);

		//����get()�������ѵ�һҳ�༭������д��Ԥ������
		Kxw_Application txtyd=((Kxw_Application)getApplicationContext());
		
		TextView fhr2=(TextView)findViewById(R.id.textView1);
		fhr2.setText("�����ˣ�");
		fhr3=(TextView)findViewById(R.id.textView2);
		fhr3.setText(txtyd.gettxtyd_fhr());
		
		TextView fhrnum2=(TextView)findViewById(R.id.textView3);
		fhrnum2.setText("�����˵绰��");
		fhrnum3=(TextView)findViewById(R.id.textView4);
		fhrnum3.setText(txtyd.gettxtyd_fhrnum());
		
		TextView fhaddress2=(TextView)findViewById(R.id.textView5);
		fhaddress2.setText("������ַ��");
		fhaddress3=(TextView)findViewById(R.id.textView6);
		fhaddress3.setText(txtyd.getprovince()+","+txtyd.getcity11()+","+txtyd.getcounty());
		
		TextView xxaddress2=(TextView)findViewById(R.id.textView7);
		xxaddress2.setText("��ϸ��ַ��");
		xxaddress22=(TextView)findViewById(R.id.textView8);
		xxaddress22.setText(txtyd.getxxaddress());
		
		TextView ddaddress2=(TextView)findViewById(R.id.textView9);
		ddaddress2.setText("����أ�");
		ddaddress3=(TextView)findViewById(R.id.textView10);
		ddaddress3.setText(txtyd.getprovince2()+","+txtyd.getcity2()+","+txtyd.getcounty2());
		
		TextView ddxxaddress2=(TextView)findViewById(R.id.textView11);
		ddxxaddress2.setText("��ϸ��ַ��");
		ddxxaddress3=(TextView)findViewById(R.id.textView12);
		ddxxaddress3.setText(txtyd.getxxaddress2());
		
		//�ڶ�ҳ��Ϣ
		TextView shr2=(TextView)findViewById(R.id.textView13);
		shr2.setText("�ջ��ˣ�");
		shr3=(TextView)findViewById(R.id.textView14);
		shr3.setText(txtyd.gettxtyd_shr());
		
		TextView shrtel2=(TextView)findViewById(R.id.textView15);
		shrtel2.setText("�ջ��˵绰��");
		shrtel3=(TextView)findViewById(R.id.textView16);
		shrtel3.setText(txtyd.gettxtyd_shrtel());
		
		TextView shraddress2=(TextView)findViewById(R.id.textView17);
		shraddress2.setText("�ջ��˵�ַ��");
		shraddress3=(TextView)findViewById(R.id.textView18);
		shraddress3.setText(txtyd.getprovince3()+","+txtyd.getcity3()+","+txtyd.getcounty3());
		
		TextView xxaddress3=(TextView)findViewById(R.id.textView19);
		xxaddress3.setText("��ϸ��ַ��");
		xxaddress33=(TextView)findViewById(R.id.textView20);
		xxaddress33.setText(txtyd.getxxaddress3());
		
		TextView hwname2=(TextView)findViewById(R.id.textView21);
		hwname2.setText("Ʒ����");
		hwname3=(TextView)findViewById(R.id.textView22);
		hwname3.setText(txtyd.gettxtyd_hwname());
		
		TextView hwgg2=(TextView)findViewById(R.id.textView23);
		hwgg2.setText("�����ֵ��");
		hwgg3=(TextView)findViewById(R.id.textView24);
		hwgg3.setText(txtyd.gettxtyd_hwgg());
		
		TextView hwnum2=(TextView)findViewById(R.id.textView25);
		hwnum2.setText("������");
		hwnum3=(TextView)findViewById(R.id.textView26);
		hwnum3.setText(txtyd.gettxtyd_hwnum());
		
		TextView hwweight2=(TextView)findViewById(R.id.textView27);
		hwweight2.setText("������");
		hwweight3=(TextView)findViewById(R.id.textView28);
		hwweight3.setText(txtyd.gettxtyd_hwweight());
		
		TextView hwvol2=(TextView)findViewById(R.id.textView29);
		hwvol2.setText("�����");
		hwvol3=(TextView)findViewById(R.id.textView30);
		hwvol3.setText(txtyd.gettxtyd_hwvol());
		
		TextView hwbz2=(TextView)findViewById(R.id.textView31);
		hwbz2.setText("��װ��");
		hwbz3=(TextView)findViewById(R.id.textView32);
		hwbz3.setText(txtyd.gettxtyd_hwbz());
		
		TextView remark2=(TextView)findViewById(R.id.textView33);
		remark2.setText("��ע��");
		remark3=(TextView)findViewById(R.id.textView34);
		remark3.setText(txtyd.gettxtyd_remark());
		
		TextView ty_ispay2=(TextView)findViewById(R.id.textView35);
		ty_ispay2.setText("���˷ѷ�ʽ");
		ty_ispay3=(TextView)findViewById(R.id.textView36);
		ty_ispay3.setText(txtyd.get_ty_ispay());
		
		//��½ʱset
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
        
        if(d2<500){
        	dis_coe=0.75;
        }else if(d2>2500){
        	dis_coe=1.5;
        }else dis_coe=1;
        String d3=String.valueOf(dis_coe);
		Log.d("dis_coe", d3);
		
		String dd_province=txtyd.getprovince2();
		String dd_city=txtyd.getcity2();
		String address=dd_province+dd_city;
		if(address.equals("��������")||address.equals("������֥")||dd_province.equals("����")||dd_province.equals("����")||dd_province.equals("�½�")||dd_province.equals("�ຣ")){
			loc_coe=2;
		}else loc_coe=1;
		coe=loc_coe*dis_coe;
		
		cid=PushManager.getInstance().getClientid(getApplicationContext());
		
		//�ύ��ť����
		Button button=(Button)findViewById(R.id.btn_submit);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				new Ty_tyd().execute();
			}		
		});

	}
	
	//�������ݿ�
		class Ty_tyd extends AsyncTask<String, String, String> {

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				pDialog = new ProgressDialog(Ty_txtydYl2.this); 
				pDialog.setMessage("�ύ��"); 
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
				String fbnum=str1+str2+str3+String.valueOf(now);
				
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
					Toast.makeText(getApplicationContext(), "�ύ�ɹ���", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent();
				    intent.setClass(Ty_txtydYl2.this, Ty_mainActivity.class);
				    startActivity(intent);
				    finish();
					break;
				}
				case 0:{
					Toast.makeText(getApplicationContext(), "���ִ���", Toast.LENGTH_SHORT).show();
					break;
				}
				case 2:{
					Toast.makeText(getApplicationContext(), "�������", Toast.LENGTH_SHORT).show();
					break;
				}
				}
				pDialog.dismiss();
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
	
	@Override
	protected void onDestroy() {
		mSearch.destroy();
		super.onDestroy();
	}
	
	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		// TODO Auto-generated method stub

	}


	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
		// TODO Auto-generated method stub

	}

}
