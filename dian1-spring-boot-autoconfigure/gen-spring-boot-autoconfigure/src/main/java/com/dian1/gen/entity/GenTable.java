package com.dian1.gen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 自动生成代码-表(GenTable)表实体类
 *
 * @author
 * @since 2023-03-16 10:07:49
 */
@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = true)
public class GenTable extends Model<GenTable> {
    private static final long serialVersionUID = -247609050617123763L;
    //主键
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    //表明
    private String tableName;
    //表注释
    private String tableAnnotation;
    //作者
    private String author;
    //下载类型: zip
    private String dowType;
    //项目模块名称
    private String modelName;
    //包名
    private String tablePackage;
    //其他选项
    private String options;
    //功能名称: 如 主单,为空使用表注释
    private String functionName;
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

