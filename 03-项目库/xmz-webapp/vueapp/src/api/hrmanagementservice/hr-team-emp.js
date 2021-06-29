import request from '@/utils/request'


export default{


    //获取班组成员信息
    getHrTeamEmp(page,rows,teamUuid){
        return request({
            url:`/hrmanagementservice/hr-teams-emp/queryTeamUuId/${page}/${rows}/${teamUuid}`,
            method:'get'
        })
    },

    //添加 班组成员 
    addEmp(hrEmployees){

        return request({
            url:`/hrmanagementservice/hr-teams-emp/add`,
            method:'post',
            data: hrEmployees
        })
    }

    ,
    //删除 班组信息
    deleteTeamEmp(ids){

        return request({
            url:`/hrmanagementservice/hr-teams-emp/delete/${ids}`,
            method:'delete',
        })
    },
    //保存 班组成员 数据
    updateTeamEmp(hrEmployees){

        return request({url:`/hrmanagementservice/hr-teams-emp/update`,
        method:'put'
    })
    }

}