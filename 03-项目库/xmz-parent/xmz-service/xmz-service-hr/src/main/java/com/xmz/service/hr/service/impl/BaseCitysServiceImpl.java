package com.xmz.service.hr.service.impl;

import com.xmz.service.hr.entity.pojo.BaseCitys;
import com.xmz.service.hr.mapper.BaseCitysMapper;
import com.xmz.service.hr.service.IBaseCitysService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 城市 服务实现类
 * </p>
 *
 * @author 江志威
 * @since 2021-06-09
 */
@Service
public class BaseCitysServiceImpl extends ServiceImpl<BaseCitysMapper, BaseCitys> implements IBaseCitysService {

    @Override
    public List<BaseCitys> queryallByProvUuid(String id) {
        return baseMapper.queryallByProvUuid(id);
    }
}
