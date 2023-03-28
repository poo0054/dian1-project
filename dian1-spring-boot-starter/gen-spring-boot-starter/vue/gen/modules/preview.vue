<template>
  <el-dialog :visible.sync="show">
    <el-tabs v-model="model">
      <el-tab-pane
          v-for="(item, key) in res"
          :key="key"
          :label="key"
          :name="key"
          lazy
      >
        <p style="white-space: pre-wrap;">{{ item }}>
        </p>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script>
export default {
  name: 'preview',
  data() {
    return {
      headTitle: [],
      show: false,
      model: '',
      res: {}
    }
  },
  methods: {
    showDialog(data) {
      // this.form = this.$options.data().form
      if (data) {
        // Object.assign(this.form, data)
        this.$api.get(`gen/preview/${data.id}`).then(res => {
          this.res = res
          this.headTitle = Object.keys(res)
          this.$forceUpdate()
        })
      }
      this.show = true
    }
  }
}
</script>

<style scoped>

</style>
