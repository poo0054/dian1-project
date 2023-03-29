package com.dian1.http.controller;

import cn.hutool.core.io.StreamProgress;
import cn.hutool.http.HttpResponse;
import com.dian1.http.entity.OmsContractHead;
import com.dian1.http.http.HttpTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@RestController
public class TestController {
    @Autowired
    HttpTest httpTest;

    @GetMapping
    public String get() {
        return httpTest.get();
    }

    @GetMapping("{code}")
    public String test(@PathVariable("code") String code) {
        Map<Object, Object> map = new HashMap<>();
        map.put("username", "张三");
        map.put("password", "123456");
        return httpTest.restful(map, code);
    }

    @GetMapping("form/{code}")
    public String getOmsProductHeader(@PathVariable("code") String code) {
        AtomicReference<String> body = new AtomicReference<>();
        Consumer<HttpResponse> consumer = response -> {
            System.out.println();
            System.out.println();
            body.set(response.body());
        };
        Map<Object, Object> map = new HashMap<>();
        map.put("key", "123456789");
        httpTest.form(code, consumer, map);
        return body.get();
    }

    @GetMapping("body/{code}")
    public OmsContractHead form(@PathVariable("code") String code) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("size", 1);
        map.put("start", 1);
        map.put("productCode", code);
        OmsContractHead body = httpTest.basicAuth(map);
        return body;
    }

    @GetMapping("header/{code}")
    public OmsContractHead header(@PathVariable("code") String code) {
        OmsContractHead head = httpTest.header();
        return head;
    }

    @GetMapping("headerPar/{code}")
    public OmsContractHead headerPar(@PathVariable("code") String code) {
        OmsContractHead head = httpTest.headerPar("asd");
        return head;
    }

    @GetMapping("headerMap/{code}")
    public OmsContractHead headerMap(@PathVariable("code") String code) {
        Map<Object, Object> hashMap = new HashMap<>();
        hashMap.put("Authorization", "123456789");
        hashMap.put("Accept", " application/json");
        OmsContractHead head = httpTest.headerMap(hashMap);
        return head;
    }

    @GetMapping("dow")
    public void dow(HttpServletResponse response) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"poo0054.zip\"");
        response.setContentType("application/octet-stream; charset=UTF-8");
        StreamProgress streamProgress = new StreamProgress() {
            @Override
            public void start() {
                System.out.println("我开始了");
            }

            @Override
            public void progress(long progressSize) {
                //如果需要获取全部大小 从response 的 Content-Length获取总大小
                System.out.println("当前传了" + progressSize);
            }

            @Override
            public void finish() {
                System.out.println("结束了");
            }
        };
        HttpResponse httpResponse = httpTest.dowOutputStream(response.getOutputStream(), streamProgress);
        System.out.println(httpResponse);
    }

    @GetMapping("dowFile")
    public void dowFile() throws IOException {
        File file = new File("D:/test.zip");
        if (file.isFile()) {
            file.createNewFile();
        }
        HttpResponse httpResponse = httpTest.dowFile(file);
        System.out.println(httpResponse);
    }

    @GetMapping("upload")
    public void upload() throws IOException {
        File file = new File("D:/test.zip");
        if (file.isFile()) {
            file.createNewFile();
        }
        httpTest.upload(file);
    }

    @GetMapping("uploadMap")
    public void uploadMap() throws IOException {
        File file = new File("D:/test.zip");
        if (file.isFile()) {
            file.createNewFile();
        }
        Map<String, Object> map = new HashMap<>();
        map.put("file", file);
        httpTest.uploadMap(map);
    }

}
