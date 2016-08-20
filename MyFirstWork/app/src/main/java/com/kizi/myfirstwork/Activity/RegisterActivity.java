package com.kizi.myfirstwork.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Entity.Bean.AuthCode;
import com.kizi.myfirstwork.Entity.Bean.RegisterSuccess;
import com.kizi.myfirstwork.Entity.Bean.User;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;

import java.util.HashMap;

import me.xiaopan.android.preference.PreferencesUtils;
import me.xiaopan.android.util.Countdown;

public class RegisterActivity extends BaseActivity {

    private EditText et_userName;
    private EditText et_number;
    private EditText et_AuthCode;
    private EditText et_inputPassword;
    private EditText et_inputPassword_again;
    private TextView tv_getAuthCode;
    private TextView tv_success_register;
    private TextView tv_backToLogin;
    private Countdown countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmTitle("注册");
        setView();
        countdown=new Countdown(tv_getAuthCode,"%s秒后可重新获取",60);
        setListeners();

    }

    private void setListeners() {
        tv_getAuthCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!"".equals(et_number.getText().toString().trim())){
                    countdown.start();
                    HashMap<String,String>map=new HashMap<String, String>();
                    map.put("type","register");
                    map.put("mobile",et_number.getText().toString());
                    HttpClient.get(this, "http://seesea.demo.evebit.cn/ecall/smsSendVarifyCode/", map, new CallBack<AuthCode>() {
                        @Override
                        public void onSuccess(AuthCode result) {
                            if(!"0".equals(result.getStatus())){
                                showToast(result.getMessage());
                            }
                        }
                        @Override
                        public void onFailure(int errorType, String message) {
                            super.onFailure(errorType, message);
                            showToast("网络异常无法获取验证码");
                        }
                    });
                }else {
                    showToast("请输入手机号码");
                    return;
                }
            }
        });

        tv_backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });
        tv_success_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String>map=new HashMap<String, String>();
                map.put("type","register");
                map.put("firstname",et_userName.getText().toString().trim());
                map.put("vcode",et_AuthCode.getText().toString().trim());
                map.put("mobile",et_number.getText().toString().trim());
                map.put("password",et_inputPassword.getText().toString().trim());
                HttpClient.get(this, "http://seesea.demo.evebit.cn/ecall/customerRegister/", map, new CallBack<RegisterSuccess>() {
                    @Override
                    public void onSuccess(RegisterSuccess result) {
                        if(0==result.getStatus()){
                            System.out.println("userinfo:"+"用户id:"+result.getId()+" token:"+result.getToken());
                            App.setToken(result.getToken());
                            App.setUserId(result.getId());
                            PreferencesUtils.putString(App.getContext(),"UserId",result.getId());
                            PreferencesUtils.putString(App.getContext(),"Token",result.getToken());
                            User user=new User();
                            user.setType(1);
                            user.setPassword(et_inputPassword.getText().toString().trim());
                            user.setCustomNumber(et_number.getText().toString().trim());
                            user.setCode(et_AuthCode.getText().toString().trim());
                            System.out.println("is already");
                            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                            intent.putExtra("user",user);
                            startActivity(intent);
                            System.out.println("is finish");
                            finish();
                        }else{
                            showToast(result.getMessage());
                        }
                    }
                    @Override
                    public void onFailure(int errorType, String message) {
                        super.onFailure(errorType, message);
                        showToast("网络异常，请稍后再试");
                    }
                });
            }
        });
    }
    private void setView() {

        et_userName=(EditText)findViewById(R.id.et_register_userName);
        et_number=(EditText)findViewById(R.id.et_register_number);
        et_AuthCode=(EditText)findViewById(R.id.et_Auth_register);
        et_inputPassword=(EditText)findViewById(R.id.et_input_register);
        et_inputPassword_again=(EditText)findViewById(R.id.et_input_again_register);
        tv_getAuthCode=(TextView)findViewById(R.id.tv_getAuthCode_register);
        tv_success_register=(TextView)findViewById(R.id.tv_register_success);
        tv_backToLogin=(TextView)findViewById(R.id.tv_back_to_Login);


    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_register;
    }

    @Override
    public void onBackPressed() {
        countdown.stop();
        super.onBackPressed();
    }
}
