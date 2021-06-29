package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctBrCard;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.AcctBrCardQuery;
import com.xmz.service.card.entity.vo.MyAcctBrCard;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 门店领卡 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
public interface AcctBrCardMapper extends BaseMapper<AcctBrCard> {

    /**
     * 发卡管理分页条件查询
     * @param pageParam //分页参数
     * @param brCardQuery //查询条件
     * @return
     */
    Page<MyAcctBrCard> queryAllABrCard(Page<MyAcctBrCard> pageParam,@Param("abcq")AcctBrCardQuery
                                       brCardQuery);



}
