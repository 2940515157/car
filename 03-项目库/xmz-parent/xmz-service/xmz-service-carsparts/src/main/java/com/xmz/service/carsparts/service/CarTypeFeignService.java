package com.xmz.service.carsparts.service;

import com.xmz.common.utils.Result;
import com.xmz.common.utils.ResultMap;
import com.xmz.service.carsparts.entity.vo.BaseCarType;
import com.xmz.service.carsparts.entity.vo.CarTypeQueryParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "serviceBasedata", contextId = "carTypeClient")
@RequestMapping("/serviceBasedata/cartype")
public interface CarTypeFeignService {
    //分页查询
    @PostMapping("/queryPage/{current}/{size}")
    public ResultMap queryPage(@RequestBody CarTypeQueryParam queryParam,
                               @PathVariable Integer current,
                               @PathVariable Integer size);

    //根据id查
    @GetMapping("/queryById/{id}")
    public Result<BaseCarType> queryById(@PathVariable String id);

    //根据条件查所有
    @PostMapping("/qeuryList")
    public ResultMap queryList(@RequestBody CarTypeQueryParam queryParam);
}
