import request from '@/utils/request'

export default {
  // 绑定表格数据
  getTableList(page, size, permission) {
    return request({
      url: `/systemmanagementservice/sys-permission/pageQuery/${page}/${size}`,
      method: 'post',
      data: permission
    })
  },
  // 删除方法
  deletePermission(permissionId) {
    return request({
      url: `/systemmanagementservice/sys-permission/delete/${permissionId}`,
      method: 'delete'
    })
  }
}
