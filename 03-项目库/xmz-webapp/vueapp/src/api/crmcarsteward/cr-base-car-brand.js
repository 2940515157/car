import request from '@/utils/request'

export default{

    // 根据querybasecarbrand查询所有品牌信息
    getBaseCarBrand(current,size,baseCarBrandQuery){
            return request({
                url:`/crmcarstewardservice/base-car-brand/page/${current}/${size}`,
                method: 'post',
                 // data表示把对象转换json进行传递到接口中去
                data: baseCarBrandQuery
            })
        },
        //添加品牌信息
        getaddbasecarbrand(basecarbrand){
            return request({
                url:'/crmcarstewardservice/base-car-brand/addbasecarbrand',
                method:'post',
                //
                data:basecarbrand
            })
        },
        //删除品牌信息
        getdeletebasecarbrand(brandUuid){
            return request({
                url:`/crmcarstewardservice/base-car-brand/deleteHr/${brandUuid}`,
                method:'delete',
                
            })

        },
        //根据id查询所有品牌信息
        getqueryById(brandUuid){
            return request({
                url:`/crmcarstewardservice/base-car-brand/selbrandUuid/${brandUuid}`,
                method:'get',
                
            })

        },
        //修改所有品牌信息
        getupdatebasecarbrand(basecarbrand){
            return request({
                url:`/crmcarstewardservice/base-car-brand/updateBaseCarBrand`,
                method: 'post',
                 // data表示把对象转换json进行传递到接口中去
                 data:basecarbrand
            })
        }

}