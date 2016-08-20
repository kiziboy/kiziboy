package com.kizi.myfirstwork.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Entity.Bean.Adress_gps;
import com.kizi.myfirstwork.Entity.ChangeTagAdFr;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.xiaopan.android.app.ProgressDialogFragment;

public class GetGpsActivity extends BaseActivity {
    private LocationManager locationManager;
    private LocationListener locationListener;
    private EditText mEt_gps;
    private TextView mTv_cancel;
    private RecyclerView mRecycler;
    private List<Adress_gps>lists=new ArrayList<>();
    private CommonAdapter<Adress_gps> adapter;
    private String str="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmTitle("添加位置");
        setView();
        setListeners();
        setAdapter();
        ProgressDialogFragment.show(getSupportFragmentManager(),new ProgressDialogFragment.Builder("加载中"));
        setLocation();

    }

    private void setListeners() {
        mTv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEt_gps.getText().toString().equals("")){
                    showToast("请输入位置");
                }else {
                    System.out.println("ooo");

                    HashMap<String,String>map=new HashMap<String, String>();
                    map.put("keyword",mEt_gps.getText().toString().trim());
                    map.put("location",str);
                    HttpClient.get(GetGpsActivity.this, "http://seesea.demo.evebit.cn/ecall/geocodeInputTips/", map, new CallBack<List<Adress_gps>>() {
                        @Override
                        public void onSuccess(List<Adress_gps> result) {
                            lists.clear();
                            lists.addAll(result);
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(int errorType, String message) {
                            System.out.println("ooo fail");
                            super.onFailure(errorType, message);
                        }
                    });
                }
            }
        });
    }

    private void getData() {
        System.out.println("lijun: setData");
        HashMap<String,String>map=new HashMap<>();
        map.put("location",str);
        System.out.println("lijun:str = " + str);
        HttpClient.get(this, "http://seesea.demo.evebit.cn/ecall/geocodeRegeo/", map, new CallBack<List<Adress_gps>>() {
            @Override
            public void onSuccess(List<Adress_gps> result) {
                System.out.println("lijun: onSuccess");
                System.out.println("lijun:result.size() = " + result.size());
                ProgressDialogFragment.close(getSupportFragmentManager());
                lists.addAll(result);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int errorType, String message) {
                System.out.println("lijun: onFailure");

                super.onFailure(errorType, message);
            }
        });
    }

    private void setAdapter() {
        System.out.println("lijun: setAdapter");
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter= new CommonAdapter<Adress_gps>(this, R.layout.layout_item_gps,lists) {
            @Override
            protected void convert(ViewHolder holder, final Adress_gps adress_gps, int position) {
                holder.setText(R.id.tv_item_gps,adress_gps.getName());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new ChangeTagAdFr(2,adress_gps.getName()));
                        finish();
                    }
                });
            }
        };
        mRecycler.setAdapter(adapter);
    }

    private void setLocation() {
        checkPermission();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                showLocation(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (locationManager.getAllProviders().contains(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }else if (locationManager.getAllProviders().contains(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }else {
            //无法定位：1、提示用户打开定位服务；2、跳转到设置界面
            Toast.makeText(this, "无法定位，请打开定位服务", Toast.LENGTH_SHORT).show();
            Intent i = new Intent();
            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(i);
        }
    }

    private void setView() {
        mEt_gps=(EditText)findViewById(R.id.et_gps);
        mTv_cancel=(TextView)findViewById(R.id.tv_cancel_gps);
        mRecycler=(RecyclerView)findViewById(R.id.recycler_gps);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_get_gps;
    }

    private void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            int hasFineLocationPermission=ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            int hasCOARSELOCATIONPermission=ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            if(hasFineLocationPermission!=PackageManager.PERMISSION_GRANTED){
                Activity activity=this;
                ActivityCompat.requestPermissions(activity,new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION
                },1);
                if(hasCOARSELOCATIONPermission!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this,new String[]{
                            Manifest.permission.ACCESS_COARSE_LOCATION
                    },1);

                }
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }
    private void showLocation(Location location) {
        if(location!=null){
            str=(location.getLongitude()+","+location.getLatitude()).trim();
        }
        System.out.println("lijun: location str = " + str);
        getData();

    }

}
