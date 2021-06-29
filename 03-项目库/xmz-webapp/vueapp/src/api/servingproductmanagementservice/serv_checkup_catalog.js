import request from '@/utils/request'

export default{
    //1.分页条件查询数据
    getPageServCheckupCatalogQuery(current, size, servCheckupCatalogQuery){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-catalog/page/${current}/${size}`,
            method: 'post',
            data: servCheckupCatalogQuery
        })
    },

    //根据id查询数据
    selServCheckupCatalogByid(id){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-catalog/${id}`,
            method: 'get'
        })
    },

    //添加数据
    addServCheckupCatalog(myServCheckupCatalog){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-catalog`,
            method: 'post',
            data: myServCheckupCatalog
        })
    },

    //修改数据
    updServCheckupCatalog(myServCheckupCatalog){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-catalog/update`,
            method: 'post',
            data: myServCheckupCatalog
        })
    },

    //删除数据
    delServCheckupCatalog(id){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-catalog/${id}`,
            method: 'delete'
        })
    }
}