package com.dian1.dynamic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dian1.dynamic.constant.TableConstant;
import com.dian1.dynamic.dao.TableAttributeDao;
import com.dian1.dynamic.entity.TableAttribute;
import com.dian1.dynamic.service.TableAttributeService;
import com.dian1.dynamic.service.TableDynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * (TableAttribute)表服务实现类
 *
 * @author zhangzhi
 * @since 2022-10-09 09:15:38
 */
@Slf4j
@Service
public class TableAttributeServiceImpl extends ServiceImpl<TableAttributeDao, TableAttribute> implements TableAttributeService {

    /**
     * 只允许启动一次
     */
    public static boolean INIT_TABLE = false;
    @Resource
    private TableDynamicService tableDynamicService;

    /**
     * 根据类型查询所有数据
     *
     * @param tableCode 类型
     * @return 所有数据
     */
    @Override
    public List<TableAttribute> tableCode(String tableCode) {
        LambdaQueryWrapper<TableAttribute> queryWrapper = new QueryWrapper<TableAttribute>().lambda();
        queryWrapper.eq(TableAttribute::getTableCode, tableCode);
        return this.list(queryWrapper);
    }

    /**
     * 创建表
     *
     * @return 是否成功
     */
    @Override
    public boolean initTable() {
        if (INIT_TABLE) {
            return true;
        }
        INIT_TABLE = true;
        Set<String> set = new HashSet<>();
        set.add(TableConstant.TABLE_ATTRIBUTE);
        //不存在
        if (!this.showTable(set)) {
            log.info("{}不存在准备创建------", TableConstant.TABLE_ATTRIBUTE);
            baseMapper.createTable();
            log.info("{}创建成功------", TableConstant.TABLE_ATTRIBUTE);
        }
        set.add(TableConstant.TABLE_DYNAMIC);
        //不存在
        if (!this.showTable(set)) {
            log.info("{}不存在准备创建------", TableConstant.TABLE_DYNAMIC);
            tableDynamicService.createTable();
            log.info("{}创建成功------", TableConstant.TABLE_DYNAMIC);
        }
        log.info("表初始化完成");
        return true;
    }

    /**
     * 表是否存在
     *
     * @param tableNames 表名
     * @return 是否存在
     */
    @Override
    public boolean showTable(Set<String> tableNames) {
        int sum = this.baseMapper.showTable(tableNames);
        return sum == tableNames.size();
    }
}

