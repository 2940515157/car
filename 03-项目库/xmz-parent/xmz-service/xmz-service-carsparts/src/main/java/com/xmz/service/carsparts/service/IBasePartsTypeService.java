package com.xmz.service.carsparts.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BasePartsType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.carsparts.entity.vo.BasePartsTypeQuery;

import java.util.List;

/**
 * <p>
 * 配件目录 服务类
 * </p>
 *
 * @author 江志威
 * @since 2021-05-31
 */
public interface IBasePartsTypeService extends IService<BasePartsType> {
    /**
     * 获取总父节点
     */
    List<BasePartsType> carTypeFuTree();


    /**
     * 获取自身子节点
     */
    List<BasePartsType> carTypeZiTree(List<BasePartsType> basePartsTypeList);

    // 多条件分页查询
    Page<BasePartsType> queryQuery(Page<BasePartsType> pageParam, BasePartsTypeQuery basePartsTypeQuery);
}
