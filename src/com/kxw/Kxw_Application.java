package com.kxw;

import android.app.Application;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class Kxw_Application extends Application {
	private String hcsj_user;
	private String ty_ispay;
	private String ty_truename;
	private String ty_idcard;
	private String ty_insure_address;
	private double ty_ratio;
	private double cy_driver_ratio;
	private double cy_company_ratio;
	private double third_ratio;
	private double ty_balance;
	private double cy_driver_balance;
	private double cy_company_balance;
	private double third_balance;
	private double fh_lat;
	private double fh_lon;
	private double dd_lat;
	private double dd_lon;
	private double third_fh_lat;
	private double third_fh_lon;
	private double third_dd_lat;
	private double third_dd_lon;
	private double coe;
	private double third_coe;
	private String third_username;
	private String third_tel;
	private String third_truename;
	private String third_idcard;
	private String third_insure_address;
	private String third_insurance_num;
	private double third_time;
	private double cy_company_time;
	private String cy_driver_tel;
	private String cy_company_tel;
	private String ty_tel;
	private String driver_carnum;
	private String hcsj_carorg;
	private String hcsj_carweight;
	private String hcsj_zztj;
	private String hcsj_cllx;
	private String hcsj_jsyxm;
	private String hcsj_jsytel;
	private String hcsj_jsyid;
	private String hcsj_yhkh;
	private String hcsj_yhorg;
	private String txtyd_fhr;
	private String txtyd_fhrnum;
	private String province;
	private String city11;
	private String county;
	private String xxaddress;
	private String province2;
	private String city2;
	private String county2;
	private String xxaddress2;
	private String province3;
	private String city3;
	private String county3;
	private String xxaddress3;
	private String txtyd_fhfs;
	private String txtyd_shr;
	private String txtyd_shrtel;
	private String txtyd_hwname;
	private String txtyd_hwgg;
	private String txtyd_hwnum;
	private String txtyd_hwweight;
	private String txtyd_hwvol;
	private String txtyd_hwbz;
	private String txtyd_remark;
	private int ty_userid;
	private int cy_driver_userid;
	private int cy_company_userid;
	private int third_userid;
	private int usertype;
	private int ty_isidentify;
	private int third_isidentify;
	private int driver_isidentify;
	private int company_isidentify;
	private String cy_driver_username;
	private String cy_company_username;
	private String driver_pwd;
	private String wlorg_address;
	private String ty_login_username;
	private String tyd_fh_address;
	private String tyd_dd_address;
	private String tyd_fhr_name;
	private String tyd_fhr_tel;
	private String tyd_fh_xxaddress;
	private String tyd_dd_xxaddress;
	private String tyd_shr_name;
	private String tyd_shr_tel;
	private String tyd_shr_address;
	private String tyd_shr_xxaddress;
	private String tyd_hw_name;
	private String tyd_hw_number;
	private String tyd_hw_gg;
	private String tyd_hw_weight;
	private String tyd_hw_vol;
	private String tyd_hw_bz;
	private String tyd_hw_remark;
	private String tyd_time;
	private String tyd_fbnum;
	private String tyd_isorder;
	private String tyd_tynum;
	private String tyd_ispay;
	private String tyd_carriage;
	private String tyd_cyusername;
	private String tyd_cy_isinsure;
	private String tyd_isgo;
	private String tyd_ty_userid;
	private String carriage;
	private String cy_company_name;
	private String cy_company_address;
	private String cy_company_xxaddress;
	private String cy_company_creditnum;
	private String cy_company_insurance_num;
	private String cy_driver_company;
	private String cy_driver_address;
	private String cy_driver_xxaddress;
	private String cy_driver_weight;
	private String cy_driver_vol;
	private String cy_driver_name;
	private String cy_driver_idcard;
	private String cy_driver_bankcard;
	private String cy_driver_cartype;
	private int ty_insurance_type;
	private int ty_insurance_id;
	private int driver_insurance_type;
	private int driver_insurance_id;
	private int company_insurance_type;
	private int company_insurance_id;
	private int third_insurance_type;
	private int third_insurance_id;

	public int get_third_insurance_type(){
		return third_insurance_type;
	}
	public void set_third_insurance_type(int s){
		third_insurance_type=s;
	}
	public int get_third_insurance_id(){
		return third_insurance_id;
	}
	public void set_third_insurance_id(int s){
		third_insurance_id=s;
	}
	
	public int get_company_insurance_type(){
		return company_insurance_type;
	}
	public void set_company_insurance_type(int s){
		company_insurance_type=s;
	}
	public int get_company_insurance_id(){
		return company_insurance_id;
	}
	public void set_company_insurance_id(int s){
		company_insurance_id=s;
	}
	
	public int get_driver_insurance_type(){
		return driver_insurance_type;
	}
	public void set_driver_insurance_type(int s){
		driver_insurance_type=s;
	}
	public int get_driver_insurance_id(){
		return driver_insurance_id;
	}
	public void set_driver_insurance_id(int s){
		driver_insurance_id=s;
	}
	
	public int get_ty_insurance_type(){
		return ty_insurance_type;
	}
	public void set_ty_insurance_type(int s){
		ty_insurance_type=s;
	}
	public int get_ty_insurance_id(){
		return ty_insurance_id;
	}
	public void set_ty_insurance_id(int s){
		ty_insurance_id=s;
	}
	
	public double get_third_time(){
		return third_time;
	}
	public void set_third_time(double s){
		third_time=s;
	}
	public double get_third_coe(){
		return third_coe;
	}
	public void set_third_coe(double s){
		third_coe=s;
	}
	
	public double get_ty_ratio(){
		return ty_ratio;
	}
	public void set_ty_ratio(double s){
		ty_ratio=s;
	}
	public double get_cy_driver_ratio(){
		return cy_driver_ratio;
	}
	public void set_cy_driver_ratio(double s){
		cy_driver_ratio=s;
	}
	public double get_cy_company_ratio(){
		return cy_company_ratio;
	}
	public void set_cy_company_ratio(double s){
		cy_company_ratio=s;
	}
	public double get_third_ratio(){
		return third_ratio;
	}
	public void set_third_ratio(double s){
		third_ratio=s;
	}
	
	public double get_cy_company_time(){
		return cy_company_time;
	}
	public void set_cy_company_time(double s){
		cy_company_time=s;
	}
	public double get_third_balance(){
		return third_balance;
	}
	public void set_third_balance(double s){
		third_balance=s;
	}
	public double get_cy_driver_balance(){
		return cy_driver_balance;
	}
	public void set_cy_driver_balance(double s){
		cy_driver_balance=s;
	}
	public double get_cy_company_balance(){
		return cy_company_balance;
	}
	public void set_cy_company_balance(double s){
		cy_company_balance=s;
	}
	public double get_ty_balance(){
		return ty_balance;
	}
	public void set_ty_balance(double s){
		ty_balance=s;
	}
	
	public double get_fh_lat(){
		return fh_lat;
	}
	public void set_fh_lat(double s){
		fh_lat=s;
	}
	public double get_fh_lon(){
		return fh_lon;
	}
	public void set_fh_lon(double s){
		fh_lon=s;
	}
	
	public double get_dd_lat(){
		return dd_lat;
	}
	public void set_dd_lat(double s){
		dd_lat=s;
	}
	public double get_dd_lon(){
		return dd_lon;
	}
	public void set_dd_lon(double s){
		dd_lon=s;
	}
	public double get_third_fh_lat(){
		return third_fh_lat;
	}
	public void set_third_fh_lat(double s){
		third_fh_lat=s;
	}
	public double get_third_fh_lon(){
		return third_fh_lon;
	}
	public void set_third_fh_lon(double s){
		third_fh_lon=s;
	}
	
	public double get_third_dd_lat(){
		return third_dd_lat;
	}
	public void set_third_dd_lat(double s){
		third_dd_lat=s;
	}
	public double get_third_dd_lon(){
		return third_dd_lon;
	}
	public void set_third_dd_lon(double s){
		third_dd_lon=s;
	}
	public double get_coe(){
		return coe;
	}
	public void set_coe(double s){
		coe=s;
	}
	
	public String get_cy_company_tel(){
		return cy_company_tel;
	}
	public void set_cy_company_tel(String s){
		cy_company_tel=s;
	}
	public String get_cy_company_insurance_num(){
		return cy_company_insurance_num;
	}
	public void set_cy_company_insurance_num(String s){
		cy_company_insurance_num=s;
	}
	
	public String get_cy_driver_tel(){
		return cy_driver_tel;
	}
	public void set_cy_driver_tel(String s){
		cy_driver_tel=s;
	}
	public String get_ty_tel(){
		return ty_tel;
	}
	public void set_ty_tel(String s){
		ty_tel=s;
	}
	public String get_ty_insure_address(){
		return ty_insure_address;
	}
	public void set_ty_insure_address(String s){
		ty_insure_address=s;
	}
	public String get_ty_ispay(){
		return ty_ispay;
	}
	public void set_ty_ispay(String s){
		ty_ispay=s;
	}
	public String get_ty_truename(){
		return ty_truename;
	}
	public void set_ty_truename(String s){
		ty_truename=s;
	}	
	public String get_ty_idcard(){
		return ty_idcard;
	}
	public void set_ty_idcard(String s){
		ty_idcard=s;
	}	
	
	//货车司机注册信息
	public String gethcsj_user(){
		return hcsj_user;
	}
	public void sethcsj_user(String s){
		hcsj_user=s;
	}
	
	public String get_driver_carnum(){
		return driver_carnum;
	}
	public void set_driver_carnum(String s){
		driver_carnum=s;
	}
	
	public String gethcsj_carorg(){
		return hcsj_carorg;
	}
	public void sethcsj_carorg(String s){
		hcsj_carorg=s;
	}
	
	public String gethcsj_carweight(){
		return hcsj_carweight;
	}
	public void sethcsj_carweight(String s){
		hcsj_carweight=s;
	}
	
	public String gethcsj_zztj(){
		return hcsj_zztj;
	}
	public void sethcsj_zztj(String s){
		hcsj_zztj=s;
	}
	
	public String gethcsj_cllx(){
		return hcsj_cllx;
	}
	public void sethcsj_cllx(String s){
		hcsj_cllx=s;
	}
	
	public String gethcsj_jsyxm(){
		return hcsj_jsyxm;
	}
	public void sethcsj_jsyxm(String s){
		hcsj_jsyxm=s;
	}
	
	public String gethcsj_jsytel(){
		return hcsj_jsytel;
	}
	public void sethcsj_jsytel(String s){
		hcsj_jsytel=s;
	}
	
	public String gethcsj_jsyid(){
		return hcsj_jsyid;
	}
	public void sethcsj_jsyid(String s){
		hcsj_jsyid=s;
	}
	
	public String gethcsj_yhkh(){
		return hcsj_yhkh;
	}
	public void sethcsj_yhkh(String s){
		hcsj_yhkh=s;
	}
	public String gethcsj_yhorg(){
		return hcsj_yhorg;
	}
	public void sethcsj_yhorg(String s){
		hcsj_yhorg=s;
	}
	
	//托运人填写托运单信息
	public String gettxtyd_fhr(){
		return txtyd_fhr;
	}
	public void settxtyd_fhr(String s){
		txtyd_fhr=s;
	}
	
	public String gettxtyd_fhrnum(){
		return txtyd_fhrnum;
	}
	public void settxtyd_fhrnum(String s){
		txtyd_fhrnum=s;
	}
	
	public String getprovince(){
		return province;
	}
	public void setprovince(String s){
		province=s;
	}
	
	public String getcity11(){
		return city11;
	}
	public void setcity11(String s){
		city11=s;
	}
	
	public String getcounty(){
		return county;
	}
	public void setcounty(String s){
		county=s;
	}
	
	public String getxxaddress(){
		return xxaddress;
	}
	public void setxxaddress(String s){
		xxaddress=s;
	}
	
	public String getprovince2(){
		return province2;
	}
	public void setprovince2(String s){
		province2=s;
	}
	
	public String getcity2(){
		return city2;
	}
	public void setcity2(String s){
		city2=s;
	}
	
	public String getcounty2(){
		return county2;
	}
	public void setcounty2(String s){
		county2=s;
	}
	
	public String getxxaddress2(){
		return xxaddress2;
	}
	public void setxxaddress2(String s){
		xxaddress2=s;
	}
	
	public String getprovince3(){
		return province;
	}
	public void setprovince3(String s){
		province3=s;
	}
	
	public String getcity3(){
		return city3;
	}
	public void setcity3(String s){
		city3=s;
	}
	
	public String getcounty3(){
		return county3;
	}
	public void setcounty3(String s){
		county3=s;
	}
	
	public String getxxaddress3(){
		return xxaddress3;
	}
	public void setxxaddress3(String s){
		xxaddress3=s;
	}
	
	public String gettxtyd_fhfs(){
		return txtyd_fhfs;
	}
	public void settxtyd_fhfs(String s){
		txtyd_fhfs=s;
	}	
	
	public String gettxtyd_shr(){
		return txtyd_shr;
	}
	public void settxtyd_shr(String s){
		txtyd_shr=s;
	}
	
	public String gettxtyd_shrtel(){
		return txtyd_shrtel;
	}
	public void settxtyd_shrtel(String s){
		txtyd_shrtel=s;
	}
	
	public String gettxtyd_hwname(){
		return txtyd_hwname;
	}
	public void settxtyd_hwname(String s){
		txtyd_hwname=s;
	}
	
	public String gettxtyd_hwgg(){
		return txtyd_hwgg;
	}
	public void settxtyd_hwgg(String s){
		txtyd_hwgg=s;
	}
	
	public String gettxtyd_hwnum(){
		return txtyd_hwnum;
	}
	public void settxtyd_hwnum(String s){
		txtyd_hwnum=s;
	}
	
	public String gettxtyd_hwweight(){
		return txtyd_hwweight;
	}
	public void settxtyd_hwweight(String s){
		txtyd_hwweight=s;
	}
	
	public String gettxtyd_hwvol(){
		return txtyd_hwvol;
	}
	public void settxtyd_hwvol(String s){
		txtyd_hwvol=s;
	}
	
	public String gettxtyd_hwbz(){
		return txtyd_hwbz;
	}
	public void settxtyd_hwbz(String s){
		txtyd_hwbz=s;
	}
	
	public String gettxtyd_remark(){
		return txtyd_remark;
	}
	public void settxtyd_remark(String s){
		txtyd_remark=s;
	}
	
	public int get_ty_userid(){
		return ty_userid;
	}
	public void set_ty_userid(int s){
		ty_userid=s;
	}
	
	public int get_cy_driver_userid(){
		return cy_driver_userid;
	}
	public void set_cy_driver_userid(int s){
		cy_driver_userid=s;
	}
	public int get_cy_company_userid(){
		return cy_company_userid;
	}
	public void set_cy_company_userid(int s){
		cy_company_userid=s;
	}
	public int get_third_userid(){
		return third_userid;
	}
	public void set_third_userid(int s){
		third_userid=s;
	}
	public int get_usertype(){
		return usertype;
	}
	public void set_usertype(int s){
		usertype=s;
	}	
	public String getdriver_pwd(){
		return driver_pwd;
	}
	public void setdriver_pwd(String s){
		driver_pwd=s;
	}
	
	public String getwlorg_address(){
		return wlorg_address;
	}
	public void setwlorg_address(String s){
		wlorg_address=s;
	}
	
	public String get_ty_login_username(){
		return ty_login_username;
	}
	public void set_ty_login_username(String s){
		ty_login_username=s;
	}
	
	//托运单信息
	public String get_tyd_fh_address(){
		return tyd_fh_address;
	}
	public void set_tyd_fh_address(String s){
		tyd_fh_address=s;
	}
	public String get_tyd_dd_address(){
		return tyd_dd_address;
	}
	public void set_tyd_dd_address(String s){
		tyd_dd_address=s;
	}
	public String get_tyd_fhr_name(){
		return tyd_fhr_name;
	}
	public void set_tyd_fhr_name(String s){
		tyd_fhr_name=s;
	}
	public String get_tyd_fhr_tel(){
		return tyd_fhr_tel;
	}
	public void set_tyd_fhr_tel(String s){
		tyd_fhr_tel=s;
	}
	public String get_tyd_fh_xxaddress(){
		return tyd_fh_xxaddress;
	}
	public void set_tyd_fh_xxaddress(String s){
		tyd_fh_xxaddress=s;
	}
	public String get_tyd_dd_xxaddress(){
		return tyd_dd_xxaddress;
	}
	public void set_tyd_dd_xxaddress(String s){
		tyd_dd_xxaddress=s;
	}
	public String get_tyd_shr_name(){
		return tyd_shr_name;
	}
	public void set_tyd_shr_name(String s){
		tyd_shr_name=s;
	}
	public String get_tyd_shr_tel(){
		return tyd_shr_tel;
	}
	public void set_tyd_shr_tel(String s){
		tyd_shr_tel=s;
	}
	public String get_tyd_shr_address(){
		return tyd_shr_address;
	}
	public void set_tyd_shr_address(String s){
		tyd_shr_address=s;
	}
	public String get_tyd_shr_xxaddress(){
		return tyd_shr_xxaddress;
	}
	public void set_tyd_shr_xxaddress(String s){
		tyd_shr_xxaddress=s;
	}
	public String get_tyd_hw_name(){
		return tyd_hw_name;
	}
	public void set_tyd_hw_name(String s){
		tyd_hw_name=s;
	}
	public String get_tyd_hw_gg(){
		return tyd_hw_gg;
	}
	public void set_tyd_hw_gg(String s){
		tyd_hw_gg=s;
	}
	public String get_tyd_hw_number(){
		return tyd_hw_number;
	}
	public void set_tyd_hw_number(String s){
		tyd_hw_number=s;
	}
	public String get_tyd_hw_weight(){
		return tyd_hw_weight;
	}
	public void set_tyd_hw_weight(String s){
		tyd_hw_weight=s;
	}
	public String get_tyd_hw_vol(){
		return tyd_hw_vol;
	}
	public void set_tyd_hw_vol(String s){
		tyd_hw_vol=s;
	}
	public String get_tyd_hw_bz(){
		return tyd_hw_bz;
	}
	public void set_tyd_hw_bz(String s){
		tyd_hw_bz=s;
	}
	public String get_tyd_hw_remark(){
		return tyd_hw_remark;
	}
	public void set_tyd_hw_remark(String s){
		tyd_hw_remark=s;
	}
	public String get_tyd_time(){
		return tyd_time;
	}
	public void set_tyd_time(String s){
		tyd_time=s;
	}
	public String get_tyd_fbnum(){
		return tyd_fbnum;
	}
	public void set_tyd_fbnum(String s){
		tyd_fbnum=s;
	}
	public String get_tyd_isorder(){
		return tyd_isorder;
	}
	public void set_tyd_isorder(String s){
		tyd_isorder=s;
	}
	public String get_tyd_tynum(){
		return tyd_tynum;
	}
	public void set_tyd_tynum(String s){
		tyd_tynum=s;
	}
	public String get_tyd_ispay(){
		return tyd_ispay;
	}
	public void set_tyd_ispay(String s){
		tyd_ispay=s;
	}
	public String get_tyd_carriage(){
		return tyd_carriage;
	}
	public void set_tyd_carriage(String s){
		tyd_carriage=s;
	}
	public String get_tyd_cyusername(){
		return tyd_cyusername;
	}
	public void set_tyd_cyusername(String s){
		tyd_cyusername=s;
	}
	public String get_cy_driver_username(){
		return cy_driver_username;
	}
	public void set_cy_driver_username(String s){
		cy_driver_username=s;
	}
	public String get_cy_company_username(){
		return cy_company_username;
	}
	public void set_cy_company_username(String s){
		cy_company_username=s;
	}
	public String get_third_username(){
		return third_username;
	}
	public void set_third_username(String s){
		third_username=s;
	}
	public String get_third_tel(){
		return third_tel;
	}
	public void set_third_tel(String s){
		third_tel=s;
	}
	public String get_third_insure_address(){
		return third_insure_address;
	}
	public void set_third_insure_address(String s){
		third_insure_address=s;
	}
	public String get_third_insurance_num(){
		return third_insurance_num;
	}
	public void set_third_insurance_num(String s){
		third_insurance_num=s;
	}
	public String get_third_truename(){
		return third_truename;
	}
	public void set_third_truename(String s){
		third_truename=s;
	}
	public String get_third_idcard(){
		return third_idcard;
	}
	public void set_third_idcard(String s){
		third_idcard=s;
	}
	public String get_tyd_cy_isinsure(){
		return tyd_cy_isinsure;
	}
	public void set_tyd_cy_isinsure(String s){
		tyd_cy_isinsure=s;
	}
	public String get_tyd_isgo(){
		return tyd_isgo;
	}
	public void set_tyd_isgo(String s){
		tyd_isgo=s;
	}
	public String get_tyd_ty_userid(){
		return tyd_ty_userid;
	}
	public void set_tyd_ty_userid(String s){
		tyd_ty_userid=s;
	}
	public String get_carriage(){
		return carriage;
	}
	public void set_carriage(String s){
		carriage=s;
	}
	
	public String get_cy_company_name(){
		return cy_company_name;
	}
	public void set_cy_company_name(String s){
		cy_company_name=s;
	}
	
	public String get_cy_company_address(){
		return cy_company_address;
	}
	public void set_cy_company_address(String s){
		cy_company_address=s;
	}
	
	public String get_cy_company_xxaddress(){
		return cy_company_xxaddress;
	}
	public void set_cy_company_xxaddress(String s){
		cy_company_xxaddress=s;
	}
	
	public String get_cy_company_creditnum(){
		return cy_company_creditnum;
	}
	public void set_cy_company_creditnum(String s){
		cy_company_creditnum=s;
	}
	
	public String get_cy_driver_company(){
		return cy_driver_company;
	}
	public void set_cy_driver_company(String s){
		cy_driver_company=s;
	}
	public String get_cy_driver_address(){
		return cy_driver_address;
	}
	public void set_cy_driver_address(String s){
		cy_driver_address=s;
	}
	public String get_cy_driver_xxaddress(){
		return cy_driver_xxaddress;
	}
	public void set_cy_driver_xxaddress(String s){
		cy_driver_xxaddress=s;
	}
	public String get_cy_driver_weight(){
		return cy_driver_weight;
	}
	public void set_cy_driver_weight(String s){
		cy_driver_weight=s;
	}
	public String get_cy_driver_vol(){
		return cy_driver_vol;
	}
	public void set_cy_driver_vol(String s){
		cy_driver_vol=s;
	}
	public String get_cy_driver_name(){
		return cy_driver_name;
	}
	public void set_cy_driver_name(String s){
		cy_driver_name=s;
	}
	public String get_cy_driver_idcard(){
		return cy_driver_idcard;
	}
	public void set_cy_driver_idcard(String s){
		cy_driver_idcard=s;
	}
	public String get_cy_driver_bankcard(){
		return cy_driver_bankcard;
	}
	public void set_cy_driver_bankcard(String s){
		cy_driver_bankcard=s;
	}
	public String get_cy_driver_cartype(){
		return cy_driver_cartype;
	}
	public void set_cy_driver_cartype(String s){
		cy_driver_cartype=s;
	}
	
	public int get_ty_isidentify(){
		return ty_isidentify;
	}
	public void set_ty_isidentify(int s){
		ty_isidentify=s;
	}	
	public int get_third_isidentify(){
		return third_isidentify;
	}
	public void set_third_isidentify(int s){
		third_isidentify=s;
	}
	public int get_driver_isidentify(){
		return driver_isidentify;
	}
	public void set_driver_isidentify(int s){
		driver_isidentify=s;
	}
	public int get_company_isidentify(){
		return company_isidentify;
	}
	public void set_company_isidentify(int s){
		company_isidentify=s;
	}

	/**  
	   * 按下这个按钮进行的颜色过滤  
	   */  
	  public final static float[] BT_SELECTED=new float[] {    
	      2, 0, 0, 0, 2,    
	      0, 2, 0, 0, 2,    
	      0, 0, 2, 0, 2,    
	      0, 0, 0, 1, 0 };   

	  /**  
	   * 按钮恢复原状的颜色过滤  
	   */  
	  public final static float[] BT_NOT_SELECTED=new float[] {    
	      1, 0, 0, 0, 0,    
	      0, 1, 0, 0, 0,    
	      0, 0, 1, 0, 0,    
	      0, 0, 0, 1, 0 };   

	  /**  
	   * 按钮焦点改变  
	   */  
	  public final static OnFocusChangeListener buttonOnFocusChangeListener=new OnFocusChangeListener() {   

	  @Override  
	  public void onFocusChange(View v, boolean hasFocus) {   
	   if (hasFocus) {   
	    v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));   
	    v.setBackgroundDrawable(v.getBackground());   
	   }   
	   else  
	   {   
	    v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));   
	     v.setBackgroundDrawable(v.getBackground());   
	   }   
	  }   
	 };   

	  /**  
	   * 按钮触碰按下效果  
	   */  
	 public final static OnTouchListener buttonOnTouchListener=new OnTouchListener() {   
	  @Override  
	  public boolean onTouch(View v, MotionEvent event) {   
	   if(event.getAction() == MotionEvent.ACTION_DOWN){   
	    v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_SELECTED));   
	    v.setBackgroundDrawable(v.getBackground());   
	    }   
	    else if(event.getAction() == MotionEvent.ACTION_UP){   
	     v.getBackground().setColorFilter(new ColorMatrixColorFilter(BT_NOT_SELECTED));   
	     v.setBackgroundDrawable(v.getBackground());   
	    }   
	   return false;   
	  }   
	 };   

	 /**  
	  * 设置图片按钮获取焦点改变状态  
	  * @param inImageButton  
	  */  
	 public final static void setButtonFocusChanged(View inView)   
	 {   
	  inView.setOnTouchListener(buttonOnTouchListener);   
	  inView.setOnFocusChangeListener(buttonOnFocusChangeListener);   
	 }
	
}
