package com.xmz.br.basedata.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrTeamsEmp;
import com.xmz.br.basedata.pojo.vo.MyHrTeamsEmp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 班组成员信息 Mapper 接口
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-08
 */
public interface HrTeamsEmpMapper extends BaseMapper<HrTeamsEmp> {


      Page<MyHrTeamsEmp> queryTeamUuId(Page<MyHrTeamsEmp> page, @Param("id") String id);

}
