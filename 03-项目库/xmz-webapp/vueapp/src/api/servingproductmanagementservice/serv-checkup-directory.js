import request from '@/utils/request'

export default {

     // 1. 点检目录列表（条件分页查询）
     getServCheckupDirectoryList (current, size, servCheckupDirectory) {
        return request({
            url: `/servingproductmanagementservice/serv-checkup-directory/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: servCheckupDirectory
          })
    },
    
    // 添加点检目录
    addServCheckupDirectory (servCheckupDirectory) {
      return request({
          url: `/servingproductmanagementservice/serv-checkup-directory/add`,
          method: 'post',
          data: servCheckupDirectory
      })
    },
    //修改点检目录
    updateServCheckupDirectory (servCheckupDirectory) {
      return request({
          url: `/servingproductmanagementservice/serv-checkup-directory/update`,
          method: 'post',
          data: servCheckupDirectory
      })
    },
    // 2. 根据编号删除
    deleteServCheckupDirectory (id) {
      return request({
          url: `/servingproductmanagementservice/serv-checkup-directory/delete/${id}`,
          method: 'delete'
      })
    },

// 根据id查询
    getServCheckupDirectoryById (id) {
      return request({
          url: `/servingproductmanagementservice/serv-checkup-directory/getById/${id}`,
          method: 'get'
      })
    },
}