package com.xmz.service.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.common.serviceBase.MyQueryWrapper;
import com.xmz.service.basedata.entity.pojo.EosDictEntry;
import com.xmz.service.basedata.mapper.EosDictEntryMapper;
import com.xmz.service.basedata.service.EosDictEntryService;
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
public class EosDictEntryServiceImpl extends ServiceImpl<EosDictEntryMapper, EosDictEntry> implements EosDictEntryService {

    @Override
    public Page<EosDictEntry> queryPage(Page<EosDictEntry> page,String id) {
        QueryWrapper<EosDictEntry> queryWrapper=new MyQueryWrapper<EosDictEntry>()
                .eq("dicttypeid",id);
        return baseMapper.selectPage(page,queryWrapper);
    }

    @Override
    public EosDictEntry queryById(String dicttypeid, String dictid) {
        QueryWrapper<EosDictEntry> queryWrapper=new MyQueryWrapper<EosDictEntry>()
                .eq("dicttypeid",dicttypeid)
                .eq("dictid",dictid);
        return baseMapper.selectOne(queryWrapper);
    }
}
