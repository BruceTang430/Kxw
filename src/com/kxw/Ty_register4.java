package com.kxw;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Third_register2.Upload;

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
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Ty_register4 extends Activity implements OnClickListener {
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_register.php"; 
	private static String actionUrl = "http://120.27.26.219/kxw/android/ty_upload.php";
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog,pDialog2; 
	JSONParser jsonParser = new JSONParser();
	EditText username2,pwd2,repwd2,tel2,truename2,cardnum2,insure_address2;
	int success=1,p=0,userid;
	String username,pwd,truename,tel,cardnum,repwd,srcPath,uploadFile,insure_address;
	Button submit,selectImage;
    private ImageView imageView;     
    private String picPath = null; 
    public static final int UPLOAD=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ty_register2);
		ActivityCollector.getInstance().addActivity(this);
		
		username2=(EditText)findViewById(R.id.edit_user);
		pwd2=(EditText)findViewById(R.id.edit_pwd);
		repwd2=(EditText)findViewById(R.id.edit_repwd);
		tel2=(EditText)findViewById(R.id.edit_tel);
		truename2=(EditText)findViewById(R.id.edit_name);
		cardnum2=(EditText)findViewById(R.id.edit_id);
		insure_address2=(EditText)findViewById(R.id.edit_insure_address);

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
  				username=username2.getText().toString();
  				pwd=pwd2.getText().toString();
  				repwd=repwd2.getText().toString();
  				tel=tel2.getText().toString();
  				truename=truename2.getText().toString();
  				cardnum=cardnum2.getText().toString();
  				insure_address=insure_address2.getText().toString();
  				if(!"".equals(insure_address)&&!"".equals(username)&&!"".equals(pwd)&&!"".equals(repwd)&&!"".equals(tel)&&!"".equals(truename)&&!"".equals(cardnum)){
  					if(!pwd.equals(repwd)){
						Toast.makeText(Ty_register4.this, "������������벻һ�£�����������",Toast.LENGTH_LONG).show();
						((EditText)findViewById(R.id.edit_pwd)).setText("");
						((EditText)findViewById(R.id.edit_repwd)).setText("");
						((EditText)findViewById(R.id.edit_pwd)).requestFocus();
						break;
  					}else if(pwd.length()<6||pwd.length()>16){
						Toast.makeText(Ty_register4.this, "�����ʽ����",Toast.LENGTH_LONG).show();
						((EditText)findViewById(R.id.edit_pwd)).setText("");
						((EditText)findViewById(R.id.edit_repwd)).setText("");
						((EditText)findViewById(R.id.edit_pwd)).requestFocus();
						break;
  					}else if(!isMobileNO(tel)){
	  					    Toast.makeText(Ty_register4.this, "�ֻ����벻��ȷ", Toast.LENGTH_LONG).show();
	  					    ((EditText)findViewById(R.id.edit_tel)).setText("");
						    ((EditText)findViewById(R.id.edit_tel)).requestFocus();	
						    break;
  					}
  					else if(!isIdNO(cardnum)){
	  					    Toast.makeText(Ty_register4.this, "���֤���벻��ȷ", Toast.LENGTH_LONG).show();
	  					    ((EditText)findViewById(R.id.edit_id)).setText("");
						    ((EditText)findViewById(R.id.edit_id)).requestFocus();
						    break;
	  				}
  					else{				
	  				     new NewUser().execute();
	  				     
	  				   break;
					}
  				}else{
					Toast.makeText(Ty_register4.this, "�뽫ע����Ϣ��������", Toast.LENGTH_LONG).show();
					break;
				}
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
	class Upload extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog2 = new ProgressDialog(Ty_register4.this); 
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
				para.add(new BasicNameValuePair("userid", String.valueOf(userid))); 
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
			Toast.makeText(getApplicationContext(), "�ϴ��ɹ���", Toast.LENGTH_SHORT).show();
			Intent it= new Intent();							
			it.setClass(getApplication(),Ty_loginActivity.class);
			startActivity(it);
			finish();
//		if(success==1) 
//			Toast.makeText(getApplicationContext(), "�ϴ��ɹ���", Toast.LENGTH_SHORT).show();
//		else if(success==0) Toast.makeText(getApplicationContext(), "�ϴ�ʧ��",Toast.LENGTH_SHORT).show();
		pDialog2.dismiss();
		}
	
	}
	//�������ݿ�
	class NewUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Ty_register4.this); 
			pDialog.setMessage("ע����"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			
		    String pwd3=getMD5String(pwd2.getText().toString());
							
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("username", username)); 
			para.add(new BasicNameValuePair("pwd", pwd3)); 
			para.add(new BasicNameValuePair("tel", tel));
			para.add(new BasicNameValuePair("truename", truename));
			para.add(new BasicNameValuePair("cardnum", cardnum));
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
			userid = json.getInt("userid");
						
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
					Toast.makeText(getApplicationContext(), "ע��ɹ���", Toast.LENGTH_SHORT).show();
					Intent it= new Intent();							
					it.setClass(getApplication(),Ty_loginActivity.class);
					startActivity(it);
					finish();
				}
				else{
				
					new Upload().execute();
					Toast.makeText(getApplicationContext(), "�ϴ�ͼƬ��...", Toast.LENGTH_SHORT).show();
				}
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
//    @Override
//    protected void onDestroy() {
//        // TODO Auto-generated method stub
//        try{
//            pDialog.dismiss();
//        }catch (Exception e) {
//            System.out.println("myDialogȡ����ʧ�ܣ�");
//            // TODO: handle exception
//        }
//        super.onDestroy();
//    }
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
