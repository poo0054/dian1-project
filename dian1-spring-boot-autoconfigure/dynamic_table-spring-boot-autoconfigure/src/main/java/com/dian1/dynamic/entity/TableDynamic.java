package com.dian1.dynamic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (TableDynamic)表实体类
 *
 * @author zhangzhi
 * @since 2022-10-09 10:10:28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TableDynamic extends Model<TableDynamic> {

    private static final long serialVersionUID = 5897204306455302610L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 表格名称
     */
    private String name;
    /**
     * 表类型
     */
    private String tableCode;
    /**
     * 表格值
     */
    private String value;

    /**
     * 表格状态
     */
    private Boolean disabled;
    /**
     * 宽度
     */
    private Double width;
    /**
     * 固定列
     */
    private String fixed;
    /**
     * 父级id  待扩展
     */
    private Integer parentId;

    /**
     * input select date time等
     */
    private String type;

    /**
     * 类型的类型
     */
    private String typeType;

    /**
     * 下拉框的请求地址 暂只支持get请求
     */
    private String selectUrl;

    /**
     * 对齐方式
     */
    private String align;

    /**
     * 是否以该行进行排序
     */
    private Boolean sortable;

    /**
     * filters  用来过滤
     */
    private String filters;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否需要进行查询
     */
    private Boolean showSearch;

    /**
     * 提示语句
     */
    private String placeholder;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

