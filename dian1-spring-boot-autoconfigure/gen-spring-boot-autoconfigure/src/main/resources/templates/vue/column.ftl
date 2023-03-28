export default {
data() {
return {
formData: {
list: [
<#if modelColumn??>
    <#list modelColumn as model>
        <#if model.isQuery == 1>
            {
            type: "${model.htmlType}",
            label: "${model.columnComment}",
            prop: "${model.javaField}",
            },
        </#if>
    </#list>
</#if>
],
ruleForm: {}
}
}
},
methods: {
getHeader() {
return [
<#if modelColumn??>
    <#list modelColumn as model>
        <#if model.isList == 1>
            {
            display: "${model.columnComment}",
            prop: "${model.javaField}",
            },
        </#if>
    </#list>
</#if>
]
},
opsButtonList() {
return [
{
type: 'update',
title: '修改'
},
{
type: 'delete',
title: '删除'
}
]
}
}
}
