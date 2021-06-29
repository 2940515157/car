package com.xmz.br.vipCard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctCardSetup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.pojo.MyAcctCardSetup;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import com.xmz.br.vipCard.entity.vo.AcctCardSetupQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 储值卡产品定义 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
public interface AcctCardSetupMapper extends BaseMapper<AcctCardSetup> {
    /**
     * 查询所有上架了的储值卡
     * @param acctCardSetupQuery 查询条件
     * @return
     */
    List<MyAcctCardSetup> listAcs(@Param(value = "acs") AcctCardSetupQuery acctCardSetupQuery);

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctCardSetupQuery  查询条件对象
     * @return
     */
    Page<MyAcctCardSetup> myPage(Page<MyAcctCardSetup> pageParam,
                              @Param(value = "acs") AcctCardSetupQuery acctCardSetupQuery);

}
