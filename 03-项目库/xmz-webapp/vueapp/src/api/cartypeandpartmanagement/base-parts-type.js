import request from '@/utils/request'

export default{

      // 1.获取 配件目录
      getPart () {
        return request({
            url: `/cartypeandpartmanagementservice/base-parts-type/queryAllPartsAndqueryChildren`,
            method: 'post'
          })
          },
      queryAllPage(page,rows,queryBasePartsType){

        return request({
          url: `/cartypeandpartmanagementservice/base-parts-type/QueryPage/${page}/${rows}`,
          method: 'post',
          data: queryBasePartsType  
        })
      }
          

}