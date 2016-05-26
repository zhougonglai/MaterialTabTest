package com.yifudai.zhougonglai.materialtabtest.po;

import java.io.Serializable;

/**
 * Created by zhougonglai on 16/4/29.
 */
public class UserInfo implements Serializable{
    public String username,basicDec,basicInt,phone,rate;
    public boolean login = false,idcheck;
    public Integer rechargeCouponMoney,redmoney;
    public Double integralSum,interest,inusemoney,invSum;


    public UserInfo(String username) {
        this.username = username;
    }

    public UserInfo(String username, String basicDec, String basicInt, String phone, boolean login, boolean idcheck, Integer rechargeCouponMoney, Integer redmoney, Double integralSum, Double interest, Double inusemoney, Double invSum, String rate) {
        this.username = username;
        this.basicDec = basicDec;
        this.basicInt = basicInt;
        this.phone = phone;
        this.login = login;
        this.idcheck = idcheck;
        this.rechargeCouponMoney = rechargeCouponMoney;
        this.redmoney = redmoney;
        this.integralSum = integralSum;
        this.interest = interest;
        this.inusemoney = inusemoney;
        this.invSum = invSum;
        this.rate = rate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBasicDec() {
        return basicDec;
    }

    public void setBasicDec(String basicDec) {
        this.basicDec = basicDec;
    }

    public String getBasicInt() {
        return basicInt;
    }

    public void setBasicInt(String basicInt) {
        this.basicInt = basicInt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isIdcheck() {
        return idcheck;
    }

    public void setIdcheck(boolean idcheck) {
        this.idcheck = idcheck;
    }

    public Integer getRechargeCouponMoney() {
        return rechargeCouponMoney;
    }

    public void setRechargeCouponMoney(Integer rechargeCouponMoney) {
        this.rechargeCouponMoney = rechargeCouponMoney;
    }

    public Integer getRedmoney() {
        return redmoney;
    }

    public void setRedmoney(Integer redmoney) {
        this.redmoney = redmoney;
    }

    public Double getIntegralSum() {
        return integralSum;
    }

    public void setIntegralSum(Double integralSum) {
        this.integralSum = integralSum;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getInusemoney() {
        return inusemoney;
    }

    public void setInusemoney(Double inusemoney) {
        this.inusemoney = inusemoney;
    }

    public Double getInvSum() {
        return invSum;
    }

    public void setInvSum(Double invSum) {
        this.invSum = invSum;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
