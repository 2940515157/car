package com.xmz.service.system.handler;

import com.xmz.common.utils.Result;
import com.xmz.service.system.exception.LoginException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Component
public class BaseExceptionHandler {

    /**
     * 登录异常
     * @param ex 登录异常类
     * @return 返回包 Code: 50005
     */
    @ExceptionHandler(LoginException.class)
    @ResponseBody
    public Result<Object> error(LoginException ex){
        ex.printStackTrace();
        return Result.getErrorInstance(50005, ex.getMessage());
    }


}
