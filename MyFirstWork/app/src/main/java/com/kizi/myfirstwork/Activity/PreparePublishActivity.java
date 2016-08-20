package com.kizi.myfirstwork.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Base.CommonAdapter;
import com.kizi.myfirstwork.Entity.ChangeTagAdFr;
import com.kizi.myfirstwork.R;
import com.kizi.myfirstwork.utils.GlideImageLoader;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import me.xiaopan.android.hardware.DeviceUtils;
import me.xiaopan.android.widget.NestedGridView;

public class PreparePublishActivity extends BaseActivity {
    private final int REQUEST_CODE_GALLERY = 1001;
    private List<String>list=new ArrayList<>();
    private List<String>imagePaths= new ArrayList<>();
    private EditText et;
    private NestedGridView mPhotos;
    private CommonAdapter<String> adapter;
    private FunctionConfig functionConfig;
    private ArrayList<PhotoInfo> infos=new ArrayList<>();
    private boolean b;
    private LinearLayout mLl;
    private RelativeLayout mRl_tag;
    private RelativeLayout mRl_adress;
    private RelativeLayout mRl_friend;
    private TextView tv_addTag;
    private TextView tv_addAdress;
    private TextView tv_addFriend;
    private TextView tv_addTag_xx;
    private TextView tv_addFriends_xx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        b=getIntent().getBooleanExtra("isTrue",false);
        imagePaths.add("R.mipmap.ic_launcher");
        list.add("add");
        setmTitle("发布");
        setMigi("发布");
        setView();

        setListener();
        if(b){
            mLl.setVisibility(View.VISIBLE);
        }
        setAdapter();

    }
    @Subscribe
    public void onEvent(ChangeTagAdFr event){
        doWork(event);
    }

    private void doWork(ChangeTagAdFr m) {

        switch (m.getType()){
            case 1:
                if(!m.getContent().equals("")){
                    tv_addTag.setText(m.getContent());
                }else {
                    tv_addTag.setText("添加标签");
                }
                break;
            case 2:
                if(!m.getContent().equals("")){
                    tv_addAdress.setText(m.getContent());
                }else {
                    tv_addAdress.setText("添加位置");
                }
                tv_addAdress.setText(m.getContent());
                break;
            case 3:

                tv_addFriend.setText(m.getContent());
                break;
        }
    }

    private void setListener() {
        mRl_tag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreparePublishActivity.this,AddTagActivity.class));
            }
        });
        mRl_adress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PreparePublishActivity.this,GetGpsActivity.class));
            }
        });
        tv_addTag_xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_addTag.setText("");
            }
        });
        tv_addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_addFriends_xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_addFriend.setText("");
            }
        });
    }

    private void setAdapter() {
        adapter= new CommonAdapter<String>(this, R.layout.layout_item_image, list) {
            @Override
            public void convert(com.kizi.myfirstwork.Base.ViewHolder holder, String s) {
                if("add".equals(s)){
                    holder.setImageResource(R.id.image,R.mipmap.push_add);
                    holder.setOnClickListener(R.id.image, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setGallery();
                            openGallery();
                        }
                    });
                }else {
                    Picasso.with(PreparePublishActivity.this).load(new File(s))
                            .resize(DeviceUtils.getScreenSize(PreparePublishActivity.this).x / 4, DeviceUtils.getScreenSize(PreparePublishActivity.this).x / 4)
                            .centerCrop()
                            .into((ImageView) holder.getView(R.id.image));
                    holder.setOnClickListener(R.id.image, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(PreparePublishActivity.this, "image", Toast.LENGTH_SHORT).show();
                            setGallery();
                            openGallery();
                        }
                    });
                }
            }
        };
        mPhotos.setAdapter(adapter);
    }

    private void openGallery() {
        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, functionConfig, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                list.clear();
                infos.clear();
                infos.addAll(resultList);
                for (PhotoInfo photoInfo : resultList) {
                    list.add(photoInfo.getPhotoPath());
                }
                if(resultList.size()!=9){
                    list.add("add");
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {
            }
        });
    }

    private void setGallery() {
        final ThemeConfig theme = new ThemeConfig.Builder()
                .setTitleBarBgColor(getResources().getColor(R.color.colorPrimary))
                .setTitleBarTextColor(getResources().getColor(R.color.colorBackgroundAccent))
                .setFabNornalColor(getResources().getColor(R.color.colorPrimary))
                .setFabPressedColor(getResources().getColor(R.color.colorPrimaryDark))
                .setCheckNornalColor(getResources().getColor(R.color.colorGrey))
                .setCheckSelectedColor(getResources().getColor(R.color.colorPrimary))
                .build();
        functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setMutiSelectMaxSize(9)
                .setSelected(infos)
                .setEnableCrop(true)
                .setEnablePreview(true)
                .setCropSquare(true)
                .build();
        CoreConfig coreConfig = new CoreConfig.Builder(PreparePublishActivity.this, new GlideImageLoader(), theme)
                .setFunctionConfig(functionConfig)
                .setNoAnimcation(true)
                .build();
        GalleryFinal.init(coreConfig);
    }

    private void setView() {
        mRl_tag=(RelativeLayout)findViewById(R.id.rl_addTag);
        mRl_adress=(RelativeLayout)findViewById(R.id.rl_addAdress);
        mRl_friend=(RelativeLayout)findViewById(R.id.rl_addFriend);
        tv_addTag=(TextView)findViewById(R.id.tv_add_tag);
        tv_addAdress=(TextView)findViewById(R.id.tv_add_adress);
        tv_addFriend=(TextView)findViewById(R.id.tv_add_friend);
        et=(EditText)findViewById(R.id.et);
        mPhotos=(NestedGridView)findViewById(R.id.girdView);
        mLl=(LinearLayout)findViewById(R.id.ll_fourTag);
        tv_addTag_xx=(TextView)findViewById(R.id.tv_tag_xx);
        tv_addFriends_xx=(TextView)findViewById(R.id.tv_friends_xx);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_prepare_publish;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
