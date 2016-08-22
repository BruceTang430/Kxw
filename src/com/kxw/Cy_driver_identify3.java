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
import org.json.JSONObject;

import com.kxw.Cy_driver_identify1.Upload;

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
import android.widget.ImageView;
import android.widget.Toast;

public class Cy_driver_identify3 extends Activity implements OnClickListener {
	private static String actionUrl = "http://120.27.26.219/kxw/android/cy_driver_idcard_upload.php"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int p=0,userid;
	String srcPath,uploadFile;
	Button submit,selectImage;
    private ImageView imageView;     
    private String picPath = null; 
    public static final int UPLOAD=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_driver_identify3);
		ActivityCollector.getInstance().addActivity(this);

  		submit=(Button)this.findViewById(R.id.btn_next);
  	    //�����ύ��ť��ɫ
  		GradientDrawable myGrad = (GradientDrawable)submit.getBackground();
		myGrad.setColor(0xFF4f94cd);
				
		selectImage=(Button)this.findViewById(R.id.btn_selectImage);
		
        selectImage.setOnClickListener(this);  
        submit.setOnClickListener(this); 
        
        imageView = (ImageView) this.findViewById(R.id.imageView);
        
        Kxw_Application d=((Kxw_Application)getApplicationContext());
        userid=d.get_cy_driver_userid();

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
	        case R.id.btn_next:
	        	if(p==1){
	        		new Upload().execute();		        	
	        	}else Toast.makeText(Cy_driver_identify3.this, "��ѡ��ͼƬ", Toast.LENGTH_LONG).show();
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
			pDialog = new ProgressDialog(Cy_driver_identify3.this); 
			pDialog.setMessage("wait.."); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
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
			Toast.makeText(Cy_driver_identify3.this, "��ȴ�����Ա���", Toast.LENGTH_LONG).show();
			Intent it= new Intent();							
			it.setClass(getApplication(),Cy_driver_set.class);
			startActivity(it);
			finish();
//		if(success==1) 
//			Toast.makeText(getApplicationContext(), "�ϴ��ɹ���", Toast.LENGTH_SHORT).show();
//		else if(success==0) Toast.makeText(getApplicationContext(), "�ϴ�ʧ��",Toast.LENGTH_SHORT).show();
		pDialog.dismiss();
		}
	
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
