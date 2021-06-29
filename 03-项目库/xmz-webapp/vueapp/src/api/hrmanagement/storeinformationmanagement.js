import request from '@/utils/request'

export default{
  //   根据大区查出所有的区域
  queryAllMenDian(areaUuid) {
    return request({
      url: `/hrmanagementservice/hr-branches/queryAllMenDian/${areaUuid}`,
      method: 'get'
    })
  },
  //   分页查询不包含门店的数据
  getNoQuery(current,size,brCode,brName) {
    return request({
      url: `/hrmanagementservice/hr-branches/noPageQuery/${current}/${size}`,
      method: 'post',
      data:{
        brCode,
        brName
      }
    })
  },

//   根据条件分页查询门店数据
  query(current,size,hrBranchesQuery) {
    return request({
      url: `/hrmanagementservice/hr-branches/page/${current}/${size}`,
      method: 'post',
      data: hrBranchesQuery
    })
  },

  //根据id查询门店信息
  queryById(id) {
    return request({
      url: `/hrmanagementservice/hr-branches/queryById/${id}`,
      method: 'get',
      data: {}
    })
  },

    //   查询全部门店信息
    queryAll() {
        return request({
        url: `/hrmanagementservice/hr-branches/queryAll`,
        method: 'get',
        data: {}
        })
    },

//   添加门店
  isnert(hrBranches) {
    return request({
      url: `/hrmanagementservice/hr-branches/insert`,
      method: 'post',
      data: hrBranches
    })
  },

//   修改门店
  update(hrBranches) {
    return request({
      url: `/hrmanagementservice/hr-branches/update`,
      method: 'post',
      data: hrBranches
    })
  },

//   逻辑删除门店
  delete(id) {
    return request({
      url: `/hrmanagementservice/hr-branches/${id}`,
      method: 'delete',
      data: {}
    })
  },


//  门店 班组 成员查询 员工
queryBrandEmp(current,size,tid,bid){
  return request({
    url: `/hrmanagementservice/hr-branches/queryBrandEmp/${current}/${size}/${tid}/${bid}`,
    method:'post',
  })
}
,
  //  分页查询出 未授权 门店信息
  queryUnAuthorizationBranches(current,size,hrBranchesQuery) {
    return request({
      url: `/hrmanagementservice/hr-branches/queryUnAuthorizationBranches/${current}/${size}`,
      method: 'post',
      data: hrBranchesQuery
    })
  },

  //  分页查询出 已授权 门店信息
  queryAuthorizationBranches(current,size,hrBranchesQuery) {
    return request({
      url: `/hrmanagementservice/hr-branches/queryAuthorizationBranches/${current}/${size}`,
      method: 'post',
      data: hrBranchesQuery
    })
  },


}