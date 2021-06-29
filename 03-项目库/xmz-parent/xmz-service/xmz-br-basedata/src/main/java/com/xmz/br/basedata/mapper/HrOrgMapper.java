package com.xmz.br.basedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrOrg;
import com.xmz.br.basedata.pojo.vo.HrOrgPage;
import com.xmz.br.basedata.pojo.vo.HrOrgQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 部门信息 Mapper 接口
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-26
 */
@Repository
@Component
public interface HrOrgMapper extends BaseMapper<HrOrg> {

    /**
     * 按条件查询出部门数据
     * @param hrOrgQuery
     * @return
     */
    public List<HrOrgPage> query(@Param("hoq") HrOrgQuery hrOrgQuery);

    /**
     * 按条件查询出最外层部门的级别
     * @param hrOrgQuery
     * @return
     */
    public Integer queryMinOrg(@Param("hoq") HrOrgQuery hrOrgQuery);

    //分页查询不包含大区的数据
    Page<HrOrg> noPageQuery(Page<HrOrg> pageParams, String[] orgNo);

    /**
     * 根据上级部门id查询出上级部门名
     * @param parentId
     * @return
     */
    public String queryParentNameByParentId(String parentId);

    /**
     *通过部门id查询部门信息 页面特殊需要
     * @param orgUuid
     * @return
     */
    public HrOrgPage queryOrgById(String orgUuid);


}
