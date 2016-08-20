package com.kizi.myfirstwork.Activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Entity.Bean.Root;
import com.kizi.myfirstwork.Entity.IntoActivity;
import com.kizi.myfirstwork.R;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends BaseActivity {
    private IntoActivity intoActivity;
    private ImageView iv_tou;
    private RecyclerView mRecycler;
    private List<String>lists=new ArrayList<>();
    private CommonAdapter<String> adapter;
    private Root root;
    private List<String>images_url=new ArrayList<>();
    private HeaderAndFooterWrapper mHeaderAndFooterWrapper;
    private List<Integer> pLists=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmTitle("评论");
        setData();
        setView();
        setAdapter();
        getData();

    }

    private void getData() {
/*        HashMap<String ,String>map=new HashMap<>();
        map.put("item_id",root.getItem_id());
        HttpClient.get(this, " http://seesea.demo.evebit.cn/ecall/tidingsItemImages/", map, new CallBack<List<ImageUrl>>() {
            @Override
            public void onSuccess(List<ImageUrl> result) {
                //images_url
            }

        });*/

    }

    private void setAdapter() {
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CommonAdapter<String>(this, R.layout.layout_item_detail, lists) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

            }
        };
        mHeaderAndFooterWrapper=new HeaderAndFooterWrapper(adapter);
        RelativeLayout linearLayout= (RelativeLayout) getLayoutInflater().inflate(R.layout.layout_item2_detail,null);
        setChildView(linearLayout);
        mHeaderAndFooterWrapper.addHeaderView(linearLayout);
        mRecycler.setAdapter(mHeaderAndFooterWrapper);
        mHeaderAndFooterWrapper.notifyDataSetChanged();
    }

    private void setChildView(RelativeLayout linearLayout) {
        TextView tv_name= (TextView) linearLayout.findViewById(R.id.tv_detail1);
        tv_name.setText(root.getCustomer_name());
        TextView tv_data=(TextView)linearLayout.findViewById(R.id.tv_detail2);
        tv_data.setText(root.getPublished_at());
        TextView tv_adress=(TextView)linearLayout.findViewById(R.id.tv_detail3);
        tv_adress.setText(root.getProvince()+" "+root.getAddress());
        TextView tv_content=(TextView)linearLayout.findViewById(R.id.tv_detail_content);
        tv_content.setText(root.getContent());
        TextView tv_tag=(TextView)linearLayout.findViewById(R.id.tv_detail_lab);
        tv_tag.setText(root.getTags());
        TextView tv_num_love=(TextView)linearLayout.findViewById(R.id.tv_num_love_detail);
        tv_num_love.setText(root.getLike_count());
        TextView tv_num_info=(TextView)linearLayout.findViewById(R.id.tv_num_info_detail);
        tv_num_info.setText(root.getReview_count());
       /* BannerLayout banner= (BannerLayout) linearLayout.findViewById(R.id.banner);
        banner.setViewRes(pLists);*/
        ImageView iv_image=(ImageView)linearLayout.findViewById(R.id.iv_detail_image);
        Glide.with(this).load(root.getCover_image_url()).into(iv_image);
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void setView() {
        iv_tou=(ImageView)findViewById(R.id.iv_detail);
        mRecycler=(RecyclerView)findViewById(R.id.swipe_target);
    }

    private void setData() {
        for (int i=0;i<10;i++){
            lists.add(""+i);
        }
        intoActivity= (IntoActivity) getIntent().getSerializableExtra("data");
        if(intoActivity!=null){
            this.root=intoActivity.getRoot();
        }
        pLists.add(R.mipmap.p1);
        pLists.add(R.mipmap.p2);
        pLists.add(R.mipmap.p3);
        pLists.add(R.mipmap.p4);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_detail;
    }

}
