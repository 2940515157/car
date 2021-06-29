package com.xmz.br.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.basedata.pojo.entity.HrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.MyHrBrancheTeams;
import com.xmz.br.basedata.pojo.vo.QueryHrBrancheTeams;

/**
 * <p>
 * 班组信息 服务类
 * </p>
 *
 * @author 驰骋
 * @since 2021-06-03
 */
public interface HrBrancheTeamsService extends IService<HrBrancheTeams> {

    Page<MyHrBrancheTeams> queryBrancheTeams(Page page, QueryHrBrancheTeams queryHrBrancheTeams);

}
