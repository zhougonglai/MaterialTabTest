package com.yifudai.zhougonglai.materialtabtest.service;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.util.Xml;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.yifudai.zhougonglai.materialtabtest.fgm.FourFragment;
import com.yifudai.zhougonglai.materialtabtest.po.UserInfo;
import com.yifudai.zhougonglai.materialtabtest.util.SharedHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhougonglai on 16/4/22.
 */
public class VolleyService extends Application{
    private static final String TAG = VolleyService.class.getSimpleName();

    private static RequestQueue queue;
    private ImageLoader imageLoader;
    private static VolleyService instance;
    private static final String baseUrl = "http://192.168.0.102:8080/";
    public UserInfo userInfo;
    public static FourFragment four = new FourFragment();
    public SharedHelper sharedHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if(queue == null){
            queue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    public static synchronized VolleyService getInstance(){
        return instance;
    }

    public RequestQueue getRequestQueue(){
        return queue;
    }

    public void makeJsonObjectRequest(JSONObject object,Context context,Boolean promise){
        if(promise){
            Toast.makeText(context, "请求成功" + object.toString(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"请求失败" + object.toString(), Toast.LENGTH_LONG).show();
        }
    }
    public void makeJsonArrayRequest(){

    }

    public void CacheMessage(String url){
        Cache cache = queue.getCache();
        Cache.Entry entry = cache.get(url);
        if(entry != null){
            try {
                String data = new String(entry.data,"UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }else{

        }
    }

    public void volley_Get(String phone, final Context context){
        if (phone.length() >= 7) {
//            String url = "http://apis.juhe.cn/mobile/get?phone="+phone+"&dtype=json&key=d4969f0b6a6426dad0571a6493d6d4d9";
            String url="http://api.androidhive.info/volley/person_object.json";
            //Request.Method.GET,
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            Toast.makeText(context,"请求成功"+response.toString(),Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"请求失败" + error.toString(), Toast.LENGTH_LONG).show();
                }
            });
//            StringRequest request = new StringRequest(Request.Method.GET, url,new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Toast.makeText(context,"success1" + response,Toast.LENGTH_LONG).show();
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(context,"error1" + error.toString(), Toast.LENGTH_LONG).show();
//                }
//            });
            request.setTag("json_obj_req");
            queue.add(request);

        }else{
            Toast.makeText(getApplicationContext(),"手机号码不正确",Toast.LENGTH_LONG).show();
        }
    }

    public void volley_JPost(final String phone,final Context context){
        String url="http://apis.juhe.cn/mobile/get";
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("phone",phone);
        map.put("key","d4969f0b6a6426dad0571a6493d6d4d9");
        JSONObject object = new JSONObject(map);
        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(context, "请求成功" + response.optString("error_code"), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"请求失败",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("username",phone);
//                params.put("password",password);
                return super.getParams();
            }
        };
    }
    ProgressDialog progressDialog;

    public void login(final String phone, final String password, final Context context){
        String url = baseUrl + "wechat/signIn";
        final StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                setuplogin(response,context);
//                Toast.makeText(context,response,Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,error.toString(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",phone);
                params.put("password",password);
                return params;
            }
        };
        request.setTag("login");
        queue.add(request);
    }
    public void setUserInfo(UserInfo userInfo){
        this.userInfo = userInfo;
    }

    public void volley_SPost(final String phone){
        String url="http://apis.juhe.cn/mobile/get";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> hashMap = new HashMap<String,String>();
                hashMap.put("phone",phone);
                hashMap.put("key","d4969f0b6a6426dad0571a6493d6d4d9");
                return hashMap;
            }
        };
        request.setTag("string_req");
        getRequestQueue().add(request);
    }

    public void setuplogin(String response,Context context){
        try {
            JSONObject jsonObject = new JSONObject(response);
            Integer count = (Integer) jsonObject.get("check");
            switch (count){
                case 0:
                    JSONObject config = (JSONObject) jsonObject.get("config");
                    Boolean idcheck = (Boolean) jsonObject.get("idcheck");

                    Double integralSum = (Double) config.get("integralSum");
                    String basicDec = (String) config.get("basicDec");
                    String username = (String) config.get("username");
                    String basicInt = (String) config.get("basicInt");
                    String phone = (String) config.get("phone");

                    Integer rechargeCouponMoney = (Integer) config.get("rechargeCouponMoney");
                    Integer redmoney = (Integer) config.get("redmoney");

                    Double interest = (Double) config.get("interest");
                    Double inusemoney = (Double) config.get("inusemoney");
                    Double invSum = (Double) config.get("invSum");
                    String rate = (String) config.get("rate");


                    sharedHelper = new SharedHelper(context);
//                    sharedHelper.save(username,phone);

                    Toast.makeText(context,"用户名:"+username,Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(context,"空用户",Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(context,"密码错误",Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(context,"错误状态大于等于3次",Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(context,"账号或密码有误",Toast.LENGTH_LONG).show();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context,"数据有误",Toast.LENGTH_LONG).show();
        }
    }

    public <T> void addToRequestQueue(Request<T> req,String tag){
        req.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


    public void cancelPendingRequests(Object tag) {
        if (queue != null) {
            queue.cancelAll(tag);
        }
    }

    public void logout(){
//        pref.clear();
    }
}
