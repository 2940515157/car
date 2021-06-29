import request from '@/utils/request'

export default {

     // 1. 套餐产品列表（条件分页查询）
     getAcctCouponList (current, size, acctCouponQuery) {
        return request({
            url: `/couponmanagementservice/acct-coupon/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: acctCouponQuery
          })
    },
     // 修改上架
     updateUp (acctCoupon) {
      return request({
          url: `/couponmanagementservice/acct-coupon/update/up`,
          method: 'post',
          data: acctCoupon
      })
    },
    // 修改下架
    updateUnder (acctCoupon) {
        return request({
            url: `/couponmanagementservice/acct-coupon/update/under`,
            method: 'post',
            data: acctCoupon
        })
    },
    // 2. 根据编号删除
    deleteAcctCoupon(id) {
        return request({
            url: `/couponmanagementservice/acct-coupon/delete/${id}`,
            method: 'delete'
        })
    },
    // 根据id查询
    getAcctCouponById (id) {
        return request({
            url: `/couponmanagementservice/acct-coupon/getById/${id}`,
            method: 'get'
        })
    },
    //  添加优惠券
    addAcctCoupon (form,replacePrice,catalogUuid,stUuid) {
        return request({
            url: `/couponmanagementservice/acct-coupon/add`,
            method: 'post',
            data: {
                form:JSON.stringify(form),
                replacePrice,
                catalogUuid,
                stUuid
            } 
        })
    },
    //  修改优惠券
    updateAcctCoupon (form,updateServiceId,replacePrice,catalogUuid,stUuid) {
        return request({
            url: `/couponmanagementservice/acct-coupon/update`,
            method: 'post',
            data: {
                form:JSON.stringify(form),
                updateServiceId,
                replacePrice,
                catalogUuid,
                stUuid
            } 
        })
    },

}