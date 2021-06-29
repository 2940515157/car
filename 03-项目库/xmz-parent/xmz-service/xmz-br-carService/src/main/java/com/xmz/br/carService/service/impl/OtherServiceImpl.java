package com.xmz.br.carService.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.carService.entity.vo.Cust;
import com.xmz.br.carService.mapper.OtherMapper;
import com.xmz.br.carService.service.IOtherService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OtherServiceImpl implements IOtherService {

    @Resource
    OtherMapper baseMapper;

    @Override
    public Page<Cust> getCustPage(Page<Cust> page, String licensePlate) {
        return baseMapper.getCustPage(page,licensePlate);
    }
}
