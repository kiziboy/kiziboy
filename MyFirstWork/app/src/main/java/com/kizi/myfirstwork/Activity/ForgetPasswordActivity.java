package com.kizi.myfirstwork.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kizi.myfirstwork.Base.BaseActivity;
import com.kizi.myfirstwork.Entity.Bean.AuthCode;
import com.kizi.myfirstwork.Entity.Bean.User;
import com.kizi.myfirstwork.Http.CallBack;
import com.kizi.myfirstwork.Http.HttpClient;
import com.kizi.myfirstwork.R;

import java.util.HashMap;

import me.xiaopan.android.util.Countdown;

public class ForgetPasswordActivity extends BaseActivity {

    private TextView tv_Auth;
    private EditText et_number;
    private EditText et_password;
    private TextView tv_next;
    private Countdown countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setmTitle("忘记密码");
        setView();
        setListeners();
    }

    private void setListeners() {
        tv_Auth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(et_number.getText())){
                    showToast("手机号不能为空");
                    return;
                }else {
                    countDown.start();
                    HashMap<String,String> map=new HashMap<String, String>();
                    map.put("type","password");
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
                }

            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("".equals(et_number.getText().toString())||"".equals(et_password.getText().toString())){
                    showToast("请输入完整");
                    return;
                }else {
                    User user=new User();
                    user.setCustomNumber(et_number.getText().toString());
                    user.setCode(et_password.getText().toString());
                    Intent intent=new Intent(ForgetPasswordActivity.this,ResetPasswordActivity.class);
                    intent.putExtra("user1",user);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void setView() {
        tv_Auth=(TextView)findViewById(R.id.tv_getAuthCode);
        et_number=(EditText)findViewById(R.id.et_forget_number);
        et_password=(EditText)findViewById(R.id.et_forget_password);
        tv_next=(TextView)findViewById(R.id.tv_next);
        countDown=new Countdown(tv_Auth,"%s秒后重新获取",30);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_forget_password;
    }

    @Override
    public void onBackPressed() {
        countDown.stop();
        super.onBackPressed();
    }
}
