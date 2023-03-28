<template>
  <div class="page pageCont">
    <v-json-from
        ref="jsonFromRef"
        :buttonColSpan="buttonColSpan"
        :data="formData"
        :labelPosition="labelPosition"
        :labelWidth="labelWidth"
        :ruleForm="formData.ruleForm"
        @onResetForm="onResetForm()"
        @onSubmitForm="SubmitForm"
    >
            <span slot="buttonSlot">
                <el-button size="mini" type="primary" @click="handleAdd">新增</el-button>
                 <el-button size="mini" type="primary" @click="handleDelete">删除</el-button>
            </span>
    </v-json-from>
    <v-json-table
        ref="table"
        :currentPage="query.start"
        :data="tableData"
        :header="getHeader()"
        :height="height"
        :isIndex="true"
        :isSelection="true"
        :menuConfigShow="false"
        :opsButton="opsButtonList()"
        :opsWidth="opsWidth"
        :pageSize="query.size"
        :showPagination="true"
        :totalCount="totalCount"
        border
        @refreshList="onResetForm"
        @selection-change="handleSelectionChange"
        @on-operate-click="operateClick"
        @on-page-change="onPageChange"
        @size-change="onSizeChange"
    />
    <editDialog ref="editDialogRef" @onEditSuccess="publicTable"/>
    <preview ref="previewRef"/>
  </div>
</template>
<script>
import Column from './Column'
import pagepublic from '@/mixins/pagepublic'
import editDialog from './modules/editDialog.vue'
import preview from './modules/preview.vue'
import axios from 'axios'

export default {
  name: 'gen',
  mixins: [Column, pagepublic],
  components: {editDialog, preview},
  data() {
    return {
      urls: {
        list: '/genTable/page'
      },
      formData: {},
      activate: '',
      buttonColSpan: {md: 16, xl: 24},
      dialogVisible: false,
      jsonData: '',
      row: {}, //当前行数据
      threadPoolStatus: null,
      pushListVisible: false,
      pushData: [],
      ids: []
    }
  },
  mounted() {
  },
  methods: {
    handleSelectionChange(val) {
      this.ids = []
      val.forEach(item => {
        if (item) {
          this.ids.push(item.id)
        }
      })
    },
    handleAdd() {
      this.$refs['editDialogRef'].showDialog()
    },
    publicTable() {
      if (this.urls.list == '') {
        return
      }
      let ruleForm = this.formData.ruleForm ? this.formData.ruleForm : this.ruleForm
      this.$api.http('post', this.urls.list, {...this.query, ...ruleForm}, null, true).then((res) => {
        if (res) {
          this.tableData = res.records
          this.totalCount = Number(res.total)
        }
      })
    },
    SubmitForm() {
      this.publicTable()
    },
    operateClick(menuItem, row, index) {
      if (menuItem.type == 'preview') {
        this.$refs['previewRef'].showDialog(row)
      } else if (menuItem.type == 'update') {
        this.$refs['editDialogRef'].showDialog(row)
      } else {
        // this.axios.defaults.headers.common['Authorization'] = 'Bearer ' + getToken()
        axios.post(`gen/download/${row.id}`, {}, {responseType: 'blob'}).then(response => {
          //这里res.data是返回的blob对象
          const blob = new Blob([response], {type: 'application/zip;charset=utf-8'}) //application/vnd.openxmlformats-officedocument.spreadsheetml.sheet这里表示xlsx类型
          const downloadElement = document.createElement('a')
          const href = window.URL.createObjectURL(blob) //创建下载的链接
          downloadElement.href = href
          downloadElement.download = 'poo0054.zip' //下载后文件名
          document.body.appendChild(downloadElement)
          downloadElement.click() //点击下载
          document.body.removeChild(downloadElement) //下载完成移除元素
          window.URL.revokeObjectURL(href) //释放掉blob对象

          /* const blob = new Blob([response], { type: 'application/zip' })
          const downloadElement = document.createElement('a')
          const href = window.URL.createObjectURL(blob) //创建下载的链接
          downloadElement.href = href;
          [downloadElement.download] = 'attachment; filename=poo0054.zip'
          document.body.appendChild(downloadElement)
          downloadElement.click() //点击下载
          document.body.removeChild(downloadElement) //下载完成移除元素
          window.URL.revokeObjectURL(href) //释放blob对 */
          // this.$tools.saveBlob(res, `poo0054.zip`, 'application/zip; charset=UTF-8')
        })
      }
    },
    handleDelete() {
      this.$confirm('删除后将不可恢复，确认删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$api.delete(`/genTable?idList=${this.ids}`).then((res) => {
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

  &.string-box {
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
