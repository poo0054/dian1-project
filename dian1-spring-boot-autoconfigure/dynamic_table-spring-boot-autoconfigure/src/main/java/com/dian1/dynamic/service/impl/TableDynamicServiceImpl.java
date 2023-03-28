package com.dian1.dynamic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dian1.dynamic.dao.TableDynamicDao;
import com.dian1.dynamic.entity.TableDynamic;
import com.dian1.dynamic.service.TableDynamicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (TableDynamic)表服务实现类
 *
 * @author makejava
 * @since 2022-10-09 10:10:30
 */
@Slf4j
@Service
public class TableDynamicServiceImpl extends ServiceImpl<TableDynamicDao, TableDynamic> implements TableDynamicService {


    /**
     * 根据类型查询所有数据
     *
     * @param tableCode 类型
     * @return 所有数据
     */
    @Override
    public List<TableDynamic> tableCode(String tableCode) {
        LambdaQueryWrapper<TableDynamic> queryWrapper = new QueryWrapper<TableDynamic>().lambda();
        queryWrapper.eq(TableDynamic::getTableCode, tableCode);
        return this.list(queryWrapper);
    }


    /**
     * 创建表
     *
     * @return 是否成功
     */
    @Override
    public boolean createTable() {
        baseMapper.createTable();
        System.out.println("table_dynamic创建成功------");
        return true;
    }
}

