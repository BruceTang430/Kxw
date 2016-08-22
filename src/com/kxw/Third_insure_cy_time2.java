package com.kxw;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.kxw.Third_insure_cy_time.Third;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Third_insure_cy_time2 extends Activity implements OnGetGeoCoderResultListener {
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
	
	private static String url_create_product = "http://120.27.26.219/kxw/android/third_insure_cy_cash.php"; 
	private static final String TAG_SUCCESS = "success"; 
	private ProgressDialog pDialog; 
	JSONParser jsonParser = new JSONParser();
	int success=1,userid,usertype;
	EditText insure_name,insured_tel,insured_address,insured_name,insuremoney,fhaddress,ddaddress,
	goods_name,goods_weight,carnum,driver_name,driver_tel,driver_license;
	TextView premium2;
	String premium3,carnum2,fbnum,fhprovince2,fhcity2,fhcounty2,ddprovince2,ddcity2,ddcounty2;
	double time;
	RadioGroup pay,add;
	Spinner fhprovince,fhcity,fhcounty,ddprovince,ddcity,ddcounty;
	double lat,lon;	
	GeoCoder mSearch = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.third_insure_cy_time);
		ActivityCollector.getInstance().addActivity(this);
		loadSpinner();
		
		// 初始化搜索模块，注册事件监听
		mSearch = GeoCoder.newInstance();
		mSearch.setOnGetGeoCodeResultListener(this);
		
		//调用全局变量
		Kxw_Application id=((Kxw_Application)getApplicationContext());
		userid=id.get_third_userid();
		time=id.get_third_time();
		
		fhprovince=((Spinner)findViewById(R.id.province_spinner));
		fhcity=((Spinner)findViewById(R.id.city_spinner));
		fhcounty=((Spinner)findViewById(R.id.county_spinner));		
		ddprovince=((Spinner)findViewById(R.id.province_spinner2));
		ddcity=((Spinner)findViewById(R.id.city_spinner2));
		ddcounty=((Spinner)findViewById(R.id.county_spinner2));
		

		
		insure_name=(EditText)findViewById(R.id.edit_insure_name);
		insured_tel=(EditText)findViewById(R.id.edit_insured_tel);
		insured_address=(EditText)findViewById(R.id.edit_insured_address);
		insured_name=(EditText)findViewById(R.id.edit_insured_name);
		goods_name=(EditText)findViewById(R.id.edit_goods_name);
		goods_weight=(EditText)findViewById(R.id.edit_goods_weight);
		carnum=(EditText)findViewById(R.id.edit_carnum);
		driver_name=(EditText)findViewById(R.id.edit_driver_name);
		driver_tel=(EditText)findViewById(R.id.edit_driver_tel);
		driver_license=(EditText)findViewById(R.id.edit_driver_license);
		
		final RadioGroup pay=(RadioGroup)findViewById(R.id.radgroup);
		
		//获取余额
		Kxw_Application i=((Kxw_Application)getApplicationContext());

		
		//"提交保单"按钮监听
		Button button=(Button)findViewById(R.id.btn_submit);
		//设置按钮颜色
		GradientDrawable myGrad = (GradientDrawable)button.getBackground();
		myGrad.setColor(0xFF4f94cd);
		button.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				fhprovince2=fhprovince.getSelectedItem().toString();		
				fhcity2=fhcity.getSelectedItem().toString();		
				fhcounty2=fhcounty.getSelectedItem().toString();
				ddprovince2=ddprovince.getSelectedItem().toString();		
				ddcity2=ddcity.getSelectedItem().toString();		
				ddcounty2=ddcounty.getSelectedItem().toString();
				
				// Geo搜索
				mSearch.geocode(new GeoCodeOption().city(fhcity2).address(fhcounty2+"政府"));	
				Log.d("a", String.valueOf(lat));
				Log.d("b", String.valueOf(lon));
				
				double a=lat;double b=lon;
				//mSearch.geocode(new GeoCodeOption().city(ddcity2).address(ddcounty2+"政府"));	
				//double c=lat;double d=lon;

//				Log.d("c", String.valueOf(c));
//				Log.d("d", String.valueOf(d));
				
				for(int i=0;i<pay.getChildCount();i++){
//					r=(RadioButton)pay.getChildAt(i);
//					if(r.isChecked()){
//						r.getText();
//						break;
//					}
				}
				String tel=insured_tel.getText().toString();
				String tel2=driver_tel.getText().toString();
				String carnum2=carnum.getText().toString();
				if(insure_name.length()!=0&&insured_tel.length()!=0&&insured_address.length()!=0&&insured_name.length()!=0&&insuremoney.length()!=0&&fhaddress.length()!=0
						&&ddaddress.length()!=0&&goods_name.length()!=0&&goods_weight.length()!=0&&carnum.length()!=0&&driver_name.length()!=0&&driver_tel.length()!=0&&driver_license.length()!=0){
					if(!isMobileNO(tel)){
  					    Toast.makeText(Third_insure_cy_time2.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_insured_tel)).setText("");
					    ((EditText)findViewById(R.id.edit_insured_tel)).requestFocus();	
					}else if(!isMobileNO(tel2)){
  					    Toast.makeText(Third_insure_cy_time2.this, "手机号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_driver_tel)).setText("");
					    ((EditText)findViewById(R.id.edit_driver_tel)).requestFocus();	
					}else if(!isCarnumberNO(carnum2)){
  					    Toast.makeText(Third_insure_cy_time2.this, "车牌号码不正确", Toast.LENGTH_LONG).show();
  					    ((EditText)findViewById(R.id.edit_carnum)).setText("");
					    ((EditText)findViewById(R.id.edit_carnum)).requestFocus();						
					}else
						new Third().execute();
				}else Toast.makeText(Third_insure_cy_time2.this, "请将信息填写完整！", Toast.LENGTH_SHORT).show();
				
			}			
		}); 
	}

	//访问数据库
	class Third extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pDialog = new ProgressDialog(Third_insure_cy_time2.this); 
			pDialog.setMessage("提交中"); 
			pDialog.setIndeterminate(false); 
			pDialog.setCancelable(true); 
			pDialog.show(); 
		}
		
		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			String insure_name2=((EditText)findViewById(R.id.edit_insured_name)).getText().toString();
			String insured_tel2=((EditText)findViewById(R.id.edit_insured_tel)).getText().toString();
			String insured_address2=((EditText)findViewById(R.id.edit_insured_address)).getText().toString();
			String insured_name2=((EditText)findViewById(R.id.edit_insured_name)).getText().toString();
			String carnum2=carnum.getText().toString();
			String driver_name2=driver_name.getText().toString();
			String driver_tel2=driver_tel.getText().toString();
			String driver_license2=driver_license.getText().toString();
			
			String str1=insured_tel2.substring(0, 3);
			String str2="****";
			String str3=insured_tel2.substring(7, 11);
			
			long now = System.currentTimeMillis();
			fbnum=str1+str2+str3+String.valueOf(now);
			
			List<NameValuePair> para = new ArrayList<NameValuePair>(); 
			para.add(new BasicNameValuePair("fh_address", fhaddress.getText().toString()));
			para.add(new BasicNameValuePair("dd_address", ddaddress.getText().toString()));
			para.add(new BasicNameValuePair("goods_name", goods_name.getText().toString()));
			para.add(new BasicNameValuePair("goods_weight", goods_weight.getText().toString()));
			para.add(new BasicNameValuePair("insure_name", insure_name2));
			para.add(new BasicNameValuePair("insured_tel", insured_tel2));
			para.add(new BasicNameValuePair("insured_address", insured_address2));
			para.add(new BasicNameValuePair("insured_name", insured_name2));
			para.add(new BasicNameValuePair("userid", Integer.toString(userid)));
			para.add(new BasicNameValuePair("usertype", Integer.toString(usertype)));
			para.add(new BasicNameValuePair("fbnum", fbnum));
			para.add(new BasicNameValuePair("carnum", carnum2));
			para.add(new BasicNameValuePair("driver_name", driver_name2));
			para.add(new BasicNameValuePair("driver_tel", driver_tel2));
			para.add(new BasicNameValuePair("driver_license", driver_license2));

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
			switch (success){
			case 1:{

			}
			case 0:{
				Toast.makeText(getApplicationContext(), "出现错误", Toast.LENGTH_SHORT).show();
				break;
			}
			}
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
//手机号码正则
public static boolean isMobileNO(String mobiles) {  
		String telRegex = "[1][3458]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。  
		if (TextUtils.isEmpty(mobiles)) return false;  
		else return mobiles.matches(telRegex);  
}
//车牌号正则
public static boolean isCarnumberNO(String carnumber) {
	String carnumRegex = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}";
	if (TextUtils.isEmpty(carnumber)) return false;
	else return carnumber.matches(carnumRegex);
	}

@Override
protected void onDestroy() {
	mSearch.destroy();
	super.onDestroy();
}
	@Override
	public void onGetGeoCodeResult(GeoCodeResult result) {
		// TODO Auto-generated method stub
		
			lat=result.getLocation().latitude;
			lon=result.getLocation().longitude;
			Log.d("lat", String.valueOf(lat));
			Log.d("lon", String.valueOf(lon));


	}


	@Override
	public void onGetReverseGeoCodeResult(ReverseGeoCodeResult arg0) {
		// TODO Auto-generated method stub

	}

}
