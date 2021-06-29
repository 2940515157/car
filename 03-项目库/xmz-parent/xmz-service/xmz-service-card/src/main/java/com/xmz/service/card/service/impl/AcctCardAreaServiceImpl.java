package com.xmz.service.card.service.impl;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCardArea;

import com.xmz.service.card.entity.vo.MyAcctCardArea;
import com.xmz.service.card.mapper.AcctCardAreaMapper;

import com.xmz.service.card.service.IAcctCardAreaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * <p>
 * 卡与使用区域关系表 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
@Service
public class AcctCardAreaServiceImpl extends ServiceImpl<AcctCardAreaMapper, AcctCardArea> implements IAcctCardAreaService {
    @Autowired(required = false)
    AcctCardAreaMapper acctCardAreaMapper;

    /**
     * 根据卡产品的id 查询出卡与使用区域关系表
     * @param pageParam
     * @param acsUuid
     * @return
     */
    @Override
    public Page<MyAcctCardArea> queryAllPage(Page<MyAcctCardArea> pageParam, String acsUuid) {
        return acctCardAreaMapper.queryAllPage(pageParam,acsUuid);
    }

}
