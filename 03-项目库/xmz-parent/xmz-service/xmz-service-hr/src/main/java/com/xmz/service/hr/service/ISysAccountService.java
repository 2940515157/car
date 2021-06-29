package com.xmz.service.hr.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.service.hr.entity.pojo.SysAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.hr.entity.vo.SysAccountQuery;
import com.xmz.service.hr.entity.vo.SysRoleGrantVo;

import java.util.Map;

import java.util.List;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author 罗俊
 * @since 2021-06-10
 */
public interface ISysAccountService extends IService<SysAccount> {
    /**
     * 查询所有领卡人
     *
     * @return
     */
    List<SysAccount> queryAllSa();

    Page<Map<String, Object>> queryPage(Page<Map<String, Object>> page, SysAccountQuery accountQuery);

    boolean addAccount(SysAccount account);

    Page<SysRoleGrantVo> infiniteList(Page<SysRoleGrantVo> page, String accountId, String name);

}
