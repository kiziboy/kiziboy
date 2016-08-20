package com.kizi.myfirstwork.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Entity.Bean.Friends;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends BaseActivity {
    private List<Friends>lists=new ArrayList<>();
    private RecyclerView mRecyclerView;
    private CommonAdapter<Friends> adapter;
    private List<String>ints=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmTitle("添加");
        setView();
        setAdapter();
    }
    private void setAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter= new CommonAdapter<Friends>(this, R.layout.layout_item_friends, lists) {
            @Override
            protected void convert(final ViewHolder holder, Friends friends, final int position) {
                holder.setText(R.id.tv_item_friend,friends.getFirstname());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ints.contains(""+position)){
                            ints.remove(""+position);
                            ((TextView)holder.getView(R.id.tv_item_friend)).setBackgroundResource(R.color.colorBackgroundAccent);
                            ((TextView)holder.getView(R.id.tv_item_friend)).setTextColor(Color.BLACK);
                        }else {
                            ints.add(""+position);
                            ((TextView)holder.getView(R.id.tv_item_friend)).setBackgroundResource(R.color.colorBlue);
                            ((TextView)holder.getView(R.id.tv_item_friend)).setTextColor(Color.WHITE);
                        }
                    }
                });
            }
        };
        mRecyclerView.setAdapter(adapter);
        getData();
    }
    private void getData() {
        HttpClient.get(this, "http://seesea.demo.evebit.cn/ecall/customerTips/", null, new CallBack<List<Friends>>() {
            @Override
            public void onSuccess(List<Friends> result) {
                lists.addAll(result);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int errorType, String message) {
                super.onFailure(errorType, message);
                showToast("获取失败");
            }
        });
    }
    private void setView() {
        mRecyclerView=(RecyclerView)findViewById(R.id.RecylerView_friends);

    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_friend;
    }
}
