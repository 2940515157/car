package com.xmz.br.basedata.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.basedata.pojo.entity.HrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.MyHrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.QueryHrBrancheTeams;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 班组信息 Mapper 接口
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-03
 */
public interface HrBrancheTeamsMapper extends BaseMapper<HrBrancheTeams> {


    Page<MyHrBrancheTeams> queryBrancheTeams(Page page, @Param("hbt") QueryHrBrancheTeams queryHrBrancheTeams);
}
