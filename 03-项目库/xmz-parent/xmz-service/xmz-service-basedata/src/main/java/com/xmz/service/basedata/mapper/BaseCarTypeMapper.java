package com.xmz.service.basedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.basedata.entity.pojo.BaseCarType;
import com.xmz.service.basedata.entity.vo.CarTypeQueryParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 车型 Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2021-06-02
 */
public interface BaseCarTypeMapper extends BaseMapper<BaseCarType> {
    List<BaseCarType> selectData(CarTypeQueryParam queryParam);

    //去重查询所有品牌
    List<BaseCarType> list2();
    //根据品牌车型去重查询年款
    List<BaseCarType> list3();
    //根据品牌车型年款去重查询排量
    List<BaseCarType> list4();
    //车系
    List<BaseCarType> list5();
    //查询厂家
    List<BaseCarType> list6();
}
