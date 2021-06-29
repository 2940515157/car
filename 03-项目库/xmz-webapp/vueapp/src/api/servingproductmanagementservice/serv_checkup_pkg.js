import request from '@/utils/request'

export default{
    //分页条件查询点检包
    selServCheckPkgTable(current,size,servCheckupPkg){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-pkg/page/${current}/${size}`,
            method: 'post',
            data: servCheckupPkg
        })
    },
    //根据id查询数据
    selServCheckPkgById(id){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-pkg/${id}`,
            method: 'get'
        })
    },

    //添加点检包
    addServCheckPkg(myServCheckupPkg){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-pkg`,
            method: 'post',
            data: myServCheckupPkg
        })
    },

    //修改点检包
    updServCheckPkg(myServCheckupPkg){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-pkg/update`,
            method: 'post',
            data: myServCheckupPkg
        })
    },

    //点检包上下架
    updHidOrOut(myServCheckupPkg){
        return request({
            url: `/servingproductmanagementservice/serv-checkup-pkg/updateHidOrOut`,
            method: 'post',
            data: myServCheckupPkg
        })
    }
}