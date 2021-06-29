import request from '@/utils/request'

export default {

     // 1. 营销组合产品列表（条件分页查询）
     getAcctCompositeProductList (current, size, acctCompositeProductQuery) {
        return request({
            url: `/couponmanagementservice/acct-composite-product/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: acctCompositeProductQuery
          })
    },
     // 修改上架
     updateUp (acctCompositeProduct) {
      return request({
          url: `/couponmanagementservice/acct-composite-product/update/up`,
          method: 'post',
          data: acctCompositeProduct
      })
    },
    // 修改下架
    updateUnder (acctCompositeProduct) {
        return request({
            url: `/couponmanagementservice/acct-composite-product/update/under`,
            method: 'post',
            data: acctCompositeProduct
        })
    },
    // 2. 根据编号删除
    deleteAcctCompositeProduct (id) {
        return request({
            url: `/couponmanagementservice/acct-composite-product/delete/${id}`,
            method: 'delete'
        })
    },
    // 3. 添加储蓄卡产品定义
    addAcctCompositeProduct (acctCompositeProduct) {
        return request({
            url: `/couponmanagementservice/acct-composite-product/add`,
            method: 'post',
            data: acctCompositeProduct
        })
    },
    // 3. 修改储蓄卡产品定义
    updateAcctCompositeProduct (acctCompositeProduct) {
        return request({
            url: `/couponmanagementservice/acct-composite-product/update`,
            method: 'post',
            data: acctCompositeProduct
        })
    },
    // 根据id查询
    getAcctCompositeProductById (id) {
        return request({
            url: `/couponmanagementservice/acct-composite-product/getById/${id}`,
            method: 'get'
        })
    },

}