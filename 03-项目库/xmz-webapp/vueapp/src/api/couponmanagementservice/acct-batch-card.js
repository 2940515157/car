import request from '@/utils/request'

export default {

     // 1. 批量开卡列表（条件分页查询）
     getAcctBatchCardList (current, size, acctBatchCard) {
        return request({
            url: `/couponmanagementservice/acct-batch-card/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: acctBatchCard
          })
    }

}