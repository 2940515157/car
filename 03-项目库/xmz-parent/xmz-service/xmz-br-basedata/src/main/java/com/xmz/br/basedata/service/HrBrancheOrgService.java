package com.xmz.br.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.basedata.pojo.entity.HrBrancheOrg;
import com.xmz.br.basedata.pojo.vo.MyHrBrancheOrg;
import com.xmz.br.basedata.pojo.vo.QueryHrBrancheOrg;

/**
 * <p>
 * 车间信息 服务类
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-31
 */
public interface HrBrancheOrgService extends IService<HrBrancheOrg> {


    //分店  车间信息查询
    Page<MyHrBrancheOrg> queryBrancheOrg(Page<MyHrBrancheOrg> page, QueryHrBrancheOrg queryHrBrancheOrg);


}
