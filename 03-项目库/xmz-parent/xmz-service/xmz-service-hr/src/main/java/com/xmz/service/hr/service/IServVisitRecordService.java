package com.xmz.service.hr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.hr.entity.vo.HrBranchesQuery;
import com.xmz.service.hr.entity.vo.ServVisitRecordQuery;

import java.util.List;

/**
 * <p>
 * 回访记录 服务类
 * </p>
 *
 * @author hwb
 * @since 2021-06-04
 */
public interface IServVisitRecordService extends IService<HuiFang> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param servVisitRecordQuery  查询条件对象
     * @return
     */
    Page<HuiFang> myPage(Page<HuiFang> pageParam,
                                      ServVisitRecordQuery servVisitRecordQuery);

    List<HrOrg> listAll();

    List<HrArea> arealistAll(HrOrg hrOrg);

    List<HrBranches> brancheslistAll(HrBranchesQuery hrBranchsQuery);

}
