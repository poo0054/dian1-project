<template>
    <el-dialog :visible.sync="show" :title="title">
        <el-form inline :model="form" ref="formRef">
            <#if modelColumn??>
                <#list modelColumn as model>
                    <#if 1 == model.isEdit>
                        <el-form-item label="${model.columnComment}"
                                      prop="${model.javaField}">
                            <el-input v-model="form.${model.javaField}" clearable/>
                        </el-form-item>
                    </#if>
                </#list>
            </#if>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="hideDialog()">取 消</el-button>
            <el-button type="primary" :loading="loading" @click="handleSubmit()">确 定</el-button>
        </div>
    </el-dialog>
</template>
<script>

    export default {
        data() {
            return {
                show: false,
                loading: false,
                form: {},
            };
        },
        computed: {
            title() {
                return this.form.id ? "修改${tableAnnotation}" : "新增${tableAnnotation}";
            }
        },
        methods: {
            handleSubmit() {
                this.$refs["formRef"].validate((valid) => {
                    if (valid) {
                        const url = "/${tableClassName}";
                        const fn = this.form.id ? this.$api.put : this.$api.post;
                        this.loading = true;
                        fn(url, this.form)
                            .then(() => {
                                this.$message.success("操作成功");
                                this.$emit("onEditSuccess");
                                this.hideDialog();
                            })
                            .finally(() => {
                                this.loading = false;
                            });
                    }
                });
            },
            showDialog(data) {
                this.form = {};
                if (data) {
                    this.form = JSON.parse(JSON.stringify(data))
                }
                this.show = true;
                this.$nextTick(() => this.$refs["formRef"].clearValidate());
            },
            hideDialog() {
                this.show = false;
            }
        }
    };
</script>
