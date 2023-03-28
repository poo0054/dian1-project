<template>
  <div>
    <el-card v-for="tableAttribute in tableAttributes"
             id="asd"
             :style="{'margin-left': '10px' ,'margin-right': '10px','height':height+'px','width':width+'px'}">
      <div slot="header" class="clearfix">
        <span>{{ tableAttribute.name }}</span>
        <el-button v-if="modify" class="button" @click="batchDeletion">删除选中</el-button>
        <span class="button" style="width: 10px"> &nbsp</span>
        <el-button
            v-if="modify"
            class="button"
            @click="handleAdd">添加行
        </el-button>
      </div>
      <dynamic-form
          :diasicOptions="options"
          :formCode="table_code"
          :formList="tableDynamics"
          :tableAttribute="tableAttribute"
          @searchFun="searchFun"
      ></dynamic-form>

      <!-- <el-button @click="$testFun['tableRowClassName']">1234</el-button> -->

      <div>
        <el-table
            ref="tableData"
            :border="tableAttribute.border"
            :data="tableDate"
            :height="(tableAttribute.height?tableAttribute.height:height) +'px'"
            :stripe="tableAttribute.stripe"
            @selection-change="batchSelect">
          <el-table-column v-if="modify"
                           fixed="left"
                           type="selection"/>

          <el-table-column v-for="tableDynamic in tableDynamics"
                           :align="tableDynamic.align"
                           :class-name="tableDynamic.className"
                           :filter-method="tableDynamic.filters?filterHandler:null"
                           :filters="tableDynamic.filters?('select'===tableDynamic.filters?filterOptions[tableDynamic.value]:JSON.parse(tableDynamic.filters)):null"
                           :label="tableDynamic.name"
                           :property="tableDynamic.value"
                           :sortable="tableDynamic.sortable"
                           :type="modify?tableDynamic.type:''"
                           :width="tableDynamic.width+'px'">
            <template slot-scope="scope">
              <div>
                <dynamicType :modify="modify" :options="options" :scopeRow="scope.row"
                             :tableDynamic="tableDynamic">
                </dynamicType>
              </div>
            </template>
          </el-table-column>

          <el-table-column v-if="modify"
                           align="center"
                           fixed="right"
                           label="操作"
                           width="100">
            <template slot-scope="scope">
              <el-button size="small" type="text" @click="handleDelete(scope.$index, scope.row)">删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div>
        <el-button class="button" type="primary" @click="confirm">确定
        </el-button>
        <span class="button" style="width: 10px"> &nbsp</span>
        <el-button class="button" @click="cancel">取消</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>

//例如：import <组件名称> from '《组件路径》'
import {GET} from "@/utils/commonFinal";
import dynamicForm from './dynamicForm.vue'
import dynamicType from './dynamicType.vue'

export default {
  name: "dynamicTable",
  //import引入的组件需要注入到对象中才能使用
  components: {dynamicForm, dynamicType},
  data() {
    //这里存放数据",
    return {
      selectIndexs: [],
      tableDate: [],
      tableAttributes: [],
      tableDynamics: [],
      //用来新增的数据
      copyRow: {},
      options: {},
      filterOptions: {},
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now();
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date());
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24);
            picker.$emit('pick', date);
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date();
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
            picker.$emit('pick', date);
          }
        }]
      },
    };
  },
  //接收参数
  props: {
    /**
     * 类型
     */
    table_code: {
      type: String,
      required: true
    },
    /**
     * 是否允许修改
     */
    modify: {
      type: Boolean,
      default: true
    },
    height: {
      type: Number,
    },
    width: {
      type: Number,
    }
  },
  //监听属性 类似于data概念
  computed: {},
  //监控data中的数据变化
  watch: {},
  //方法集合
  methods: {
    init() {
      this.$api.http(GET, 'http://192.168.0.127:8100/tableAttribute/tableCode/' + this.table_code).then(res => {
        let optionalParams = res.data;
        this.tableAttributes = optionalParams;

        //获取真实数据
        if (optionalParams) {
          let tableAttribute = optionalParams[0];
          this.$api.http(tableAttribute.urlType, tableAttribute.url).then(res => {
            if (res.list) {
              this.tableDate = res.list;
              let listElement = res.list[0];
              //需要使用深拷贝
              Object.assign(this.copyRow, listElement);
            }

          })
        }
      });

      this.$api.http(GET, 'http://192.168.0.127:8100/tableDynamic/tableCode/' + this.table_code).then(res => {
        let data = res.data;
        data.sort((tableCode1, tableCode2) => (tableCode1.sort - tableCode2.sort));
        this.tableDynamics = data;
        //运行修改 需要把一些下拉框数据提前加载
        if (this.modify) {
          for (let i = 0; i < data.length; i++) {
            let datum = data[i];
            if (datum.type && "select" === datum.type && datum.selectUrl) {
              let selectUrl = datum.selectUrl;
              this.$api.get(selectUrl).then(res => {
                let dataList = res.map(item => {
                  return {
                    'value': item.value,
                    'desc': item.desc,
                  }
                })
                //条件过滤的值
                this.filterOptions[datum.value] = res.map(item => {
                  return {
                    'value': item.value,
                    'text': item.desc,
                  }
                });
                this.options[datum.value] = dataList;
                //使用强制给值 刷新
                this.$set(this.options, this.options[datum.value], dataList)
              })
            }
          }
        }
      });
      this.$refs.tableData.doLayout()
    },

    /**
     * 过滤
     */
    filterHandler(value, row, column) {
      const property = column['property'];
      let rowElement = row[property];
      if (rowElement instanceof Object) {
        //TODO 数组方法 待扩展
        if (rowElement instanceof Array) {
          return rowElement[0] === value;
        }
        //对象
        return rowElement.value === value;
      }
      return rowElement === value;
    },

    /**
     * 选中
     * @param rows
     */
    batchSelect(rows) {
      // 用一个变量来存放被选中的index
      this.selectIndexs = rows;
    },

    /**
     * 批量删除
     */
    batchDeletion() {
      this.$confirm('是否删除选中数据', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.selectIndexs.forEach((item) => {
          for (let i = 0; i < this.tableDate.length; i++) {
            if (this.tableDate[i].id === item.id) {
              this.tableDate.splice(i, 1);
              break;
            }
          }
        });
        this.selectIndexs = [];
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    /**
     * 新增行
     */
    handleAdd() {
      const newObj = {};
      //需要使用深拷贝
      Object.assign(newObj, this.copyRow);
      this.tableDate.push(newObj);
    },
    /**
     *  删除
     * @param index
     * @param row
     */
    handleDelete(index, row) {
      this.$confirm('是否删除', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.tableDate.splice(index, 1)
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },

    /**
     *确认事件
     */
    confirm() {
      this.$emit('confirm', this.tableDate);
    },
    /**
     * 取消事件
     */
    cancel() {
      this.$emit('cancel', this.tableDate);
    },
    /**
     * 搜索后的值
     * @param data
     */
    searchFun(data) {
      this.tableDate = data;
    }
  },
//生命周期 - 挂载完成（可以访问DOM元素）
  mounted() {

    this.init();
  }
}
</script>

<style scoped>
.button {
  float: right;
}
</style>
