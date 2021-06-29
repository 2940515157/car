package com.xmz.service.serviceproduct.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.BaseServType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupDirectory;
import com.xmz.service.serviceproduct.entity.vo.ServCheckQuery;
import com.xmz.service.serviceproduct.entity.vo.ServTypeQuery;

import java.util.List;

/**
 * <p>
 * 服务类型 服务类
 * </p>
 *
 * @author hwb
 * @since 2021-06-03
 */
public interface IBaseServTypeService extends IService<BaseServType> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param servTypeQuery  查询条件对象
     * @return
     */
    Page<BaseServType> myPage(Page<BaseServType> pageParam,
                                      ServTypeQuery servTypeQuery);

    /**
     * 查询左边套餐标签 树状
     * @return
     */
    List<BaseServType> findAllBase();

    /**
     * 查询左边套餐标签 树状
     * @return
     */
    List<BaseServType> getMenuChildren(List<BaseServType> list);

}
