import request from '@/utils/request'

export default {

     // 1. 制卡管理列表（条件分页查询）
     getAcctCardStoreList (current, size, acctCardStoreQuery) {
        return request({
            url: `/couponmanagementservice/acct-card-store/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: acctCardStoreQuery
          })
    },
     // 根据id查询
     getAcctCardStoreById (id) {
      return request({
          url: `/couponmanagementservice/acct-card-store/getById/${id}`,
          method: 'get'
      })
    },
     // 2. 根据编号删除
     deleteAcctCardStore (id) {
      return request({
          url: `/couponmanagementservice/acct-card-store/delete/${id}`,
          method: 'delete'
      })
    },
    // 3. 添加制卡
    addAcctCardStore (acctCardStore) {
        return request({
            url: `/couponmanagementservice/acct-card-store/add`,
            method: 'post',
            data: acctCardStore
        })
    },
    // 3. 修改制卡
    updateAcctCardStore (acctCardStore) {
        return request({
            url: `/couponmanagementservice/acct-card-store/update`,
            method: 'post',
            data: acctCardStore
        })
    },
    // 1. 制卡管理列表(所有)
    getAllList () {
        return request({
            url: `/couponmanagementservice/acct-card-store/queryAll`,
            method: 'get'
          })
    },

}