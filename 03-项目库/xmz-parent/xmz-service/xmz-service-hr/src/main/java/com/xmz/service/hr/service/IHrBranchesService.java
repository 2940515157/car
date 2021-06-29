package com.xmz.service.hr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrBranches;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.hr.entity.vo.HrBranchesQuery;
import com.xmz.service.hr.entity.vo.MyHrBranches;
import feign.Param;

import java.util.List;

/**
 * <p>
 * 门店信息 服务类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-06-04
 */
public interface IHrBranchesService extends IService<HrBranches> {
    /**
     * 分页查询门店
     * @param pageParam
     * @return
     */
    Page<MyHrBranches> queryAllPage(Page<MyHrBranches> pageParam,String brName);

    /**
     * 根据大区和区域查询出门店
     * @param hrBranches
     * @return
     */
    List<HrBranches> queryAllMenDian(HrBranches hrBranches);

    /**
     * 门店信息 分页条件查询
     * @param pageParam 分页参数
     * @param hrBrancherQuery 查询条件对象
     * @return
     */
    Page<MyHrBranches> query(Page<MyHrBranches> pageParam,HrBranchesQuery hrBrancherQuery);


    /**
     * 根据id查询门店信息
     */
    HrBranches getById(String brUuid);

    /**
     * 查询所有门店
     */
    List<HrBranches> queryAll();
}
