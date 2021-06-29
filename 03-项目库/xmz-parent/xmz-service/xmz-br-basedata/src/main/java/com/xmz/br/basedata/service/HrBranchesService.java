package com.xmz.br.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.basedata.pojo.entity.HrBranches;
import com.xmz.br.basedata.pojo.vo.HrBranchesPage;
import com.xmz.br.basedata.pojo.vo.HrBranchesQuery;
import com.xmz.br.basedata.pojo.vo.MyHrBranches;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 门店信息 服务类
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-28
 */
public interface HrBranchesService extends IService<HrBranches> {


    MyHrBranches queryMyHrBranches(String brCode);


    public void query(Page<HrBranchesPage> hrBranchesPagePage, HrBranchesQuery hrBranchesQuery);

    //分页查询不包含门店的数据
    Page<HrBranchesPage> noPageQuery(Page<HrBranchesPage> pageParams,String[] brCode,String brName);

    //分页条件查询门店的数据
    Page<HrBranchesPage> pageQuery(Page<HrBranchesPage> pageParams,String brName);

    /**
     * 根据账号id查询出 未 授权的门店
     * @param hrBranchesPagePage
     * @param hrBranchesQuery
     * @return
     */
    Page<HrBranchesPage> queryUnAuthorizationBranches(Page<HrBranchesPage> hrBranchesPagePage, @Param(value = "hbq")HrBranchesQuery hrBranchesQuery);

    /**
     * 根据账号id查询出 已 授权的门店
     * @param hrBranchesPagePage
     * @param hrBranchesQuery
     * @return
     */
    Page<HrBranchesPage> queryAuthorizationBranches(Page<HrBranchesPage> hrBranchesPagePage, @Param(value = "hbq")HrBranchesQuery hrBranchesQuery);

    /**
     * 根据区域查询出门店
     * @param areaUuid
     * @return
     */
    List<HrBranches> queryAllMenDian(String areaUuid);
}
