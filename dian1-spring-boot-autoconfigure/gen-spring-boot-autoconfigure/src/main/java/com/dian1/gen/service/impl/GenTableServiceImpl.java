package com.dian1.gen.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dian1.gen.entity.Columns;
import com.dian1.gen.entity.GenTable;
import com.dian1.gen.entity.GenTableRow;
import com.dian1.gen.entity.po.GenTablePO;
import com.dian1.gen.entity.po.GenTableRowPO;
import com.dian1.gen.mapper.GenTableDao;
import com.dian1.gen.service.GenTableRowService;
import com.dian1.gen.service.GenTableService;
import com.dian1.gen.utils.TemplateUtils;
import freemarker.template.Configuration;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 自动生成代码-表(GenTable)表服务实现类
 *
 * @author
 * @since 2023-03-16 10:07:49
 */
@Service("genTableService")
public class GenTableServiceImpl extends ServiceImpl<GenTableDao, GenTable> implements GenTableService {
    /**
     * 服务对象
     */
    @Resource
    private GenTableRowService genTableRowService;


    @Resource
    private Configuration configuration;


    @Override
    public List<String> showTables() {
        return baseMapper.showTables();
    }

    @Override
    @Transactional
    public boolean add(GenTablePO genTablePO) {
        GenTable genTable = new GenTable();
        BeanUtils.copyProperties(genTablePO, genTable);
        this.save(genTable);
        List<GenTableRowPO> rowPOS = genTablePO.getModelColumn();
        List<GenTableRow> genTableRows = JSON.parseArray(JSON.toJSONString(rowPOS), GenTableRow.class);
        genTableRows.forEach(item -> {
            item.setTableId(genTable.getId());
        });
        genTableRowService.saveBatch(genTableRows);
        return false;
    }

    @Override
    public boolean put(GenTablePO genTablePO) {
        GenTable genTable = new GenTable();
        BeanUtils.copyProperties(genTablePO, genTable);
        this.updateById(genTable);
        List<GenTableRowPO> rowPOS = genTablePO.getModelColumn();
        List<GenTableRow> genTableRows = JSON.parseArray(JSON.toJSONString(rowPOS), GenTableRow.class);
        genTableRowService.updateBatchById(genTableRows);
        return false;
    }

    @Override
    public Map<String, Object> preview(Long tableId) {
        GenTablePO genTablePO = new GenTablePO();
        BeanUtils.copyProperties(this.getById(tableId), genTablePO);
        genTablePO.setModelColumn(genTableRowService.getByTable(tableId));
        Map<String, Object> stringObjectMap = TemplateUtils.getMapData(configuration, genTablePO);
        return stringObjectMap;
    }

    @Override
    public byte[] download(Long tableId) {
        GenTablePO genTablePO = new GenTablePO();
        BeanUtils.copyProperties(this.getById(tableId), genTablePO);
        genTablePO.setModelColumn(genTableRowService.getByTable(tableId));
        return TemplateUtils.download(configuration, genTablePO);

    }

    @Override
    public GenTablePO getTableNameInfo(String tableName) {
        String comment = baseMapper.getTableNameInfo(tableName);
        List<Columns> tableNameInfo = genTableRowService.getTableNameInfo(tableName);
        GenTablePO tablePO = new GenTablePO();
        tablePO.setTableName(tableName);
        tablePO.setAuthor("zhangzhi");
        tablePO.setTablePackage("cn.dian1");
        tablePO.setModelName("oms");
        tablePO.setFunctionName("gen");
        tablePO.setDowType("zip");
        tablePO.setTableAnnotation(comment);
        List<GenTableRowPO> collect = tableNameInfo.stream().map(item -> {
            GenTableRowPO rowPO = new GenTableRowPO();
            rowPO.setColumnName(item.getCOLUMN_NAME());
            if (null != item.getIS_NULLABLE() && item.getIS_NULLABLE().equals("YES")) {
                rowPO.setIsRequired(1);
            }
            rowPO.setColumnType(item.getDATA_TYPE());
            rowPO.setColumnLength(item.getCHARACTER_MAXIMUM_LENGTH());
            if (null != item.getCOLUMN_KEY() && item.getCOLUMN_KEY().equals("PRI")) {
                rowPO.setIsPk(1);
            }
            rowPO.setColumnComment(item.getCOLUMN_COMMENT());
            rowPO.setJavaField(rowPO.getColumnName());
            return rowPO;
        }).collect(Collectors.toList());
        tablePO.setModelColumn(collect);
        return tablePO;
    }
}

