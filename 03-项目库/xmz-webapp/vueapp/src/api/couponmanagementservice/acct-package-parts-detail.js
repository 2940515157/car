import request from '@/utils/request'

export default {

     // 1. 套餐配件
     getApUuidList (apUuid) {
        return request({
            url: `/couponmanagementservice/acct-package-parts-detail/getApUuid/${apUuid}`,
            method: 'get',
          })
    }

}