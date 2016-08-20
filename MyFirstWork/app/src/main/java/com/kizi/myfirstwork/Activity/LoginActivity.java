package com.kizi.myfirstwork.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.Base.SuperBaseActivity;
import com.kizi.myfirstwork.Entity.Bean.LoginSuccess;
import com.kizi.myfirstwork.Entity.Bean.User;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;

import java.util.HashMap;

import me.xiaopan.android.preference.PreferencesUtils;

public class LoginActivity extends SuperBaseActivity {

    private TextView tv_close;
    private ImageView iv_image;
    private EditText et_number;
    private TextView tv_number_clear;
    private EditText et_password;
    private TextView tv_password_clear;
    private TextView tv_login;
    private TextView tv_forget;
    private TextView tv_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferencesUtils.putBoolean(App.getContext(),"isLogin",false);
        System.out.println("onCreate");
        setContentView(R.layout.activity_login);
        setView();
        /*预先加载来自本地的用户，手机和密码，提前显示在控件上*/
        setPre();
        setListeners();
    }

    private void setPre() {
        String userId=PreferencesUtils.getString(App.getContext(),"number","");
        //String password=PreferencesUtils.getString(App.getContext(),"password","");
        if(!"".equals(userId)){
            System.out.println("pre user number");
            et_number.setText(userId);
        }
       /* if(!"".equals(password)){
            System.out.println("pre user password");
            et_password.setText(password);
        }*/
    }

    private void setListeners() {
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tv_number_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_number.setText("");
            }
        });
        tv_password_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_password.setText("");
            }
        });
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();

            }
        });
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

    }

    private void Login() {
        HashMap<String,String>map=new HashMap<String, String>();
        map.put("mobile",et_number.getText().toString().trim());
        map.put("password",et_password.getText().toString().trim());
        HttpClient.get(this, "http://seesea.demo.evebit.cn/ecall/customerLogin/", map, new CallBack<LoginSuccess>() {
            @Override
            public void onSuccess(LoginSuccess result) {
                if("0".equals(result.getStatus())){
                    PreferencesUtils.putString(App.getContext(),"number",et_number.getText().toString());
                    PreferencesUtils.putString(App.getContext(),"password",et_password.getText().toString());
                    App.setToken(result.getToken());
                    App.setUserId(result.getId());
                    PreferencesUtils.putString(App.getContext(),"UserId",result.getId());
                    PreferencesUtils.putString(App.getContext(),"Token",result.getToken());
                    PreferencesUtils.putBoolean(App.getContext(),"isLogin",true);
                    finish();
                }else {
                    PreferencesUtils.putBoolean(App.getContext(),"isLogin",false);
                    showToast(result.getMessage());
                }
            }
            @Override
            public void onFailure(int errorType, String message) {
                PreferencesUtils.putBoolean(App.getContext(),"isLogin",false);
                super.onFailure(errorType, message);
                showToast("登陆失败");
            }
        });
    }

    private void setView() {
        tv_close=(TextView)findViewById(R.id.tv_closeLogin);
        iv_image=(ImageView)findViewById(R.id.iv_login);
        et_number=(EditText)findViewById(R.id.et_CustomNumber);
        tv_number_clear=(TextView)findViewById(R.id.tv_clear_custom_number);
        et_password=(EditText)findViewById(R.id.et_password);
        tv_password_clear=(TextView)findViewById(R.id.tv_clear_password);
        tv_login = (TextView) findViewById(R.id.tv_login);
        tv_forget=(TextView)findViewById(R.id.tv_forget_password);
        tv_register=(TextView)findViewById(R.id.tv_register);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        User user= (User) intent.getSerializableExtra("user");
        switch (user.getType()){
            case 0:
                System.out.println("user1 type+"+user.getType());
                System.out.println("user.getCustomNumber() = " + user.getCustomNumber());
                System.out.println("user.getPassword() = " + user.getPassword());
                et_number.setText(user.getCustomNumber());
                et_password.setText(user.getPassword());
                PreferencesUtils.putString(App.getContext(),"number",user.getCustomNumber());
                PreferencesUtils.putString(App.getContext(),"password",user.getPassword());
                /*进去登陆逻辑方法*/
                Login();
                break;
            case 1:
                /*注册成功返回，直接finish掉当前页面*/
                PreferencesUtils.putString(App.getContext(),"number",user.getCustomNumber());
                PreferencesUtils.putString(App.getContext(),"password",user.getPassword());
                PreferencesUtils.putBoolean(App.getContext(),"isLogin",true);
                finish();
                break;
        }
    }
}
