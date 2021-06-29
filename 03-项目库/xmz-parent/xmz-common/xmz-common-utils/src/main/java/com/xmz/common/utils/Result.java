package com.xmz.common.utils;


public class Result<T> {
    protected boolean flag;
    protected int code = 20000;
    protected String message;
    protected T data;

    public Result() {}
    public Result(boolean flag, String message, T data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
    public Result(boolean flag, int code, String message, T data) {
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

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    public Result<T> flag(boolean flag){
        this.flag = flag;
        return this;
    }
    public Result<T> code(int code){
        this.code = code;
        return this;
    }
    public Result<T> message(String message){
        this.message = message;
        return this;
    }
    public Result<T> data(T data){
        this.data = data;
        return this;
    }



    public static Result<Object> getInstance() {
        return new Result<>();
    }
    public static <T> Result<T> getInstance(Class<T> c) {
        return new Result<T>();
    }
    public static <T> Result<T> getInstance(T t){
        return new Result<T>().data(t);
    }

    public static Result<Object> getOKInstance(){
        return new Result<>(true,"OK",null);
    }
    public static Result<Object> getOKInstance(String message){
        return new Result<>(true,message,null);
    }
    public static <T> Result<T> getOKInstance(Class<T> c) {
        return new Result<T>(true,"OK",null);
    }
    public static <T> Result<T> getOKInstance(T t){
        return new Result<T>(true,"OK",t);
    }
    public static <T> Result<T> getOKInstance(String message, T t){
        return new Result<T>(true,message,t);
    }

    public static Result<Object> getErrorInstance(){
        return new Result<>(false,30000,"ERROR",null);
    }
    public static Result<Object> getErrorInstance(String message){
        return new Result<>(false,30000,message,null);
    }
    public static <T> Result<T> getErrorInstance(Class<T> c) {
        return new Result<>(false,30000,"ERROR",null);
    }
    public static <T> Result<T> getErrorInstance(T t) {
        return new Result<>(false,30000,"ERROR",t);
    }
    public static Result<Object> getErrorInstance(int code, String message) {
        return new Result<>(false,code,message,null);
    }
    public static <T> Result<T> getErrorInstance(int code, String message, T t) {
        return new Result<>(false,code,message,t);
    }

    @Override
    public String toString() {
        return "Result{" +
                "flag=" + flag +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

