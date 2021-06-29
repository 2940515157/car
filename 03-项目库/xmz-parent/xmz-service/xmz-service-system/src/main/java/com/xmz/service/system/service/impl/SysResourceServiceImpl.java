package com.xmz.service.system.service.impl;

import com.xmz.service.system.entity.pojo.SysResource;
import com.xmz.service.system.entity.vo.SysResourceQuery;
import com.xmz.service.system.entity.vo.SysResourceVo;
import com.xmz.service.system.mapper.SysResourceMapper;
import com.xmz.service.system.service.ISysResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author KONG_No001
 * @since 2021-05-28
 */
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {
    public List<SysResourceVo> queryResource(SysResourceQuery resourceQuery){
        return baseMapper.queryResource(resourceQuery);
    }

    @Override
    public List<String> getRes(String id) {
        List<String> res = baseMapper.getRes(id);
        if(res.size()>1) res.remove(id);
        return res;
    }
}
