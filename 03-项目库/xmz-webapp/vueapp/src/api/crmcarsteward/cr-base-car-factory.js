import request from '@/utils/request'

export default{

         // 根据baseCarFactory分页查询所有厂家信息
    getbaseCarFactory(current,size,baseCarFactoryQuery){
        return request({
            url:`/crmcarstewardservice/base-car-factory/page/${current}/${size}`,
            method: 'post',
             // data表示把对象转换json进行传递到接口中去
            data: baseCarFactoryQuery
        })
    },
      //添加厂家信息
      getaddbaseCarFactory(baseCarFactory){
        return request({
            url:`/crmcarstewardservice/base-car-factory/addbaseCarFactory`,
            method:'post',
            //
            data:baseCarFactory
        })
    },
     //根据id查询所有厂家信息
     getselfactoryUuid(factoryUuid){
        return request({
            url:`/crmcarstewardservice/base-car-factory/selfactoryUuid/${factoryUuid} `,
            method:'get',
            
        })

    },
    //修改所有品牌信息
    getupdatebaseCarFactory(baseCarFactory){
        return request({
            url:`/crmcarstewardservice/base-car-factory/updatebaseCarFactory`,
            method: 'post',
             // data表示把对象转换json进行传递到接口中去
             data:baseCarFactory
        })
    },
    //删除品牌信息
    getdeletebasecarbrand(factoryUuid){
        return request({
            url:`/crmcarstewardservice/base-car-factory/deletebaseCarFactory/${factoryUuid} `,
            method:'delete',
            
        })

    },

}