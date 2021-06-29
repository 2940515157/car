package com.xmz.service.hr.mapper;

import com.xmz.service.hr.entity.pojo.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-09
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    // -- 1 查出操作列
    List<Map<String,String>> listOper(List<String> ids);
    // -- 2 查出权限列
    List<Map<String,String>> listPrs(@Param("roleId") String roleId, @Param("resId") String resId);

}
