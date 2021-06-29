package com.xmz.service.hr.service.impl;

import com.xmz.service.hr.entity.pojo.SysRole;
import com.xmz.service.hr.mapper.SysRoleMapper;
import com.xmz.service.hr.service.FResourceService;
import com.xmz.service.hr.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    FResourceService resourceService;

    @Override
    public Map<String, Object> getPrs(String roleId,String resId) {
        List<String> res = resourceService.getRes(resId);
        List<Map<String,String>> operCol = baseMapper.listOper(res);
        List<Map<String,Object>> prs = new ArrayList<>();

        for (String r:res) {
            boolean b = true;
            Map<String,Object> p = new HashMap<>();
            for(Map<String,String> out: baseMapper.listPrs(roleId, r)){
                if(b) {
                    p.put("resName",out.get("res_name"));
                    b = false;
                }

                HashMap<String, String> m = new HashMap<>();
                m.put("rolePermissionId",out.get("prs_ID"));
                m.put("permissionId",out.get("perm_ID"));
                m.put("isActive",out.get("is_active"));
                m.put("isShow",out.get("is_show"));

                p.put(out.get("oper_ID"),m);
            }
            prs.add(p);
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("col",operCol);
        data.put("prs",prs);

        return data;
    }
}
