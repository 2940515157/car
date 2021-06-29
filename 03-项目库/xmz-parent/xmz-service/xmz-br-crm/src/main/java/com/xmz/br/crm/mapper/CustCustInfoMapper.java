package com.xmz.br.crm.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.crm.entity.pojo.CustCustInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmz.br.crm.entity.vo.CustInfoQueryParam;
import com.xmz.br.crm.entity.vo.SaInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户信息 Mapper 接口
 * </p>
 *
 * @author weikang
 * @since 2021-06-08
 */
public interface CustCustInfoMapper extends BaseMapper<CustCustInfo> {

    Page<CustCustInfo> queryPage(Page<CustCustInfo> page, @Param("qp")CustInfoQueryParam queryParam,@Param("shop") String shop);

    List<CustCustInfo> queryList(@Param("qp")CustInfoQueryParam queryParam,@Param("shop") String shop);

    List<SaInfo> querySaInfoList(String shop);

    String queryBrUuidByShop(String shop);
}
