package com.xmz.service.system.service;

import com.xmz.service.system.entity.pojo.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.system.entity.vo.SysResourceQuery;
import com.xmz.service.system.entity.vo.SysResourceVo;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author KONG_No001
 * @since 2021-05-28
 */
public interface ISysResourceService extends IService<SysResource> {
    List<SysResourceVo> queryResource(SysResourceQuery resourceQuery);
    List<String> getRes(String id);
}
