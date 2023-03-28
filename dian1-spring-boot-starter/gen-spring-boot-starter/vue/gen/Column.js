export default {
    data() {
        return {
            formData: {
                list: [
                    {
                        type: 'input',
                        label: '表名:',
                        prop: 'tableName'
                    },
                    {
                        type: 'input',
                        label: '模块:',
                        prop: 'modelName'
                    }
                ],
                ruleForm: {}
            }
        }
    },
    mounted() {
    },
    created() {
    },
    methods: {
        getHeader() {
            return [
                {
                    display: '表名',
                    prop: 'tableName'
                },
                {
                    display: '作者',
                    prop: 'author'
                },
                {
                    display: '下载类型',
                    prop: 'dowType'
                },
                {
                    display: '包名',
                    prop: 'tablePackage'
                },
                {
                    display: '模块名',
                    prop: 'modelName'
                },
                {
                    display: '功能名',
                    prop: 'functionName'
                },
                {
                    display: '其他参数',
                    prop: 'options'
                }
            ]
        },
        opsButtonList() {
            return [
                {
                    type: 'update',
                    title: '修改'
                },
                {
                    type: 'preview',
                    title: '预览'
                },
                {
                    type: 'download',
                    title: '下载'
                }
            ]
        }
    }
}
