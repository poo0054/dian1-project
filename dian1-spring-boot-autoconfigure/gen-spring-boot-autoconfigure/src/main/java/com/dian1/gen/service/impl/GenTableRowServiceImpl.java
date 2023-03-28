package com.dian1.gen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dian1.gen.entity.Columns;
import com.dian1.gen.entity.GenTableRow;
import com.dian1.gen.entity.po.GenTableRowPO;
import com.dian1.gen.mapper.GenTableRowDao;
import com.dian1.gen.service.GenTableRowService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自动生成代码-行(GenTableRow)表服务实现类
 *
 * @author
 * @since 2023-03-16 10:11:01
 */
@Service("genTableRowService")
public class GenTableRowServiceImpl extends ServiceImpl<GenTableRowDao, GenTableRow> implements GenTableRowService {

    @Override
    public List<GenTableRowPO> getByTable(Long tableId) {
        LambdaQueryWrapper<GenTableRow> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GenTableRow::getTableId, tableId);
        List<GenTableRow> list = this.list(queryWrapper);
        return JSON.parseArray(JSON.toJSONString(list), GenTableRowPO.class);
    }

    @Override
    public List<Columns> getTableNameInfo(String tableName) {
        return baseMapper.getTableNameInfo(tableName);
    }

}

