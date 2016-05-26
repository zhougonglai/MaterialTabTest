package com.yifudai.zhougonglai.materialtabtest.fgm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.rey.material.widget.Spinner;
import com.yifudai.zhougonglai.materialtabtest.R;
import com.yifudai.zhougonglai.materialtabtest.util.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * Created by zhougonglai on 16/5/20.
 */
public class TestFragment extends Fragment{
    private Spinner city;
    ArrayAdapter<String> adapter;
    private List<String> list;
    public Utils utils = new Utils();
    Map<String,String> header = new HashMap<String, String>();
    ArrayAdapter<CharSequence> listAdapter;
    private static final String baseUrl = "http://192.168.0.109:8080/";

    private final OkHttpClient client = new OkHttpClient();

    public void setOnClick(Button btn){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            OkHttpUtils.get().url(baseUrl+"wechat/getMyBank").build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            utils.showMessage(getContext(),"error");
                        }

                        @Override
                        public void onResponse(String response) {
                            utils.showMessage(getContext(),"success"+response);
                        }
                    });
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recharge,container,false);
        city = (Spinner) view.findViewById(R.id.city);
        final List<CharSequence> data = new ArrayList<>();
        final JSONArray arrayList = new JSONArray();
        OkHttpUtils.get().url(baseUrl+"wechat/getCity").build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        utils.showMessage(getContext(),"error");
                    }

                    @Override
                    public void onResponse(String response) {
                        JSONArray items = JSON.parseArray(response);
                        for (int i = 0; i< items.size(); i++){
                            JSONObject obj = (JSONObject) items.get(i);
                            data.add(obj.getString("name"));
                            arrayList.add(i,obj);
                        }
                        listAdapter = new ArrayAdapter<CharSequence>(getContext(),android.R.layout.simple_spinner_dropdown_item,data);
                        city.setAdapter(listAdapter);
                    }
                });
        city.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner parent, View view, int position, long id) {
                JSONObject item = (JSONObject) arrayList.get(position);
                utils.showMessage(getContext(),"selected:"+ item.toString());
            }
        });


        Button btn = (Button) view.findViewById(R.id.button);
        btn.setText("确定");

        setOnClick(btn);


        return view;
    }

    private void initDatas(){

    }
}
