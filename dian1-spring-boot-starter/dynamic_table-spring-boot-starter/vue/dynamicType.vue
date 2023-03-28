<template>
  <div>
    <!-- input输入框                            -->
    <span v-if="'input' === tableDynamic.type">
          <el-input v-model="scopeRow[tableDynamic.value]"
                    :disabled="updateStatus?false:(!modify || tableDynamic.disabled)"
                    :type="tableDynamic.typeType"
                    clearable size="medium"/>
             </span>

    <!--select下拉框                       -->
    <span v-else-if="'select' === tableDynamic.type">
                 <el-select v-if="updateStatus?true:modify"
                            v-model="scopeRow[tableDynamic.value].value"
                            :disabled="updateStatus?false:tableDynamic.disabled"
                            clearable>
                     <el-option
                         v-for="item in options[tableDynamic.value]"
                         :key="item.desc"
                         :label="item.desc"
                         :value="item.value"/>
                 </el-select>

                 <el-select
                     v-if="updateStatus?false:!modify"
                     v-model="scopeRow[tableDynamic.value].desc"
                     :disabled="tableDynamic.disabled">
                 </el-select>
             </span>

    <!--time时间                            -->
    <span v-else-if="'time' === tableDynamic.type">
                                <el-time-select
                                    v-model="scopeRow[tableDynamic.value]"
                                    :disabled="updateStatus?false:(!modify || tableDynamic.disabled)"
                                    clearable>
                                    </el-time-select>
                            </span>

    <!--date时间                            -->
    <span v-else-if="'date' === tableDynamic.type">
                                <div class="block">
                               <el-date-picker
                                   v-model="scopeRow[tableDynamic.value]"
                                   :disabled="updateStatus?false:(!modify || tableDynamic.disabled)"
                                   :picker-options="pickerOptions"
                                   align="right"
                                   clearable
                                   placeholder="选择日期"
                                   type="date">
                               </el-date-picker>
                             </div>
                            </span>

    <!--dateTime时间                            -->
    <span v-else-if="'dateTime' === tableDynamic.type">
                                <div class="block">
                                    <el-date-picker
                                        v-model="scopeRow[tableDynamic.value]"
                                        :disabled="updateStatus?false:(!modify || tableDynamic.disabled)"
                                        :picker-options="options"
                                        align="right"
                                        clearable
                                        placeholder="选择日期时间"
                                        type="datetime">
                                    </el-date-picker>
                                  </div>
                            </span>

    <!--tag 标签 用来过滤                    -->
    <span v-else-if="'tag' === tableDynamic.type"
          :disabled="updateStatus?false:(!modify || tableDynamic.disabled)"
    >
                                <p>tag标签暂不可用</p>
                            </span>
  </div>
</template>


<script>
export default {
  name: "dynamicType",
  //import引入的组件需要注入到对象中才能使用
  components: {},
  data() {
    //这里存放数据",
    return {};
  },
  //接收参数
  props: {
    /**
     * 类型
     */
    tableDynamic: {
      type: Object,
      required: true
    },
    /**
     * 是否允许修改
     */
    modify: {
      type: Boolean,
      default: true
    },
    /**
     * 是否强制允许修改
     */
    updateStatus: {
      type: Boolean,
      default: false
    },
    /**
     * 下拉框的值
     */
    options: {
      type: Object
    },
    /**
     * 真实数据其中一行
     */
    scopeRow: {
      type: Object,
      default: {}
    }
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    init() {
      if (!this.scopeRow[this.tableDynamic.value]) {
        let data = {
          "value": '',
          "desc": ''
        }
        this.scopeRow[this.tableDynamic.value] = data
        //使用强制给值 刷新
        this.$set(this.scopeRow, this.scopeRow[this.tableDynamic.value], data)
      }
    }
  },
  beforeMount() {
    this.init();
  },
}
</script>

<style scoped>

</style>
