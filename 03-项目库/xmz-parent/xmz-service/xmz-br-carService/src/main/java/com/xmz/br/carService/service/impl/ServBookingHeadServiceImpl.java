package com.xmz.br.carService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import com.xmz.br.carService.entity.pojo.ServBookingHead;
import com.xmz.br.carService.entity.pojo.ServBookingItem;
import com.xmz.br.carService.entity.pojo.ServBookingPart;
import com.xmz.br.carService.entity.vo.Cust;
import com.xmz.br.carService.entity.vo.ServBookingHeadListQuery;
import com.xmz.br.carService.entity.vo.ServBookingHeadListVo;
import com.xmz.br.carService.mapper.OtherMapper;
import com.xmz.br.carService.mapper.ServBookingHeadMapper;
import com.xmz.br.carService.service.IServBookingHeadService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.br.carService.service.IServBookingItemService;
import com.xmz.br.carService.service.IServBookingPartService;
import com.xmz.common.utils.Result;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.beans.Transient;
import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 * 预约单 服务实现类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-17
 */
@Service
public class ServBookingHeadServiceImpl extends ServiceImpl<ServBookingHeadMapper, ServBookingHead> implements IServBookingHeadService {
    @Resource
    OtherMapper otherMapper;

    @Autowired
    IServBookingItemService bookingItemService;
    @Autowired
    IServBookingPartService bookingPartService;


    @Override
    public Page<ServBookingHeadListVo> getVoPage(Page<ServBookingHeadListVo> page,
                                                 ServBookingHeadListQuery query) {
        String[] blDate = query.getBlDate();
        if(!ArrayUtils.isEmpty(blDate)){
            query.setBlDateStart(blDate[0]);
            query.setBlDateEnd(blDate[1]);
        }
        String[] createTime = query.getCreateTime();
        if(!ArrayUtils.isEmpty(createTime)){
            query.setCreateTimeStart(createTime[0]);
            query.setCreateTimeEnd(createTime[1]);
        }

        return baseMapper.getVoPage(page, query);
    }

    @Override
    @Transactional
    public boolean saveOrUpdate(ServBookingHead head, List<ServBookingItem> items, List<ServBookingPart> parts,
                                List<String> dItemIds, List<String> dPartIds) {
        boolean b;
        head.setVersion(new BigDecimal(0));
        if(StringUtils.isNullOrEmpty(head.getSbUuid())){
            head.setCreatedDate(Calendar.getInstance().getTime());
            head.setUpdatedDate(Calendar.getInstance().getTime());
            head.setStatus("booking");
            b = save(head);
        }else {
            head.setUpdatedDate(Calendar.getInstance().getTime());
            b = updateById(head);
        }
        for(ServBookingItem item : items){
            item.setSbUuid(head.getSbUuid());
        }
        for(ServBookingPart part : parts){
            part.setSbUuid(head.getSbUuid());
        }
        b = bookingItemService.saveOrUpdateBatch(items);
        if (parts.size()>0) b = bookingPartService.saveOrUpdateBatch(parts);
        if(dItemIds.size()>0) b = bookingItemService.removeByIds(dItemIds);
        if(dPartIds.size()>0) b = bookingPartService.removeByIds(dPartIds);
        if(!b) throw new RuntimeException("执行异常");
        return true;
    }

    @Override
    public Map<String,Object> getAppointInfo(String sbUuid){
        Map<String,Object> _return = new HashMap<>();
        ServBookingHead head = getById(sbUuid);
//        List<ServBookingItem> items =
//                bookingItemService.list(new QueryWrapper<ServBookingItem>().eq("SB_UUID", sbUuid));
//        List<ServBookingPart> parts =
//                bookingPartService.list(new QueryWrapper<ServBookingPart>().eq("SB_UUID", sbUuid));
        Page<Cust> cust = otherMapper.getCustPage(new Page<>(1, 1), head.getLicensePlate());
        List<Map<String, Object>> items = otherMapper.getServBookingItems(sbUuid);
        List<Map<String, Object>> parts = otherMapper.getServBookingParts(sbUuid);

        _return.put("cust",cust.getRecords().get(0));
        _return.put("head",head);
        _return.put("items",items);
        _return.put("parts",parts);

        return _return;
    }

}
