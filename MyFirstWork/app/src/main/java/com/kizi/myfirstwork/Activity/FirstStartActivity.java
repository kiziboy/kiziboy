package com.kizi.myfirstwork.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.Base.SuperBaseActivity;
import com.kizi.myfirstwork.R;

public class FirstStartActivity extends SuperBaseActivity{
    private Handler handler;
    private Thread thread=new Thread(new Runnable() {
        @Override
        public void run() {
            /*if(PreferencesUtils.getBoolean(App.getContext(),"isLogin",false)){
                startActivity(new Intent(App.getContext(),MainActivity.class));
            }else{
                startActivity(new Intent(App.getContext(),LoginActivity.class));
            }*/
            startActivity(new Intent(App.getContext(),MainActivity.class));
            finish();
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_start);
        handler=new Handler();
        handler.postDelayed(thread,1500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(thread);
    }
}
