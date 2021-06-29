import request from '@/utils/request'

export default {

     // 1.优惠券与区域
     getAcUuidList (current, size, acUuid) {
        return request({
            url: `/couponmanagementservice/acct-coupon-area/getAcUuid/${current}/${size}/${acUuid}`,
            method: 'get',
          })
    },
    // 3. 添加优惠券的发行范围
    addAcctCouponArea (acctCouponArea) {
      return request({
          url: `/couponmanagementservice/acct-coupon-area/add`,
          method: 'post',
          data: acctCouponArea
      })
    },
    // 2. 根据编号删除
    deleteAcctCouponArea (id) {
      return request({
          url: `/couponmanagementservice/acct-coupon-area/delete/${id}`,
          method: 'delete'
      })
  },

}