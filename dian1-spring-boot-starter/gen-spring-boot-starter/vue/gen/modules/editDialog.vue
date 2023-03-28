<template>
  <el-dialog :title="title" :visible.sync="show" width="1500px">
    <el-form ref="formRef" :inline="true" :model="form">
      <el-form-item label="表名" prop="tableName">
        <el-input v-model="form.tableName" clearable/>
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input v-model="form.author" clearable/>
      </el-form-item>
      <el-form-item label="下载类型" prop="dowType">
        <el-input v-model="form.dowType" clearable/>
      </el-form-item>
      <el-form-item label="包名" prop="tablePackage">
        <el-input v-model="form.tablePackage" clearable/>
      </el-form-item>
      <el-form-item label="模块名" prop="modelName">
        <el-input v-model="form.modelName" clearable/>
      </el-form-item>
      <el-form-item label="功能名" prop="functionName">
        <el-input v-model="form.functionName" clearable/>
      </el-form-item>
      <el-form-item label="其他选项" prop="options">
        <el-input v-model="form.options" clearable/>
      </el-form-item>
    </el-form>
    <el-button @click="addRow()"> 查询</el-button>
    <el-table
        :data="form.modelColumn"
        border
        style="width: 100%"
    >
      <el-table-column
          label="列名称"
          prop="columnName"
          width="120"
      >
        <template slot-scope="scope">
          <el-input v-model="scope.row.columnName" disabled/>
        </template>
      </el-table-column>
      <el-table-column
          label="列描述"
          prop="columnComment"
          width="120"
      >
        <template slot-scope="scope">
          <el-input v-model="scope.row.columnComment" disabled/>
        </template>
      </el-table-column>
      <el-table-column
          label="列类型"
          prop="columnType"
          width="120"
      >
        <template slot-scope="scope">
          <el-input v-model="scope.row.columnType" disabled/>
        </template>
      </el-table-column>
      <el-table-column
          label="列长度"
          prop="columnLength"
          width="80"
      >
        <template slot-scope="scope">
          <el-input v-model="scope.row.columnLength" disabled/>
        </template>
      </el-table-column>
      <el-table-column
          label="JAVA类型"
          prop="javaType"
          width="120"
      >
        <template slot-scope="scope">
          <el-input v-model="scope.row.javaType"/>
        </template>
      </el-table-column>
      <el-table-column
          label="JAVA字段名"
          prop="javaField"
          width="120"
      >
        <template slot-scope="scope">
          <el-input v-model="scope.row.javaField"/>
        </template>
      </el-table-column>
      <el-table-column
          label="是否主键"
          prop="isPk"
          width="80"
      >
        <template slot-scope="scope">
          <el-select v-model="scope.row.isPk" clearable>
            <el-option
                v-for="(item,index) in options"
                :key="index"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
          label="是否必填"
          prop="isRequired"
          width="80"
      >
        <template slot-scope="scope">
          <el-select v-model="scope.row.isRequired" clearable>
            <el-option
                v-for="(item,index) in options"
                :key="index"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
          label="插入字段"
          prop="isInsert"
          width="80"
      >
        <template slot-scope="scope">
          <el-select v-model="scope.row.isInsert" clearable>
            <el-option
                v-for="(item,index) in options"
                :key="index"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
          label="编辑字段"
          prop="isEdit"
          width="80"
      >
        <template slot-scope="scope">
          <el-select v-model="scope.row.isEdit" clearable>
            <el-option
                v-for="(item,index) in options"
                :key="index"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
          label="列表字段"
          prop="isList"
          width="80"
      >
        <template slot-scope="scope">
          <el-select v-model="scope.row.isList" clearable>
            <el-option
                v-for="(item,index) in options"
                :key="index"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
          label="查询字段"
          prop="isQuery"
          width="80"
      >
        <template slot-scope="scope">
          <el-select v-model="scope.row.isQuery" clearable>
            <el-option
                v-for="(item,index) in options"
                :key="index"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
          label="查询方式"
          prop="queryType"
          width="80"
      >
        <template slot-scope="scope">
          <el-select v-model="scope.row.queryType" clearable>
            <el-option
                v-for="(item,index) in queryType"
                :key="index"
                :label="item.label"
                :value="item.value"
            >
            </el-option>
          </el-select>
        </template>
      </el-table-column>
      <el-table-column
          label="显示类型"
          prop="htmlType"
          width="120"
      >
        <template slot-scope="scope">
          <el-input v-model="scope.row.htmlType"/>
        </template>
      </el-table-column>
      <el-table-column
          label="字典类型"
          prop="dictType"
          width="150"
      >
        <template slot-scope="scope">
          <el-select v-model="scope.row.dictType" clearable filterable>
            <el-option
                v-for="(item,index) in dictType"
                :key="index"
                :label="item.basicTypeLabel"
                :value="item.basicType"
            >
              <span style="float: left">{{ item.basicType }}</span>
              <span style="float: right; color: #8492a6; font-size: 13px">{{ item.basicTypeLabel }}</span>
            </el-option>
          </el-select>
        </template>
      </el-table-column>

      <el-table-column
          fixed="right"
          label="操作"
          width="120"
      >
        <template slot-scope="scope">
          <el-button
              size="small"
              type="text"
              @click.native.prevent="deleteRow(scope.$index, form.modelColumn)"
          >
            移除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div slot="footer" class="dialog-footer">
      <el-button @click="hideDialog()">取 消</el-button>
      <el-button :loading="loading" type="primary" @click="handleSubmit()">确 定</el-button>
    </div>
  </el-dialog>
</template>
<script>

export default {
  mixins: [],
  data() {
    return {
      show: false,
      loading: false,
      form: {modelColumn: []},
      options: [
        {
          value: 1,
          label: '是'
        }, {
          value: 0,
          label: '否'
        }
      ],
      queryType: [
        {
          value: 'gt',
          label: '大于'
        },
        {
          value: 'lt',
          label: '小于'
        },
        {
          value: 'eq',
          label: '等于'
        },
        {
          value: 'ne',
          label: '不等于'
        },
        {
          value: 'between',
          label: '范围'
        }
      ],
      dictType: []
    }
  },
  created() {
    this.init()
  },
  computed: {
    title() {
      return this.form.id ? '修改' : '新增'
    }

  },
  methods: {
    init() {
      this.$api.post(`/basicType/list`).then(res => {
        console.log(res)
        this.dictType = res
      })
    },
    addRow() {
      this.$api.get(`gen/tableName/${this.form.tableName}`).then(res => {
        this.form = res
      })

    },
    deleteRow(index, rows) {
      rows.splice(index, 1)
    },
    handleSubmit() {
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          const fn = this.form.id ? this.$api.put : this.$api.post
          this.loading = true
          fn('/gen', this.form)
              .then((res) => {
                this.$message.success('操作成功')
                this.$emit('onEditSuccess')
                this.hideDialog()
              })
              .finally(() => {
                this.loading = false
              })
        }
      })
    },
    showDialog(data) {
      // this.form = this.$options.data().form
      this.form = {modelColumn: []}
      if (data) {
        this.form = JSON.parse(JSON.stringify(data))
        // Object.assign(this.form, data)
        this.$api.get('genTableRow/table/' + this.form.id).then(res => {
          this.form.modelColumn = res
          this.$forceUpdate()
        })
      }
      this.show = true

      this.$nextTick(() => this.$refs['formRef'].clearValidate())

    },
    hideDialog() {
      this.show = false
    }
  }
}
</script>
