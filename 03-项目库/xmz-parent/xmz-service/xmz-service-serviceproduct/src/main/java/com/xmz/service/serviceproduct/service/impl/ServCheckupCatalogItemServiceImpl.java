package com.xmz.service.serviceproduct.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupCatalogItem;
import com.xmz.service.serviceproduct.mapper.ServCheckupCatalogItemMapper;
import com.xmz.service.serviceproduct.service.IServCheckupCatalogItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 点检项目明细 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-05-28
 */
@Service
public class ServCheckupCatalogItemServiceImpl extends ServiceImpl<ServCheckupCatalogItemMapper, ServCheckupCatalogItem> implements IServCheckupCatalogItemService {

    @Override
    public int deleteBySccUuid(String id) {
        return baseMapper.deleteBySccUuid(id);
    }

    @Override
    public List<ServCheckupCatalogItem> querySccUuid(String sccUuid) {
        return baseMapper.querySccUuid(sccUuid);
    }
}
