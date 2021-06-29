import request from '@/utils/request'

export default {

     // 1. 套餐服务
     getApUuidList (apUuid) {
        return request({
            url: `/couponmanagementservice/acct-package-detail/getApUuid/${apUuid}`,
            method: 'get',
          })
    }

}