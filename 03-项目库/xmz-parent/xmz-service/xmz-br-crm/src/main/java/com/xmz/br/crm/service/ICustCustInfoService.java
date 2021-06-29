package com.xmz.br.crm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.crm.entity.pojo.CustCustInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.crm.entity.vo.CustInfoQueryParam;
import com.xmz.br.crm.entity.vo.SaInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户信息 服务类
 * </p>
 *
 * @author weikang
 * @since 2021-06-08
 */
public interface ICustCustInfoService extends IService<CustCustInfo> {

    Page<CustCustInfo> queryPage(Page<CustCustInfo> page,CustInfoQueryParam queryParam, String shop);

    List<CustCustInfo> queryList(CustInfoQueryParam queryParam,String shop);

    List<SaInfo> querySaInfoList(String shop);

    int add(CustCustInfo custCustInfo);

}
