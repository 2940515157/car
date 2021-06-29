package com.xmz.br.vipCard.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xmz.br.vipCard.entity.pojo.BrPackage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.br.vipCard.entity.pojo.MyBrPackage;
import com.xmz.br.vipCard.entity.vo.BrPackageQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 门店套餐 服务类
 * </p>
 *
 * @author zt
 * @since 2021-06-11
 */
public interface IBrPackageService extends IService<BrPackage> {
    /**
     * 分页的多表查询方法
     * @param pageParam  分页参数
     * @param brPackageQuery  查询条件对象
     * @return
     */
    Page<MyBrPackage> myPage(Page<MyBrPackage> pageParam,BrPackageQuery brPackageQuery);

    //查询所有门店已上架的套餐同时还是有效期内的套餐
    List<MyBrPackage> listQuery(@Param(value = "bp") BrPackageQuery brPackageQuery);

    boolean add(Map<String,String> data);
}
