package com.xmz.service.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.system.entity.pojo.SysOperation;
import com.xmz.service.system.entity.vo.SysOperationInfiniteListVo;
import com.xmz.service.system.mapper.SysOperationMapper;
import com.xmz.service.system.service.ISysOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 页面操作按钮 服务实现类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-02
 */
@Service
public class SysOperationServiceImpl extends ServiceImpl<SysOperationMapper, SysOperation> implements ISysOperationService {
    public Page<SysOperationInfiniteListVo> infiniteList(
            Page<SysOperationInfiniteListVo> page,
            String resourceId,String name){
        return baseMapper.infiniteList(page, resourceId,name);
    }

    @Override
    public boolean batchEdit(List<Map<String, Object>> data) {
        return false;
    }
}
