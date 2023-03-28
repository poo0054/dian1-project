package com.dian1.http.controller;

import cn.hutool.http.HttpResponse;
import com.dian1.http.http.HttpTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    HttpTest httpTest;

    @GetMapping
    public String test() {
        HttpResponse test = httpTest.test();
        return test.body();
    }
}
