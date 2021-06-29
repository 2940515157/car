import request from '@/utils/request'

export default{
    // 多条件分页查询资源管理数据
    getSysResource(page,size,resource){
        return request({
            url : `/systemmanagementservice/sys-resource/pageQuery/${page}/${size}`,
            method : 'post',
            data : resource
        })
    },
    // 显示树形表格
    getTbaleList(){
        return request({
            url : '/systemmanagementservice/sys-resource/queryParentResource',
            method :'get'
        })
    }
}