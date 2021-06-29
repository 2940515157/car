import request from '@/utils/request'

export default{
    // 分页查询不包含国家的数据
    getNoCountrys(page,size,countryCode){
        return request({
            url : `basicsdataheadservice/base-countrys/noPageQuery/${page}/${size}`,
            method : 'post',
            data: JSON.parse(JSON.stringify(countryCode))
        })
    },
    // 获取国家地理信息分页
    getCountrys(page,size){
        return request({
            url : `basicsdataheadservice/base-countrys/baseCountrys/${page}/${size}`,
            method : 'get'
        })
    },
     
    // 获取省份地理信息分页 s
    getProvinces (page, size,Provinces) {
        return request({
            // 绑定到表格数据
            url: `basicsdataheadservice/base-provinces/pageQuery/${page}/${size}`,
            method: 'post',
            data: Provinces
          })
    },
    // 获取城市地理信息数据分页
    getCitys(page,size,citys){
        return request({
            url : `basicsdataheadservice/base-citys/pageQuery/${page}/${size}`,
            method: 'post',
            data :citys
        })
    },
    // 获取全部国家地理信息
    getCountrysList(){
        return request({
            url : '/basicsdataheadservice/base-countrys/queryAll',
            method: 'get'
        })
    },
    // 获取全部省份地理信息
    getProvincesList(){
        return request({
            url : '/basicsdataheadservice/base-provinces/queryAll',
            method: 'get'
        })
    },
    // 获取全部城市地理信息
    getCitysList(){
        return request({
            url : '/basicsdataheadservice/base-citys/queryAll',
            method: 'get'
        })
    },
    // 添加城市
    addCity(city){
        return request({
            url : `basicsdataheadservice/base-citys/saveCity`,
            method: 'post',
            data: city
        })
    },
    // 删除城市信息
    deleteCity(cityUuid){
        return request({
            url : `basicsdataheadservice/base-citys/deleteCity/${cityUuid}`,
            method: 'delete'
        })
    },
    // 修改城市信息
    updateCity(city){
        return request({
            url : `basicsdataheadservice/base-citys/updateCity`,
            method: 'post',
            data: city
        })
    },
    // 通过城市代码查询城市信息
    queryById(cityCode){
        return request({
            url:`basicsdataheadservice/base-citys/queryById/${cityCode}`,
            method:'get'
        })
    },
    // 通过省份id查询下属的城市信息
    queryByProvId(provUuid){
        return request({
            url : `/basicsdataheadservice/base-citys/queryByProv/${provUuid}`,
            method : 'get'
        })
    },
    // 通过国家id查询下属的省份信息
    getByCountId(countryCode){
        return request({
            url : `/basicsdataheadservice/base-provinces/queryByCountryCode/${countryCode}`,
            method : 'get'
        })
    },
    // 添加省份信息
    addProv(prov){
        return request({
            url : `/basicsdataheadservice/base-provinces/addBaseProvinces`,
            method : 'post',
            data : prov
        })
    },
    // 删除省份信息
    deleteProv(provUuid){
        return request({
            url : `/basicsdataheadservice/base-provinces/deleteBaseProvinces/${provUuid}`,
            method : 'delete'
        })
    },
    // 修改省份信息
    updateProv(prov){
        return request({
            url : `/basicsdataheadservice/base-provinces/updateBaseProvinces`,
            method : 'post' ,
            data : prov
        })
    },
    // 添加国家地理信息
    addCount(count){
        return request({
            url : `/basicsdataheadservice/base-countrys/saveBaseCountrys`,
            method : 'post',
            data :count
        })
    },
    // 删除国家地理信息
    deleteCount(countryUuid){
        return request({
            url : `/basicsdataheadservice/base-countrys/deleteBaseCountrys/${countryUuid}`,
            method : 'delete'
        })
    },
    // 修改国家地理信息
    updateCount(count){
        return request({
            url : '/basicsdataheadservice/base-countrys/updateBaseCountrys',
            method : 'post',
            data :count
        })
    }
}