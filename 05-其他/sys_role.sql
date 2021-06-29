DELETE FROM `sys_role`;
-- UPDATE sys_role SET CREATED_DATE = NOW(),UPDATED_DATE = NOW()
INSERT INTO `sys_role` (`ROLE_ID`, `NAME`, `ROLE_NO`, `MEMO`, `IS_ACTIVE`, `CREATED_DATE`, `UPDATED_DATE`, `CREATED_BY`, `UPDATED_BY`, `VERSION`, `ROLE_ORIGIN`) VALUES ('d561f5324cf599a722a7743cf3deac17', '管理员', 'Admin', '最高权限管理者', 'Y', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '', 0.00, 'base');
INSERT INTO `sys_role` (`ROLE_ID`, `NAME`, `ROLE_NO`, `MEMO`, `IS_ACTIVE`, `CREATED_DATE`, `UPDATED_DATE`, `CREATED_BY`, `UPDATED_BY`, `VERSION`, `ROLE_ORIGIN`) VALUES ('dfc40fef99dc8d3fc3991872b06532bc', '门店店长', 'store_manager', '门店管理者', 'Y', '0000-00-00 00:00:00', '0000-00-00 00:00:00', '', '', 0.00, 'store');
