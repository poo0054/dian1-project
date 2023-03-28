<template>
    <div class="page pageCont">
        <v-json-from
                ref="jsonFromRef"
                :data="formData"
                :ruleForm="formData.ruleForm"
                :labelPosition="labelPosition"
                :labelWidth="labelWidth"
                @onSubmitForm="SubmitForm"
                @onResetForm="onResetForm();"
        >
      <span slot="buttonSlot">
        <el-button type="primary" size="mini" @click="handleAdd">新增</el-button>
        <el-button type="primary" size="mini" @click="handleImport">导入</el-button>
        <el-button
                type="primary"
                size="mini"
                @click="handleImport"
                style="margin-right: 10px"
        >导入</el-button>
      </span>
        </v-json-from>
        <v-json-table
                ref="table"
                :height="height"
                :isSelection="false"
                border
                :header="getHeader()"
                :opsButton="opsButtonList()"
                :opsWidth="opsWidth"
                :data="tableData"
                :isIndex="true"
                :showPagination="true"
                :currentPage="query.start"
                :pageSize="query.size"
                :totalCount="totalCount"
                :menuConfigShow="false"
                @on-operate-click="operateClick"
                @refreshList="onResetForm"
                @on-page-change="onPageChange"
                @size-change="onSizeChange"
        />
        <editDialog ref="editDialogRef" @onEditSuccess="publicTable"/>
        <importDialog ref="importDialogRef" @onUploadSuccess="publicTable"/>
    </div>
</template>
<script>
    import Column from './column'
    import pagepublic from '@/mixins/pagepublic'
    import editDialog from './modules/editDialog.vue'
    import importDialog from './modules/importDialog.vue'

    export default {
        name: '${tableClassName}',
        mixins: [Column, pagepublic],
        components: {
            editDialog,
            importDialog
        },
        data() {
            return {
                urls: {
                    list: '${tableClassName}/page'
                },
                formData: {},
                activate: '',
                dialogVisible: false,
                jsonData: '',
                row: {}, //当前行数据
                pushData: []
            }
        },
        mounted() {
        },
        methods: {
            SubmitForm() {
                this.publicTable()
            },
            handleImport() {
                this.$refs['importDialogRef'].showDialog()
            },
            handleAdd() {
                this.$refs['editDialogRef'].showDialog()
            },
            operateClick(menuItem, row, index) {
                if (menuItem.type == 'delete') {
                    this.deleteRow(row)
                } else {
                    this.$refs['editDialogRef'].showDialog(row)
                }
            },
            deleteRow(row) {
                this.$confirm('删除后将不可恢复，确认删除?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$api.delete(`${tableClassName}/${r'${'}row.id${r'}'}`).then((res) => {
                        this.$api.success('删除成功')
                        this.publicTable()
                    })
                })
            }
        }
    }
</script>
<style lang="scss">
    .json-item {
        width: 100%;
        height: 100%;

    &
    .string-box {
        height: auto;
        line-height: 20px;
        overflow: hidden;
        word-break: break-all;
    }

    .number {
        color: #2fa0ed;
    }

    .string {
        color: #f16222;
    }

    .boolean {
        color: #00c099;
    }

    .null {
        color: #cc33cc;
    }

    .key {
        color: #424456;
    }

    }
</style>
