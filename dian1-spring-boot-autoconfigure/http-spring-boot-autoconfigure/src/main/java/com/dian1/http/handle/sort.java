package com.dian1.http.handle;

/**
 * 处理器的执行顺序
 *
 * @author zhangzhi
 * @date 2023/3/29
 */
public interface sort {

    default int order() {
        return 5;
    }
}
