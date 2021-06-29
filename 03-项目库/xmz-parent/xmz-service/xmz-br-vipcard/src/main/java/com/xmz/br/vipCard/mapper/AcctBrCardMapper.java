package com.xmz.br.vipCard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctBrCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.HrEmployees;
import com.xmz.br.vipCard.entity.pojo.MyAcctBrCard;
import com.xmz.br.vipCard.entity.vo.AcctBrCardQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门店领卡 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-06
 */
public interface AcctBrCardMapper extends BaseMapper<AcctBrCard> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctBrCardQuery  查询条件对象
     * @return
     */
    Page<MyAcctBrCard> myPage(Page<MyAcctBrCard> pageParam,
                              @Param(value = "abc") AcctBrCardQuery acctBrCardQuery);


    Page<MyAcctBrCard> queryByCardNo(Page<MyAcctBrCard> pageParam,
                                     @Param(value = "abc") AcctBrCardQuery acctBrCardQuery);

    List<HrEmployees> listHe();
}
