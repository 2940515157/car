package com.xmz.common.utils;

import java.util.HashMap;
import java.util.Map;

public class ResultMap {
    protected boolean flag;
    protected int code;
    protected String message;
    protected Map<String,Object> data;

    public ResultMap() {
        this.flag = true;
        this.code = 20000;
        this.message = "OK";
        this.data = new HashMap<>();
    }
    public ResultMap(String message, Map<String, Object> data) {
        this.flag = true;
        this.code = 20000;
        this.message = message;
        this.data = data;
    }
    public ResultMap(boolean flag, int code, String message, Map<String,Object> data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String,Object> getData() {
        return data;
    }
    public void setData(Map<String,Object> data) {
        this.data = data;
    }

    public ResultMap flag(boolean flag){
        this.flag = flag;
        return this;
    }
    public ResultMap code(int code){
        this.code = code;
        return this;
    }
    public ResultMap message(String message){
        this.message = message;
        return this;
    }
    public ResultMap data(Map<String,Object> data){
        this.data = data;
        return this;
    }
    public ResultMap putData(String key, Object value){
        this.data.put(key, value);
        return this;
    }


    public static ResultMap getOKInstance(){
        return new ResultMap();
    }
    public static ResultMap getOKInstance(String message){
        return new ResultMap(message,new HashMap<>());
    }
    public static ResultMap getOKInstance(Map<String,Object> data){
        return new ResultMap("OK",data);
    }
    public static ResultMap getOKInstance(String message, Map<String,Object> data){
        return new ResultMap(message,data);
    }

    public static ResultMap getErrorInstance(){
        return new ResultMap(false,30000,"ERROR",new HashMap<>());
    }
    public static ResultMap getErrorInstance(String message){
        return new ResultMap(false,30000,message,new HashMap<>());
    }
    public static ResultMap getErrorInstance(Map<String,Object> m) {
        return new ResultMap(false,30000,"ERROR",m);
    }
    public static ResultMap getErrorInstance(int code, String message, Map<String,Object> m) {
        return new ResultMap(false,code,message,m);
    }

    @Override
    public String toString() {
        return "ResultMap{" +
                "flag=" + flag +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
