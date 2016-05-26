package com.yifudai.zhougonglai.materialtabtest.fgm;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.rey.material.app.Dialog;
import com.rey.material.widget.EditText;
import com.rey.material.widget.TextView;
import com.yifudai.zhougonglai.materialtabtest.R;
import com.yifudai.zhougonglai.materialtabtest.adt.FruitAdapter;
import com.yifudai.zhougonglai.materialtabtest.po.Fruit;
import com.yifudai.zhougonglai.materialtabtest.po.UserInfo;
import com.yifudai.zhougonglai.materialtabtest.service.VolleyService;
import com.yifudai.zhougonglai.materialtabtest.util.SharedHelper;
import com.yifudai.zhougonglai.materialtabtest.util.Utils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by zhougonglai on 16/4/6.
 */
public class FourFragment extends Fragment {

    private List<Fruit> fruits = new ArrayList<Fruit>();
    ProgressDialog progressDialog;
    public VolleyService volley;
    private static final String baseUrl = "http://192.168.0.109:8080/";
    public UserInfo userInfo;
    Dialog login;
    private View view = getView();
    private TextView name;
    public SharedHelper sharedHelper;
    public SharedPreferences sp;
    public SharedPreferences.Editor editor;
    public FruitAdapter adapter;

    public ListView listView;

    public Utils utils= new Utils();

    Boolean idcheck;

    private TextView inv,invSumText,inuse,inuseMoney,rest,inteRest,gral,integralsum;
    private Map<String,String> userInfoMsg = new HashMap<String, String>();


    public FourFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpLogin();

        sharedHelper = new SharedHelper(getActivity().getApplicationContext());
        sp = sharedHelper.getSp();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("请稍后...");
        progressDialog.setCancelable(false);
    }

    public void setUpLogin(){
        login = new Dialog(getContext());
        login.cornerRadius(0).title("用户登录")
                .positiveAction("确定").positiveActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isEmpty((EditText) login.findViewById(R.id.user_phone)) && !isEmpty((EditText) login.findViewById(R.id.user_password))){
                    loginPositive(login);
                }else{
                    Toast.makeText(getContext(),"账号或密码为空",Toast.LENGTH_LONG).show();
                }
            }
        }).negativeAction("取消").negativeActionClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                login.hide();
            }
        }).contentView(R.layout.test_layout).cancelable(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.four_layout,container,false);
        ImageView image = (ImageView) view.findViewById(R.id.image_view);
        image.setBackgroundResource(R.drawable.qq20160225);
        name = (TextView) view.findViewById(R.id.username);
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login.show();
            }
        });
        setupUserInfo();
        setupAdapter();
        initFruits();
        return view;
    }

    public Boolean isEmpty(EditText editText){
        return editText.getText().toString().trim().length() == 0;
    }
    public BigDecimal setLoDec(BigDecimal numbers){
        return numbers.setScale(2,BigDecimal.ROUND_FLOOR);
    }
    public Boolean idCheck(EditText editText){
        return editText.getText().toString().trim().length() == 18;
    }

    public void setupAdapter(){
        final Dialog userDialog = new Dialog(getContext());
        userDialog.positiveAction("确定").negativeAction("取消").negativeActionClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDialog.hide();
            }
        }).cancelable(true).cornerRadius(0);

        adapter = new FruitAdapter(getContext(),R.layout.fruit_item,fruits);
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, View view, int position, long id) {
                if (idcheck == null) {
                    login.show();
                } else {
                    switch (position) {
                        case 0:
                            boolean i = idcheck.booleanValue();
                            if (i == true) {
                                toast("已认证");
                            } else if (i == false) {
                                userDialog.title("身份认证").positiveActionClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        EditText name = (EditText) userDialog.findViewById(R.id.name);
                                        EditText idNumber = (EditText) userDialog.findViewById(R.id.idNumber);
                                        Boolean id = idCheck(idNumber);
                                        if (idNumber.getText().length() != 18) {
                                            toast("姓名或身份证号不完整");
                                        } else if (!isEmpty(name) && idCheck(idNumber)) {
                                            toast(name.getText().toString() + idNumber.getText().toString());
                                        }
                                    }
                                }).contentView(R.layout.idcheck);
                                userDialog.show();
                            }
                           break;
                        case 1:
                            Spinner area = (Spinner) userDialog.findViewById(R.id.area);

                            userDialog.title("绑卡").positiveActionClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                }
                            }).contentView(R.layout.bind_card);


                            userDialog.show();
                            break;
                        case 2:
                            TestFragment testFragment = new TestFragment();
                            transaction(testFragment);
                            break;
                        default:
                            toast("无效点击");
                            break;
                    }
                }
            }
        });
    }

    public void transaction(Fragment fragment){
        listView.removeAllViewsInLayout();
        adapter.clear();
        getFragmentManager().beginTransaction().replace(R.id.fourFragmetn,fragment).commit();
    }

    public void setUserInfo(BigDecimal interest,BigDecimal invSum,BigDecimal inusemoney,BigDecimal integralSum){
        if (interest != null && invSum != null && inusemoney != null && integralSum != null){
            TextView inv = (TextView) view.findViewById(R.id.inv);
            inv.setText("账户资产");
            TextView invSumText = (TextView) view.findViewById(R.id.invSum);
            invSumText.setText(setLoDec(invSum).toString());

            TextView inuse = (TextView) view.findViewById(R.id.inuse);
            inuse.setText("账户余额");
            TextView inuseMoney = (TextView) view.findViewById(R.id.inuseMoney);
            inuseMoney.setText(setLoDec(inusemoney).toString());

            TextView rest = (TextView) view.findViewById(R.id.rest);
            rest.setText("累计收益");
            TextView inteRest = (TextView) view.findViewById(R.id.interest);
            inteRest.setText(setLoDec(interest).toString());

            TextView gral = (TextView) view.findViewById(R.id.gral);
            gral.setText("用户积分");
            TextView integralsum = (TextView) view.findViewById(R.id.integralSum);
            integralsum.setText(setLoDec(integralSum).toString());

            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sharedHelper.remove();
                }
            });
        }
    }

    public void setupUserInfo(){
            JSONObject o = sharedHelper.readJson();
            Integer check = (Integer) o.get("check");
        if (check != null) {
            switch (check) {
                case 0:
                    JSONObject config = (JSONObject) o.get("config");
                    String rate = config.getString("rate");
                    BigDecimal interest = config.getBigDecimal("interest");
                    String username = config.getString("username");
                    BigDecimal invSum = config.getBigDecimal("invSum");
                    String phone = config.getString("phone");
                    BigDecimal inusemoney = config.getBigDecimal("inusemoney");
                    BigDecimal rechargeCouponMoney = config.getBigDecimal("rechargeCouponMoney");
                    String basicDec = config.getString("basicDec");
                    BigDecimal integralSum = config.getBigDecimal("integralSum");
                    String basicInt = config.getString("basicInt");
                    BigDecimal redmoney = config.getBigDecimal("redmoney");
                    idcheck = (Boolean) o.get("idcheck");

                    sharedHelper.save(userInfoMsg);

//                    toast(idcheck ? "已认证" : "未认证");

                    if (interest != null && invSum != null && inusemoney != null && integralSum != null) {
                        name.setText(username);
                        setUserInfo(interest, invSum, inusemoney, integralSum);
                    }
                    login.hide();
                    break;
                case 1:
                    toast("空用户");
                    login.hide();
                    break;
                case 2:
                    toast("密码错误");
                    login.hide();
                    break;
                case 3:
                    toast("密码错误超过3次");
                    login.hide();
                    break;
                default:
                    toast("数据失效,请稍后再试");
                    login.show();
                    break;
            }
        }
        }


    public void loginPositive(final Dialog login){
        EditText phone = (EditText) login.findViewById(R.id.user_phone);
        EditText password = (EditText) login.findViewById(R.id.user_password);
        String url = baseUrl + "/wechat/signIn";
        showDialog();

        OkHttpUtils.post()
                .url(url)
                .addParams("username",phone.getText().toString())
                .addParams("password",password.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        hideDialog();
                        Toast.makeText(getContext(),"链接出错",Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onResponse(String response) {
                        hideDialog();
                        sharedHelper.save(response);
                        setupUserInfo();
                    }
                });
    }

    public void toast(String message){
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    public void showDialog(){
        if(!progressDialog.isShowing())progressDialog.show();
    }
    public void hideDialog(){
        if (progressDialog.isShowing())progressDialog.dismiss();
    }


    public void initFruits(){
        Fruit contacts = new Fruit("实名",R.drawable.ic_contacts);
        fruits.add(contacts);
        Fruit bunldCard = new Fruit("绑卡",R.drawable.ic_card);
        fruits.add(bunldCard);
        Fruit recharge = new Fruit("充值",R.drawable.ic_recharge);
        fruits.add(recharge);
    }

}