package com.kizi.myfirstwork.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kizi.myfirstwork.Base.SuperBaseActivity;
import com.kizi.myfirstwork.R;

public class PublishActivity extends SuperBaseActivity {
    private ImageView iv_turn_right;
    private ImageView iv_picture;
    private ImageView iv_vedio;
    private TextView iv_xx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        setView();
        setListener();
    }

    private void setListener() {
        iv_turn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntoOnePublish(true);
            }
        });
        iv_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntoOnePublish(false);
            }
        });
        iv_xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void IntoOnePublish(Boolean b) {
        Intent intent=new Intent(this,PreparePublishActivity.class);
        intent.putExtra("isTrue",b);
        startActivity(intent);
    }

    private void setView() {
        iv_turn_right=(ImageView)findViewById(R.id.iv_fragment_tright);
        iv_picture = (ImageView) findViewById(R.id.iv_fragment_picture);
        iv_vedio=(ImageView)findViewById(R.id.iv_fragment_video);
        iv_xx=(TextView)findViewById(R.id.tv_fragment_xx);
    }
}
