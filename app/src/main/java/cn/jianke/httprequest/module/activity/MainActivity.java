package cn.jianke.httprequest.module.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import cn.jianke.httprequest.R;
import cn.jianke.httprequest.httprequest.ApiCallback;
import cn.jianke.httprequest.httprequest.api.ApiLogin;
import cn.jianke.httprequest.httprequest.httpresponse.LoginResponse;

/**
 * @className: MainActivity
 * @classDescription: 首页
 * @author: leibing
 * @createTime: 2016/08/30
 */
public class MainActivity extends BaseActivity {
    // 登录信息显示
    private TextView mLoginMsgTv;
    // Api
    private ApiLogin mApiLogin;
    // 用户名
    private final static String USERNAME = "18818917198";
    // 密码
    private final static String PASSWORD = "123456";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // findView
        mLoginMsgTv = (TextView) findViewById(R.id.tv_show_login_msg);
        // 初始化ApiLogin
        mApiLogin = new ApiLogin();
        // onClick
        findViewById(R.id.btn_request_login_msg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mApiLogin.login(USERNAME, PASSWORD, MainActivity.this,
                        new ApiCallback<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse response) {
                        mLoginMsgTv.setText("login accesstoken : \n" + response.accesstoken);
                    }

                    @Override
                    public void onError(String err_msg) {
                        mLoginMsgTv.setText("error msg : " + err_msg);
                    }

                    @Override
                    public void onFailure() {
                        Toast.makeText(MainActivity.this, "网络不给力,请检查!",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        // 历史上的今天
        findViewById(R.id.btn_history_today).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryTodayActivity.class);
                startActivity(intent);
            }
        });
    }
}