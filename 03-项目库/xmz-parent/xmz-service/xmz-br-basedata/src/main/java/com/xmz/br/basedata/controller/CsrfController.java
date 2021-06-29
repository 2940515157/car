package com.xmz.br.basedata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CsrfController {

    @GetMapping("/update")
    public String update() {
        return "/csrf/csrfTest";
    }

    @PostMapping("/csrf_token")
    public String token() {
        // 新增业务
        return "/csrf/csrfToken";
    }
}
