package com.kizi.myfirstwork.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.Base.BaseFragment;
import com.kizi.myfirstwork.CustomView.WrapContentLinearLayoutManager;
import com.kizi.myfirstwork.Entity.Bean.MainPage;
import com.kizi.myfirstwork.Entity.Bean.Root;
import com.kizi.myfirstwork.Entity.ChangeTitle;
import com.kizi.myfirstwork.Entity.MultipleLayout.ImageLayout;
import com.kizi.myfirstwork.Entity.MultipleLayout.OnlyImageLayout;
import com.kizi.myfirstwork.Entity.MultipleLayout.VideoLayout;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;
import com.yyydjk.library.BannerLayout;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BlankFragment extends BaseFragment implements OnRefreshListener, OnLoadMoreListener {
    private RecyclerView mRecycler;
    private List<String>lists=new ArrayList<>();
    private SwipeToLoadLayout swipeToLoadLayout;
    private List<Root>roots=new ArrayList<>();
    private int page;
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private List<Integer> pLists=new ArrayList<>();
    private List<MainPage>list_main=new ArrayList<>();
    private MultiItemTypeAdapter adapter2;
    private View view;
    public BlankFragment() {
    }
    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("66666");
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        setData();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){
            System.out.println("111");
            view=inflater.inflate(R.layout.fragment_blank, container, false);
            mRecycler=(RecyclerView)view.findViewById(R.id.swipe_target);
            swipeToLoadLayout = (SwipeToLoadLayout)view.findViewById(R.id.swipeToLoadLayout);
            swipeToLoadLayout.setOnRefreshListener(this);
            swipeToLoadLayout.setOnLoadMoreListener(this);
            setAdapter2();
            onRefresh();
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        /*System.out.println("87912932");
        View view=inflater.inflate(R.layout.fragment_blank, container, false);
        mRecycler=(RecyclerView)view.findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout)view.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        setAdapter2();*/
        return view;
    }

    private void setAdapter2() {
        try{
            mRecycler.setLayoutManager(new WrapContentLinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
            adapter2=new MultiItemTypeAdapter(getActivity(),list_main);
            adapter2.addItemViewDelegate(new ImageLayout());
            adapter2.addItemViewDelegate(new VideoLayout());
            adapter2.addItemViewDelegate(new OnlyImageLayout());
            mHeaderAndFooterWrapper=new HeaderAndFooterWrapper(adapter2);
            RelativeLayout mRelativeLayout= (RelativeLayout) getActivity().getLayoutInflater().inflate(R.layout.layout_banner,null);
            BannerLayout bannerLayout= (BannerLayout) mRelativeLayout.findViewById(R.id.banner);
            bannerLayout.setViewRes(pLists);
            mHeaderAndFooterWrapper.addHeaderView(mRelativeLayout);
            mRecycler.setAdapter(mHeaderAndFooterWrapper);
            mHeaderAndFooterWrapper.notifyDataSetChanged();
        }catch (IndexOutOfBoundsException e){
            showToast("出现异常");
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        System.out.println("3333");
        //onRefresh();
    }
    private void setData() {
        for (int i=0;i<5;i++){
            lists.add(""+i);
        }
        pLists.add(R.mipmap.p1);
        pLists.add(R.mipmap.p2);
        pLists.add(R.mipmap.p3);
        pLists.add(R.mipmap.p4);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().post(new ChangeTitle("看海"));

    }


    @Override
    public void onLoadMore() {
        try{
            getData2(false);
        }catch (IndexOutOfBoundsException e){
            showToast("出现异常");
        }

    }
    @Override
    public void onRefresh() {
        try{
            if(roots.size()==0){
            }
            page=1;
            getData2(true);
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(App.getContext(), "捕获异常3", Toast.LENGTH_SHORT).show();
        }

    }
    private void getData2(final boolean isRefresh) {
        HashMap<String, String> map = new HashMap<>();
        map.put("page",""+page);
        map.put("limit","5");
        HttpClient.get(getActivity(), "http://seesea.demo.evebit.cn/ecall/tidingsListHomepage/", map, new CallBack<List<MainPage>>() {
            @Override
            public void onSuccess(List<MainPage> result) {
                if(swipeToLoadLayout!=null){
                    swipeToLoadLayout.setRefreshing(false);
                    swipeToLoadLayout.setLoadingMore(false);
                }
                if (isRefresh) {
                    list_main.clear();
                }
                page++;
                list_main.addAll(result);
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int errorType, String message) {
                super.onFailure(errorType, message);
                if(swipeToLoadLayout!=null){
                    swipeToLoadLayout.setRefreshing(false);
                    swipeToLoadLayout.setLoadingMore(false);
                }
            }
        });
    }

}
