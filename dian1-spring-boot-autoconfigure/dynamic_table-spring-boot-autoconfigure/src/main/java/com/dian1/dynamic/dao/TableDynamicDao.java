package com.dian1.dynamic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dian1.dynamic.entity.TableDynamic;
import org.apache.ibatis.annotations.Mapper;

/**
 * (TableDynamic)表数据库访问层
 *
 * @author zhangzhi
 * @since 2022-10-09 10:10:28
 */
@Mapper
public interface TableDynamicDao extends BaseMapper<TableDynamic> {

    /**
     * 创建表
     *
     * @return
     */
    void createTable();
}

