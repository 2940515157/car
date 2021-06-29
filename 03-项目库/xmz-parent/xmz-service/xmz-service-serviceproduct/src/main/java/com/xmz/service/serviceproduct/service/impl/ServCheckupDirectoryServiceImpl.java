package com.xmz.service.serviceproduct.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupDirectory;
import com.xmz.service.serviceproduct.entity.vo.ServCheckQuery;
import com.xmz.service.serviceproduct.mapper.ServCheckupDirectoryMapper;
import com.xmz.service.serviceproduct.service.IServCheckupDirectoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 点检目录 服务实现类
 * </p>
 *
 * @author hwb
 * @since 2021-05-26
 */
@Service
public class ServCheckupDirectoryServiceImpl extends ServiceImpl<ServCheckupDirectoryMapper, ServCheckupDirectory> implements IServCheckupDirectoryService {

    @Autowired(required = false)
    private ServCheckupDirectoryMapper servCheckupDirectoryMapper;

    @Override
    public List<ServCheckupDirectory> listAll() {
        return baseMapper.listAll();
    }

    @Override
    public List<ServCheckupDirectory> findAllTree() {
        List<ServCheckupDirectory> menus =servCheckupDirectoryMapper.findAllTree();
        getMenuChildren(menus);
        return menus;
    }

    @Override
    public List<ServCheckupDirectory> getMenuChildren(List<ServCheckupDirectory> list) {
        for(ServCheckupDirectory servCheckupDirectory:list){
            String scdUuid=servCheckupDirectory.getScdUuid();
            List<ServCheckupDirectory> servCheckupDirectories=servCheckupDirectoryMapper.getMenuChildren(scdUuid);
            servCheckupDirectory.setLabel(servCheckupDirectory.getLabel());

            if(servCheckupDirectories!=null){
                servCheckupDirectory.setChildren(servCheckupDirectories);
                getMenuChildren(servCheckupDirectories);
            }
        }
        return null;
    }



    @Override
    public Page<ServCheckupDirectory> myPage(Page<ServCheckupDirectory> pageParam, ServCheckQuery servCheckQuery) {

        return baseMapper.myPage(pageParam, servCheckQuery);
    }
}
