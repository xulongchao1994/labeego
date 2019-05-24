package com.hechuang.labeego.bean;

public class V_bean {

    /**
     * browser : 2
     * where : https://www.pgyer.com/hechuangyinghang
     * url : http://lafeng.hetianpay.com
     * versionCode : 2.8
     * versionName : 45
     * status : 1
     * auto : 0
     * msg : 成功
     */

    private int browser;
    private String where;
    private String url;
    private String versionCode;
    private String versionName;
    private String status;
    private String auto;
    private String msg;

    @Override
    public String toString() {
        return "V_bean{" +
                "browser=" + browser +
                ", where='" + where + '\'' +
                ", url='" + url + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", versionName='" + versionName + '\'' +
                ", status='" + status + '\'' +
                ", auto='" + auto + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }

    public int getBrowser() {
        return browser;
    }

    public void setBrowser(int browser) {
        this.browser = browser;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuto() {
        return auto;
    }

    public void setAuto(String auto) {
        this.auto = auto;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
