package com.kizi.myfirstwork.Entity.MultipleLayout;

import android.widget.Toast;

import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.Entity.Bean.MainPage;
import com.kizi.myfirstwork.R;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * Created by ASUS on 2016/8/11.
 */
public class VideoLayout implements ItemViewDelegate<MainPage> {

    @Override
    public int getItemViewLayoutId() {
        return R.layout.layout_item_video;
    }

    @Override
    public boolean isForViewType(MainPage item, int position) {
        if("2".equals(item.getType())){
            return true;
        }
        return false;
    }

    @Override
    public void convert(ViewHolder holder, MainPage mainPage, int position) {
        try{
            holder.setText(R.id.tv_cus1,mainPage.getCustomer_name());
            holder.setText(R.id.tv_cus2,mainPage.getPublished_at());
            holder.setText(R.id.tv_cus3,mainPage.getProvince()+" "+mainPage.getCity());
            holder.setText(R.id.tv_content,mainPage.getContent());
            holder.setText(R.id.tv_lab,mainPage.getTags());
            holder.setText(R.id.tv_num_love,mainPage.getLike_count());
            holder.setText(R.id.tv_num_info,mainPage.getReview_count());
        }catch (IndexOutOfBoundsException e){
            Toast.makeText(App.getContext(), "捕获异常2", Toast.LENGTH_SHORT).show();
        }

    }

}
