import request from '@/utils/request'

export default{
    //  通过门店查询领卡人
    queryBrUuid(brUuid) {
      return request({
        url: `/hrmanagementservice/hr-employees/queryBrUuid/${brUuid}`,
        method: 'get'
      })
    },

//   根据条件分页查询员工数据
  query(current,size,hrEmployeesQuery) {
    return request({
      url: `/hrmanagementservice/hr-employees/page/${current}/${size}`,
      method: 'post',
      data: hrEmployeesQuery
    })
  },

  //根据id查询员工信息
  queryById(id) {
    return request({
      url: `/hrmanagementservice/hr-employees/queryById/${id}`,
      method: 'get',
      data: {}
    })
  },

    //   查询全部员工信息
    queryAll() {
        return request({
        url: `/hrmanagementservice/hr-employees/queryAll`,
        method: 'get',
        data: {}
        })
    },

//   添加员工
  isnert(hrEmployees) {
    return request({
      url: `/hrmanagementservice/hr-employees/insert`,
      method: 'post',
      data: hrEmployees
    })
  },

//   修改员工
  update(hrEmployees) {
    return request({
      url: `/hrmanagementservice/hr-employees/update`,
      method: 'post',
      data: hrEmployees
    })
  },

//   逻辑删除员工
  delete(id) {
    return request({
      url: `/hrmanagementservice/hr-employees/${id}`,
      method: 'delete',
      data: {}
    })
  },

// 设置员工离职
leaveJob(hrEmployess) {
    return request({
      url: `/hrmanagementservice/hr-employees/leaveJob`,
      method: 'post',
      data: hrEmployess
    })
  },

  // 撤销员工离职
  unLeaveJob(id) {
    return request({
      url: `/hrmanagementservice/hr-employees/unLeaveJob/${id}`,
      method: 'post',
      data: {}
    })
    
  },

  //获取 门店 班组 员工
  queryBrandEmp(current,size,tid,bid){
    return request({
      url:`/hrmanagementservice/hr-employees/queryBrandEmp/${current}/${size}/${tid}/${bid}`,
      method:'post'
    })
  }
,
  // 查询出还没有账号的员工
  queryNoAccEmps(current,size,hrEmployeesQuery) {
    return request({
      url: `/hrmanagementservice/hr-employees/queryNoAccEmps/${current}/${size}`,
      method: 'post',
      data: hrEmployeesQuery
    })
  },


}