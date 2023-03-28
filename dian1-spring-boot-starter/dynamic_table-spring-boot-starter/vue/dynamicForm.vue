<template>
  <el-form :inline="true" :model="ruleForm" label-position="right" label-width="80px">
        <span v-for="(item,index) in formList" :key="index">
            <el-form-item v-if="item.showSearch" :label="item.name"
                          :prop="item.value">
                <dynamicType :modify="true"
                             :options="diasicOptions"
                             :scopeRow="ruleForm"
                             :tableDynamic="item"
                             :updateStatus="true">
                </dynamicType>
            </el-form-item>
        </span>
    <el-form-item label prop="test">
      <el-button @click="searchBtn">查询</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import dynamicType from './dynamicType.vue'

export default {
  name: 'dynamicForm',
  data() {
    return {
      ruleForm: {}
    }
  },
  components: {dynamicType},
  mounted() {
    // this.init();
  },
  props: {
    /**
     * 类型
     */
    formCode: {
      type: String,
      required: ''
    },
    diasicOptions: {
      type: Object,
      required: {}
    },
    tableAttribute: {
      type: Object
    },
    formList: {
      type: Array,
    },
  },
  methods: {
    searchBtn() {
      this.$api.http(this.tableAttribute.urlType, this.tableAttribute.url, null, this.ruleForm).then(res => {
        this.$emit('searchFun', res.list)
        // this.formList = res.data;
      })
    },

    init() {
    }
  }
}
</script>
<style lang="less" scoped>
.el-date-editor.el-input {
  width: 100%;
}
</style>
