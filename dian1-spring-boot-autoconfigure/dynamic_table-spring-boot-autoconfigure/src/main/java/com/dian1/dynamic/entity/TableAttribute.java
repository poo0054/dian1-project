package com.dian1.dynamic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * (TableAttribute)表实体类
 *
 * @author zhangzhi
 * @since 2022-10-09 09:15:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TableAttribute extends Model<TableAttribute> {
    private static final long serialVersionUID = -7671673029198045352L;
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 表类型
     */
    private String tableCode;
    /**
     * 名字
     */
    private String name;

    /**
     * 查询地址
     */
    private String url;

    /**
     * 请求类型
     */
    private String urlType;

    /**
     * 是否携带斑马纹
     */
    private Boolean stripe;

    /**
     * 是否携带边框
     */
    private Boolean border;

    /**
     * 高度
     */
    private String height;

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

