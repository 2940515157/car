package com.xmz.service.system.service.impl;

import com.mysql.cj.util.StringUtils;
import com.xmz.common.serviceBase.auth.JwtUtils;
import com.xmz.common.serviceBase.auth.MD5;
import com.xmz.service.system.entity.pojo.SysResource;
import com.xmz.service.system.entity.vo.AccountInfo;
import com.xmz.service.system.exception.LoginException;
import com.xmz.service.system.mapper.SysSecurityMapper;
import com.xmz.service.system.service.ISysResourceService;
import com.xmz.service.system.service.ISysSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
public class SysSecurityServiceImpl implements ISysSecurityService {

    @Resource
    SysSecurityMapper sysSecurityMapper;

    @Autowired
    ISysResourceService resourceService;

    @Override
    public String login(String username, String password) {
//        String code = MD5.encrypt(password);
        AccountInfo account = sysSecurityMapper.getAccountByLogin(username,password);
        if(account == null)
            throw new LoginException("登录失败! 你的用户名或密码不正确！");
        if("locking".equals(account.getStatus()))
            throw new LoginException("登录失败! 你的所登录的用户是被锁定的！");
        sysSecurityMapper.updateLoginTime(account.getId());
        return JwtUtils.getJwtToken(account.getId(),account.getName());
    }

    public AccountInfo getAccount(HttpServletRequest request){
        String accountId = JwtUtils.getMemberIdByJwtToken(request);
        return sysSecurityMapper.geAccountById(accountId);
    }

    @Override
    public boolean updatePassword(String oldPassword, String newPassword, String id) {
        if(StringUtils.isNullOrEmpty(oldPassword+newPassword+id))
            return false;
        return sysSecurityMapper.updatePassword(oldPassword, newPassword, id)>0;
    }

    @Override
    public Map<String,Object> getRoutes(String origin,HttpServletRequest request) {
        HashMap<String, Object> _return = new HashMap<>();
        String accountId = JwtUtils.getMemberIdByJwtToken(request);
        HashMap<String, Map<String, Object>> routes = new HashMap<>();
//        HashMap<Integer,String> indexMap = new HashMap<>();
        TreeMap<BigDecimal, String> indexMap = new TreeMap<>();
        List<Map<String, String>> resInfoMap = sysSecurityMapper.listResInfoMap(origin,accountId);

//        int i = 0;
        for(Map<String, String> item :resInfoMap){
            String parentId = item.get("parentId");
            /*if(StringUtils.isNullOrEmpty(parentId)){
                HashMap<String, Object> parentRoute = new HashMap<>();
                parentRoute.put("name", item.get("resourceName"));
                parentRoute.put("path", item.get("resourceNo"));
                parentRoute.put("icon", item.get("img"));
                parentRoute.put("children", new ArrayList<Object>());
                routes.put(item.get("resourceId"), parentRoute);
                indexMap.put(i++,item.get("resourceId"));
            }else {
                ArrayList<Object> children = (ArrayList<Object>) routes.get(parentId).get("children");
                HashMap<String, Object> childRoute = new HashMap<>();
                childRoute.put("name", item.get("resourceName"));
                childRoute.put("path", item.get("resourceNo"));
                childRoute.put("icon", item.get("img"));
                childRoute.put("url", item.get("url"));
                children.add(childRoute);
            }*/
            if(StringUtils.isNullOrEmpty(parentId)){
                HashMap<String, Object> route = new HashMap<>();
                route.put("name", item.get("resourceName"));
                route.put("path", item.get("resourceNo"));
                route.put("icon", item.get("img"));
                routes.put(item.get("resourceId"), route);
                String treeSeq = item.get("treeSeq");
                indexMap.put(new BigDecimal(treeSeq),item.get("resourceId"));
                continue;
            }
            if(!routes.containsKey(parentId)){
                SysResource res = resourceService.getById(parentId);
                HashMap<String, Object> parentRoute = new HashMap<>();
                parentRoute.put("name", res.getResourceName());
                parentRoute.put("path", res.getResourceNo());
                parentRoute.put("icon", res.getImg());
                parentRoute.put("children", new ArrayList<Object>());
                routes.put(parentId, parentRoute);
                indexMap.put(res.getTreeSeq(),parentId);
            }
            ArrayList<Object> children = (ArrayList<Object>) routes.get(parentId).get("children");
            HashMap<String, Object> childRoute = new HashMap<>();
            childRoute.put("resourceId", item.get("resourceId"));
            childRoute.put("name", item.get("resourceName"));
            childRoute.put("path", item.get("resourceNo"));
            childRoute.put("icon", item.get("img"));
            childRoute.put("url", item.get("url"));
            children.add(childRoute);
        }
        _return.put("indexMap",indexMap);
        _return.put("routes",routes);

        return _return;
    }

    @Override
    public List<String> getBtnCode(String resId, HttpServletRequest request) {
        String acctId = JwtUtils.getMemberIdByJwtToken(request);
        return sysSecurityMapper.getBtnCode(acctId, resId);
    }


}
