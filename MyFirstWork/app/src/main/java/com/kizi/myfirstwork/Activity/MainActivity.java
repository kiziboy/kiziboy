package com.kizi.myfirstwork.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Entity.ChangeTitle;
import com.kizi.myfirstwork.Entity.IntoActivity;
import com.kizi.myfirstwork.Entity.IntoPublishActivity;
import com.kizi.myfirstwork.Fragment.BlankFragment;
import com.kizi.myfirstwork.Fragment.MessageFragment;
import com.kizi.myfirstwork.Fragment.MyFragment;
import com.kizi.myfirstwork.Fragment.PublishFragment;
import com.kizi.myfirstwork.Fragment.TopicFragment;
import com.kizi.myfirstwork.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;
import java.util.List;

import me.xiaopan.android.preference.PreferencesUtils;

public class MainActivity extends BaseActivity {

    private FragmentTabHost mTabHost;
    List<Integer> tab_icons = Arrays.asList(R.drawable.selector_main,R.drawable.selector_topic,R.drawable.selector_photo,
            R.drawable.selector_message,R.drawable.selector_my
            );
    private int currentIndex=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        inVisible();
        setmTitle("看海");
        setView();
        setData();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_main;
    }

    @Subscribe
    public void onEvent(IntoActivity event){
        IntoNewActivity(event);
    }
    @Subscribe
    public void onEvent(ChangeTitle event){
        changeTitle(event.getTitle());
    }
    @Subscribe
    public void onEvent(IntoPublishActivity event){
        Intent intent=new Intent(MainActivity.this,PublishActivity.class);
        intent.putExtra("topic_id",event.getTopic_id());
        startActivity(intent);
    }


    private void changeTitle(String title) {
        setmTitle(title);
    }


    private void setData() {
        mTabHost.setup(this,getSupportFragmentManager(),R.id.container1);
        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator(getTabItemView(0)), BlankFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator(getTabItemView(1)), TopicFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("2").setIndicator(getTabItemView(2)), PublishFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("3").setIndicator(getTabItemView(3)), MessageFragment.class,null);
        mTabHost.addTab(mTabHost.newTabSpec("4").setIndicator(getTabItemView(4)), MyFragment.class,null);
        mTabHost.setCurrentTab(currentIndex);
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if("2".equals(tabId)){
                    mTabHost.setCurrentTab(currentIndex);
                    if(!PreferencesUtils.getBoolean(App.getContext(),"isLogin",false)){
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }else {
                        startActivity(new Intent(MainActivity.this,PublishActivity.class));
                    }
                }else{
                    currentIndex=Integer.parseInt(tabId);
                }
                System.out.println("tabId = " + tabId);
            }
        });
    }
    private View getTabItemView(int index) {
        View view = getLayoutInflater().inflate(R.layout.item_tab_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.item_icon);
        imageView.setImageResource(tab_icons.get(index));
        return view;
    }
    private void setView() {
        mTabHost= (FragmentTabHost) findViewById(R.id.tabHost);
    }
    private void IntoNewActivity(IntoActivity event) {
        showToast("想进入详情页");
       /* Intent intent=new Intent(new Intent(this,DetailActivity.class));
        intent.putExtra("data",  event);
        startActivity(intent);*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
