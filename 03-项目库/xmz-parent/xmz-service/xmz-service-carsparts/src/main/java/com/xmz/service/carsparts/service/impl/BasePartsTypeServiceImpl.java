package com.xmz.service.carsparts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.carsparts.entity.pojo.BasePartsType;
import com.xmz.service.carsparts.entity.vo.BasePartsTypeQuery;
import com.xmz.service.carsparts.mapper.BasePartsTypeMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmz.service.carsparts.service.IBasePartsTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 配件目录 服务实现类
 * </p>
 *
 * @author 江志威
 * @since 2021-05-31
 */
@Service
public class BasePartsTypeServiceImpl extends ServiceImpl<BasePartsTypeMapper, BasePartsType> implements IBasePartsTypeService {

    @Override
    public List<BasePartsType> carTypeFuTree() {
        return baseMapper.carTypeFuTree();
    }

    @Override
    public List<BasePartsType> carTypeZiTree(List<BasePartsType> basePartsTypeList) {
        for(BasePartsType basePartsType:basePartsTypeList){
            String partTypeUuid=basePartsType.getPartTypeUuid();
            List<BasePartsType> basePartsTypes=baseMapper.carTypeZiTree(partTypeUuid);
            basePartsType.setName(basePartsType.getName());

            if(basePartsTypes!=null){
                basePartsType.setChildren(basePartsTypes);
                carTypeZiTree(basePartsTypes);
            }
        }
        return basePartsTypeList;
    }

    @Override
    public Page<BasePartsType> queryQuery(Page<BasePartsType> pageParam, BasePartsTypeQuery basePartsTypeQuery) {
        return baseMapper.queryQuery(pageParam,basePartsTypeQuery);
    }
}
