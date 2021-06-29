import request from '@/utils/request'

export default{
    //1.点检项目目录
    getServCheckupDirectory(){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-directory`,
            method: 'get'
        })
    }
}