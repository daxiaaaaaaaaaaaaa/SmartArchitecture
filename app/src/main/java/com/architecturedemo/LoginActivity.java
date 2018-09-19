package com.architecturedemo;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.architecturedemo.base.BaseDto;
import com.architecturedemo.dto.LoginDto;
import com.architecturedemo.viewmodel.LoginViewModel;
public class LoginActivity extends LifecycleActivity {
    private static final String TAG ="LoginActivity" ;
    private EditText etUsername;
    private EditText etPwd;
    private Button btnLogin;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etUsername = (EditText) findViewById(R.id.et_username);
        etPwd = (EditText) findViewById(R.id.et_pwd);
        btnLogin = (Button) findViewById(R.id.btn_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    /**
     * 登录
     */
    private void login() {
        loginViewModel.login(etUsername.getText().toString(), etPwd.getText().toString());
        loginViewModel.getLoginDtoLiveData().observe(this, loginDtoBaseDto -> handLogin(loginDtoBaseDto));
    }
    /**
     * 处理登陆数据
     *
     * @param loginDtoBaseDto
     */
    private void handLogin(BaseDto<LoginDto> loginDtoBaseDto) {
        if (loginDtoBaseDto.getCode() == Constant.Server.SUCCESS_CODE) {
            Log.e(TAG, "handLogin: 登录成功" );
        } else {
            Log.e(TAG, "handLogin: 登录失败" );
        }
    }
}
