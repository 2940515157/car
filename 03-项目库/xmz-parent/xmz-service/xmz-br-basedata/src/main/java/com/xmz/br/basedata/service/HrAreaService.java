package com.xmz.br.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.basedata.pojo.entity.HrArea;
import com.xmz.br.basedata.pojo.vo.HrAreaPage;
import com.xmz.br.basedata.pojo.vo.HrAreaQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 区域表 服务类
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-28
 */
public interface HrAreaService extends IService<HrArea> {

    /**
     * 分页查询区域信息
     * @param hrAreaPagePage 分页对象
     * @param hrAreaQuery 查询条件
     * @return
     */
    Page<HrAreaPage> query(Page<HrAreaPage> hrAreaPagePage, @Param(value = "haq") HrAreaQuery hrAreaQuery);

    /**
     * 根据大区查出所有的区域
     * @param orgUuid
     * @return
     */
    List<HrArea> queryAllHrAreas(String orgUuid);

    /**
     * 根据区域id查询出大区id
     * @param areaUuid
     * @return
     */
    HrArea queryAllOrgUuid(String areaUuid);
}
