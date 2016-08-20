package com.kizi.myfirstwork.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Entity.Bean.Tag;
import com.kizi.myfirstwork.Entity.ChangeTagAdFr;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class AddTagActivity extends BaseActivity {

    private EditText et_addTag;
    private RecyclerView mRecycler;
    private List<Tag>lists=new ArrayList<>();
    private CommonAdapter<Tag> mAdapter;
    private List<String>ints=new ArrayList<>();
    private StringBuffer strs=new StringBuffer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmTitle("添加标签");
        setMigi("完成");
        setView();
        setListener();
    }

    private void setListener() {
        mMigi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ints.size()!=0){
                    for (String str :
                            ints) {
                        strs.append(","+lists.get(Integer.parseInt(str)).getTitle());
                    }
                    setStr();
                }else {
                    if(!et_addTag.getText().toString().equals("")){
                        strs.append(","+et_addTag.getText().toString());
                    }
                    System.out.println("strs = " + strs.toString());
                    EventBus.getDefault().post(new ChangeTagAdFr(1,strs.toString()));
                }
                finish();
            }

            private void setStr() {
                if(!et_addTag.getText().toString().equals("")){
                    strs.append(","+et_addTag.getText().toString());

                }
                strs.deleteCharAt(0);
                System.out.println("strs = " + strs.toString());
                EventBus.getDefault().post(new ChangeTagAdFr(1,strs.toString()));
            }
        });
    }

    private void setView() {
        et_addTag=(EditText)findViewById(R.id.et_add_tag);
        mRecycler=(RecyclerView)findViewById(R.id.mRecylerView);
        setAdapter();
    }

    private void setAdapter() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        mAdapter= new CommonAdapter<Tag>(this, R.layout.layout_item_add_tag, lists) {
            @Override
            protected void convert(final ViewHolder holder, Tag tag, final int position) {
                holder.setText(R.id.tv_item_add_tag,tag.getTitle());
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ints.contains(""+position)){
                            ints.remove(""+position);
                            ((TextView)holder.getView(R.id.tv_item_add_tag)).setBackgroundResource(R.color.colorBackgroundAccent);
                            ((TextView)holder.getView(R.id.tv_item_add_tag)).setTextColor(Color.BLACK);
                        }else {
                            ints.add(""+position);
                            ((TextView)holder.getView(R.id.tv_item_add_tag)).setBackgroundResource(R.color.colorBlue);
                            ((TextView)holder.getView(R.id.tv_item_add_tag)).setTextColor(Color.WHITE);
                        }
                    }
                });
            }
        };
        mRecycler.setAdapter(mAdapter);
        setData();
    }

    private void setData() {
        HttpClient.get(this, "http://seesea.demo.evebit.cn/ecall/tidingsPopularTags/", null, new CallBack<List<Tag>>() {
            @Override
            public void onSuccess(List<Tag> result) {
                System.out.println("result.size() = " + result.size());
                lists.addAll(result);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int errorType, String message) {
                super.onFailure(errorType, message);
                showToast("获取失败");
            }
        });
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_add_tag;
    }
}
