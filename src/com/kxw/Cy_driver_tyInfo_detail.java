package com.kxw;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.ITemplate;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.exceptions.RequestException;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.igexin.sdk.PushManager;
import com.kxw.Cy_driver_login.LogUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Cy_driver_tyInfo_detail extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_tyinfo_detail.php"; 
	private static String url_create_product2 = "http://120.27.26.219/kxw/android/push.php";
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog,pDialog2; 
    int success=0,success2=0;
    String fbnum,carriage2,cid,distance,fhaddress,ddaddress;
    EditText carriage;
	JSONParser jsonParser = new JSONParser();
	JSONParser jsonParser2 = new JSONParser();
	
	private static final String MASTERSECRET = "6NnZJeZFYl622MLW1H9V48";
	private String appkey = "";
	private String appsecret = "";
	private String appid = "";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
	//public static TextView tView = null;
	//public static TextView tLogView = null;
	private Date curDate = null;
	private SimpleDateFormat formatter = null;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
		    setContentView(R.layout.cy_tyinfo_detail);
		    ActivityCollector.getInstance().addActivity(this);
		    
		    
		    Intent it = getIntent();
		    Bundle bun = it.getExtras();
		    
		    String ty_userid=bun.getString("ty_userid");
		    TextView userid2=(TextView)findViewById(R.id.txt_userid);
		    userid2.setText(ty_userid);
		    String fhrname=bun.getString("fhr_name");
		    TextView fhrname2=(TextView)findViewById(R.id.txt_fhrname);
		    fhrname2.setText(fhrname);
		    String fhrtel=bun.getString("fhr_tel");
		    TextView fhrtel2=(TextView)findViewById(R.id.txt_fhrtel);
		    fhrtel2.setText(fhrtel);
		    fhaddress=bun.getString("fh_address");
		    TextView fhaddress2=(TextView)findViewById(R.id.txt_fhaddress);
		    fhaddress2.setText(fhaddress);
		    String fhxxaddress=bun.getString("fh_xxaddress");
		    TextView fhxxaddress2=(TextView)findViewById(R.id.txt_fhxxaddress);
		    fhxxaddress2.setText(fhxxaddress);
		    ddaddress=bun.getString("dd_address");
		    TextView ddaddress2=(TextView)findViewById(R.id.txt_ddaddress);
		    ddaddress2.setText(ddaddress);
		    String ddxxaddress=bun.getString("dd_xxaddress");
		    TextView ddxxaddress2=(TextView)findViewById(R.id.txt_ddxxaddress);
		    ddxxaddress2.setText(ddxxaddress);
		    String shrname=bun.getString("shr_name");
		    TextView shrname2=(TextView)findViewById(R.id.txt_shrname);
		    shrname2.setText(shrname);
		    String shrtel=bun.getString("shr_tel");
		    TextView shrtel2=(TextView)findViewById(R.id.txt_shrtel);
		    shrtel2.setText(shrtel);
		    String shraddress=bun.getString("shr_address");
		    TextView shraddress2=(TextView)findViewById(R.id.txt_shraddress);
		    shraddress2.setText(shraddress);
		    String shrxxaddress=bun.getString("shr_xxaddress");
		    TextView shrxxaddress2=(TextView)findViewById(R.id.txt_shrxxaddress);
		    shrxxaddress2.setText(shrxxaddress);
		    String hwname=bun.getString("hw_name");
		    TextView hwname2=(TextView)findViewById(R.id.txt_hwname);
		    hwname2.setText(hwname);
		    String hwgg=bun.getString("hw_gg");
		    TextView hwgg2=(TextView)findViewById(R.id.txt_hwgg);
		    hwgg2.setText(hwgg);
		    String hwnumber=bun.getString("hw_number");
		    TextView hwnumber2=(TextView)findViewById(R.id.txt_hwnumber);
		    hwnumber2.setText(hwnumber);
		    String hwweight=bun.getString("hw_weight");
		    TextView hwweight2=(TextView)findViewById(R.id.txt_hwweight);
		    hwweight2.setText(hwweight);
		    String hwvol=bun.getString("hw_vol");
		    TextView hwvol2=(TextView)findViewById(R.id.txt_hwvol);
		    hwvol2.setText(hwvol);
		    String hwbz=bun.getString("hw_bz");
		    TextView hwbz2=(TextView)findViewById(R.id.txt_hwbz);
		    hwbz2.setText(hwbz);
		    String hwremark=bun.getString("hw_remark");
		    TextView hwremark2=(TextView)findViewById(R.id.txt_hwremark);
		    hwremark2.setText(hwremark);
		    String ty_tel=bun.getString("ty_tel");
		    TextView ty_tel2=(TextView)findViewById(R.id.txt_ty_tel);
		    ty_tel2.setText(ty_tel);
		    String carriage_type=bun.getString("carriage_type");
		    TextView carriage_type2=(TextView)findViewById(R.id.txt_carriage_type);
		    carriage_type2.setText(carriage_type);
		    
		    fbnum=bun.getString("fbnum");
		    distance=bun.getString("distance");
		    
		    carriage=(EditText)findViewById(R.id.edit_carriage);
		    
		    PushManager.getInstance().initialize(this.getApplicationContext());
		    
		 // ��AndroidManifest.xml��meta-data�ж�ȡSDK������Ϣ
			String packageName = getApplicationContext().getPackageName();
			ApplicationInfo appInfo;
			try {
				appInfo = getPackageManager().getApplicationInfo(packageName, PackageManager.GET_META_DATA);
				if (appInfo.metaData != null) {

					appid = appInfo.metaData.getString("PUSH_APPID");
					appsecret = appInfo.metaData.getString("PUSH_APPSECRET");
					appkey = (appInfo.metaData.get("PUSH_APPKEY") != null) ? appInfo.metaData.get("PUSH_APPKEY").toString() : null;
				}

			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
		    //"�ӵ�"��ť����
			Button button=(Button)findViewById(R.id.btn_jd);
			//���ð�ť��ɫ
			GradientDrawable myGrad = (GradientDrawable)button.getBackground();
			myGrad.setColor(0xFF4f94cd);
//			final LinearLayout ca=(LinearLayout)getLayoutInflater().inflate(R.layout.input_carriage, null);
			
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){  
					if(carriage.length()!=0){
						new LogUser().execute(); 
					}else{
						Toast.makeText(Cy_driver_tyInfo_detail.this, "�������˷ѣ�", Toast.LENGTH_SHORT).show();
					}
		
				}
			});
			
		    //"�鿴����"��ť����
			Button button2=(Button)findViewById(R.id.btn_map);
			//���ð�ť��ɫ
			GradientDrawable myGrad2 = (GradientDrawable)button2.getBackground();
			myGrad2.setColor(0xFF4f94cd);		
			button2.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){  
			        Intent in = new Intent(getApplicationContext(),Cy_company_map.class); 
			        in.putExtra("fhaddress", fhaddress);
			        in.putExtra("ddaddress", ddaddress);
			        in.putExtra("distance", distance);		        
			        startActivityForResult(in, 1); 
		
				}
			});
		    
		}

		//�ӵ�
				class LogUser extends AsyncTask<String, String, String> {

					@Override
					protected void onPreExecute() {
						// TODO Auto-generated method stub
						super.onPreExecute();
						pDialog = new ProgressDialog(Cy_driver_tyInfo_detail.this); 
						pDialog.setMessage("������.."); 
						pDialog.setIndeterminate(false); 
						pDialog.setCancelable(true); 
						pDialog.show(); 
					}
					
					@Override
					protected String doInBackground(String... args) {
						// TODO Auto-generated method stub	
						Kxw_Application id3=((Kxw_Application)getApplicationContext());
						String cy_username=id3.get_cy_driver_username();
						String cy_driver_tel=id3.get_cy_driver_tel();
						carriage2=carriage.getText().toString();
						List<NameValuePair> para = new ArrayList<NameValuePair>(); 
						para.add(new BasicNameValuePair("cy_username", cy_username)); 
						para.add(new BasicNameValuePair("fbnum", fbnum)); 
						para.add(new BasicNameValuePair("carriage", carriage2)); 
						para.add(new BasicNameValuePair("cy_tel", cy_driver_tel)); 
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
							cid=json.getString("cid");
							//Log.d("ty_cid", cid);
							//new Push().execute();
							//payload();
							//push(cid);
//							IGtPush push = new IGtPush(host, appkey, MASTERSECRET);
//							NotificationTemplate template = new NotificationTemplate();
//						    template.setAppId(appid);
//						    template.setAppkey(appkey);
//						    // ����֪ͨ������������
//						    template.setTitle("������֪ͨ");
//						    template.setText("�������˵�������");
//						    // ����֪ͨ��ͼ��
//						    template.setLogo("car2.png");
//						    // ����֪ͨ������ͼ��
//						    //template.setLogoUrl("");
//						    // ����֪ͨ�Ƿ����壬�𶯣����߿����
//						    template.setIsRing(true);
//						    template.setIsVibrate(true);
//						    template.setIsClearable(true);
//						    // ͸����Ϣ���ã�1Ϊǿ������Ӧ�ã��ͻ��˽��յ���Ϣ��ͻ���������Ӧ�ã�2Ϊ�ȴ�Ӧ������
//						    template.setTransmissionType(2);
//						    template.setTransmissionContent("��������Ҫ͸��������");
//
//							SingleMessage message = new SingleMessage();
//						       message.setOffline(true);
//						        //������Чʱ�䣬��λΪ���룬��ѡ
//						        message.setOfflineExpireTime(24 * 3600 * 1000);
//						        message.setData(template);
//						        message.setPushNetWorkType(0); //��ѡ���ж��Ƿ�ͻ����Ƿ�wifi���������ͣ�1Ϊ��WIFI�����£�0Ϊ���������绷����
//						        Target target = new Target();
//						 
//						        target.setAppId(appid);
//						        target.setClientId(cid);
//						        //�û��������ͣ�cid���û�����ֻ��2��ѡ��һ
//						        //String alias = "��";
//						        //target.setAlias(alias);
//						        IPushResult ret = null;
//						        try{
//						            ret = push.pushMessageToSingle(message, target);
//						        }catch(RequestException e){
//						            e.printStackTrace();
//						            ret = push.pushMessageToSingle(message, target, e.getRequestId());
//						        }
//						        if(ret != null){
//						            System.out.println(ret.getResponse().toString());
//						        }else{
//						            System.out.println("��������Ӧ�쳣");
//						        }

 

							Map<String, Object> param = new HashMap<String, Object>();
							param.put("action", "pushSpecifyMessage"); // pushSpecifyMessageΪ�ӿ�����ע���Сд
							/*---���´��������趨�ӿ���Ӧ����---*/
							param.put("appkey", appkey);
							Log.d("appkey", appkey);
							Log.d("ty_cid", cid);
							param.put("type", 2); // �������ͣ� 2Ϊ��Ϣ
							param.put("pushTitle", "֪ͨ������"); // pushTitle����д����Ӧ������

							// ������Ϣ���ͣ���TransmissionMsg��LinkMsg��NotifyMsg���֣��˴���LinkMsg����
							param.put("pushType", "LinkMsg");

							param.put("offline", true); // �Ƿ����������Ϣ

							param.put("offlineTime", 72); // ��Ϣ���߱���ʱ��
							param.put("priority", 1); // �����������ȼ�

							List<String> cidList = new ArrayList<String>();
							cidList.add(cid); // ����ȡ��ClientID
							param.put("tokenMD5List", cidList);

							// ����Signֵ�����ڼ�Ȩ����ҪMasterSecret���������д
							param.put("sign", GetuiSdkHttpPost.makeSign(MASTERSECRET, param));

							// LinkMsg��Ϣʵ��
							Map<String, Object> linkMsg = new HashMap<String, Object>();
							linkMsg.put("linkMsgIcon", "car2.png"); // ��Ϣ��֪ͨ����ͼ��
							linkMsg.put("linkMsgTitle", "��������Ϣ"); // ������Ϣ�ı���
							linkMsg.put("linkMsgContent", "�������˵�������"); // ������Ϣ������
							linkMsg.put("linkMsgUrl", "http://120.27.26.219/kxw/"); // ���֪ͨ��ת��Ŀ����ҳ
							param.put("msg", linkMsg);
					           
							GetuiSdkHttpPost.httpPost(param); 	
							
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
						if(success==0) 
							Toast.makeText(getApplicationContext(), "ϵͳ����", Toast.LENGTH_SHORT).show();
						if(success==1) 
							Toast.makeText(getApplicationContext(), "�ӵ��ɹ���", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent();
					    intent.setClass(Cy_driver_tyInfo_detail.this, Cy_driver_mainActivity.class);
					    startActivity(intent);
					    finish();
						pDialog.dismiss();
					}
				
				}




	//����
//    public void push(String s){
//		// !!!!!!ע�⣺����Ϊ���Ʒ����API1.0�ӿڣ��������ԡ����Ƽ�������ϵͳʹ��1.0�����˽ӿڣ���ο����µĸ��Ʒ����API�ӿ��ĵ���ʹ�����µ�2.0��ӿ�
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("action", "pushSpecifyMessage"); // pushSpecifyMessageΪ�ӿ�����ע���Сд
//		/*---���´��������趨�ӿ���Ӧ����---*/
//		param.put("appkey", appkey);
//		param.put("type", 2); // �������ͣ� 2Ϊ��Ϣ
//		param.put("pushTitle", "֪ͨ������"); // pushTitle����д����Ӧ������
//
//		// ������Ϣ���ͣ���TransmissionMsg��LinkMsg��NotifyMsg���֣��˴���LinkMsg����
//		param.put("pushType", "LinkMsg");
//
//		param.put("offline", true); // �Ƿ����������Ϣ
//
//		param.put("offlineTime", 72); // ��Ϣ���߱���ʱ��
//		param.put("priority", 1); // �����������ȼ�
//
//		List<String> cidList = new ArrayList<String>();
//		cidList.add(cid); // ����ȡ��ClientID
//		param.put("tokenMD5List", cidList);
//
//		// ����Signֵ�����ڼ�Ȩ����ҪMasterSecret���������д
//		param.put("sign", GetuiSdkHttpPost.makeSign(MASTERSECRET, param));
//
//		// LinkMsg��Ϣʵ��
//		Map<String, Object> linkMsg = new HashMap<String, Object>();
//		//linkMsg.put("linkMsgIcon", "push.png"); // ��Ϣ��֪ͨ����ͼ��
//		linkMsg.put("linkMsgTitle", "֪ͨ������"); // ������Ϣ�ı���
//		linkMsg.put("linkMsgContent", "���յ�һ��������Ϣ���������www.igetui.com��"); // ������Ϣ������
//		linkMsg.put("linkMsgUrl", "http://www.igetui.com/"); // ���֪ͨ��ת��Ŀ����ҳ
//		param.put("msg", linkMsg);
//
//		GetuiSdkHttpPost.httpPost(param); 	
//    }
	//͸��
//	   public void payload(){
//			// !!!!!!ע�⣺����Ϊ���Ʒ����API1.0�ӿڣ��������ԡ����Ƽ�������ϵͳʹ��1.0�����˽ӿڣ���ο����µĸ��Ʒ����API�ӿ��ĵ���ʹ�����µ�2.0��ӿ�
//			Map<String, Object> param = new HashMap<String, Object>();
//			param.put("action", "pushmessage"); // pushmessageΪ�ӿ�����ע��ȫ��Сд
//			/*---���´��������趨�ӿ���Ӧ����---*/
//			param.put("appkey", appkey);
//			param.put("appid", appid);
//			// ע��͸�����ݺ�����������֤�ӿڵ����Ƿ�ɹ����ٶ���дΪhello girl~
//			param.put("data", "�յ�һ��͸��������Ϣ");
//
//			curDate = new Date(System.currentTimeMillis());
//			param.put("time", formatter.format(curDate)); // ��ǰ����ʱ�䣬��ѡ
//			param.put("clientid", cid); // ����ȡ��ClientID
//			param.put("expire", 3600); // ��Ϣ��ʱʱ�䣬��λΪ�룬��ѡ
//
//			// ����Signֵ�����ڼ�Ȩ
//			param.put("sign", GetuiSdkHttpPost.makeSign(MASTERSECRET, param));
//
//			GetuiSdkHttpPost.httpPost(param);	
//	    }


}
