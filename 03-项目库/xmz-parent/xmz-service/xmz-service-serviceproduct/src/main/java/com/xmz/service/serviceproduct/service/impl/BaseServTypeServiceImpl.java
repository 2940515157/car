package com.xmz.service.serviceproduct.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.BaseServType;
import com.xmz.service.serviceproduct.entity.vo.ServTypeQuery;
import com.xmz.service.serviceproduct.mapper.BaseServTypeMapper;
import com.xmz.service.serviceproduct.service.IBaseServTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务类型 服务实现类
 * </p>
 *
 * @author hwb
 * @since 2021-06-03
 */
@Service
public class BaseServTypeServiceImpl extends ServiceImpl<BaseServTypeMapper, BaseServType> implements IBaseServTypeService {

    @Autowired(required = false)
    BaseServTypeMapper baseServTypeMapper;

    @Override
    public Page<BaseServType> myPage(Page<BaseServType> pageParam, ServTypeQuery servTypeQuery) {

        return baseMapper.myPage(pageParam, servTypeQuery);
    }

    /**
     * 查找左边的父菜单
     * @return
     */
    @Override
    public List<BaseServType> findAllBase() {
        List<BaseServType> menus=baseServTypeMapper.findAllBase();
        getMenuChildren(menus);
        return menus;
    }

    /**
     * 查询左边的菜单
     * @param list
     * @return
     */
    @Override
    public List<BaseServType> getMenuChildren(List<BaseServType> list) {
        for(BaseServType baseServType:list){
            String stUuid=baseServType.getStUuid();
            List<BaseServType>   baseServTypes1=baseServTypeMapper.getMenuChildren(stUuid);
            baseServType.setLabel(baseServType.getLabel());

            if(baseServTypes1!=null){
                baseServType.setChildren(baseServTypes1);
                getMenuChildren(baseServTypes1);
            }
        }
        return null;
    }


}
