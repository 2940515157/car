package com.xmz.service.system.mapper;

import com.xmz.service.system.entity.vo.AccountInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface SysSecurityMapper {

    AccountInfo getAccountByLogin(@Param("username") String username, @Param("password") String password);

    AccountInfo geAccountById(String id);

    Integer updateLoginTime(String id);

    Integer updatePassword(@Param("oldPassword") String oldPassword,
                           @Param("newPassword") String newPassword,
                           @Param("id") String id);

    List<Map<String,String>> listResInfoMap(@Param("origin") String origin, @Param("accountId")String accountId);

    List<String> getBtnCode(@Param("acctId") String acctId, @Param("resId") String resId);

}
