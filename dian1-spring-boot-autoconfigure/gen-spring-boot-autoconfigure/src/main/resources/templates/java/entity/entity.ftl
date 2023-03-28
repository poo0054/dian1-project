package ${tablePackage}.${modelName}.${functionName}. entity;

import cn.dian1.core.annotation.CommentAnnotation;

import java.util.Date;

import cn.dian1.core.base.BasePojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import cn.dian1.oms.util.SerialClone;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* ${tableAnnotation}
* @author ${author}
* @date   ${.now?date}
*/
@Data
@TableName("${tableName}")
@EqualsAndHashCode(callSuper = true)
public class ${tableClassName?cap_first}Entity extends BasePojo implements SerialClone {

private static final long serialVersionUID = 1L;

<#-- 忽略字段  -->
<#assign ignoreColumns = ['createUserId','createTime','createBy','updateUserId','updateTime','updateBy']>
<#if modelColumn??>
    <#list modelColumn as model>
        <#if !ignoreColumns?seq_contains(model.javaField)>
            /**
            * ${model.columnComment!}
            */
            <#if model.isPk == 1>
                @TableId(value = "${model.columnName}", type = IdType.ASSIGN_ID)
            </#if>
            @CommentAnnotation("${model.columnName}")
            private ${model.javaType} ${model.javaField};
        </#if>
    </#list>
</#if>
}
