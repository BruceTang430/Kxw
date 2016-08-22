package com.kxw;

import com.baidu.mapapi.map.BaiduMap.OnMapClickListener;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteResult;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Cy_company_map extends Activity implements OnMapClickListener, OnGetRoutePlanResultListener {
    MapView mMapView = null;    // 地图View
    BaiduMap mBaidumap = null;
//    Button mBtnPre = null; // 上一个节点
//    Button mBtnNext = null; // 下一个节点
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    
    int nodeIndex = -1; // 节点索引,供浏览节点时使用
    RouteLine route = null;
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    private TextView popupText = null; // 泡泡view
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.cy_company_map);
        // 初始化地图
        mMapView = (MapView) findViewById(R.id.map);
        mBaidumap = mMapView.getMap();
        mBaidumap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 
//        mBtnPre = (Button) findViewById(R.id.pre);
//        mBtnNext = (Button) findViewById(R.id.next);
//        mBtnPre.setVisibility(View.INVISIBLE);
//        mBtnNext.setVisibility(View.INVISIBLE);
        // 地图点击事件处理
        mBaidumap.setOnMapClickListener(this);
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
        
	    Intent it = getIntent();
	    Bundle bun = it.getExtras();	    
	    String fhaddress=bun.getString("fhaddress");
	    String ddaddress=bun.getString("ddaddress");
	    String distance=bun.getString("distance");
	    
	    TextView distance2=(TextView)findViewById(R.id.txt_distance);
	    distance2.setText(distance);
	    
	    String fhcity=fhaddress.split(",")[1];
	    String ddcity=ddaddress.split(",")[1];
	    
	    String s1=fhaddress.substring(fhaddress.indexOf(",")+1, fhaddress.length());
	    String fhcounty=s1.substring(s1.indexOf(",")+1, s1.length());
	    String s2=ddaddress.substring(ddaddress.indexOf(",")+1, ddaddress.length());
	    String ddcounty=s2.substring(s2.indexOf(",")+1, s2.length());
	    
	    Log.d("fhcity", fhcity);
	    Log.d("fhcounty", fhcounty);
	    Log.d("ddcity", ddcity);
	    Log.d("ddcounty", ddcounty);
        
        PlanNode stNode = PlanNode.withCityNameAndPlaceName(fhcity, fhcounty);
        PlanNode enNode = PlanNode.withCityNameAndPlaceName(ddcity, ddcounty);
        
        mSearch.drivingSearch((new DrivingRoutePlanOption())
                .from(stNode).to(enNode));
    }

    /**
     * 节点浏览示例
     *
     * @param v
     */
//    public void nodeClick(View v) {
//        if (route == null || route.getAllStep() == null) {
//            return;
//        }
//        if (nodeIndex == -1 && v.getId() == R.id.pre) {
//            return;
//        }
//        // 设置节点索引
//        if (v.getId() == R.id.next) {
//            if (nodeIndex < route.getAllStep().size() - 1) {
//                nodeIndex++;
//            } else {
//                return;
//            }
//        } else if (v.getId() == R.id.pre) {
//            if (nodeIndex > 0) {
//                nodeIndex--;
//            } else {
//                return;
//            }
//        }
//        // 获取节结果信息
//        LatLng nodeLocation = null;
//        String nodeTitle = null;
//        Object step = route.getAllStep().get(nodeIndex);
//            nodeLocation = ((DrivingRouteLine.DrivingStep) step).getEntrance().getLocation();
//            nodeTitle = ((DrivingRouteLine.DrivingStep) step).getInstructions();
//
//
//        if (nodeLocation == null || nodeTitle == null) {
//            return;
//        }
//        // 移动节点至中心
//        mBaidumap.setMapStatus(MapStatusUpdateFactory.newLatLng(nodeLocation));
//        // show popup
//        popupText = new TextView(Cy_company_map.this);
//        popupText.setBackgroundResource(R.drawable.popup);
//        popupText.setTextColor(0xFF000000);
//        popupText.setText(nodeTitle);
//        mBaidumap.showInfoWindow(new InfoWindow(popupText, nodeLocation, 0));
//
//    }
//
//    /**
//     * 切换路线图标，刷新地图使其生效
//     * 注意： 起终点图标使用中心对齐.
//     */
//    public void changeRouteIcon(View v) {
//        if (routeOverlay == null) {
//            return;
//        }
//        if (useDefaultIcon) {
//            ((Button) v).setText("自定义起终点图标");
//            Toast.makeText(this,
//                    "将使用系统起终点图标",
//                    Toast.LENGTH_SHORT).show();
//
//        } else {
//            ((Button) v).setText("系统起终点图标");
//            Toast.makeText(this,
//                    "将使用自定义起终点图标",
//                    Toast.LENGTH_SHORT).show();
//
//        }
//        useDefaultIcon = !useDefaultIcon;
//        routeOverlay.removeFromMap();
//        routeOverlay.addToMap();
//    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
    
	@Override
	public void onGetBikingRouteResult(BikingRouteResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetDrivingRouteResult(DrivingRouteResult result) {
		// TODO Auto-generated method stub
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(Cy_company_map.this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;
//            mBtnPre.setVisibility(View.VISIBLE);
//            mBtnNext.setVisibility(View.VISIBLE);
            route = result.getRouteLines().get(0);
            DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaidumap);
            routeOverlay = overlay;
            mBaidumap.setOnMarkerClickListener(overlay);
            overlay.setData(result.getRouteLines().get(0));
            overlay.addToMap();
            overlay.zoomToSpan();
        }

	}
    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_st);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (useDefaultIcon) {
                return BitmapDescriptorFactory.fromResource(R.drawable.icon_en);
            }
            return null;
        }
    }
	@Override
	public void onGetTransitRouteResult(TransitRouteResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGetWalkingRouteResult(WalkingRouteResult arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapClick(LatLng arg0) {
		// TODO Auto-generated method stub
		mBaidumap.hideInfoWindow();

	}

	@Override
	public boolean onMapPoiClick(MapPoi arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	
    @Override
    protected void onPause() {
        mMapView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mMapView.onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mSearch.destroy();
        mMapView.onDestroy();
        super.onDestroy();
    }
}
