package club.banyuan.mbm.entity;

import javax.xml.crypto.Data;
import java.util.Date;

public class Bill {
    private int id;        //账单编号
    private int providerId;  //供货商名称
    private double money;    //交易金额
    private String product;   //商品名称
    private int  isPay;    //是否付款  1付款  0没有
    private String providerName;//绑定的厂商名
    private String isPayStr;  //是否付款
    private  String  updateTime;

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getIsPayStr() {
        return isPayStr;
    }

    public void setIsPayStr(String isPayStr) {
        this.isPayStr = isPayStr;
    }

    public void setIsPayStr(int isPay) {
        if(isPay == 0){
            this.isPayStr = "未付款";
        }else {
            this.isPayStr= "已付款";
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }


}
