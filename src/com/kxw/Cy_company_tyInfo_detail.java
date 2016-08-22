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

import com.igexin.sdk.PushManager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Cy_company_tyInfo_detail extends Activity {
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_tyinfo_detail.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;
    String fbnum,carriage2,cy_username,cid,distance,fhaddress,ddaddress;
    EditText carriage;
	JSONParser jsonParser = new JSONParser();
	
	private static final String MASTERSECRET = "6NnZJeZFYl622MLW1H9V48";
	private String appkey = "";
	private String appsecret = "";
	private String appid = "";
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
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
		    String carriage_type=bun.getString("carriage_type");
		    TextView carriage_type2=(TextView)findViewById(R.id.txt_carriage_type);
		    carriage_type2.setText(carriage_type);
		    String ty_tel=bun.getString("ty_tel");
		    TextView ty_tel2=(TextView)findViewById(R.id.txt_ty_tel);
		    ty_tel2.setText(ty_tel);
		    fbnum=bun.getString("fbnum");
		    distance=bun.getString("distance");
		    
		    carriage=(EditText)findViewById(R.id.edit_carriage);
		    
		    PushManager.getInstance().initialize(this.getApplicationContext());
		    
		 // 从AndroidManifest.xml的meta-data中读取SDK配置信息
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

		    //"接单"按钮监听
			Button button=(Button)findViewById(R.id.btn_jd);
			//设置按钮颜色
			GradientDrawable myGrad = (GradientDrawable)button.getBackground();
			myGrad.setColor(0xFF4f94cd);		
			button.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v){  
					if(carriage.length()!=0){
						new LogUser().execute(); 
					}else{
						Toast.makeText(Cy_company_tyInfo_detail.this, "请输入运费！", Toast.LENGTH_SHORT).show();
					}
		
				}
			});

		    //"查看距离"按钮监听
			Button button2=(Button)findViewById(R.id.btn_map);
			//设置按钮颜色
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

		//访问数据库
				class LogUser extends AsyncTask<String, String, String> {

					@Override
					protected void onPreExecute() {
						// TODO Auto-generated method stub
						super.onPreExecute();
						pDialog = new ProgressDialog(Cy_company_tyInfo_detail.this); 
						pDialog.setMessage("处理中.."); 
						pDialog.setIndeterminate(false); 
						pDialog.setCancelable(true); 
						pDialog.show(); 
					}
					
					@Override
					protected String doInBackground(String... args) {
						// TODO Auto-generated method stub	
						Kxw_Application id3=((Kxw_Application)getApplicationContext());
						String cy_username=id3.get_cy_company_username();
						carriage2=carriage.getText().toString();
						String cy_company_tel=id3.get_cy_company_tel();
						
						
						List<NameValuePair> para = new ArrayList<NameValuePair>(); 
						para.add(new BasicNameValuePair("cy_username", cy_username)); 
						para.add(new BasicNameValuePair("fbnum", fbnum)); 
						para.add(new BasicNameValuePair("carriage", carriage2)); 
						para.add(new BasicNameValuePair("cy_tel", cy_company_tel));
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

							Map<String, Object> param = new HashMap<String, Object>();
							param.put("action", "pushSpecifyMessage"); // pushSpecifyMessage为接口名，注意大小写
							/*---以下代码用于设定接口相应参数---*/
							param.put("appkey", appkey);
							Log.d("appkey", appkey);
							Log.d("ty_cid", cid);
							param.put("type", 2); // 推送类型： 2为消息
							param.put("pushTitle", "通知栏测试"); // pushTitle请填写您的应用名称

							// 推送消息类型，有TransmissionMsg、LinkMsg、NotifyMsg三种，此处以LinkMsg举例
							param.put("pushType", "LinkMsg");

							param.put("offline", true); // 是否进入离线消息

							param.put("offlineTime", 72); // 消息离线保留时间
							param.put("priority", 1); // 推送任务优先级

							List<String> cidList = new ArrayList<String>();
							cidList.add(cid); // 您获取的ClientID
							param.put("tokenMD5List", cidList);

							// 生成Sign值，用于鉴权，需要MasterSecret，请务必填写
							param.put("sign", GetuiSdkHttpPost.makeSign(MASTERSECRET, param));

							// LinkMsg消息实体
							Map<String, Object> linkMsg = new HashMap<String, Object>();
							linkMsg.put("linkMsgIcon", "car2.png"); // 消息在通知栏的图标
							linkMsg.put("linkMsgTitle", "控险网消息"); // 推送消息的标题
							linkMsg.put("linkMsgContent", "您的托运单单被接收"); // 推送消息的内容
							linkMsg.put("linkMsgUrl", "http://120.27.26.219/kxw/"); // 点击通知跳转的目标网页
							param.put("msg", linkMsg);
					           
							GetuiSdkHttpPost.httpPost(param); 	
							
							Intent intent=new Intent();
						    intent.setClass(Cy_company_tyInfo_detail.this, Cy_company_tyInfo.class);
						    startActivity(intent);
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
							Toast.makeText(getApplicationContext(), "系统出错！", Toast.LENGTH_SHORT).show();
						if(success==1) 
							Toast.makeText(getApplicationContext(), "接单成功！", Toast.LENGTH_SHORT).show();
						Intent intent=new Intent();
					    intent.setClass(Cy_company_tyInfo_detail.this, Cy_company_tyInfo.class);
					    startActivity(intent);
					    finish();
						pDialog.dismiss();
					}
				
				}

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

