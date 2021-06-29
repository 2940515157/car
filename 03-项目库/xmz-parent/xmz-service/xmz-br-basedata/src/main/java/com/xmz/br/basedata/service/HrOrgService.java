package com.xmz.br.basedata.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.basedata.pojo.entity.HrOrg;
import com.xmz.br.basedata.pojo.vo.HrOrgPage;
import com.xmz.br.basedata.pojo.vo.HrOrgQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门信息 服务类
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-26
 */
public interface HrOrgService extends IService<HrOrg> {

    /**
     * 分页查询全部大区
     * @param HrOrgPage
     * @return
     */
    IPage<HrOrg> queryAllBigAreaPage(Page<HrOrg> HrOrgPage);

    //分页查询不包含大区的数据
    Page<HrOrg> noPageQuery(Page<HrOrg> pageParams, String[] orgNo);

    public List<HrOrgPage> findList(HrOrgQuery hrOrgQuery);

    public List<HrOrgPage> parentAndChildren(List<HrOrgPage> list,Integer minOrg);

    public List<HrOrgPage> getTree(List<HrOrgPage> rootList, List<HrOrgPage> bodyList);

    public void getChild(HrOrgPage parent,List<HrOrgPage> bodyList, Map<String,String> map);

    /**
     *通过部门id查询部门信息 页面特殊需要
     * @param orgUuid
     * @return
     */
    public HrOrgPage queryOrgById(String orgUuid);

    /**
     * 通过上级部门id查询出部门信息
     * @param parentId
     * @return
     */
//    public HrOrg queryByParentId(String parentId);
}
