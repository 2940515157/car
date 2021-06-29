package com.xmz.service.card.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.card.entity.pojo.AcctCardArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.card.entity.pojo.HrOrg;
import com.xmz.service.card.entity.vo.MyAcctCardArea;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * <p>
 * 卡与使用区域关系表 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-03
 */
public interface IAcctCardAreaService extends IService<AcctCardArea> {


    //根据卡产品的id 查询出卡与使用区域关系表

    Page<MyAcctCardArea> queryAllPage(Page<MyAcctCardArea> pageParam, String acsUuid);


}
