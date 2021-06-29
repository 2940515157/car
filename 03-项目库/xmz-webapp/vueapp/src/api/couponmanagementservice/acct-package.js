import request from '@/utils/request'

export default {

     // 1. 套餐产品列表（条件分页查询）
     getAcctPackageList (current, size, acctPackageQuery) {
        return request({
            url: `/couponmanagementservice/acct-package/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: acctPackageQuery
          })
    },

    // 修改上架
    updateUp (acctPackage) {
      return request({
          url: `/couponmanagementservice/acct-package/update/up`,
          method: 'post',
          data: acctPackage
      })
    },
    // 修改下架
    updateUnder (acctPackage) {
        return request({
            url: `/couponmanagementservice/acct-package/update/under`,
            method: 'post',
            data: acctPackage
        })
    },
    // 2. 根据编号删除
    deleteAcctPackage (id) {
        return request({
            url: `/couponmanagementservice/acct-package/delete/${id}`,
            method: 'delete'
        })
    },
     // 根据id查询
     getAcctPackageById (id) {
        return request({
            url: `/couponmanagementservice/acct-package/getById/${id}`,
            method: 'get'
        })
    },
    //  添加套餐
    addAcctPackage (form,servCatalogUuid,stdPrice,apdNumber,salePrice) {
        return request({
            url: `/couponmanagementservice/acct-package/add`,
            method: 'post',
            data: {
                form:JSON.stringify(form),
                servCatalogUuid,
                stdPrice,
                apdNumber,
                salePrice
            } 
        })
    },
    //  修改套餐
    updateAcctPackage (form,updateServiceId,servCatalogUuid,stdPrice,apdNumber,salePrice) {
        return request({
            url: `/couponmanagementservice/acct-package/update`,
            method: 'post',
            data: {
                form:JSON.stringify(form),
                updateServiceId,
                servCatalogUuid,
                stdPrice,
                apdNumber,
                salePrice
            }
        })
    },

}