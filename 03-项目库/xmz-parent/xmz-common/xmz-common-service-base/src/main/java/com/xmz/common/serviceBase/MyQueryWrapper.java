package com.xmz.common.serviceBase;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;


public class MyQueryWrapper<T> extends QueryWrapper<T> {
    @Override
    public QueryWrapper<T> eq(boolean condition, String column, Object val) {
        if (StringUtils.isEmpty(val))
            condition=false;
        return super.eq(condition, column, val);
    }

    @Override
    public QueryWrapper<T> like(boolean condition, String column, Object val) {
        if (StringUtils.isEmpty(val))
            condition=false;
        return super.like(condition, column, val);
    }
}
