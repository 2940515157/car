package com.xmz.service.hr.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrArea;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.hr.entity.pojo.HrAreaPage;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.xmz.service.hr.entity.vo.HrAreaQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 区域表 Mapper 接口
 * </p>
 *
 * @author lm
 * @since 2021-06-01
 */
public interface HrAreaMapper extends BaseMapper<HrArea> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param hrAreaQuery  查询条件对象
     * @return
     */
    Page<HrAreaPage> myPage(Page<HrAreaPage> pageParam,
                            @Param(value = "haq") HrAreaQuery hrAreaQuery);

    List<HrOrg> listAll();

    /**
     * 根据大区id查询区域
     * @return
     */
    List<HrArea> queryallQuYu(@Param(value = "id") String id);

    /**
     * 根据大区查出所有的区域
     * @param orgUuid
     * @return
     */
    List<HrArea> queryAllHrAreas(String orgUuid);

    /**
     * 根据大区ID查询出所有的ID 用于SysAccountImpl模块
     * @param orgId 大区ID
     * @author 罗俊
     * @return 区域ID
     */
    List<String> getIdsByOrgId(String orgId);
    /**
     * 根据区域id查询出大区id
     * @param areaUuid
     * @return
     */
    HrArea queryAllOrgUuid(String areaUuid);

}
