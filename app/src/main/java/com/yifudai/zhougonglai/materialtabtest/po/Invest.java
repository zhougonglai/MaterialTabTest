package com.yifudai.zhougonglai.materialtabtest.po;

/**
 * Created by zhougonglai on 16/4/11.
 */
public class Invest {
    private String title;
    private Integer type,money,month,in_money;
    private Double rate;

    public Invest(String title, Integer type, Integer money, Integer month, Integer in_money,Double rate) {
        this.title = title;
        this.type = type;
        this.money = money;
        this.month = month;
        this.in_money = in_money;
        this.rate = rate;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getIn_money() {
        return in_money;
    }

    public void setIn_money(Integer in_money) {
        this.in_money = in_money;
    }
}
