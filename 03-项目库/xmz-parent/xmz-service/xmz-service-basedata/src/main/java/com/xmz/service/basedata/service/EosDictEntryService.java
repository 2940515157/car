package com.xmz.service.basedata.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xmz.service.basedata.entity.pojo.EosDictEntry;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author weikang
 * @since 2021-05-24
 */
public interface EosDictEntryService extends IService<EosDictEntry> {
    Page<EosDictEntry> queryPage(Page<EosDictEntry> page, String id);

    EosDictEntry queryById(String dicttypeid,String dictid);
}
