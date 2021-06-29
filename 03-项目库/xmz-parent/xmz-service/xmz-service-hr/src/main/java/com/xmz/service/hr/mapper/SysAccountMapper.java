package com.xmz.service.hr.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.SysAccount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.service.hr.entity.vo.SysAccountQuery;
import com.xmz.service.hr.entity.vo.SysRoleGrantVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-10
 */
public interface SysAccountMapper extends BaseMapper<SysAccount> {

    /**
     * 查询所有领卡人
     *
     * @return
     */
    List<SysAccount> queryAllSa();

    Page<Map<String, Object>> queryPage(Page<Map<String, Object>> page,
                                        @Param("aq") SysAccountQuery accountQuery);


    Page<SysRoleGrantVo> infiniteList(
            Page<SysRoleGrantVo> page,
            @Param("accountId") String accountId, @Param("name") String name
    );

}
