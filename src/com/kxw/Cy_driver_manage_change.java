package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.kxw.Cy_driver_manage_detail.Delete;
import com.kxw.Cy_driver_publish.Driver_publish;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Cy_driver_manage_change extends Activity {
	private Spinner province_spinner,province_spinner2;
	private Spinner city_spinner,city_spinner2;
	private Spinner county_spinner,county_spinner2;
	private Integer provinceId, cityId,provinceId2, cityId2;
	private String strProvince, strCity, strCounty,strProvince2, strCity2, strCounty2;
	
	private int[] city = {R.array.beijin_province_item, R.array.tianjin_province_item, R.array.heibei_province_item, R.array.shanxi1_province_item, R.array.neimenggu_province_item, R.array.liaoning_province_item, R.array.jilin_province_item, R.array.heilongjiang_province_item, R.array.shanghai_province_item, R.array.jiangsu_province_item, R.array.zhejiang_province_item, R.array.anhui_province_item, R.array.fujian_province_item, R.array.jiangxi_province_item, R.array.shandong_province_item, R.array.henan_province_item, R.array.hubei_province_item, R.array.hunan_province_item, R.array.guangdong_province_item,  R.array.guangxi_province_item, R.array.hainan_province_item, R.array.chongqing_province_item, R.array.sichuan_province_item, R.array.guizhou_province_item, R.array.yunnan_province_item, R.array.xizang_province_item, R.array.shanxi2_province_item, R.array.gansu_province_item, R.array.qinghai_province_item, R.array.linxia_province_item, R.array.xinjiang_province_item, R.array.hongkong_province_item, R.array.aomen_province_item, R.array.taiwan_province_item};
	private int[] countyOfBeiJing = {R.array.beijin_city_item};
	private int[] countyOfTianJing = {R.array.tianjin_city_item};
	private int[] countyOfHeBei = {R.array.shijiazhuang_city_item, R.array.tangshan_city_item, R.array.qinghuangdao_city_item, R.array.handan_city_item, R.array.xingtai_city_item, R.array.baoding_city_item, R.array.zhangjiakou_city_item, R.array.chengde_city_item, R.array.cangzhou_city_item, R.array.langfang_city_item, R.array.hengshui_city_item};
	private int[] countyOfShanXi1 = {R.array.taiyuan_city_item, R.array.datong_city_item, R.array.yangquan_city_item, R.array.changzhi_city_item, R.array.jincheng_city_item, R.array.shuozhou_city_item, R.array.jinzhong_city_item, R.array.yuncheng_city_item, R.array.xinzhou_city_item, R.array.linfen_city_item, R.array.lvliang_city_item};
	private int[] countyOfNeiMengGu = {R.array.huhehaote_city_item, R.array.baotou_city_item, R.array.wuhai_city_item, R.array.chifeng_city_item, R.array.tongliao_city_item, R.array.eerduosi_city_item, R.array.hulunbeier_city_item, R.array.bayannaoer_city_item, R.array.wulanchabu_city_item, R.array.xinganmeng_city_item, R.array.xilinguolemeng_city_item, R.array.alashanmeng_city_item};
	private int[] countyOfLiaoNing = {R.array.shenyang_city_item, R.array.dalian_city_item, R.array.anshan_city_item, R.array.wushun_city_item, R.array.benxi_city_item, R.array.dandong_city_item, R.array.liaoning_jinzhou_city_item, R.array.yingkou_city_item, R.array.fuxin_city_item, R.array.liaoyang_city_item, R.array.panjin_city_item, R.array.tieling_city_item, R.array.zhaoyang_city_item, R.array.huludao_city_item};
	private int[] countyOfJiLin = {R.array.changchun_city_item, R.array.jilin_city_item, R.array.siping_city_item, R.array.liaoyuan_city_item, R.array.tonghua_city_item, R.array.baishan_city_item, R.array.songyuan_city_item, R.array.baicheng_city_item, R.array.yanbian_city_item};
	private int[] countyOfHeiLongJiang = {R.array.haerbing_city_item, R.array.qiqihaer_city_item, R.array.jixi_city_item, R.array.hegang_city_item, R.array.shuangyashan_city_item, R.array.daqing_city_item, R.array.heilongjiang_yichun_city_item, R.array.jiamusi_city_item, R.array.qitaihe_city_item, R.array.mudanjiang_city_item, R.array.heihe_city_item, R.array.suihua_city_item, R.array.daxinganling_city_item};
	private int[] countyOfShangHai = {R.array.shanghai_city_item};
	
	private int[] countyOfJiangSu = {R.array.nanjing_city_item, R.array.wuxi_city_item, R.array.xuzhou_city_item, R.array.changzhou_city_item, R.array.nanjing_suzhou_city_item, R.array.nantong_city_item, R.array.lianyungang_city_item, R.array.huaian_city_item, R.array.yancheng_city_item, R.array.yangzhou_city_item, R.array.zhenjiang_city_item, R.array.jiangsu_taizhou_city_item, R.array.suqian_city_item};
	private int[] countyOfZheJiang = {R.array.hangzhou_city_item, R.array.ningbo_city_item, R.array.wenzhou_city_item, R.array.jiaxing_city_item, R.array.huzhou_city_item, R.array.shaoxing_city_item, R.array.jinhua_city_item, R.array.quzhou_city_item, R.array.zhoushan_city_item, R.array.zejiang_huzhou_city_item, R.array.lishui_city_item};
	private int[] countyOfAnHui = {R.array.hefei_city_item, R.array.wuhu_city_item, R.array.bengbu_city_item, R.array.huainan_city_item, R.array.maanshan_city_item, R.array.huaibei_city_item, R.array.tongling_city_item, R.array.anqing_city_item, R.array.huangshan_city_item, R.array.chuzhou_city_item, R.array.fuyang_city_item, R.array.anhui_suzhou_city_item, R.array.chaohu_city_item, R.array.luan_city_item, R.array.haozhou_city_item, R.array.chizhou_city_item, R.array.xuancheng_city_item};
	private int[] countyOfFuJian = {R.array.huzhou_city_item, R.array.xiamen_city_item, R.array.putian_city_item, R.array.sanming_city_item, R.array.quanzhou_city_item, R.array.zhangzhou_city_item, R.array.nanp_city_item, R.array.longyan_city_item, R.array.ningde_city_item};
	private int[] countyOfJiangXi = {R.array.nanchang_city_item, R.array.jingdezhen_city_item, R.array.pingxiang_city_item, R.array.jiujiang_city_item, R.array.xinyu_city_item, R.array.yingtan_city_item, R.array.ganzhou_city_item, R.array.jian_city_item, R.array.jiangxi_yichun_city_item, R.array.jiangxi_wuzhou_city_item, R.array.shangrao_city_item};
	private int[] countyOfShanDong = {R.array.jinan_city_item, R.array.qingdao_city_item, R.array.zaobo_city_item, R.array.zaozhuang_city_item, R.array.dongying_city_item, R.array.yantai_city_item, R.array.weifang_city_item, R.array.jining_city_item, R.array.taian_city_item, R.array.weihai_city_item, R.array.rizhao_city_item, R.array.laiwu_city_item, R.array.linxi_city_item, R.array.dezhou_city_item, R.array.liaocheng_city_item, R.array.shandong_bingzhou_city_item, R.array.heze_city_item};
	private int[] countyOfHeNan = {R.array.zhenshou_city_item, R.array.kaifang_city_item, R.array.luoyang_city_item, R.array.kaipingshan_city_item, R.array.anyang_city_item, R.array.hebi_city_item, R.array.xinxiang_city_item, R.array.jiaozuo_city_item, R.array.buyang_city_item, R.array.xuchang_city_item, R.array.leihe_city_item, R.array.sanmenxia_city_item, R.array.nanyang_city_item, R.array.shangqiu_city_item, R.array.xinyang_city_item, R.array.zhoukou_city_item, R.array.zhumadian_city_item};
	private int[] countyOfHuBei = {R.array.wuhan_city_item, R.array.huangshi_city_item, R.array.shiyan_city_item, R.array.yichang_city_item, R.array.xiangpan_city_item, R.array.erzhou_city_item, R.array.jinmen_city_item, R.array.xiaogan_city_item, R.array.hubei_jinzhou_city_item, R.array.huanggang_city_item, R.array.xianning_city_item, R.array.suizhou_city_item, R.array.enshi_city_item, R.array.shenglongjia_city_item};
	
	private int[] countyOfHuNan = {R.array.changsha_city_item, R.array.zhuzhou_city_item, R.array.xiangtan_city_item, R.array.hengyang_city_item, R.array.shaoyang_city_item, R.array.yueyang_city_item, R.array.changde_city_item, R.array.zhangjiajie_city_item, R.array.yiyang_city_item, R.array.hunan_bingzhou_city_item, R.array.yongzhou_city_item, R.array.huaihua_city_item, R.array.loudi_city_item, R.array.xiangxi_city_item};
	private int[] countyOfGuangDong = {R.array.guangzhou_city_item, R.array.shaoguan_city_item, R.array.shenzhen_city_item, R.array.zhuhai_city_item, R.array.shantou_city_item, R.array.foshan_city_item, R.array.jiangmen_city_item, R.array.zhangjiang_city_item, R.array.maoming_city_item, R.array.zhaoqing_city_item, R.array.huizhou_city_item, R.array.meizhou_city_item, R.array.shanwei_city_item, R.array.heyuan_city_item, R.array.yangjiang_city_item, R.array.qingyuan_city_item, R.array.dongguan_city_item, R.array.zhongshan_city_item, R.array.chaozhou_city_item, R.array.jiyang_city_item, R.array.yunfu_city_item};
	private int[] countyOfGuangXi = {R.array.nanning_city_item, R.array.liuzhou_city_item, R.array.guilin_city_item, R.array.guangxi_wuzhou_city_item, R.array.beihai_city_item, R.array.fangchenggang_city_item, R.array.qinzhou_city_item, R.array.guigang_city_item, R.array.yuelin_city_item, R.array.baise_city_item, R.array.hezhou_city_item, R.array.hechi_city_item, R.array.laibing_city_item, R.array.chuangzuo_city_item};
	private int[] countyOfHaiNan = {R.array.haikou_city_item, R.array.sanya_city_item};
	private int[] countyOfChongQing = {R.array.chongqing_city_item};
	private int[] countyOfSiChuan = {R.array.chengdu_city_item, R.array.zigong_city_item, R.array.panzhihua_city_item, R.array.luzhou_city_item, R.array.deyang_city_item, R.array.mianyang_city_item, R.array.guangyuan_city_item, R.array.suining_city_item, R.array.neijiang_city_item, R.array.leshan_city_item, R.array.nanchong_city_item, R.array.meishan_city_item, R.array.yibing_city_item, R.array.guangan_city_item, R.array.dazhou_city_item, R.array.yaan_city_item, R.array.bazhong_city_item, R.array.ziyang_city_item, R.array.abei_city_item, R.array.ganmu_city_item, R.array.liangshan_city_item};
	private int[] countyOfGuiZhou = {R.array.guiyang_city_item, R.array.lupanshui_city_item, R.array.zhunyi_city_item, R.array.anshun_city_item, R.array.tongren_city_item, R.array.qingxinan_city_item, R.array.biji_city_item, R.array.qingdongnan_city_item, R.array.qingnan_city_item};
	private int[] countyOfYunNan = {R.array.kunming_city_item, R.array.qujing_city_item, R.array.yuexi_city_item, R.array.baoshan_city_item, R.array.zhaotong_city_item, R.array.lijiang_city_item, R.array.simao_city_item, R.array.lingcang_city_item, R.array.chuxiong_city_item, R.array.honghe_city_item, R.array.wenshan_city_item, R.array.xishuangbanna_city_item, R.array.dali_city_item, R.array.dehuang_city_item, R.array.nujiang_city_item, R.array.diqing_city_item};
	private int[] countyOfXiZang = {R.array.lasa_city_item, R.array.changdu_city_item, R.array.shannan_city_item, R.array.rgeze_city_item, R.array.naqu_city_item, R.array.ali_city_item, R.array.linzhi_city_item};
	
	private int[] countyOfShanXi2 = {R.array.xian_city_item, R.array.tongchuan_city_item, R.array.baoji_city_item, R.array.xianyang_city_item, R.array.weinan_city_item, R.array.yanan_city_item, R.array.hanzhong_city_item, R.array.yulin_city_item, R.array.ankang_city_item, R.array.shangluo_city_item};
	private int[] countyOfGanSu = {R.array.lanzhou_city_item, R.array.jiayuguan_city_item, R.array.jinchang_city_item, R.array.baiyin_city_item, R.array.tianshui_city_item, R.array.wuwei_city_item, R.array.zhangyue_city_item, R.array.pingliang_city_item, R.array.jiuquan_city_item, R.array.qingyang_city_item, R.array.dingxi_city_item, R.array.longnan_city_item, R.array.linxia_city_item, R.array.gannan_city_item};
	private int[] countyOfQingHai = {R.array.xining_city_item, R.array.haidong_city_item, R.array.haibai_city_item, R.array.huangnan_city_item, R.array.hainan_city_item, R.array.guluo_city_item, R.array.yushu_city_item, R.array.haixi_city_item};
	private int[] countyOfNingXia = {R.array.yinchuan_city_item, R.array.shizuishan_city_item, R.array.wuzhong_city_item, R.array.guyuan_city_item, R.array.zhongwei_city_item};
	private int[] countyOfXinJiang = {R.array.wulumuqi_city_item, R.array.kelamayi_city_item, R.array.tulyfan_city_item, R.array.hami_city_item, R.array.changji_city_item, R.array.boertala_city_item, R.array.bayinguolen_city_item, R.array.akesu_city_item, R.array.kemuleisu_city_item, R.array.geshen_city_item, R.array.hetian_city_item, R.array.yili_city_item, R.array.tacheng_city_item, R.array.aleitai_city_item, R.array.shihezi_city_item, R.array.alaer_city_item, R.array.tumushihe_city_item, R.array.wujiaqu_city_item};
	private int[] countyOfHongKong = {R.array.hongkongdao_city_item,R.array.jiulong_city_item,R.array.xinjie_city_item};
	private int[] countyOfAoMen = {R.array.aomenbandao_city_item,R.array.aomenlidao_city_item,R.array.tiantangqu_city_item};
	private int[] countyOfTaiWan = {};
	
	private ArrayAdapter<CharSequence> province_adapter;
	private ArrayAdapter<CharSequence> city_adapter;
	private ArrayAdapter<CharSequence> county_adapter;
	private ArrayAdapter<CharSequence> province_adapter2;
	private ArrayAdapter<CharSequence> city_adapter2;
	private ArrayAdapter<CharSequence> county_adapter2;
	
	private static String url_create_product = "http://120.27.26.219/kxw/android/cy_driver_carinfo_change.php"; 
    private static final String TAG_SUCCESS = "success"; 
    private ProgressDialog pDialog; 
    int success=0;
	JSONParser jsonParser = new JSONParser();
	EditText load_address,destination_address,car_type,car_number,car_length,car_weight,car_vol,
	go_time,tel2,remark2,fbnum2;
	String fbnum,fhprovince2,fhcity2,fhcounty2,ddprovince2,ddcity2,ddcounty2,cartype2,length,vol,weight,
	number,remark,time,tel;
	Bundle bun;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cy_driver_manage_change);
		ActivityCollector.getInstance().addActivity(this);
		loadSpinner();

		Intent intent=getIntent();
	    bun = intent.getExtras();

		fbnum=bun.getString("fbnum");
		
		car_number=(EditText)findViewById(R.id.edit_number);
		car_number.setText(bun.getString("car_number"));
		
		car_length=(EditText)findViewById(R.id.edit_length);
		car_length.setText(bun.getString("car_length"));
		
		car_weight=(EditText)findViewById(R.id.edit_weight);
		car_weight.setText(bun.getString("car_weight"));
		
		car_vol=(EditText)findViewById(R.id.edit_vol);
		car_vol.setText(bun.getString("car_vol"));

		go_time=(EditText)findViewById(R.id.edit_time);
		go_time.setText(bun.getString("go_time"));

		tel2=(EditText)findViewById(R.id.edit_tel);
		tel2.setText(bun.getString("tel"));

		remark2=(EditText)findViewById(R.id.edit_remark);
		remark2.setText(bun.getString("remark"));

		
		//"修改信息"按钮监听
		Button c=(Button)findViewById(R.id.btn_submit);
		//设置按钮颜色
		GradientDrawable myGrad5 = (GradientDrawable)c.getBackground();
		myGrad5.setColor(0xFF4f94cd);	
		c.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				//获取编辑框内容
				Spinner fhprovince=((Spinner)findViewById(R.id.province_spinner));
				fhprovince2=fhprovince.getSelectedItem().toString();
				Spinner fhcity=((Spinner)findViewById(R.id.city_spinner));
				fhcity2=fhcity.getSelectedItem().toString();
				Spinner fhcounty=((Spinner)findViewById(R.id.county_spinner));
				fhcounty2=fhcounty.getSelectedItem().toString();
				Spinner ddprovince=((Spinner)findViewById(R.id.province_spinner2));
				ddprovince2=ddprovince.getSelectedItem().toString();
				Spinner ddcity=((Spinner)findViewById(R.id.city_spinner2));
				ddcity2=ddcity.getSelectedItem().toString();
				Spinner ddcounty=((Spinner)findViewById(R.id.county_spinner2));
				ddcounty2=ddcounty.getSelectedItem().toString();
				Spinner cartype=((Spinner)findViewById(R.id.spin_cartype));
				cartype2=cartype.getSelectedItem().toString();
				length=((EditText)findViewById(R.id.edit_length)).getText().toString();
				number=((EditText)findViewById(R.id.edit_number)).getText().toString();
				vol=((EditText)findViewById(R.id.edit_vol)).getText().toString();
				weight=((EditText)findViewById(R.id.edit_weight)).getText().toString();
				time=((EditText)findViewById(R.id.edit_time)).getText().toString();
				remark=((EditText)findViewById(R.id.edit_remark)).getText().toString();
				tel=((EditText)findViewById(R.id.edit_tel)).getText().toString();
				//判断编辑框是否全部填写
				if(!"".equals(length)&&!"".equals(number)&&!"".equals(vol)&&!"".equals(weight)&&!"".equals(time)&&!"".equals(tel)){									
				    if(time.length()!=8){
  					    Toast.makeText(Cy_driver_manage_change.this, "发车时间格式不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_time)).setText("");
					    ((EditText)findViewById(R.id.edit_time)).requestFocus();
  				    }else if(!isMobileNO(tel)){
					        Toast.makeText(Cy_driver_manage_change.this, "手机号码不正确", Toast.LENGTH_LONG).show();
					        ((EditText)findViewById(R.id.edit_tel)).setText("");
				        ((EditText)findViewById(R.id.edit_tel)).requestFocus();
			        }else{
					    AlertDialog alert =new AlertDialog.Builder(Cy_driver_manage_change.this).create();
					    alert.setTitle("系统提示:");
					    alert.setMessage("确认修改?");
					    //添加"取消"按钮
					    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
					    	}
					    });
					    //添加"确定"按钮
					    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener(){
					    	@Override
					    	public void onClick(DialogInterface dialog,int which){
					    		new Change().execute();
					    	}
					    });
					    alert.show();  					    
  				        }
				
				}else{
					Toast.makeText(Cy_driver_manage_change.this, "请将信息输入完整", Toast.LENGTH_LONG).show();
				}


			}
		});

	}
	//修改信息
	class Change extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Cy_driver_manage_change.this); 
			pDialog.setMessage("Loging.."); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub	
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("fbnum", fbnum)); 
			para.add(new BasicNameValuePair("fhaddress", fhprovince2+","+fhcity2+","+fhcounty2)); 
			para.add(new BasicNameValuePair("ddaddress", ddprovince2+","+ddcity2+","+ddcounty2)); 
			para.add(new BasicNameValuePair("cartype", cartype2));
			para.add(new BasicNameValuePair("length", length));
			para.add(new BasicNameValuePair("weight", weight));
			para.add(new BasicNameValuePair("number", number));
			para.add(new BasicNameValuePair("vol", vol));
			para.add(new BasicNameValuePair("time", time));
			para.add(new BasicNameValuePair("remark", remark));
			para.add(new BasicNameValuePair("tel", tel));
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
				Intent it= new Intent();							
				it.setClass(Cy_driver_manage_change.this,Cy_driver_manage.class);
				startActivity(it);
				finish();
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
			if(success==1) 
				Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_SHORT).show();
			pDialog.dismiss();
		}
	
	}
	
	//发货地省市区三级联动
	private void loadSpinner()
	{
		province_spinner = (Spinner) findViewById(R.id.province_spinner);		
		province_adapter = ArrayAdapter.createFromResource(this, R.array.province_item, android.R.layout.simple_spinner_item);
		province_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	province_spinner.setAdapter(province_adapter);
    	
    	province_spinner2 = (Spinner) findViewById(R.id.province_spinner2);
    	province_adapter2 = ArrayAdapter.createFromResource(this, R.array.province_item, android.R.layout.simple_spinner_item);
		province_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	province_spinner2.setAdapter(province_adapter2);
//    	select(province_spinner, province_adapter, R.array.province_item);
    	province_spinner.setOnItemSelectedListener(new OnItemSelectedListener() 
    	{	
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			{					
				provinceId = province_spinner.getSelectedItemPosition();
				strProvince = province_spinner.getSelectedItem().toString();
				city_spinner = (Spinner) findViewById(R.id.city_spinner);
				if(true)
				{	
					Log.v("test", "province: "+province_spinner.getSelectedItem().toString()+provinceId.toString());
					county_spinner = (Spinner) findViewById(R.id.county_spinner);
					city_spinner = (Spinner) findViewById(R.id.city_spinner);
					select(city_spinner, city_adapter, city[provinceId]);
					city_spinner.setOnItemSelectedListener(new OnItemSelectedListener() 
					{
						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							cityId = city_spinner.getSelectedItemPosition();
							strCity = city_spinner.getSelectedItem().toString();
							Log.v("test", "city: "+city_spinner.getSelectedItem().toString()+cityId.toString());
							if(true)
							{
								county_spinner = (Spinner) findViewById(R.id.county_spinner);
								county_spinner.setPrompt("请选择县区");
								switch (provinceId) {
								case 0:
									select(county_spinner, county_adapter, countyOfBeiJing[cityId]);
									break;
								case 1:
									select(county_spinner, county_adapter, countyOfTianJing[cityId]);
									break;
								case 2:
									select(county_spinner, county_adapter, countyOfHeBei[cityId]);
									break;
								case 3:
									select(county_spinner, county_adapter, countyOfShanXi1[cityId]);
									break;
								case 4:
									select(county_spinner, county_adapter, countyOfNeiMengGu[cityId]);
									break;
								case 5:
									select(county_spinner, county_adapter, countyOfLiaoNing[cityId]);
									break;
								case 6:
									select(county_spinner, county_adapter, countyOfJiLin[cityId]);
									break;
								case 7:
									select(county_spinner, county_adapter, countyOfHeiLongJiang[cityId]);
									break;
								case 8:
									select(county_spinner, county_adapter, countyOfShangHai[cityId]);
									break;
								case 9:
									select(county_spinner, county_adapter, countyOfJiangSu[cityId]);
									break;
								case 10:
									select(county_spinner, county_adapter, countyOfZheJiang[cityId]);
									break;
								case 11:
									select(county_spinner, county_adapter, countyOfAnHui[cityId]);
									break;
								case 12:
									select(county_spinner, county_adapter, countyOfFuJian[cityId]);
									break;
								case 13:
									select(county_spinner, county_adapter, countyOfJiangXi[cityId]);
									break;
								case 14:
									select(county_spinner, county_adapter, countyOfShanDong[cityId]);
									break;
								case 15:
									select(county_spinner, county_adapter, countyOfHeNan[cityId]);
									break;
								case 16:
									select(county_spinner, county_adapter, countyOfHuBei[cityId]);
									break;
								case 17:
									select(county_spinner, county_adapter, countyOfHuNan[cityId]);
									break;
								case 18:
									select(county_spinner, county_adapter, countyOfGuangDong[cityId]);
									break;
								case 19:
									select(county_spinner, county_adapter, countyOfGuangXi[cityId]);
									break;
								case 20:
									select(county_spinner, county_adapter, countyOfHaiNan[cityId]);
									break;
								case 21:
									select(county_spinner, county_adapter, countyOfChongQing[cityId]);
									break;
								case 22:
									select(county_spinner, county_adapter, countyOfSiChuan[cityId]);
									break;
								case 23:
									select(county_spinner, county_adapter, countyOfGuiZhou[cityId]);
									break;
								case 24:
									select(county_spinner, county_adapter, countyOfYunNan[cityId]);
									break;
								case 25:
									select(county_spinner, county_adapter, countyOfXiZang[cityId]);
									break;
								case 26:
									select(county_spinner, county_adapter, countyOfShanXi2[cityId]);
									break;
								case 27:
									select(county_spinner, county_adapter, countyOfGanSu[cityId]);
									break;
								case 28:
									select(county_spinner, county_adapter, countyOfQingHai[cityId]);
									break;
								case 29:
									select(county_spinner, county_adapter, countyOfNingXia[cityId]);
									break;
								case 30:
									select(county_spinner, county_adapter, countyOfXinJiang[cityId]);
									break;
								case 31:
									select(county_spinner, county_adapter, countyOfHongKong[cityId]);
									break;
								case 32:
									select(county_spinner, county_adapter, countyOfAoMen[cityId]);
									break;
								case 33:
									select(county_spinner, county_adapter, countyOfTaiWan[cityId]);
									break;
				
								default:
									break;
								}
								
								county_spinner.setOnItemSelectedListener(new OnItemSelectedListener() 
								{

									@Override
									public void onItemSelected(
											AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
										strCounty = county_spinner.getSelectedItem().toString();
									}
									@Override
									public void onNothingSelected(
											AdapterView<?> arg0) {	
										
									}								
								});
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub							
						}
					});							
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
    	province_spinner2.setOnItemSelectedListener(new OnItemSelectedListener() 
    	{	
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) 
			{					
				provinceId2 = province_spinner2.getSelectedItemPosition();
				strProvince2 = province_spinner2.getSelectedItem().toString();
				city_spinner2 = (Spinner) findViewById(R.id.city_spinner2);
				if(true)
				{	
					Log.v("test", "province: "+province_spinner2.getSelectedItem().toString()+provinceId2.toString());
					county_spinner2 = (Spinner) findViewById(R.id.county_spinner2);
					city_spinner2 = (Spinner) findViewById(R.id.city_spinner2);
					select(city_spinner2, city_adapter2, city[provinceId2]);
					city_spinner2.setOnItemSelectedListener(new OnItemSelectedListener() 
					{
						@Override
						public void onItemSelected(AdapterView<?> arg0,
								View arg1, int arg2, long arg3) {
							cityId2 = city_spinner2.getSelectedItemPosition();
							strCity2 = city_spinner2.getSelectedItem().toString();
							Log.v("test", "city: "+city_spinner2.getSelectedItem().toString()+cityId2.toString());
							if(true)
							{
								county_spinner2 = (Spinner) findViewById(R.id.county_spinner2);
								county_spinner2.setPrompt("请选择县区");
								switch (provinceId2) {
								case 0:
									select(county_spinner2, county_adapter2, countyOfBeiJing[cityId2]);
									break;
								case 1:
									select(county_spinner2, county_adapter2, countyOfTianJing[cityId2]);
									break;
								case 2:
									select(county_spinner2, county_adapter2, countyOfHeBei[cityId2]);
									break;
								case 3:
									select(county_spinner2, county_adapter2, countyOfShanXi1[cityId2]);
									break;
								case 4:
									select(county_spinner2, county_adapter2, countyOfNeiMengGu[cityId2]);
									break;
								case 5:
									select(county_spinner2, county_adapter2, countyOfLiaoNing[cityId2]);
									break;
								case 6:
									select(county_spinner2, county_adapter2, countyOfJiLin[cityId2]);
									break;
								case 7:
									select(county_spinner2, county_adapter2, countyOfHeiLongJiang[cityId2]);
									break;
								case 8:
									select(county_spinner2, county_adapter2, countyOfShangHai[cityId2]);
									break;
								case 9:
									select(county_spinner2, county_adapter2, countyOfJiangSu[cityId2]);
									break;
								case 10:
									select(county_spinner2, county_adapter2, countyOfZheJiang[cityId2]);
									break;
								case 11:
									select(county_spinner2, county_adapter2, countyOfAnHui[cityId2]);
									break;
								case 12:
									select(county_spinner2, county_adapter2, countyOfFuJian[cityId2]);
									break;
								case 13:
									select(county_spinner2, county_adapter2, countyOfJiangXi[cityId2]);
									break;
								case 14:
									select(county_spinner2, county_adapter2, countyOfShanDong[cityId2]);
									break;
								case 15:
									select(county_spinner2, county_adapter2, countyOfHeNan[cityId2]);
									break;
								case 16:
									select(county_spinner2, county_adapter2, countyOfHuBei[cityId2]);
									break;
								case 17:
									select(county_spinner2, county_adapter2, countyOfHuNan[cityId2]);
									break;
								case 18:
									select(county_spinner2, county_adapter2, countyOfGuangDong[cityId2]);
									break;
								case 19:
									select(county_spinner2, county_adapter2, countyOfGuangXi[cityId2]);
									break;
								case 20:
									select(county_spinner2, county_adapter2, countyOfHaiNan[cityId2]);
									break;
								case 21:
									select(county_spinner2, county_adapter2, countyOfChongQing[cityId2]);
									break;
								case 22:
									select(county_spinner2, county_adapter2, countyOfSiChuan[cityId2]);
									break;
								case 23:
									select(county_spinner2, county_adapter2, countyOfGuiZhou[cityId2]);
									break;
								case 24:
									select(county_spinner2, county_adapter2, countyOfYunNan[cityId2]);
									break;
								case 25:
									select(county_spinner2, county_adapter2, countyOfXiZang[cityId2]);
									break;
								case 26:
									select(county_spinner2, county_adapter2, countyOfShanXi2[cityId2]);
									break;
								case 27:
									select(county_spinner2, county_adapter2, countyOfGanSu[cityId2]);
									break;
								case 28:
									select(county_spinner2, county_adapter2, countyOfQingHai[cityId2]);
									break;
								case 29:
									select(county_spinner2, county_adapter2, countyOfNingXia[cityId2]);
									break;
								case 30:
									select(county_spinner2, county_adapter2, countyOfXinJiang[cityId2]);
									break;
								case 31:
									select(county_spinner2, county_adapter2, countyOfHongKong[cityId2]);
									break;
								case 32:
									select(county_spinner2, county_adapter2, countyOfAoMen[cityId2]);
									break;
								case 33:
									select(county_spinner2, county_adapter2, countyOfTaiWan[cityId2]);
									break;
				
								default:
									break;
								}
								
								county_spinner2.setOnItemSelectedListener(new OnItemSelectedListener() 
								{

									@Override
									public void onItemSelected(
											AdapterView<?> arg0, View arg1,
											int arg2, long arg3) {
										strCounty2 = county_spinner2.getSelectedItem().toString();
									}
									@Override
									public void onNothingSelected(
											AdapterView<?> arg0) {	
										
									}								
								});
							}
						}
						@Override
						public void onNothingSelected(AdapterView<?> arg0) {
							// TODO Auto-generated method stub							
						}
					});							
				}
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
	}	
	private void select(Spinner spin, ArrayAdapter<CharSequence> adapter, int arry)
	{
		adapter = ArrayAdapter.createFromResource(this, arry, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adapter);
		//spin.setSelection(0,true);
	}
	//手机号码正则
	public static boolean isMobileNO(String mobiles) {  
			String telRegex = "[1][3458]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
			if (TextUtils.isEmpty(mobiles)) return false;  
			else return mobiles.matches(telRegex);  
	}
	//左上角返回上一级
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



