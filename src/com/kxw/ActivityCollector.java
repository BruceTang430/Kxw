package com.kxw;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;

public class ActivityCollector {
	  private List<Activity> activitys = null;
	  private static ActivityCollector instance;
	  private ActivityCollector() {
	    activitys = new LinkedList<Activity>();
	  }
	  /**
	   * ����ģʽ�л�ȡΨһ��MyApplicationʵ��
	   * 
	   * @return
	   */
	  public static ActivityCollector getInstance() {
	    if (null == instance) {
	      instance = new ActivityCollector();
	    }
	    return instance;
	  }
	  // ���Activity��������
	  public void addActivity(Activity activity) {
	    if (activitys != null && activitys.size() > 0) {
	      if(!activitys.contains(activity)){
	        activitys.add(activity);
	      }
	    }else{
	      activitys.add(activity);
	    }
	  }
	  // ��������Activity��finish
	  public void exit() {
	    if (activitys != null && activitys.size() > 0) {
	      for (Activity activity : activitys) {
	        activity.finish();
	      }
	    }
	    System.exit(0);
	  }
}
