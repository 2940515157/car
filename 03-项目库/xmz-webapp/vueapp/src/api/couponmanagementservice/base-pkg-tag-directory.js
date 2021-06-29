import request from '@/utils/request'

export default {
     // 1. 标签目录
     getBasePkgTagDirectoryCatalogue () {
        return request({
            // url: '/table/list',
            url: `/couponmanagementservice/base-pkg-tag-directory/catalogue`,
            method: 'get'
          })
    },

    // 标签信息
    getBasePkgTagDirectory (current, size, basePkgTagDirectoryQuery) {
        return request({
            // url: '/table/list',
            url: `/couponmanagementservice/base-pkg-tag-directory/page/${current}/${size}`,
            method: 'post',
            // data表示把对象转换json进行传递到接口中去
            data: basePkgTagDirectoryQuery
          })
    },
    // 添加标签
    addBasePkgTagDirectory (basePkgTagDirectory) {
      return request({
          url: `/couponmanagementservice/base-pkg-tag-directory/add`,
          method: 'post',
          data: basePkgTagDirectory
      })
    },
    //修改标签
    updateBasePkgTagDirectory (basePkgTagDirectory) {
      return request({
          url: `/couponmanagementservice/base-pkg-tag-directory/update`,
          method: 'post',
          data: basePkgTagDirectory
      })
    },
    // 根据id查询
    getBasePkgTagDirectoryById (id) {
      return request({
          url: `/couponmanagementservice/base-pkg-tag-directory/getById/${id}`,
          method: 'get'
      })
    },
     // 2. 根据编号删除
     deleteBasePkgTagDirectory (id) {
      return request({
          url: `/couponmanagementservice/base-pkg-tag-directory/delete/${id}`,
          method: 'delete'
      })
    },
}