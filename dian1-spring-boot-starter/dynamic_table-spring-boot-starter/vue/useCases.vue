<template>
  <div>
    <!-- Table -->
    <el-button type="text" @click="dialogTableVisible = true">打开指定的动态表格</el-button>
    <el-dialog :visible.sync="dialogTableVisible" title="我是父组件的名称">
      <DynamicTable :height=600
                    :modify="true" :width=600
                    table_code="storage"
                    @cancel="cancel"
                    @confirm="confirm"></DynamicTable>
    </el-dialog>

  </div>
</template>
<script>

//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import <组件名称> from '《组件路径》'

import DynamicTable from "@/views/assembly/dynamicTable";

export default {
  //import引入的组件需要注入到对象中才能使用
  components: {DynamicTable},
  data() {
    //这里存放数据",
    return {
      dialogTableVisible: false,
      data: [],
      tableAttributes: [],
      tableDynamic: [],
    };
  },
  //方法集合
  methods: {
    confirm(data) {
      console.log("我点了下确认：", data)
    },
    cancel(data) {
      console.log("我点了下取消", data)
    },

  },
}
</script>

<style scoped>

</style>
