package com.dian1.dynamic.config;

import com.dian1.dynamic.service.TableAttributeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.annotation.Resource;

/**
 * 启动项目处理器 创建数据库
 *
 * @author ZhangZhi
 * @version 1.0
 * @since 2022/10/8 15:16
 */
@Slf4j
public class DbCreateListener implements ApplicationListener<ContextRefreshedEvent> {

    @Resource
    private TableAttributeService tableAttributeService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //初始化表
        tableAttributeService.initTable();
    }
}
