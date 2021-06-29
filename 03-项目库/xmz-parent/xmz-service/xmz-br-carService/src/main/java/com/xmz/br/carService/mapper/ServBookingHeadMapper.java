package com.xmz.br.carService.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.carService.entity.pojo.ServBookingHead;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.carService.entity.vo.ServBookingHeadListQuery;
import com.xmz.br.carService.entity.vo.ServBookingHeadListVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 预约单 Mapper 接口
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-17
 */
public interface ServBookingHeadMapper extends BaseMapper<ServBookingHead> {

    Page<ServBookingHeadListVo> getVoPage(Page<ServBookingHeadListVo> page,
                                          @Param("vo") ServBookingHeadListQuery vo);

}
