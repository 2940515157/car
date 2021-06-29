package com.xmz.service.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.system.entity.pojo.SysOperation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.system.entity.vo.SysOperationInfiniteListVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 页面操作按钮 服务类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-02
 */
public interface ISysOperationService extends IService<SysOperation> {
    Page<SysOperationInfiniteListVo> infiniteList(
            Page<SysOperationInfiniteListVo> page,
            String resourceId,String name
    );

    public boolean batchEdit(List<Map<String, Object>> data);
}
