package com.yifudai.zhougonglai.materialtabtest.util;

import android.content.Context;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


/**
 * Created by zhougonglai on 16/5/20.
 */
public class Utils {
    private Toast toast;
    public void showMessage(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }

    public void volley_Get(String url, final Context context){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showMessage(context,"success:"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                showMessage(context,"error:"+error.toString());
            }
        });
    }

    public JSONObject transformFSJson(String data){
        JSONObject obj = new JSONObject();
        if (data != null && data != ""){
            obj = JSON.parseObject(data,JSONObject.class);
        }else{
            obj.put("error",true);
        }
        return obj;
    }

    public JSONArray transformFSArray(String data){
        JSONArray array = new JSONArray();
        if(data != null && data != ""){
            array = JSON.parseArray(data);
        }
        return array;
    }
}
