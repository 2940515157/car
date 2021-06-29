import request from '@/utils/request'

export default{
  
   //   根据大区查出所有的区域
   queryAllArea(orgUuid) {
    return request({
      url: `/hrmanagementservice/hr-area/queryAllHrAreas/${orgUuid}`,
      method: 'get'
    })
  },

//   根据条件分页查询区域数据
  query(current,size,hrAreaQuery) {
    return request({
      url: `/hrmanagementservice/hr-area/page/${current}/${size}`,
      method: 'post',
      data: hrAreaQuery
    })
  },

//   根据id查询出区域信息
  queryById(id) {
    return request({
      url: `/hrmanagementservice/hr-area/queryById/${id}`,
      method: 'get',
      data: {}
    })
  },

  //   查询出全部的区域信息
  queryAll() {
    return request({
      url: `/hrmanagementservice/hr-area/queryAll`,
      method: 'get',
      data: {}
    })
  },

//   插入区域信息
  isnert(hrArea) {
    return request({
      url: `/hrmanagementservice/hr-area/insert`,
      method: 'post',
      data: hrArea
    })
  },

//   修改区域信息
  update(hrArea) {
    return request({
      url: `/hrmanagementservice/hr-area/update`,
      method: 'post',
      data: hrArea
    })
  },

//   删除区域信息
  delete(id) {
    return request({
      url: `/hrmanagementservice/hr-area/${id}`,
      method: 'delete',
      data: {}
    })
  },

}