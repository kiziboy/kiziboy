package com.kizi.myfirstwork.Fragment;


import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.kizi.myfirstwork.Base.BaseFragment;
import com.kizi.myfirstwork.Entity.Bean.TopicList;
import com.kizi.myfirstwork.Entity.ChangeTitle;
import com.kizi.myfirstwork.Entity.IntoPublishActivity;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TopicFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {


    private RecyclerView mRecycler;
    private SwipeToLoadLayout swipeToLoadLayout;
    private int page;
    private List<TopicList> TopicLists=new ArrayList<>();
    private CommonAdapter<TopicList> mAdapter;
    public TopicFragment() {
    }

    public static TopicFragment newInstance() {
        TopicFragment fragment = new TopicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_topic, container, false);
        mRecycler=(RecyclerView)view.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout)view.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        setAdapter();
        return view;
    }

    private void setAdapter() {
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter= new CommonAdapter<TopicList>(getActivity(), R.layout.layout_item_fragmentopic, TopicLists) {
            @Override
            protected void convert(final ViewHolder holder, final TopicList topicList, int position) {
                List<String>imageLists=new ArrayList<>();
                holder.setText(R.id.tv_fragment_topic_title,topicList.getTitle());
                holder.setText(R.id.tv_fragment_topic_description,topicList.getDescription());
                holder.setText(R.id.tv_fragment_topic_num,topicList.getTotal_customer()+"人已参与");
                holder.setOnClickListener(R.id.tv_canjia, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EventBus.getDefault().post(new IntoPublishActivity(topicList.getTopic_id()));
                    }
                });
                holder.getConvertView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.out.println("this is convertView Click");
                    }
                });
                Glide.with(getActivity()).load(topicList.getUrl_image_1()).into(new SimpleTarget<GlideDrawable>() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        holder.getView(R.id.rl_fragment_topic_item).setBackground(resource);
                    }
                });
                if(TopicLists.size()!=0){
                    imageLists.add(topicList.getUrl_image_2());
                    imageLists.add(topicList.getUrl_image_3());
                    imageLists.add(topicList.getUrl_image_4());
                    ((NineGridImageView)holder.getView(R.id.NineImageView)).setAdapter(new NineGridImageViewAdapter<String>() {
                        @Override
                        protected void onDisplayImage(Context context, ImageView imageView, String strings) {
                            Glide.with(getActivity()).load(strings).placeholder(R.mipmap.ic_launcher).into(imageView);
                        }
                    });
                    ((NineGridImageView)holder.getView(R.id.NineImageView)).setImagesData(imageLists);
                }



            }
        };
        mRecycler.setAdapter(mAdapter);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onRefresh();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new ChangeTitle("专题"));
    }

    @Override
    public void onLoadMore() {
        getData(false);
    }

    @Override
    public void onRefresh() {
        page=1;
        getData(true);
    }

    private void getData(final boolean isRefresh) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page",""+page);
        map.put("limit","5");
        HttpClient.get(getActivity(), "http://seesea.demo.evebit.cn/ecall/tidingsTopics/", map, new CallBack<List<TopicList>>() {
            @Override
            public void onSuccess(List<TopicList> result) {
                if(swipeToLoadLayout!=null){
                    swipeToLoadLayout.setRefreshing(false);
                    swipeToLoadLayout.setLoadingMore(false);
                }
                if (isRefresh) {
                    TopicLists.clear();
                }
                page++;
                TopicLists.addAll(result);
                mAdapter.notifyDataSetChanged();
            }

        });
    }
}
