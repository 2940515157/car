import request from '@/utils/request'

export default{

//   根据条件分页查询角色数据
  query(current,size,sysRoleQuery) {
    return request({
      url: `/hrmanagementservice/sys-role/page/${current}/${size}`,
      method: 'post',
      data: sysRoleQuery
    })
  },

//   根据id查询出角色信息
  queryById(id) {
    return request({
      url: `/hrmanagementservice/sys-role/queryById/${id}`,
      method: 'get',
      data: {}
    })
  },

  //   查询出全部的角色信息
  queryAll() {
    return request({
      url: `/hrmanagementservice/sys-role/queryAll`,
      method: 'get',
      data: {}
    })
  },

//   插入角色信息
  isnert(sysRole) {
    return request({
      url: `/hrmanagementservice/sys-role/insert`,
      method: 'post',
      data: sysRole
    })
  },

//   修改角色信息
  update(sysRole) {
    return request({
      url: `/hrmanagementservice/sys-role/update`,
      method: 'post',
      data: sysRole
    })
  },

//   删除角色信息
  delete(id) {
    return request({
      url: `/hrmanagementservice/sys-role/${id}`,
      method: 'delete',
      data: {}
    })
  },

  //   根据账号id查询出 已 授权角色列表
  queryRoleAccount(sysAccountId) {
    return request({
      url: `/hrmanagementservice/sys-role/queryRoleAccount/${sysAccountId}`,
      method: 'get',
      data: {}
    })
  },

  //   根据账号id查询出 未 授权角色列表
  queryUnRoleAccount(sysAccountId) {
    return request({
      url: `/hrmanagementservice/sys-role/queryUnRoleAccount/${sysAccountId}`,
      method: 'get',
      data: {}
    })
  },

  //   查询全部Tree结构总店菜单列表
  queryAllHeadTreeMenu() {
    return request({
      url: `/hrmanagementservice/sys-resource/queryAllHeadTreeMenu`,
      method: 'post',
      data: {}
    })
  },

   //   查询全部Tree结构门店菜单列表
   queryAllSubbranchTreeMenu() {
    return request({
      url: `/hrmanagementservice/sys-resource/queryAllSubbranchTreeMenu`,
      method: 'post',
      data: {}
    })
  },

  //   根据资源id和角色id查询资源列表
  queryResourceByRid(resourceId) {
    return request({
      url: `/hrmanagementservice/sys-resource/queryResourceByRid/${resourceId}`,
      method: 'post',
      data: {}
    })
  },

  //   根据角色id查询出全部属于这个角色的权限id集合
  queryPermissionIdsByRoleId(roleId) {
    return request({
      url: `/hrmanagementservice/sys-resource/queryPermissionIdsByRoleId/${roleId}`,
      method: 'post',
      data: {}
    })
  },

}