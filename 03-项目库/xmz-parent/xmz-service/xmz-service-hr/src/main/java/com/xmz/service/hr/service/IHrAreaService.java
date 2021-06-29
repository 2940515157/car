package com.xmz.service.hr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.HrArea;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.hr.entity.pojo.HrAreaPage;
import com.xmz.service.hr.entity.pojo.HrOrg;
import com.xmz.service.hr.entity.vo.HrAreaQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author lm
 * @since 2021-06-01
 */
public interface IHrAreaService extends IService<HrArea> {

    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param hrAreaQuery  查询条件对象
     * @return
     */
    Page<HrAreaPage> myPage(Page<HrAreaPage> pageParam,
                        HrAreaQuery hrAreaQuery);

    List<HrOrg> listAll();

    /**
     * 根据大区查出所有的区域
     * @param orgUuid
     * @return
     */
    List<HrArea> queryAllHrAreas(String orgUuid);

    /**
     * 根据大区id查询区域
     * @return
     */
    List<HrArea> queryallQuYu(String id);

    /**
     * 根据区域id查询出大区id
     * @param areaUuid
     * @return
     */
    HrArea queryAllOrgUuid(String areaUuid);
}
