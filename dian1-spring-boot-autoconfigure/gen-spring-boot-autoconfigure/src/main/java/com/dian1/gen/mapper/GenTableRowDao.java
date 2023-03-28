package com.dian1.gen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dian1.gen.entity.Columns;
import com.dian1.gen.entity.GenTableRow;

import java.util.List;

/**
 * 自动生成代码-行(GenTableRow)表数据库访问层
 *
 * @author
 * @since 2023-03-16 10:11:00
 */
public interface GenTableRowDao extends BaseMapper<GenTableRow> {

    List<Columns> getTableNameInfo(String tableName);
}

