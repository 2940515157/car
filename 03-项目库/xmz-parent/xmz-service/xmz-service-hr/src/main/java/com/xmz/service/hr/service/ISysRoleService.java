package com.xmz.service.hr.service;

import com.xmz.service.hr.entity.pojo.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-09
 */
public interface ISysRoleService extends IService<SysRole> {

    public Map<String,Object> getPrs(String roleId,String resId);

}
