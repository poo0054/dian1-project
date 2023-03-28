package com.dian1.gen.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Columns)实体类
 *
 * @author
 * @since 2023-03-16 16:22:54
 */
@Data
public class Columns implements Serializable {
    private static final long serialVersionUID = -36858981635583441L;

    /**
     * 列名
     */
    private String COLUMN_NAME;

    /**
     * 是否为空
     */
    private String IS_NULLABLE;

    /**
     * 数据库类型
     */
    private String DATA_TYPE;

    /**
     * 最大长度
     */
    private Integer CHARACTER_MAXIMUM_LENGTH;

    /**
     * 小数长度
     */
    private String CHARACTER_OCTET_LENGTH;

    /**
     * 主键
     */
    private String COLUMN_KEY;

    /**
     * 注释
     */
    private String COLUMN_COMMENT;


}

