package com.bejson.pojo;

/**
 * @author zhanzhan
 * @date 2021/6/12 17:16
 */
public class Root {
    private String msg;

    private int code;

    private Data data;

    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
}
