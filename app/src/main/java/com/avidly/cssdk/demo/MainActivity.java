package com.avidly.cssdk.demo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aas.sdk.account.AASTokenCallback;
import com.aas.sdk.account.AASdk;
import com.aly.sdk.ALYAnalysis;
import com.css.sdk.cservice.CServiceSdk;
import com.css.sdk.cservice.InitCallback;

public class MainActivity extends AppCompatActivity {
    private Button open;
    private Button login;
    private Button usercenter;
    private TextView tv;
    private ImageView ivShow;
    private Bitmap firstFrame;
    private static String TAG = "roy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        open = findViewById(R.id.open);
        ivShow = findViewById(R.id.iv);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CServiceSdk.feedback(MainActivity.this);
            }
        });
        login = findViewById(R.id.login);
        tv = findViewById(R.id.info);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AASdk.accountLogin(MainActivity.this);
            }
        });
        usercenter = findViewById(R.id.open_center);
        usercenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AASdk.showUserManagerUI(MainActivity.this);
            }
        });
        ALYAnalysis.init(this, "600180", "32401");

        AASdk.initSdk(this, "600180");

        CServiceSdk.initSdk(MainActivity.this, new InitCallback() {
            @Override
            public void onInitSuccess() {
                Toast.makeText(MainActivity.this, "Init success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInitFailed(String errorMsg) {
                Toast.makeText(MainActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
            }
        });
        setAAUTokenCallback();
    }

    /**
     * 用于获得登录token的回调
     */
    public void setAAUTokenCallback() {
        AASdk.setAAUTokenCallback(new AASTokenCallback() {
            @Override
            public void onUserTokenLoginSuccess(String token, int mode) {
                StringBuilder sb = new StringBuilder();
                sb.append("ggid:" + AASdk.getLoginedGGid()).append("\n")
                        .append("productId:").append(ALYAnalysis.getProductId()).append("\n")
                        .append("token:").append(ALYAnalysis.getUserId());
                tv.setText(sb.toString());
            }

            @Override
            public void onUserTokenLoginFailed(int code, String msg) {
            }
        });
    }


}
