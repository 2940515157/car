package com.xmz.service.card.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.service.card.entity.pojo.BrPackage;
import com.xmz.service.card.entity.vo.BrPackageQuery;
import com.xmz.service.card.entity.vo.MyBrPackage;
import com.xmz.service.card.mapper.BrPackageMapper;
import com.xmz.service.card.service.IBrPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 门店套餐 服务实现类
 * </p>
 *
 * @author liujiaxi,
 * @since 2021-05-28
 */
@Service
public class BrPackageServiceImpl extends ServiceImpl<BrPackageMapper, BrPackage> implements IBrPackageService {

    @Autowired(required = false)
    BrPackageMapper brPackageMapper;

    /**
     * 门店套餐情况 分页多表查询方法
     * @param pageParam 分页参数
     * @param brPackageQuery 查询条件对象
     * @return
     */
    @Override
    public Page<MyBrPackage> queryAllBrPack(Page<MyBrPackage> pageParam, BrPackageQuery brPackageQuery) {
        return brPackageMapper.queryAllBrPack(pageParam,brPackageQuery);
    }

    @Override
    public MyBrPackage getByBrPackageUuid(String brPackageUuid) {
        return baseMapper.getByBrPackageUuid(brPackageUuid);
    }


}
