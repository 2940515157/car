package com.xmz.br.vipCard.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctBrCard;
import com.xmz.br.vipCard.entity.pojo.HrEmployees;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.mapper.AcctBrCardMapper;
import com.xmz.br.vipCard.service.IAcctBrCardService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 门店领卡 服务实现类
 * </p>
 *
 * @author zt
 * @since 2021-06-06
 */
@Service
public class AcctBrCardServiceImpl extends ServiceImpl<AcctBrCardMapper, AcctBrCard> implements IAcctBrCardService {

    @Override
    public Page<MyAcctBrCard> myPage(Page<MyAcctBrCard> pageParam, AcctBrCardQuery acctBrCardQuery) {
        return baseMapper.myPage(pageParam,acctBrCardQuery);
    }

    @Override
    public Page<MyAcctBrCard> queryByCardNo(Page<MyAcctBrCard> pageParam, AcctBrCardQuery acctBrCardQuery) {
        return baseMapper.queryByCardNo(pageParam,acctBrCardQuery);
    }

    @Override
    public List<HrEmployees> listHe() {
        return baseMapper.listHe();
    }


}
