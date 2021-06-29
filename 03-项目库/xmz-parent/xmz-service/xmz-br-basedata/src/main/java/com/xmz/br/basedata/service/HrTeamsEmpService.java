package com.xmz.br.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.basedata.pojo.entity.HrTeamsEmp;
import com.xmz.br.basedata.pojo.vo.MyHrTeamsEmp;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 班组成员信息 服务类
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-08
 */
public interface HrTeamsEmpService extends IService<HrTeamsEmp> {

    Page<MyHrTeamsEmp> queryTeamUuId(Page<MyHrTeamsEmp> page, @Param("id") String id);
}
