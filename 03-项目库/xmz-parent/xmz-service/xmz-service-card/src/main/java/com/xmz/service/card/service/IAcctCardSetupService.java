package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCardSetup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.vo.AcctCardSetupQuery;
import com.xmz.service.card.entity.vo.MyAcctCardSetup;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 储值卡产品定义 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-01
 */
public interface IAcctCardSetupService extends IService<AcctCardSetup> {

    /**
     * 储值卡产品定义 分页条件查询
     * @param pageParam 分页参数
     * @param acctCardSetupQuery 查询条件对象
     * @return
     */
    Page<MyAcctCardSetup> queryAllAcct(Page<MyAcctCardSetup> pageParam,AcctCardSetupQuery acctCardSetupQuery);


}
