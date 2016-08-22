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
	   * 单例模式中获取唯一的MyApplication实例
	   * 
	   * @return
	   */
	  public static ActivityCollector getInstance() {
	    if (null == instance) {
	      instance = new ActivityCollector();
	    }
	    return instance;
	  }
	  // 添加Activity到容器中
	  public void addActivity(Activity activity) {
	    if (activitys != null && activitys.size() > 0) {
	      if(!activitys.contains(activity)){
	        activitys.add(activity);
	      }
	    }else{
	      activitys.add(activity);
	    }
	  }
	  // 遍历所有Activity并finish
	  public void exit() {
	    if (activitys != null && activitys.size() > 0) {
	      for (Activity activity : activitys) {
	        activity.finish();
	      }
	    }
	    System.exit(0);
	  }
}
