package com.xmz.service.system.service;

import com.xmz.service.system.entity.vo.AccountInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ISysSecurityService {

    String login(String username, String password);
    public AccountInfo getAccount(HttpServletRequest httpServletRequest);

    boolean updatePassword(String oldPassword,String newPassword,String id);

    Map<String,Object> getRoutes(String origin,HttpServletRequest httpServletRequest);

    List<String> getBtnCode(String resId, HttpServletRequest request);

}
