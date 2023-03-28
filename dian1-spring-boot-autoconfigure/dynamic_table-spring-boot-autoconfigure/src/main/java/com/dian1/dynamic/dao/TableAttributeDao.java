package com.dian1.dynamic.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dian1.dynamic.entity.TableAttribute;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * (TableAttribute)表数据库访问层
 *
 * @author zhangzhi
 * @since 2022-10-09 09:15:36
 */
@Mapper
public interface TableAttributeDao extends BaseMapper<TableAttribute> {

    /**
     * 表是否存在
     *
     * @param tableNames 表名
     * @return 是否存在
     */
    int showTable(@Param("tableNames") Set<String> tableNames);

    /**
     * 创建表
     *
     * @return
     */
    void createTable();
}

