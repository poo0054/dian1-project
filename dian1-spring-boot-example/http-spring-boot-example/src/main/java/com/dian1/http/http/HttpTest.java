package com.dian1.http.http;

import cn.hutool.http.HttpResponse;
import com.dian1.http.annotate.OpenHttp;
import com.dian1.http.annotate.type.Get;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@OpenHttp("http://127.0.0.1:4523/m1")
public interface HttpTest {

    @Get("/2406035-0-7638cb9b/forwarder/v1/stock/paging")
    HttpResponse test();

}
