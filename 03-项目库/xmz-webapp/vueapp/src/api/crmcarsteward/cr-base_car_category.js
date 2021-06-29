import request from '@/utils/request'
export default{
    // 根据querybasecarbrand查询所有车系信息
    getaseCarCategory(current,size,baseCarCategoryQuery){
        return request({
            url:`/crmcarstewardservice/base-car-category/page/${current}/${size}`,
            method: 'post',
             // data表示把对象转换json进行传递到接口中去
            data: baseCarCategoryQuery
        })
    },
    // 根据queryAllbaseCarBrandList查询所有车系信息
    getqueryAllbaseCarBrandList(){
        return request({
            url:`/crmcarstewardservice/base-car-brand/queryAllbaseCarBrandList`,
            method: 'get',
        
        })
    },
    //根据queryAllbaseCarFactory查询所有车系信息
    getqueryAllbaseCarFactory(){
        return request({
            url:`/crmcarstewardservice/base-car-factory/queryAllbaseCarFactory`,
            method: 'get',
        
        })
    },
    //删除车系信息
    getdeletebaseCarCategory(categoryUuid){
        return request({
            url:`/crmcarstewardservice/base-car-category/deletebaseCarCategory/${categoryUuid} `,
            method: 'delete'
        })

    },
     //添加车系信息
     getaddbaseCarCategory(baseCarCategory){
        return request({
            url:`/crmcarstewardservice/base-car-category/addbaseCarCategory `,
            method:'post',
            //
            data:baseCarCategory
        })
    },
    //根据id查询所有车系信息
    getqueryById(categoryUuid){
        return request({
            url:`/crmcarstewardservice/base-car-category/selcategoryUuid/${categoryUuid}`,
            method:'get',
            
        })

    },
     //修改所有车系信息
     getupdatebaseCarCategory(baseCarCategory){
        return request({
            url:`/crmcarstewardservice/base-car-category/updatebaseCarCategory`,
            method: 'post',
             // data表示把对象转换json进行传递到接口中去
             data:baseCarCategory
        })
    }
    
     


}