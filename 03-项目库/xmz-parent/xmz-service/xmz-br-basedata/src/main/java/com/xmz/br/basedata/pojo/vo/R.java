package com.xmz.br.basedata.pojo.vo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装所有请求统一的结果集对象
 */
@Data
public class R {
    // 是否成功
    private Boolean success;
    // 返回码
    private Integer code;
    // 返回消息
    private String message;
    // 返回数据
    private Map<String, Object> data = new HashMap<String, Object>();

    // 构造方法私有化
    private R() {}


    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    // 链式调用   R.ok().data("", value).data("",value2);
    public R sucess(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }


}
