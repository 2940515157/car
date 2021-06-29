import request from '@/utils/request'

export default {

     // 1. 储值卡产品定义列表（条件分页查询）
     getAcctCardSetupList (current, size, acctCardSetupQuery) {
        return request({
            // url: '/table/list',
            // url: `http://localhost:8005/couponmanagementservice/acct-card-setup/page/${current}/${size}`,
            url: `/couponmanagementservice/acct-card-setup/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: acctCardSetupQuery
          })
    },
     // 2. 根据编号删除
    deleteAcctCardSetup (id) {
        return request({
            // url: `http://localhost:8005/couponmanagementservice/acct-card-setup/delete/${id}`,
            url: `/couponmanagementservice/acct-card-setup/delete/${id}`,
            method: 'delete'
        })
    },
    // 3. 添加储蓄卡产品定义
    addAcctCardSetup (acctCardSetup) {
        return request({
            url: `/couponmanagementservice/acct-card-setup/add`,
            method: 'post',
            data: acctCardSetup
        })
    },
    // 3. 修改储蓄卡产品定义
    updateAcctCardSetup (acctCardSetup) {
        return request({
            url: `/couponmanagementservice/acct-card-setup/update`,
            method: 'post',
            data: acctCardSetup
        })
    },
    // 根据id查询
    getAcctCardSetupById (id) {
        return request({
            url: `/couponmanagementservice/acct-card-setup/getById/${id}`,
            method: 'get'
        })
    },
    // 修改上架
    updateUp (acctCardSetup) {
        return request({
            url: `/couponmanagementservice/acct-card-setup/update/up`,
            method: 'post',
            data: acctCardSetup
        })
    },
    // 修改下架
    updateUnder (acctCardSetup) {
        return request({
            url: `/couponmanagementservice/acct-card-setup/update/under`,
            method: 'post',
            data: acctCardSetup
        })
    }

}