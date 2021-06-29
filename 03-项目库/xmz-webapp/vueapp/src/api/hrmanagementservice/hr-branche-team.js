import request from '@/utils/request'


export default{


    //获取班组 信息
    gethrBrancheTeams(page,rows,hrBrancheTeamsQuery){

        return request({
            url:`/hrmanagementservice/hr-branche-teams/${page}/${rows}`,
            method: 'post',
            data: hrBrancheTeamsQuery
        })
    },
    // 添加 班组信息
    add(hrBrancheTeam){
        
        return request({
            url:`/hrmanagementservice/hr-branche-teams/add`,
            method: 'post',
            data: hrBrancheTeam
        })
    },
    // 通过  id 查询 班组信息
    queryById(id){

        return request({
            url:`/hrmanagementservice/hr-branche-teams/queryById/${id}`,
            method: 'get'
        })
    },
    // 修改 班组信息
    update(hrBrancheTeam){
        
        return request({
            url:`/hrmanagementservice/hr-branche-teams/update`,
            method: 'put',
            data: hrBrancheTeam
        })
    },
    // 删除 班组信息
    deleteById(id){
        
        return request({
            url:`/hrmanagementservice/hr-branche-teams/delete/${id}`,
            method: 'delete',
        })
    }


}