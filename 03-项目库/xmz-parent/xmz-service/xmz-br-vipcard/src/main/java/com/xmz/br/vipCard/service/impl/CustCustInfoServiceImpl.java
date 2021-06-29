package com.xmz.br.vipCard.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.CustCustInfo;
import com.xmz.br.vipCard.entity.pojo.CustVo;
import com.xmz.br.vipCard.entity.pojo.MyCustCustInfo;
import com.xmz.br.vipCard.entity.vo.CustCustInfoQuery;
import com.xmz.br.vipCard.mapper.CustCustInfoMapper;
import com.xmz.br.vipCard.service.ICustCustInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 客户信息 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
@Service
public class CustCustInfoServiceImpl extends ServiceImpl<CustCustInfoMapper, CustCustInfo> implements ICustCustInfoService {


    @Override
    public List<MyCustCustInfo> queryByPhone(CustCustInfoQuery custCustInfoQuery) {
        return baseMapper.queryByPhone(custCustInfoQuery);
    }

    @Override
    public List<CustVo> pageList(Page<CustVo> pojoPage, CustVo custVo) {
        return baseMapper.pageList(pojoPage,custVo);
    }
}
