import request from '@/utils/request'

export default {

     // 1. 优惠券服务
     getAcUuidList (acUuid) {
        return request({
            url: `/couponmanagementservice/acct-coupon-detail/getAcUuid/${acUuid}`,
            method: 'get',
          })
    }

}