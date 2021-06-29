package com.xmz.service.card.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.AcctCardArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.card.entity.vo.MyAcctCardArea;

/**
 * <p>
 * 卡与使用区域关系表 Mapper 接口
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
public interface AcctCardAreaMapper extends BaseMapper<AcctCardArea> {

    //根据卡产品的id 查询出卡与使用区域关系表

    Page<MyAcctCardArea> queryAllPage(Page<MyAcctCardArea> pageParam, String acsUuid);

}
