import request from '@/utils/request'

export default{

          //获取点击  的 查
      queryAllPage(page,rows,queryBasePartsType){

        return request({
          url: `/cartypeandpartmanagementservice/base-car-parts-sku/queryBaseCarInfoPage/${page}/${rows}`,
          method: 'post',
          data: queryBasePartsType  
        })
      }
          

}