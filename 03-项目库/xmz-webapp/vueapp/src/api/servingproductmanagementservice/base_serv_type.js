import request from '@/utils/request'

export default{
    //1.根据条件查询表格数据
    getBaseServType(baseServTypeQuery){
        return request({
            url: `/servingproductmanagementservice/base-serv-type/select`,
            method: 'post',
            data: baseServTypeQuery
        })
    },

    //2.查询所有父id不为空的菜单
    getBaseServTypeNotNull(){
        return request({
            url: `/servingproductmanagementservice/base-serv-type/select/notNull`,
            method: 'post'
        })
    }
}