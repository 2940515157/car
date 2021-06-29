import request from '@/utils/request'

export default {
  // 绑定表格数据
  getOperation(page, size, Operation) {
    return request({
      url: `/systemmanagementservice/sys-operation/pageQuery/${page}/${size}`,
      method: 'post',
      data: Operation
    })
  },
  // 删除操作信息
  deleteOperation(operationId) {
    return request({
      url: `/systemmanagementservice/sys-operation/delete/${operationId}`,
      method: 'delete'
    })
  },
  // 添加操作信息
  addOperation(Operation) {
    return request({
      url: '/systemmanagementservice/sys-operation/add',
      method: 'post',
      data: Operation
    })
  },
  // 修改操作信息
  editOperation(Operation) {
    return request({
      url: '/systemmanagementservice/sys-operation/update',
      method: 'post',
      data: Operation
    })
  },
  // 根据id查询操作信息
  queryById(operationId) {
    return request({
      url: `/systemmanagementservice/sys-operation/queryById/${operationId}`,
      method: 'get'
    })
  }

}
