package com.xmz.service.serviceproduct.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupDirectory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.serviceproduct.entity.vo.ServCheckQuery;

import java.util.List;

/**
 * <p>
 * 点检目录 服务类
 * </p>
 *
 * @author hwb
 * @since 2021-05-26
 */
public interface IServCheckupDirectoryService extends IService<ServCheckupDirectory> {
    /**
     * 查询所有子目录
     * @return
     */
    List<ServCheckupDirectory> listAll();

    /**
     * 查询所有目录 树状
     * @return
     */
    List<ServCheckupDirectory> findAllTree();

    /**
     * 查询所有目录 树状
     * @return
     */
    List<ServCheckupDirectory> getMenuChildren(List<ServCheckupDirectory> list);


    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param servCheckQuery  查询条件对象
     * @return
     */
    Page<ServCheckupDirectory> myPage(Page<ServCheckupDirectory> pageParam,
                              ServCheckQuery servCheckQuery);
}
