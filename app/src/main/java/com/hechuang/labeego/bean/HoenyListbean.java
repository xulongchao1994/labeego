package com.hechuang.labeego.bean;

public class HoenyListbean {
    private String Id;
    private String UserId;
    private String TypeName;
    private String Amount;
    private String BalanceAmount;
    private String FromWho;
    private String Memo;
    private String year;
    private String AddTime;

    public HoenyListbean(String id, String userId, String typeName, String amount, String balanceAmount, String fromWho, String memo,  String year, String addTime) {
        Id = id;
        UserId = userId;
        TypeName = typeName;
        Amount = amount;
        BalanceAmount = balanceAmount;
        FromWho = fromWho;
        Memo = memo;
        this.year = year;
        AddTime = addTime;
    }


    public void setYear(String year) {
        this.year = year;
    }


    public String getYear() {
        return year;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public void setAmount(String amount) {
        Amount = amount;
    }

    public void setBalanceAmount(String balanceAmount) {
        BalanceAmount = balanceAmount;
    }

    public void setFromWho(String fromWho) {
        FromWho = fromWho;
    }

    public void setMemo(String memo) {
        Memo = memo;
    }

    public void setAddTime(String addTime) {
        AddTime = addTime;
    }

    public String getId() {
        return Id;
    }

    public String getUserId() {
        return UserId;
    }

    public String getTypeName() {
        return TypeName;
    }

    public String getAmount() {
        return Amount;
    }

    public String getBalanceAmount() {
        return BalanceAmount;
    }

    public String getFromWho() {
        return FromWho;
    }

    public String getMemo() {
        return Memo;
    }

    public String getAddTime() {
        return AddTime;
    }
}
