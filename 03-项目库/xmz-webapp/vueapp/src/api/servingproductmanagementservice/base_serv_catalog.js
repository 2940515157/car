import request from '@/utils/request'

export default{
    //分页条件查询
    getBaseServCatalogTable(current,size,baseServTypeQuery){
        return request({
            url: `/servingproductmanagementservice/base-serv-catalog/page/${current}/${size}`,
            method: 'post',
            data: baseServTypeQuery
        })
    },
    //根据id查询数据
    getBaseServCatalogById(id){
        return request({
            url: `/servingproductmanagementservice/base-serv-catalog/${id}`,
            method: 'get'
        })
    },
    //增加服务项目
    addBaseServCatalog(baseServCatalog){
        return request({
            url: `/servingproductmanagementservice/base-serv-catalog`,
            method: 'post',
            data: baseServCatalog
        })
    },
    //修改服务项目
    updBaseServCatalog(baseServCatalog){
        return request({
            url: `/servingproductmanagementservice/base-serv-catalog/update`,
            method: 'post',
            data: baseServCatalog
        })
    },
    
    //删除服务项目
    delBaseServCatalog(id){
        return request({
            url: `/servingproductmanagementservice/base-serv-catalog/${id}`,
            method: 'delete'
        })
    }
}