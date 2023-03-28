package com.dian1.gen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dian1.gen.entity.Columns;
import com.dian1.gen.entity.GenTableRow;
import com.dian1.gen.entity.po.GenTableRowPO;

import java.util.List;

/**
 * 自动生成代码-行(GenTableRow)表服务接口
 *
 * @author
 * @since 2023-03-16 10:11:01
 */
public interface GenTableRowService extends IService<GenTableRow> {

    List<GenTableRowPO> getByTable(Long tableId);

    List<Columns> getTableNameInfo(String tableName);
}

