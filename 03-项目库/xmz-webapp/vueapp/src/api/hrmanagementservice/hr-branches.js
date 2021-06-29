import request from '@/utils/request'

export default{

    // 根据br_uuid查询 分店（门店） 信息   单个
        getMyHrBranches(brCode){
            return request({
                url:`/hrmanagementservice/hr-branches/${brCode}`,
                method: 'get',
            })
        }

}