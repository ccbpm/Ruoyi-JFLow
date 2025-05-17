package com.ruoyi.web.controller.jflow.model;

public class RequestData {
    private String token ;
    private String frmID ;
    private String pkValue ;
    private String data ;
    private String dataNew ;
    private String dataOld ;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFrmID() {
        return frmID;
    }

    public void setFrmID(String frmID) {
        this.frmID = frmID;
    }

    public String getPkValue() {
        return pkValue;
    }

    public void setPkValue(String pkValue) {
        this.pkValue = pkValue;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataNew() {
        return dataNew;
    }

    public void setDataNew(String dataNew) {
        this.dataNew = dataNew;
    }

    public String getDataOld() {
        return dataOld;
    }

    public void setDataOld(String dataOld) {
        this.dataOld = dataOld;
    }
}
