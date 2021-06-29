package com.xmz.service.system.mapper;

import com.xmz.service.system.entity.pojo.SysResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.system.entity.vo.SysResourceQuery;
import com.xmz.service.system.entity.vo.SysResourceVo;

import java.util.List;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author KONG_No001
 * @since 2021-05-28
 */
public interface SysResourceMapper extends BaseMapper<SysResource> {

    List<SysResourceVo> queryResource(SysResourceQuery resourceQuery);
    List<String> getRes(String id);
}
