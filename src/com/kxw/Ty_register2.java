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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Ty_register2 extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/ty_register.php"; 
	private String actionUrl = "http://120.27.26.219/kxw/android/ty_upload.php";
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	EditText username2,pwd2,repwd2,tel2,truename2,cardnum2;
	int success=1;
	String username,pwd,truename,tel,cardnum,repwd,srcPath,uploadFile;
	Button submit,selectImage;
    private ImageView imageView;     
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
		
		//�ύ��ť����
  		submit=(Button)this.findViewById(R.id.btn_submit);
  	    //�����ύ��ť��ɫ
  		GradientDrawable myGrad = (GradientDrawable)submit.getBackground();
		myGrad.setColor(0xFF4f94cd);
		
		selectImage=(Button)this.findViewById(R.id.btn_selectImage);
		selectImage.setOnClickListener(new View.OnClickListener()
	    {
	      @Override
	      public void onClick(View v)
	      {
	            Intent intent = new Intent();  
	            intent.setType("image/*");  
	            intent.setAction(Intent.ACTION_GET_CONTENT);  
	            startActivityForResult(intent, 1);  
	      }
	    });
        
		submit.setOnClickListener(new View.OnClickListener()
	    {
	      @Override
	      public void onClick(View v)
	      {
	    	  uploadFile(actionUrl); 
	      }
	    });
		
        imageView = (ImageView) this.findViewById(R.id.imageView);
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
                    Log.d("path", srcPath);
                    /*** 
                     * ���������һ���ж���Ҫ��Ϊ�˵����������ѡ�񣬱��磺ʹ�õ��������ļ��������Ļ�����ѡ����ļ��Ͳ�һ����ͼƬ�ˣ� 
                     * �����Ļ��������ж��ļ��ĺ�׺�� �����ͼƬ��ʽ�Ļ�����ô�ſ��� 
                     */  
 
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
                        srcPath = null;  
                    }  
                }).create();  
        dialog.show();  
    }  
    private void uploadFile(String uploadUrl)
    {
      String end = "\r\n";
      String twoHyphens = "--";
      String boundary = "******";
      try
      {
        URL url = new URL(uploadUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url
            .openConnection();
        // ����ÿ�δ��������С��������Ч��ֹ�ֻ���Ϊ�ڴ治�����
        // �˷���������Ԥ�Ȳ�֪�����ݳ���ʱ����û�н����ڲ������ HTTP �������ĵ�����
//      httpURLConnection.setChunkedStreamingMode(128 * 1024);// 128K
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

        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
        dos.close();
        is.close();

      } catch (Exception e)
      {
        e.printStackTrace();
        setTitle(e.getMessage());
      }
    }
	//�������ݿ�
	class NewUser extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Ty_register2.this); 
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
				Intent intent2=new Intent();
				intent2.setClass(Ty_register2.this, Ty_loginActivity.class);
				startActivity(intent2);
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



