package com.dian1.http.handle.type;


import com.dian1.http.annotate.type.Get;
import com.dian1.http.handle.TypeHandle;
import com.dian1.http.properties.HttpProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhi
 * @date 2023/3/27
 */
@Component
public class GetHandle implements TypeHandle<Get> {


    @Override
    public void resolving(HttpProperties httpProperties, Get typeArgument) {

    }


}
