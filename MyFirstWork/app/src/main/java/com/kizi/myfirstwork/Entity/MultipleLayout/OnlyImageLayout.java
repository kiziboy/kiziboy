package com.kizi.myfirstwork.Entity.MultipleLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.CustomView.CircleImageView;
import com.kizi.myfirstwork.Entity.Bean.MainPage;
import com.kizi.myfirstwork.Entity.IntoActivity;
import com.kizi.myfirstwork.R;
import com.squareup.picasso.Picasso;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by ASUS on 2016/8/19.
 */
public class OnlyImageLayout implements ItemViewDelegate<MainPage> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_item_onlyimage;
    }

    @Override
    public boolean isForViewType(MainPage item, int position) {
        if("1".equals(item.getType())&&item.getImages().size()==1){
            return true;
        }
        return false;
    }
    @Override
    public void convert(ViewHolder holder, final MainPage mainPage, int position) {
        try{
            Picasso.with(App.getContext()).load(mainPage.getCover_image_url()).into((ImageView) holder.getView(R.id.iv_item));

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
            holder.setOnClickListener(R.id.iv_item, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntoActivity intoActivity = new IntoActivity(mainPage);
                    EventBus.getDefault().post(intoActivity);
                }
            });
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(App.getContext(), "捕获异常1", Toast.LENGTH_SHORT).show();
        }

    }
}
