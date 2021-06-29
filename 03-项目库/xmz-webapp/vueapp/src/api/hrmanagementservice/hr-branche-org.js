import request from '@/utils/request'


export default{


    getHrBrancheOrgs(page,rows,hrBrancheQuery){

       return request({
            url:`/hrmanagementservice/hr-branche-org/${page}/${rows}`,
            method: 'post',
            data: hrBrancheQuery
        })
    },
    queryById(id){

        return request({
            url:`/hrmanagementservice/hr-branche-org/${id}`,
            method: 'get'
        })
    },
    queryAll(id){
        return request({
            url:`/hrmanagementservice/hr-branche-org/queryAll/${id}`,
            method:'get'
        })
    }
    ,
    addOrUpdate(hrBrancheOrg){
        return request({
            url:`/hrmanagementservice/hr-branche-org/`,
            method: 'post',
            data: hrBrancheOrg
        })
    },
    deleteById(ids){

        return request({
            url:`/hrmanagementservice/hr-branche-org/${ids}`,
            method:'delete'
        })
    }

}