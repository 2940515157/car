package com.xmz.br.basedata.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.basedata.pojo.entity.HrBrancheOrg;
import com.xmz.br.basedata.pojo.vo.MyHrBrancheOrg;
import com.xmz.br.basedata.pojo.vo.QueryHrBrancheOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 车间信息 Mapper 接口
 * </p>
 *
 * @author 驰骋
 * @since 2021-05-31
 */
public interface HrBrancheOrgMapper extends BaseMapper<HrBrancheOrg> {



    //分店  车间信息查询
    Page<MyHrBrancheOrg> queryBrancheOrg(Page<MyHrBrancheOrg> page, @Param("hb") QueryHrBrancheOrg queryHrBrancheOrg);

}
