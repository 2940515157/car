package com.xmz.br.basedata.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.basedata.mapper.HrOrgMapper;
import com.xmz.br.basedata.pojo.entity.HrOrg;
import com.xmz.br.basedata.pojo.vo.HrOrgPage;
import com.xmz.br.basedata.pojo.vo.HrOrgQuery;
import com.xmz.br.basedata.service.HrOrgService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门信息 服务实现类
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-26
 */
@Service
public class HrOrgServiceImpl extends ServiceImpl<HrOrgMapper, HrOrg> implements HrOrgService {

    /**
     * 分页查询出全部的大区数据
     * @param HrOrgPage
     * @return
     */
    @Override
    public IPage<HrOrg> queryAllBigAreaPage(Page<HrOrg> HrOrgPage) {
        QueryWrapper<HrOrg> hrOrgPageQueryWrapper = new QueryWrapper<>();
        //判断是大区
        hrOrgPageQueryWrapper.eq("IS_BIG_AREA","Y");
        //判断不停用
        hrOrgPageQueryWrapper.eq("IS_DISABLED","N");
        //判断有效
        hrOrgPageQueryWrapper.eq("IS_ACTIVE","Y");
        return baseMapper.selectPage(HrOrgPage,hrOrgPageQueryWrapper);
    }

    @Override
    public Page<HrOrg> noPageQuery(Page<HrOrg> pageParams, String[] orgNo) {
        return baseMapper.noPageQuery(pageParams,orgNo);
    }


    //--------------------------------------------------封装tree数据测试

    /**
     * 分页查询出部门Tree结构数据
     * @param hrOrgQuery 查询条件
     * @return
     */
    @Override
    public List<HrOrgPage> findList(HrOrgQuery hrOrgQuery) {
        //查询出全部满足条件的部门
        List<HrOrgPage> list = baseMapper.query(hrOrgQuery);
        //查询出满足数据的最外层部门级别
        Integer minOrg = baseMapper.queryMinOrg(hrOrgQuery);

        if (list.isEmpty()){
            return null;
        }
        //父子级组装
        return parentAndChildren(list,minOrg);
    }

    /**
     * 从条件查询来的数据中分离出 最顶层根节点 和 非最顶层根节点
     * @param list 条件查询出来的部门数据
     * @param minOrg 最外层部门级别
     * @return
     */
    @Override
    public List<HrOrgPage> parentAndChildren(List<HrOrgPage> list,Integer minOrg){

        //最顶层根节点
        List<HrOrgPage> rootList = new ArrayList<>();
        //非最顶层根节点
        List<HrOrgPage> bodyList = new ArrayList<>();

        for (HrOrgPage hrOrgPage : list) {
            //说明是当前查询出数据的最外层
            if (hrOrgPage.getLevel().equals(minOrg)){
                rootList.add(hrOrgPage);
            }else{
                bodyList.add(hrOrgPage);
            }
        }
        return getTree(rootList,bodyList);
    }

    /**
     * 拿到 最顶层根节点 和 非最顶层根节点 后进行封装
     * @param rootList 最顶层根节点
     * @param bodyList 非最顶层根节点
     * @return
     */
    @Override
    public List<HrOrgPage> getTree(List<HrOrgPage> rootList, List<HrOrgPage> bodyList){
        if (!bodyList.isEmpty()){
            //声明一个map，用来过滤已操作过的数据
            Map<String,String> map = new HashMap<>(bodyList.size());
            rootList.forEach(parent->getChild(parent,bodyList,map));
            return rootList;
        }else{
            return rootList;
        }
    }

    /**
     * 真正为集合中每一条部门对象setChildren
     * @param parent 要setChildren的部门对象
     * @param bodyList 子集合
     * @param map
     */
    @Override
    public void getChild(HrOrgPage parent,List<HrOrgPage> bodyList, Map<String,String> map){
        List<HrOrgPage> childList = new ArrayList<>();
        bodyList.stream().filter(c->!map.containsKey(c.getOrgUuid()))
                .filter(c->c.getParentUuid().equals(parent.getOrgUuid()))
                .forEach(c->{
                    map.put(c.getOrgUuid(),c.getParentUuid());
                    getChild(c,bodyList,map);
                    childList.add(c);
                });

        //设置children
        parent.setChildren(childList);
        //设置parentName
        if(!StringUtils.isEmpty(parent.getParentUuid())){
            String parentName = baseMapper.queryParentNameByParentId(parent.getParentUuid());
            parent.setParentName(parentName);
        }
    }

    @Override
    public HrOrgPage queryOrgById(String orgUuid) {
        return baseMapper.queryOrgById(orgUuid);
    }

    /**
     * 通过上级部门id查询一条部门对象信息
     * @param parentId
     * @return
//     */
//    @Override
//    public HrOrg queryByParentId(String parentId) {
//        QueryWrapper<HrOrg> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("PARENT_UUID",parentId);
//
//        HrOrg hrOrg = baseMapper.selectOne(queryWrapper);
//        return hrOrg;
//    }
//
//    //--------------------------------------------------封装tree数据测试

}
