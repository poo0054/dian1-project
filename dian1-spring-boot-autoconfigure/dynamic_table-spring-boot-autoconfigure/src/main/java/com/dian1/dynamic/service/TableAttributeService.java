package com.dian1.dynamic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dian1.dynamic.entity.TableAttribute;

import java.util.List;
import java.util.Set;

/**
 * (TableAttribute)表服务接口
 *
 * @author zhangzhi
 * @since 2022-10-09 09:15:37
 */
public interface TableAttributeService extends IService<TableAttribute> {

    /**
     * 根据类型查询所有数据
     *
     * @param tableCode 类型
     * @return 所有数据
     */
    List<TableAttribute> tableCode(String tableCode);

    /**
     * 创建表
     *
     * @return 是否成功
     */
    boolean initTable();

    /**
     * 表是否存在
     *
     * @param tableNames 表名
     * @return 是否存在
     */
    boolean showTable(Set<String> tableNames);
}

