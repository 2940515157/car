import request from '@/utils/request'

export default {

     // 1. 套餐配件
     getAcUuidList (acUuid) {
        return request({
            url: `/couponmanagementservice/acct-coupon-parts-detail/getAcUuid/${acUuid}`,
            method: 'get',
          })
    }

}