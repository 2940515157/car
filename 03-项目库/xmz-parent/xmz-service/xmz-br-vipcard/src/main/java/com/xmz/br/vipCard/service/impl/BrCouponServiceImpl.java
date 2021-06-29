package com.xmz.br.vipCard.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.vipCard.entity.pojo.BrCouponPojo;
import com.xmz.br.vipCard.entity.pojo.BrCouponVo;
import com.xmz.br.vipCard.mapper.BrCouponMapper;
import com.xmz.br.vipCard.service.BrCouponService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 门店与优惠券对应关系 服务实现类
 * </p>
 *
 * @author LLY
 * @since 2021-06-10
 */
@Service
public class BrCouponServiceImpl extends ServiceImpl<BrCouponMapper, BrCouponPojo> implements BrCouponService {

    @Override
    public List<BrCouponVo> pageList(Page<BrCouponVo> pojoPage, BrCouponVo brCouponVo) {
        return baseMapper.pageList(pojoPage,brCouponVo);
    }
}
