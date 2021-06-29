package com.xmz.br.vipCard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.AcctCardSetup;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.MyAcctCardSetup;
import com.xmz.br.vipCard.entity.vo.AcctCardSetupQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 储值卡产品定义 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-09
 */
public interface IAcctCardSetupService extends IService<AcctCardSetup> {
    /**
     * 查询所有上架了的储值卡
     * @param acctCardSetupQuery 查询条件
     * @return
     */
    List<MyAcctCardSetup> listAcs(AcctCardSetupQuery acctCardSetupQuery);

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param acctCardSetupQuery  查询条件对象
     * @return
     */
    Page<MyAcctCardSetup> myPage(Page<MyAcctCardSetup> pageParam, AcctCardSetupQuery acctCardSetupQuery);
}
