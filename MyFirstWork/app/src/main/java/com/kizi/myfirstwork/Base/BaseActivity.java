package com.kizi.myfirstwork.Base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.kizi.myfirstwork.R;
import com.kizi.myfirstwork.utils.SystemUtils;

public abstract class BaseActivity extends SuperBaseActivity {

    private ImageView mBack;
    private TextView mTitle;
    private FrameLayout mContainer;
    private View mStatusBar;
    protected TextView mMigi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mBack = (ImageView) findViewById(R.id.back);
        mTitle = (TextView) findViewById(R.id.title);
        mMigi=(TextView)findViewById(R.id.migi);
        mContainer = (FrameLayout) findViewById(R.id.container);
        mStatusBar = findViewById(R.id.status_bar);
        View view = getLayoutInflater().inflate(getLayoutResource(), null);
        mContainer.addView(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mStatusBar.setVisibility(View.VISIBLE);
            mStatusBar.getLayoutParams().height = SystemUtils.getStatusHeight(this);
            mStatusBar.setLayoutParams(mStatusBar.getLayoutParams());
        } else {
            mStatusBar.setVisibility(View.GONE);
        }
    }

    public abstract int getLayoutResource() ;

    protected void setmTitle(String title) {
        mTitle.setText(title);
    }

    protected  void inVisible(){
        mBack.setVisibility(View.INVISIBLE);
    }

    protected void setMigi(String str){
        mMigi.setText(str);
    }

    public void onBack(View view) {
        onBackPressed();
    }
}
