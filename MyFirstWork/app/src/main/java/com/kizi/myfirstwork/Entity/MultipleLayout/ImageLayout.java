package com.kizi.myfirstwork.Entity.MultipleLayout;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;
import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.CustomView.CircleImageView;
import com.kizi.myfirstwork.Entity.Bean.MainPage;
import com.kizi.myfirstwork.R;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by ASUS on 2016/8/11.
 */
public class ImageLayout implements ItemViewDelegate<MainPage> {


    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_item;
    }

    @Override
    public boolean isForViewType(MainPage item, int position) {
        if("1".equals(item.getType())&&item.getImages().size()!=1){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, final MainPage mainPage, int position) {
        try{
             /*加载含有多张图片的布局*/
            holder.getView(R.id.ngl_images).setVisibility(View.VISIBLE);
            NineGridImageView ngl_images = holder.getView(R.id.ngl_images);
            ngl_images.setAdapter(new NineGridImageViewAdapter<MainPage.ImagesBean>() {
                @Override
                protected void onDisplayImage(Context context, ImageView imageView, MainPage.ImagesBean imagesBean) {
                    Picasso.with(context).load(imagesBean.getUrl()).placeholder(R.mipmap.ic_launcher).into(imageView);
                }
            });
            ngl_images.setImagesData(mainPage.getImages());
            //常规加载
            if(!"".equals(mainPage.getCustomer_portrait_url())){
                Picasso.with(App.getContext()).load(mainPage.getCustomer_portrait_url()).placeholder(R.mipmap.tou).into((CircleImageView) holder.getView(R.id.iv_cus));
            }
            if(!"".equals(mainPage.getFlag_label())){
                ((TextView)holder.getView(R.id.tv_hot)).setText(mainPage.getFlag_label());
            }
            holder.setText(R.id.tv_cus1,mainPage.getCustomer_name());
            holder.setText(R.id.tv_cus2,mainPage.getPublished_at());
            holder.setText(R.id.tv_cus3,mainPage.getProvince()+" "+mainPage.getCity());
            holder.setText(R.id.tv_content,mainPage.getContent());
            holder.setText(R.id.tv_lab,mainPage.getTags());
            holder.setText(R.id.tv_num_love,mainPage.getLike_count());
            holder.setText(R.id.tv_num_info,mainPage.getReview_count());
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(App.getContext(), "捕获异常", Toast.LENGTH_SHORT).show();
        }

    }

}
