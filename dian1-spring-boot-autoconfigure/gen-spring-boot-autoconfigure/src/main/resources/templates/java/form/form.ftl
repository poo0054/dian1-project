package ${tablePackage}.${modelName}.${functionName}. form;

import java.util.Date;

import lombok.Data;
import cn.dian1.oms.base.BasicDataDeserializer;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import com.alibaba.fastjson.annotation.JSONField;
import cn.dian1.core.enums.EnumDeserializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import cn.dian1.oms.util.SerialClone;

/**
* ${tableAnnotation}
* @author ${author}
* @date   ${.now?date}
*/
@Data
public class ${tableClassName?cap_first}Form implements SerialClone {

private static final long serialVersionUID = 1L;

<#-- 忽略字段  -->
<#assign ignoreColumns = ['createUserId','createTime','createBy','updateUserId','updateTime','updateBy']>

<#if modelColumn??>

    <#list modelColumn as model>
        <#if !ignoreColumns?seq_contains(model.changeColumnName)>
            /**
            *${model.columnComment!}
            */
            <#if model.columnLength??>
                @Length(message = "${r'${'}valid_length:${model.javaField},${model.columnLength}${r'}'}", max = ${model.columnLength})
            </#if>

            <#if model.columnType == "String">
                @NotBlank(message = "${r'${'}not_found:${model.javaField}${"}"}")
            <#else>
                @NotNull(message = "${r'${'}not_found:${model.javaField}${"}"}")
            </#if>

            <#if  model.dictType?? && ''!=model.dictType>
                @JSONField(deserializeUsing = BasicDataDeserializer.class)
            </#if>
            private ${model.javaType} ${model.javaField};
        </#if>
    </#list>
</#if>
}
