package com.yifudai.zhougonglai.materialtabtest.service;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.rey.material.app.Dialog;
import com.rey.material.widget.EditText;
import com.yifudai.zhougonglai.materialtabtest.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by zhougonglai on 16/4/21.
 */
public class Login {
    public static Dialog dialog;
    private Context context;
    private static final String baseUrl = "http://192.168.0.102:8080/";
    private VolleyService volley = new VolleyService();

    public Login(Context context) {
        this.context = context;
    }

    public void onCreate(){
        dialog = new Dialog(context);
        dialog.cornerRadius(0).title("用户登录")
        .positiveAction("确定").positiveActionClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        loginPositive(dialog);
                                    }
                                })
        .negativeAction("取消").negativeActionClickListener(new View.OnClickListener(){
                                    @Override
                                    public void onClick(View v) {
                                        dialog.hide();
                                    }
                                })
        .contentView(R.layout.test_layout).cancelable(true).show();
    }

    public void loginPositive(Dialog dialog){
        EditText phone = (EditText) dialog.findViewById(R.id.user_phone);
        EditText password = (EditText) dialog.findViewById(R.id.user_password);
//        String url = baseUrl + "wechat/signIn";
        String url = "http://apis.juhe.cn/mobile/get";
        OkHttpUtils.get()
                .url(url)
                .addParams("phone",phone.getText().toString())
                .addParams("key","d4969f0b6a6426dad0571a6493d6d4d9")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(context,"success"+response.toString(),Toast.LENGTH_LONG).show();
                    }
                });
    }
}