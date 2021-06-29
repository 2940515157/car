package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctBrCard;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.AcctBrCardQuery;
import com.xmz.service.card.entity.vo.MyAcctBrCard;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * <p>
 * 门店领卡 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-07
 */
public interface IAcctBrCardService extends IService<AcctBrCard> {
    /**
     * 发卡管理分页条件查询
     * @param pageParam //分页参数
     * @param brCardQuery //查询条件
     * @return
     */
    Page<MyAcctBrCard> queryAllABrCard(Page<MyAcctBrCard> pageParam,AcctBrCardQuery
            brCardQuery);

    /**
     * 增加发卡
     * @param data
     */
    boolean add(Map<String,String> data);


}
