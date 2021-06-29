package com.xmz.service.system.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.system.entity.pojo.SysOperation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.system.entity.vo.SysOperationInfiniteListVo;
import com.xmz.service.system.entity.vo.SysOperationQuery;
import com.xmz.service.system.entity.vo.SysOperationVo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 页面操作按钮 Mapper 接口
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-02
 */
public interface SysOperationMapper extends BaseMapper<SysOperation> {

    Page<SysOperationInfiniteListVo> infiniteList(
            Page<SysOperationInfiniteListVo> page,
            @Param("resourceId") String resourceId,@Param("name")String name
    );

}
