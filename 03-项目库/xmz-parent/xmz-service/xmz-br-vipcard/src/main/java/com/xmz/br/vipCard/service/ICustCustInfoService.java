package com.xmz.br.vipCard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.CustCustInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.CustVo;
import com.xmz.br.vipCard.entity.pojo.MyCustCustInfo;
import com.xmz.br.vipCard.entity.vo.CustCustInfoQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 客户信息 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-04
 */
public interface ICustCustInfoService extends IService<CustCustInfo> {
    /**
     * 根据手机号查询客户与客户车辆的信息
     * @param
     * @return
     */
    List<MyCustCustInfo> queryByPhone(CustCustInfoQuery custCustInfoQuery);

    List<CustVo> pageList(Page<CustVo> pojoPage, CustVo custVo);
}
