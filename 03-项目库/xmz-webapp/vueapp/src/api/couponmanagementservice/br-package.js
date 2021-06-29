import request from '@/utils/request'

export default {

     // 1. 门店套餐列表（条件分页查询）
     getBrPackageList (current, size, brPackageQuery) {
        return request({
            url: `/couponmanagementservice/br-package/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: brPackageQuery
          })
    },
    // 根据id查询
    getByBrPackageUuid (brPackageUuid) {
      return request({
          url: `/couponmanagementservice/br-package/getByBrPackageUuid/${brPackageUuid}`,
          method: 'get'
      })
    },
     // 修改上架
     updateUp (brPackage) {
      return request({
          url: `/couponmanagementservice/br-package/update/up`,
          method: 'post',
          data: brPackage
      })
    },
    // 修改下架
    updateUnder (brPackage) {
        return request({
            url: `/couponmanagementservice/br-package/update/under`,
            method: 'post',
            data: brPackage
        })
    }

}