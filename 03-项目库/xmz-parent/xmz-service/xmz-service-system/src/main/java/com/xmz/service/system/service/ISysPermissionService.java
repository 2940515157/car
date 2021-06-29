package com.xmz.service.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.system.entity.pojo.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.system.entity.vo.SysOperationInfiniteListVo;
import com.xmz.service.system.entity.vo.SysPermissionListVo;
import com.xmz.service.system.entity.vo.SysPermissionQuery;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-01
 */
public interface ISysPermissionService extends IService<SysPermission> {

    Page<SysPermissionListVo> listPage(Page<SysPermissionListVo> permissionPage, SysPermissionQuery sysPermission);

    boolean batchEdit(SysPermission duplicate, List<SysPermission> list);

}
