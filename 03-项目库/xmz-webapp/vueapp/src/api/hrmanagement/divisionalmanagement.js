import request from '@/utils/request'

export default{
   //   分页查询不包含大区的数据
   queryNoAllBigAreaPage(page,size,orgNo) {
    return request({
      url: `/hrmanagementservice/hr-org/noPageQuery/${page}/${size}`,
      method: 'post',
      data: JSON.parse(JSON.stringify(orgNo))
    })
  },

  // 条件查询Tree结构部门列表
  queryTreeTable(hrOrgQuery) {
    return request({
      url: `/hrmanagementservice/hr-org/queryTreeTable`,
      method: 'post',
      data: hrOrgQuery
    })
  },

  //   分页查询出全部的大区
  queryAllBigAreaPage(page,size) {
    return request({
      url: `/hrmanagementservice/hr-org/queryAllBigAreaPage/${page}/${size}`,
      method: 'post',
      data: {}
    })
  },

  //   查询出全部的大区
  queryAllBigArea() {
    return request({
      url: `/hrmanagementservice/hr-org/queryAllBigArea`,
      method: 'get',
      data: {}
    })
  },

  //   根据id查询出部门信息
  queryById(id) {
    return request({
      url: `/hrmanagementservice/hr-org/queryById/${id}`,
      method: 'get',
      data: {}
    })
  },

  //   查询出全部的部门
  queryAllOrg() {
    return request({
      url: `/hrmanagementservice/hr-org/queryAllOrg`,
      method: 'get',
      data: {}
    })
  },

  //   插入部门信息
  isnert(hrOrg) {
    return request({
      url: `/hrmanagementservice/hr-org/insert`,
      method: 'post',
      data: hrOrg
    })
  },

//   修改部门信息
  update(hrOrg) {
    return request({
      url: `/hrmanagementservice/hr-org/update`,
      method: 'post',
      data: hrOrg
    })
  },

//   删除部门信息
  delete(id) {
    return request({
      url: `/hrmanagementservice/hr-org/${id}`,
      method: 'delete',
      data: {}
    })
  },

} 

