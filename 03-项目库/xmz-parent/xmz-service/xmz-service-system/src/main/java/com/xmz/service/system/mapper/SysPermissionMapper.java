package com.xmz.service.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.system.entity.pojo.SysPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.system.entity.vo.SysPermissionListVo;
import com.xmz.service.system.entity.vo.SysPermissionQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-01
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    Page<SysPermissionListVo> listPage(Page<SysPermissionListVo> permissionPage, @Param("sp") SysPermissionQuery sysPermission);

}
