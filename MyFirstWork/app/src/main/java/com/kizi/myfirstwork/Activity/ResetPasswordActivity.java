package com.kizi.myfirstwork.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kizi.myfirstwork.App.App;
import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Entity.Bean.RegisterSuccess;
import com.kizi.myfirstwork.Entity.Bean.User;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;

import java.util.HashMap;

import me.xiaopan.android.preference.PreferencesUtils;

public class ResetPasswordActivity extends BaseActivity {

    private EditText et_input;
    private EditText et_input_again;
    private TextView tv_login_again;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user= (User) getIntent().getSerializableExtra("user1");
        System.out.println("user = " + user.getCode());
        setmTitle("忘记密码");
        setView();
        setListeners();
    }

    private void setListeners() {
        tv_login_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(et_input.getText())||"".equals(et_input_again.getText())){
                    showToast("请输入完整");
                } else if(!et_input.getText().toString().equals(et_input_again.getText().toString())){
                    showToast("前后密码输入不一致");
                }else {
                    /*跳回登录页面*/
/*                    Intent intent=new Intent(ResetPasswordActivity.this,LoginActivity.class);
                    User user=new User();
                    user.setCode("123");
                    user.setCustomNumber("18696158754");
                    user.setPassword("123");
                    user.setType(1);
                    intent.putExtra("user",user);
                    startActivity(intent);
                    finish();*/
                    user.setPassword(et_input.getText().toString());
                    HashMap<String ,String>map=new HashMap<String, String>();
                    map.put("type","password");
                    map.put("vcode",user.getCode());
                    map.put("mobile",user.getCustomNumber());
                    map.put("password",user.getPassword());
                    System.out.println("user = " + user.getCode()+user.getCustomNumber()+user.getPassword());
                    HttpClient.get(this, "http://seesea.demo.evebit.cn/ecall/customerRegister/", map, new CallBack<RegisterSuccess>() {
                        @Override
                        public void onSuccess(RegisterSuccess result) {
                            System.out.println("user result = " + result.getStatus());
                            if(0 == result.getStatus()){
                                System.out.println("user = success");
                                App.setToken(result.getToken());
                                App.setUserId(result.getId());
                                PreferencesUtils.putString(App.getContext(),"UserId",result.getId());
                                PreferencesUtils.putString(App.getContext(),"Token",result.getToken());
                                user.setType(0);
                                Intent intent=new Intent(ResetPasswordActivity.this,LoginActivity.class);
                                intent.putExtra("user",user);
                                startActivity(intent);
                                finish();
                            }else {
                                System.out.println("user = fail");
                                showToast(result.getMessage());
                            }
                        }

                        @Override
                        public void onFailure(int errorType, String message) {
                            super.onFailure(errorType, message);
                            showToast("网络异常");
                        }
                    });

                }
            }
        });
    }

    private void setView() {
        et_input=(EditText)findViewById(R.id.et_input);
        et_input_again=(EditText)findViewById(R.id.et_input_again);
        tv_login_again=(TextView)findViewById(R.id.tv_login_again);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_reset_password;
    }
}
