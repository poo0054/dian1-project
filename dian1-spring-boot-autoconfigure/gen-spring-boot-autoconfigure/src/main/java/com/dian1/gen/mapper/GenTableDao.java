package com.dian1.gen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dian1.gen.entity.GenTable;

import java.util.List;

/**
 * 自动生成代码-表(GenTable)表数据库访问层
 *
 * @author
 * @since 2023-03-16 10:07:47
 */
public interface GenTableDao extends BaseMapper<GenTable> {

    List<String> showTables();

    String getTableNameInfo(String tableName);
}

