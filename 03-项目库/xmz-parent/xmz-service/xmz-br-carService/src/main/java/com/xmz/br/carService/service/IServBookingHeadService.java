package com.xmz.br.carService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.carService.entity.pojo.ServBookingHead;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.carService.entity.pojo.ServBookingItem;
import com.xmz.br.carService.entity.pojo.ServBookingPart;
import com.xmz.br.carService.entity.vo.ServBookingHeadListQuery;
import com.xmz.br.carService.entity.vo.ServBookingHeadListVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 预约单 服务类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-17
 */
public interface IServBookingHeadService extends IService<ServBookingHead> {

    Page<ServBookingHeadListVo> getVoPage(Page<ServBookingHeadListVo> page, ServBookingHeadListQuery query);

    boolean saveOrUpdate(ServBookingHead hand, List<ServBookingItem> items, List<ServBookingPart> parts,
                         List<String> dItems, List<String> dParts);

    Map<String,Object> getAppointInfo(String sbUuid);

}
