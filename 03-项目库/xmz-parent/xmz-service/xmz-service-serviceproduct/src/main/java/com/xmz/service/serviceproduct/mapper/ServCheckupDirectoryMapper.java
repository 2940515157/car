package com.xmz.service.serviceproduct.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.serviceproduct.entity.pojo.ServCheckupDirectory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.serviceproduct.entity.vo.ServCheckQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 点检目录 Mapper 接口
 * </p>
 *
 * @author hwb
 * @since 2021-05-26
 */
public interface ServCheckupDirectoryMapper extends BaseMapper<ServCheckupDirectory> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param servCheckupDirectory  查询条件对象
     * @return
     */
    Page<ServCheckupDirectory> myPage(Page<ServCheckupDirectory> pageParam,
                              @Param(value = "bst") ServCheckQuery servCheckupDirectory);
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
    List<ServCheckupDirectory> getMenuChildren(String scdUuid);

}
