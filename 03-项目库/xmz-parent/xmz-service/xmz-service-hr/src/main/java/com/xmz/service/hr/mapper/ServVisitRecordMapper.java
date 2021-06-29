package com.xmz.service.hr.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.*;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.hr.entity.vo.HrBranchesQuery;
import com.xmz.service.hr.entity.vo.ServVisitRecordQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 回访记录 Mapper 接口
 * </p>
 *
 * @author hwb
 * @since 2021-06-04
 */
public interface ServVisitRecordMapper extends BaseMapper<HuiFang> {
    Page<HuiFang> myPage(Page<HuiFang> pageParam,
                         @Param(value = "sr") ServVisitRecordQuery servVisitRecordQuery);

    List<HrOrg> listAll();

    List<HrArea> arealistAll(HrOrg hrOrg);

    List<HrBranches> brancheslistAll(HrBranchesQuery hrBranchsQuery);
}
