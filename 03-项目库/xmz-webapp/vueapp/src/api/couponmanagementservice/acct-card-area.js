import request from '@/utils/request'

export default {

     // 1.储蓄卡与区域
     getAcsUuidList (current, size, acsUuid) {
        return request({
            url: `/couponmanagementservice/acct-card-area/getAcsUuid/${current}/${size}/${acsUuid}`,
            method: 'get',
          })
    },
    // 3. 添加储蓄卡的发行范围
    addAcctCardArea (acctCardArea) {
      return request({
          url: `/couponmanagementservice/acct-card-area/add`,
          method: 'post',
          data: acctCardArea
      })
    },
    // 2. 根据编号删除
    deleteAcctCardArea (id) {
      return request({
          url: `/couponmanagementservice/acct-card-area/delete/${id}`,
          method: 'delete'
      })
  },

}