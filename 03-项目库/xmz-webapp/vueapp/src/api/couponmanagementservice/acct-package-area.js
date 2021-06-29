import request from '@/utils/request'

export default {

     // 1.套餐与区域
     getApUuidList (current, size, apUuid) {
        return request({
            url: `/couponmanagementservice/acct-package-area/getApUuid/${current}/${size}/${apUuid}`,
            method: 'get',
          })
    },
    // 3. 添加储蓄卡的发行范围
    addAcctPackageArea (acctPackageArea) {
      return request({
          url: `/couponmanagementservice/acct-package-area/add`,
          method: 'post',
          data: acctPackageArea
      })
    },
    // 2. 根据编号删除
    deleteAcctPackageArea (id) {
      return request({
          url: `/couponmanagementservice/acct-package-area/delete/${id}`,
          method: 'delete'
      })
  },

}