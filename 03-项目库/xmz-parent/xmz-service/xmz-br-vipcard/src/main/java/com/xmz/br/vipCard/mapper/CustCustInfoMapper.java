package com.xmz.br.vipCard.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.CustCustInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.vipCard.entity.pojo.CustVo;
import com.xmz.br.vipCard.entity.pojo.MyCustCustInfo;
import com.xmz.br.vipCard.entity.vo.CustCustInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户信息 Mapper 接口
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
public interface CustCustInfoMapper extends BaseMapper<CustCustInfo> {

    /**
     * 根据手机号查询客户与客户车辆的信息
     * @param custCustInfoQuery 查询条件的对象
     * @return
     */
    List<MyCustCustInfo> queryByPhone(@Param(value = "cci") CustCustInfoQuery custCustInfoQuery);

    List<CustVo> pageList(Page<CustVo> pojoPage, @Param("es") CustVo custVo);
}
