package com.xmz.common.serviceBase.handler;

import com.xmz.common.utils.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Component
public class GlobalExceptionHeader {
    /**
     * 全局异常处理
     * @param ex 异常类
     * @return 返回包
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<Object> error(Exception ex){
        ex.printStackTrace();
        return Result.getErrorInstance("未知的执行错误！！！请联系管理员！！！");
    }

}
