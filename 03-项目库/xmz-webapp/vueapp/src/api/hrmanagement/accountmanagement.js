import request from '@/utils/request'

export default{

//   根据条件分页查询账号数据
  query(current,size,hrAccountQuery) {
    return request({
      url: `/hrmanagementservice/sys-account/page/${current}/${size}`,
      method: 'post',
      data: hrAccountQuery
    })
  },

  //根据id查询账号信息
  queryById(id) {
    return request({
      url: `/hrmanagementservice/sys-account/queryById/${id}`,
      method: 'get',
      data: {}
    })
  },

    //   查询全部账号信息
    queryAll() {
        return request({
        url: `/hrmanagementservice/sys-account/queryAll`,
        method: 'get',
        data: {}
        })
    },

//   添加账号
  isnert(sysAccount) {
    return request({
      url: `/hrmanagementservice/sys-account/insert`,
      method: 'post',
      data: sysAccount
    })
  },

//   修改账号
  update(sysAccount) {
    return request({
      url: `/hrmanagementservice/sys-account/update`,
      method: 'post',
      data: sysAccount
    })
  },

//   逻辑删除账号
  delete(id) {
    return request({
      url: `/hrmanagementservice/sys-account/${id}`,
      method: 'delete',
      data: {}
    })
  },

// 重置密码
resetPwd(id) {
    return request({
      url: `/hrmanagementservice/sys-account/resetPwd/${id}`,
      method: 'post',
      data: {}
    })
  },

  // 锁定账户
lockAccount(id) {
    return request({
      url: `/hrmanagementservice/sys-account/lockAccount/${id}`,
      method: 'post',
      data: {}
    })
  },
  
  // 解锁账号
unlockAccount(id) {
    return request({
      url: `/hrmanagementservice/sys-account/unlockAccount/${id}`,
      method: 'post',
      data: {}
    })
  },

  // 给账号授角色
  accountAuthorization(sysAccountId,createdBy,sysRoles) {
  return request({
    url: `/hrmanagementservice/sys-account/accountAuthorization/${sysAccountId}/${createdBy}`,
    method: 'post',
    data: sysRoles
  })
},

// 给账号授门店
accountAuthorizationBranches(sysAccountId,createdBy,hrBranchesList) {
  return request({
    url: `/hrmanagementservice/sys-account/accountAuthorizationBranches/${sysAccountId}/${createdBy}`,
    method: 'post',
    data: hrBranchesList
  })
},

}