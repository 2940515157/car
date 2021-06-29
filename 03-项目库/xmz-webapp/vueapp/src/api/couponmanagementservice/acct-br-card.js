import request from '@/utils/request'

export default {

     // 1. 发卡列表（条件分页查询）
     getAcctBrCardList (current, size, acctBrCardQuery) {
        return request({
            url: `/couponmanagementservice/acct-br-card/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: acctBrCardQuery
          })
    },
    // 发卡
    addAcctBrCard (form,zhangshu,startKaHao,qianzhui,zongshu,shengNum) {
      return request({
          url: `/couponmanagementservice/acct-br-card/add`,
          method: 'post',
          data: {
            form:JSON.stringify(form),
            zhangshu,
            startKaHao,
            qianzhui,
            zongshu,
            shengNum
          }
      })
    },
    // 修改已作废
    updateZuoFei (acctBrCard) {
      return request({
          url: `/couponmanagementservice/acct-br-card/update/zuofei`,
          method: 'post',
          data: acctBrCard
      })
  },

}