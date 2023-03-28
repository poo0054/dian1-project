package ${tablePackage}.${modelName}.${functionName}. vo;

import cn.dian1.core.enums.ParseUserSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import cn.dian1.oms.util.SerialClone;
import cn.dian1.core.enums.EnumDeserializer;
import cn.dian1.core.annotation.DynamicEnum;

import java.util.Date;

import lombok.Data;

/**
* ${tableAnnotation}
* @author ${author}
* @date   ${.now?date}
*/
@Data
public class ${tableClassName?cap_first}VO implements SerialClone {

private static final long serialVersionUID = 1L;

<#assign parseUserColumns = ['createUserId','updateUserId']>

<#if modelColumn??>
    <#list modelColumn as model>
        <#if model.columnName !="delFlag">
            /**
            *${model.columnComment!}
            */
            <#if model.dictType?? && ''!=model.dictType>
                @DynamicEnum(type = "${model.dictType}")
            <#elseif parseUserColumns?seq_contains(model.javaField)>
                @JSONField(serializeUsing = ParseUserSerializer.class)
            </#if>
            private ${model.javaType} ${model.javaField};
        </#if>
    </#list>
</#if>
}
