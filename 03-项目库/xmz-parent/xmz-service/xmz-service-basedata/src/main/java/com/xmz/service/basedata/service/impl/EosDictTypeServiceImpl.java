package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.common.serviceBase.MyQueryWrapper;
import com.xmz.service.basedata.entity.pojo.EosDictType;
import com.xmz.service.basedata.entity.pojo.QueryParam;
import com.xmz.service.basedata.mapper.EosDictTypeMapper;
import com.xmz.service.basedata.service.EosDictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author weikang
 * @since 2021-05-24
 */
@Service
public class EosDictTypeServiceImpl extends ServiceImpl<EosDictTypeMapper, EosDictType> implements EosDictTypeService {

//    @Autowired(required = false)
//    EosDictTypeMapper mapper;

    @Override
    public Page<EosDictType> queryPage(QueryParam queryParam, Page<EosDictType> page) {
        QueryWrapper<EosDictType> queryWrapper=new MyQueryWrapper<EosDictType>()
        .like("dicttypeid",queryParam.getDicttypeid())
        .like("dicttypename",queryParam.getDicttypename())
        .eq("dict_type",queryParam.getDictType());
        return baseMapper.selectPage(page,queryWrapper);
    }
}
