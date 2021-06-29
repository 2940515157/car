package com.xmz.service.hr.service;

import com.xmz.service.hr.entity.pojo.BaseCitys;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 城市 服务类
 * </p>
 *
 * @author 江志威
 * @since 2021-06-07
 */
public interface IBaseCitysService extends IService<BaseCitys> {
    /**
     * 根据省份查询城市
     */
    List<BaseCitys> queryallByProvUuid(String id);
}
