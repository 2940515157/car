package com.xmz.service.system.controller;

import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.system.service.ISysSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/serviceSystem/sys-security")
public class SysSecurityController {

    @Autowired
    ISysSecurityService securityService;

    @PostMapping("/login")
    public Result<Object> login(@RequestBody Map<String,String> data){
        System.out.println(data);
        return Result.getOKInstance().data(securityService.login(data.get("username"),data.get("password")));
    }

    @PostMapping("/getCurrentSubject")
    public Result<Object> getCurrentSubject(HttpServletRequest httpServletRequest){
        return Result.getOKInstance(securityService.getAccount(httpServletRequest));
    }

    @PutMapping("/updatePassword")
    public Result<Object> updatePassword(@RequestBody Map<String,String> data){
        String oldPassword = data.get("oldPassword");
        String newPassword = data.get("newPassword");
        String id = data.get("id");
        if(securityService.updatePassword(oldPassword,newPassword,id)) return Result.getOKInstance();
        return Result.getErrorInstance("更改失败！没有一条记录被修改，请检查您的旧密码是否正确");
    }

    @PostMapping("/getRoutes")
    public Result<Object> getRoutes(@RequestBody Map<String,String> data
            ,HttpServletRequest httpServletRequest){
        return Result.getOKInstance(securityService.getRoutes(data.get("origin"), httpServletRequest));
    }

    @PostMapping("/getBtnCode")
    public Result<Object> getBtnCode(@RequestBody Map<String,String> data,
                                     HttpServletRequest httpServletRequest){
        return Result.getOKInstance(securityService.getBtnCode(data.get("resId"), httpServletRequest));
    }

}
