package com.xmz.service.card.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.card.entity.pojo.BasePkgTagDirectory;
import com.xmz.service.card.entity.vo.BasePkgTagDirectoryQuery;
import com.xmz.service.card.mapper.BasePkgTagDirectoryMapper;
import com.xmz.service.card.service.IBasePkgTagDirectoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 套餐标签目录 服务实现类
 * </p>
 *
 * @author liujiaxi
 * @since 2021-05-28
 */
@Service
public class BasePkgTagDirectoryServiceImpl extends ServiceImpl<BasePkgTagDirectoryMapper, BasePkgTagDirectory> implements IBasePkgTagDirectoryService {

    @Autowired(required = false)
    private BasePkgTagDirectoryMapper basePkgTagDirectoryMapper;

    /**
     * 查询左边菜单
     * @param list
     * @return
     */
    @Override
    public List<BasePkgTagDirectory> getMenuChildren(List<BasePkgTagDirectory> list) {

        for(BasePkgTagDirectory basePkgTagDirectory:list){
            String bptduuid=basePkgTagDirectory.getBptdUuid();
         List<BasePkgTagDirectory>   basePkgTagDirectories1=basePkgTagDirectoryMapper.getMenuChildren(bptduuid);
            basePkgTagDirectory.setLabel(basePkgTagDirectory.getLabel());

            if(basePkgTagDirectories1!=null){
                basePkgTagDirectory.setChildren(basePkgTagDirectories1);
                getMenuChildren(basePkgTagDirectories1);
            }
        }
        return null;
    }


    /**
     * 查询左边菜单
     * @return
     */
    @Override
    public List<BasePkgTagDirectory> findAllTaoCanBiaoQian() {
        List<BasePkgTagDirectory> menus =basePkgTagDirectoryMapper.findAllTaoCanBiaoQian();
        getMenuChildren(menus);
        return menus;
    }

    /**
     * 点击左边菜单  查询出右边的数据
     * @param pageParam
     * @param basePkgTagDirectoryQuery
     * @return
     */
    @Override
    public Page<BasePkgTagDirectory> getMenuRight(Page<BasePkgTagDirectory> pageParam, BasePkgTagDirectoryQuery basePkgTagDirectoryQuery) {

        //判断 BptdUuid 是否为空
        if(StringUtils.isEmpty(basePkgTagDirectoryQuery.getBptdUuid())){
           //查询出所有的 BptdUuid
            List<BasePkgTagDirectory> menus =basePkgTagDirectoryMapper.findAllTaoCanBiaoQian();

            for(BasePkgTagDirectory basePkgTagDirectory:menus){

                basePkgTagDirectoryQuery.setBptdUuid(basePkgTagDirectory.getBptdUuid());
            }

        }
        return basePkgTagDirectoryMapper.getMenuRight(pageParam,basePkgTagDirectoryQuery);
    }


}
