package com.yifudai.zhougonglai.materialtabtest.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.yifudai.zhougonglai.materialtabtest.po.UserInfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhougonglai on 16/5/9.
 */
public class SharedHelper {
    private Context context;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    public SharedHelper(){}

    public SharedHelper(Context context){
        this.context = context;
        sp = context.getSharedPreferences("userinfo",context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public void save(UserInfo userInfo){}

    public SharedPreferences getSp(){
        return this.sp;
    }
    public void clear(){
        editor.clear();
    }


    //定义一个保存数据的方法
    public void save(Map<String,String> userInfo) {
        String check = userInfo.get("check");
        String redmoney = userInfo.get("redmoney");
        String rechargeCouponMoney = userInfo.get("rechargeCouponMoney");

        String rate = userInfo.get("rate");
        String username = userInfo.get("username");
        String basicDec = userInfo.get("basicDec");
        String phone = userInfo.get("phone");
        String basicInt = userInfo.get("basicInt");

        String interest = userInfo.get("interest");
        String invSum = userInfo.get("invSum");
        String inusemoney = userInfo.get("inusemoney");
        String integralSum = userInfo.get("integralSum");


        editor.putString("check",check);
        editor.putString("redmoney",redmoney);
        editor.putString("rechargeCouponMoney",rechargeCouponMoney);

        editor.putString("rate",rate);
        editor.putString("username",username);
        editor.putString("phone",phone);
        editor.putString("basicDec",basicDec);
        editor.putString("basicInt",basicInt);

        editor.putString("interest",interest);
        editor.putString("invSum",invSum);
        editor.putString("inusemoney",inusemoney);
        editor.putString("integralSum",integralSum);

        editor.commit();
    }

    public void save(String userInfo){
        editor.putString("userInfo",userInfo);
        editor.commit();
    }
    public String readString(){
        String data = sp.getString("userInfo",null);
        return data;
    }

    public JSONObject readJson(){
        JSONObject obj = new JSONObject();
        JSONObject data = transformFSJson(readString());
        if(data.getBoolean("error").booleanValue()){
            obj.put("check",4);
            return obj;
        }else{
            return data;
        }
    }

    public JSONObject transformFSJson(String data){
        JSONObject obj = new JSONObject();
        if (data != null && data != ""){
            obj = JSON.parseObject(data,JSONObject.class);
            obj.put("error",false);
        }else{
            obj.put("error",true);
        }
        return obj;
    }

    public Gson readGson(){
        Gson gson = new Gson();
        String read = readString();
        if(read != null && read != ""){
            gson.fromJson(read,Gson.class);
        }else{
            gson.fromJson("{check,4}",Gson.class);
        }
        return gson;
    }

    public void remove(){
        editor.clear().commit();
        Toast.makeText(context,"已清除userInfo",Toast.LENGTH_LONG).show();
    }

    //定义一个读取SP文件的方法
    public Map<String,String> readMap() {
        Map<String,String> data = new HashMap<>();
        sp = context.getSharedPreferences("userinfo",context.MODE_PRIVATE);
        data.put("check", sp.getString("check", null));
        data.put("redmoney", sp.getString("phone",null));
        data.put("rechargeCouponMoney", sp.getString("rechargeCouponMoney", null));
        data.put("rate", sp.getString("rate", null));
        data.put("username", sp.getString("username","请登录"));
        data.put("phone", sp.getString("phone", null));
        data.put("basicDec", sp.getString("basicDec", null));
        data.put("basicInt", sp.getString("basicInt", null));
        data.put("interest", sp.getString("interest", null));
        data.put("invSum", sp.getString("invSum", null));
        data.put("inusemoney",sp.getString("inusemoney",null));
        data.put("integralSum",sp.getString("integralSum",null));
        return data;
    }
}
