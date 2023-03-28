package com.dian1.gen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dian1.gen.entity.GenTable;
import com.dian1.gen.entity.po.GenTablePO;

import java.util.List;
import java.util.Map;

/**
 * 自动生成代码-表(GenTable)表服务接口
 *
 * @author
 * @since 2023-03-16 10:07:49
 */
public interface GenTableService extends IService<GenTable> {


    /**
     * 查询所有表名
     *
     * @return 表名集合
     */
    List<String> showTables();


    /**
     * 新增
     *
     * @param genTablePO 新增数据
     * @return 是否成功
     */
    boolean add(GenTablePO genTablePO);

    Map<String, Object> preview(Long tableId);

    GenTablePO getTableNameInfo(String tableName);

    boolean put(GenTablePO genTablePO);

    byte[] download(Long tableId);
}

