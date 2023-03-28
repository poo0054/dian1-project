package ${tablePackage}.${modelName}.${functionName}. form;

import java.util.Date;

import cn.dian1.core.base.BaseSearchForm;
import lombok.Data;
import cn.dian1.oms.util.SerialClone;
import lombok.EqualsAndHashCode;

/**
* ${tableAnnotation}
* @author ${author}
* @date   ${.now?date}
*/
@Data
public class ${tableClassName?cap_first}SearchForm extends BaseSearchForm implements SerialClone {

<#-- 忽略字段  -->
<#assign ignoreColumns = ['createUserId','createTime','createBy','updateUserId','updateTime','updateBy','delFlag']>

private static final long serialVersionUID = 1L;

<#if modelColumn??>

    <#list modelColumn as model>

        <#if !ignoreColumns?seq_contains(model.changeColumnName)>

            /**
            *${model.columnComment!}
            */
            <#if model.queryType == "between">
                private ${model.javaType}[] ${model.javaField};
            <#else>
                private ${model.javaType} ${model.javaField};
            </#if>
        </#if>

    </#list>
</#if>

}
