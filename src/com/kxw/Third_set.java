package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Ty_set.Modif;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Third_set extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/third_set_mod.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;
	JSONParser jsonParser = new JSONParser();
	double balance,time;
	EditText tel;
	String tel2,username;
	int usertype,isidentify;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_set);
		ActivityCollector.getInstance().addActivity(this);

		 Kxw_Application ty=((Kxw_Application)getApplicationContext());
		 username=ty.get_third_username();
		 balance=ty.get_third_balance();
		 usertype=ty.get_usertype();
		 
		 
		 if(String.valueOf(usertype).equals("3")){
			 time=ty.get_third_time();
		 }
		 if(String.valueOf(usertype).equals("4")){
			 isidentify=ty.get_third_isidentify();
		 }
		 
			//�������ϰ�ť����
			Button button=(Button)findViewById(R.id.btn_myInfo);
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					if(String.valueOf(usertype).equals("4")){
						Intent intent=new Intent();
						intent.setClass(Third_set.this, Third_set_myInfo.class);
						startActivity(intent);
					}
					else if(String.valueOf(usertype).equals("1")) Toast.makeText(Third_set.this, "���������˽ӿڲ鿴", Toast.LENGTH_LONG).show();
					else Toast.makeText(Third_set.this, "���ڳ����˽ӿڲ鿴", Toast.LENGTH_LONG).show();
					}			
			});
			
			//ʵ����֤��ť����
			Button button2=(Button)findViewById(R.id.btn_modInfo);
			button2.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){
					if(usertype==4){
						if(isidentify==1){
							Toast.makeText(getApplicationContext(), "����֤��", Toast.LENGTH_SHORT).show();
						}else{
							AlertDialog alert=new AlertDialog.Builder(Third_set.this).create();
						    alert.setTitle("ϵͳ��ʾ:");
						    alert.setMessage("��֤�����֤��Ƭ");
						    //���"ȷ��"��ť
						    alert.setButton(DialogInterface.BUTTON_POSITIVE, "ȷ������", new DialogInterface.OnClickListener(){
						    	@Override
						    	public void onClick(DialogInterface dialog,int which){
						    		Intent intent=new Intent();
									intent.setClass(Third_set.this, Third_identify.class);
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
					}else Toast.makeText(getApplicationContext(), "�������ڴ˽ӿ���֤��", Toast.LENGTH_SHORT).show();
					
					}		
			});
			
			//����ѯ��ť����
			Button button3=(Button)findViewById(R.id.btn_balance);
			button3.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){					
					AlertDialog alert=new AlertDialog.Builder(Third_set.this).create();
					if(String.valueOf(usertype).equals("3")){
						alert.setTitle("ϵͳ��ʾ:");
						alert.setMessage("�����ֽ����:"+String.valueOf(balance)+'\n'+"       �������:"+String.valueOf(time));
					}else{
						alert.setTitle("ϵͳ��ʾ:");
					    alert.setMessage("�������Ϊ:"+String.valueOf(balance));
					}
				    
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
						intent.setClass(Third_set.this, Ty_pay_offline.class);
						startActivity(intent);	
				}
			});
	}

    private void showDialog_Layout(Context context) {  
        LayoutInflater inflater = LayoutInflater.from(this);  
        final View textEntryView = inflater.inflate(  
                R.layout.dialog_ty_modinfo, null);  
        tel=(EditText)textEntryView.findViewById(R.id.edit_tel);  
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);  
        builder.setCancelable(false);    
        builder.setTitle("�����ֻ���");  
        builder.setView(textEntryView);  
        builder.setPositiveButton("ȷ��",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {
                        tel2=tel.getText().toString();
                    	if(!isMobileNO(tel2)){
	  					    Toast.makeText(Third_set.this, "�ֻ����벻��ȷ", Toast.LENGTH_LONG).show();
	  					    tel.setText("");
						    tel.requestFocus();	
                    	}else new Modif().execute();
                    }  
                });  
        
        builder.setNegativeButton("ȡ��",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {   
                    }  
                });  
        builder.show();  
    } 
	//�������ݿ�
	class Modif extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Third_set.this); 
			pDialog.setMessage("������"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub			
							
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("tel", tel2)); 
			para.add(new BasicNameValuePair("username", username)); 

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
				Toast.makeText(getApplicationContext(), "�޸ĳɹ���", Toast.LENGTH_SHORT).show();
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

