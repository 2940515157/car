package com.xmz.service.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.system.entity.pojo.SysOperation;
import com.xmz.service.system.entity.pojo.SysPermission;
import com.xmz.service.system.entity.vo.SysOperationInfiniteListVo;
import com.xmz.service.system.entity.vo.SysPermissionListVo;
import com.xmz.service.system.entity.vo.SysPermissionQuery;
import com.xmz.service.system.mapper.SysPermissionMapper;
import com.xmz.service.system.service.ISysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-01
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public Page<SysPermissionListVo> listPage(Page<SysPermissionListVo> permissionPage, SysPermissionQuery sysPermission) {
        return baseMapper.listPage(permissionPage, sysPermission);
    }

    @Override
    public boolean batchEdit(SysPermission duplicate, List<SysPermission> list) {
        // 循环添加字段解析
        /*
           重复值：
                RESOURCE_ID，RESOURCE_NAME，RESOURCE_URI
           不重复值：
                PERMISSION_ID，OPERATION_ID，OPERATION_NAME，LOGIC_FLOW_NAME
         */
        for (SysPermission item : list) {
            item.setResourceId(duplicate.getResourceId());
            item.setResourceName(duplicate.getResourceName());
            item.setResourceUri(duplicate.getResourceUri());
        }

        return saveOrUpdateBatch(list);
    }
}
