package com.dian1.http.handle.base;

import com.dian1.http.annotate.OpenHttp;
import com.dian1.http.properties.HttpProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhi
 * @date 2023/3/28
 */
@Component
@Data
public class OpenHttpHandle implements ClassHandle<OpenHttp> {


    public HttpProperties resolving(HttpProperties httpProperties, OpenHttp openHttp) {
        httpProperties.setBaseUrl(openHttp.value());
        httpProperties.setTimeout(openHttp.timeout());
        return httpProperties;
    }
}
