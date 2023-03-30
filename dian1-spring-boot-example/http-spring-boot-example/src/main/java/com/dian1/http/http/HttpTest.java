package com.dian1.http.http;

import cn.hutool.core.io.StreamProgress;
import cn.hutool.http.HttpResponse;
import com.dian1.http.annotate.OpenHttp;
import com.dian1.http.annotate.parameter.*;
import com.dian1.http.annotate.result.Download;
import com.dian1.http.annotate.type.Get;
import com.dian1.http.annotate.type.Post;
import com.dian1.http.entity.OmsContractHead;
import com.dian1.http.handle.parameter.Download;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@OpenHttp("127.0.0.1:4523")
public interface HttpTest {

    /**
     * get方法
     *
     * @return 返回strng
     */
    @Get("/m1/2406035-0-default/omsProductDetail/get")
    @Auth("123456789")
    String get();

    @Get("m1/2406035-0-default/omsProductDetail/getProductCode/{code}")
    String restful(@BasicAuth Map nu, @Restful("code") String code);

    @Get("m1/2406035-0-default/omsProductHeader/get")
    void form(String id, Consumer consumer, @Auth("key") Map key);

    @Post("/m1/2406035-0-default/omsContractHead/list")
    @BasicAuth(username = "abc", password = "asd")
    OmsContractHead basicAuth(Map map);

    @Post("/m1/2406035-0-default/omsContractHead/list")
    @Header({"Authorization: 123456789", "Accept-Encoding: gzip"})
    OmsContractHead header();

    @Post("/m1/2406035-0-default/omsContractHead/list")
    OmsContractHead headerPar(String str);

    @Post("/m1/2406035-0-default/omsContractHead/list")
    OmsContractHead headerMap(@Header Map str);

    @Post("https://oms.test.1-dian.cn/oms/gen/download/1637748183482265601")
    @Header("Authorization: Bearer 318e7574-8e33-4a00-8e64-beeb15eb1ce3")
    HttpResponse dowFile(@Download File file);

    @Post("https://oms.test.1-dian.cn/oms/gen/download/1637748183482265601")
    @Header("Authorization: Bearer 318e7574-8e33-4a00-8e64-beeb15eb1ce3")
    HttpResponse dowOutputStream(@Download OutputStream outputStream, StreamProgress streamProgress);

    @Post("http://127.0.0.1:4523/m1/2406035-0-default/common/templateUploadFile")
    void uploadMap(Map<String, Object> map);

    @Post("test")
    void upload(@Form("file") File file);
}
