package com.dian1.gen.entity.po;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 自动生成代码-行(GenTableRow)表实体类
 *
 * @author
 * @since 2023-03-16 10:11:00
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class GenTableRowPO extends Model<GenTableRowPO> {
    private static final long serialVersionUID = -7000795499209341481L;
    //主键
    private Long id;
    //表主键
    private Long tableId;
    //列名称
    private String columnName;
    //列描述
    private String columnComment;
    //列类型
    private String columnType;
    //列长度
    private Integer columnLength;
    //JAVA类型
    private String javaType;
    //JAVA字段名
    private String javaField;
    //是否主键（1是）
    private Integer isPk = 0;
    //是否必填（1是）
    private Integer isRequired = 0;
    //是否为插入字段（1是）
    private Integer isInsert = 0;
    //是否编辑字段（1是）
    private Integer isEdit = 0;
    //是否列表字段（1是）
    private Integer isList = 1;
    //是否查询字段（1是）
    private Integer isQuery = 0;
    //查询方式（等于、不等于、大于、小于、范围）
    private String queryType = "eq";
    //显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
    private String htmlType;
    //字典类型
    private String dictType;
    //排序
    private Integer sort;
    //创建人ID
    private Long createUserId;
    //创建时间
    private Date createTime;
    //最后人修改人
    private Long updateUserId;
    //最后修改时间
    private Date updateTime;
    //备注
    private String remark;
}

