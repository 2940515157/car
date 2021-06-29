package com.xmz.service.demo.service.impl;

import com.xmz.service.demo.entity.pojo.Demo;
import com.xmz.service.demo.service.IDemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements IDemoService {

    @Override
    public Demo getD() {
        return new Demo("FOO","BAR");
    }
}
