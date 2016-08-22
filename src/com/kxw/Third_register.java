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
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Third_register extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/third_register.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	EditText user2,pwd2,repwd2,tel2,name2,id2,insure_address2;
	String user,pwd,repwd,tel,name,id,insure_address;
	int success=1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_register2);
		ActivityCollector.getInstance().addActivity(this);
		
		user2=(EditText)findViewById(R.id.edit_user);
		pwd2=(EditText)findViewById(R.id.edit_pwd);
		repwd2=(EditText)findViewById(R.id.edit_repwd);
		tel2=(EditText)findViewById(R.id.edit_tel);
		name2=(EditText)findViewById(R.id.edit_name);
		id2=(EditText)findViewById(R.id.edit_id);
		insure_address2=(EditText)findViewById(R.id.edit_insure_address);

		//�ύ��ť����
  		Button button=(Button)findViewById(R.id.btn_submit);
  	    //�����ύ��ť��ɫ
  		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
  		button.setOnClickListener(new OnClickListener(){
  			@Override
  			public void onClick(View v){
  				user=user2.getText().toString();
  				pwd=pwd2.getText().toString();
  				repwd=repwd2.getText().toString();
  				tel=tel2.getText().toString();
  				name=name2.getText().toString();
  				id=id2.getText().toString();
  				insure_address=insure_address2.getText().toString();
  				if(!"".equals(insure_address)&&!"".equals(user)&&!"".equals(pwd)&&!"".equals(repwd)&&!"".equals(tel)&&!"".equals(name)&&!"".equals(id)){
  					if(!pwd.equals(repwd)){
						Toast.makeText(Third_register.this, "������������벻һ�£�����������",Toast.LENGTH_LONG).show();
						((EditText)findViewById(R.id.edit_pwd)).setText("");
						((EditText)findViewById(R.id.edit_repwd)).setText("");
						((EditText)findViewById(R.id.edit_pwd)).requestFocus();
  					}else if(!isMobileNO(tel)){
	  					    Toast.makeText(Third_register.this, "�ֻ����벻��ȷ", Toast.LENGTH_LONG).show();
	  					    ((EditText)findViewById(R.id.edit_tel)).setText("");
						    ((EditText)findViewById(R.id.edit_tel)).requestFocus();	
  					}else if(pwd.length()<6||pwd.length()>16){
						Toast.makeText(Third_register.this, "�����ʽ����",Toast.LENGTH_LONG).show();
						((EditText)findViewById(R.id.edit_pwd)).setText("");
						((EditText)findViewById(R.id.edit_repwd)).setText("");
						((EditText)findViewById(R.id.edit_pwd)).requestFocus();
  					}else if(!isIdNO(id)){
	  					    Toast.makeText(Third_register.this, "���֤���벻��ȷ", Toast.LENGTH_LONG).show();
	  					    ((EditText)findViewById(R.id.edit_id)).setText("");
						    ((EditText)findViewById(R.id.edit_id)).requestFocus();
	  				}
  					else{				
	  				     new NewUser().execute();
					}
  				}else{
					Toast.makeText(Third_register.this, "�뽫ע����Ϣ��������", Toast.LENGTH_LONG).show();
				}
  			}
  		}); 		
	}
	//�������ݿ�
	class NewUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Third_register.this); 
			pDialog.setMessage("ע����"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			
			String pwd3=getMD5String(pwd);
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("username", user)); 
			para.add(new BasicNameValuePair("pwd", pwd3)); 
			para.add(new BasicNameValuePair("tel", tel));
			para.add(new BasicNameValuePair("idcard", id));
			para.add(new BasicNameValuePair("truename", name));
			para.add(new BasicNameValuePair("insure_address", insure_address));

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
				Toast.makeText(getApplicationContext(), "ע��ɹ���", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent();
				intent.setClass(Third_register.this, Third_login.class);
				startActivity(intent);
				finish();
				break;
			}
			case 2:{
				Toast.makeText(getApplicationContext(), "�û����Ѵ���", Toast.LENGTH_SHORT).show();
				((EditText)findViewById(R.id.edit_user)).setText("");
			    ((EditText)findViewById(R.id.edit_user)).requestFocus();
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
	//�ֻ���������
	public static boolean isMobileNO(String mobiles) {  
			String telRegex = "[1][3458]\\d{9}";//"[1]"�����1λΪ����1��"[358]"����ڶ�λ����Ϊ3��5��8�е�һ����"\\d{9}"��������ǿ�����0��9�����֣���9λ��  
			if (TextUtils.isEmpty(mobiles)) return false;  
			else return mobiles.matches(telRegex);  
	}
	//���֤��������
	public static boolean isIdNO(String id) {  		    
		    String isIDCard="(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";
			if (TextUtils.isEmpty(id)) return false;  
			else return id.matches(isIDCard);  
	}
	//���ϽǷ�����һ��
	@Override
    protected void onStart() {
        super.onStart();
        ActionBar actionBar = this.getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        
    }
    @Override 
	public boolean onOptionsItemSelected(MenuItem item) {    
        return super.onOptionsItemSelected(item);     
    } 
}

