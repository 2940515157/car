import request from '@/utils/request'

export default {
  // 绑定表格数据
  getWarehouseList(page, size, warehouse) {
    return request({
      url: `/warehousemanagementservice/wm-warehouse/pageQuery/${page}/${size}`,
      method: 'post',
      data: warehouse
    })
  },
  // 删除仓库信息
  deleteWareHouse(wmWarehouseUuid) {
    return request({
      url: `/warehousemanagementservice/wm-warehouse/delete/${wmWarehouseUuid}`,
      method: 'delete'
    })
  },
  // 禁用仓库
  disableWareHouse(wareHouseId) {
    return request({
      url: `/warehousemanagementservice/wm-warehouse/disable/${wareHouseId}`,
      method: 'get'
    })
  },
  // 启用仓库
  NodisableWareHouse(wareHouseId) {
    return request({
      url: `/warehousemanagementservice/wm-warehouse/Nodisable/${wareHouseId}`,
      method: 'get'
    })
  },
  // 编辑仓库信息
  editWareHouse(wareHouse){
    return request({
      url : '/warehousemanagementservice/wm-warehouse/update',
      method : 'post',
      data : wareHouse
    })
  },
  // 新增仓库信息
  saveWareHouse(wareHouse){
    return request({
      url : '/warehousemanagementservice/wm-warehouse/add',
      method : 'post',
      data : wareHouse
    })
  },
  // 通过id查询仓库信息
  queryWareHouseById(wmWarehouseUuid){
    return request({
      url : `/warehousemanagementservice/wm-warehouse/queryByWareHouseId/${wmWarehouseUuid}`,
      method : 'get',
    })
  },
  queryAllEmpName(brName){
    return request({
      url : `/warehousemanagementservice/wm-warehouse/queryAllEmp/${brName}`,
      method : 'get'
    })
  }
}
