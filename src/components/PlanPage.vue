<template>
  <el-main>
    <el-tabs class="el-tabs" v-model="edit_tab_value" type="border-card" editable @edit="tab_edit">
      <el-tab-pane v-for="(item,index) in edit_tab" :key="index" :label="item.name" :name="index">
        {{ item.content }}
      </el-tab-pane>
    </el-tabs>
  </el-main>
</template>

<script>
export default {
  name: "PlanPage",
  data() {
    return {
      edit_tab_value: 0,
      edit_tab: [
        {
          id: 0,
          name: "默认计划",
          content: "Tab content",
        }
      ],
    }
  },
  methods: {
    tab_edit(target_index, action) {
      if (action === "add") {
        this.edit_tab.push({
          name: "默认计划",
          content: "New Tab content",
        })
        this.edit_tab_value++
      }
      if (action === 'remove') {
        if (this.edit_tab_value >= target_index) {
          this.edit_tab_value--;
        }
        this.edit_tab.splice(target_index, 1);
      }
    }
  }
}
</script>

<style scoped>
.el-tabs {
  height: calc(100vh - 130px);
  overflow-y: auto;
}

/*.el-tabs__content {*/
/*  height: calc(100vh - 130px);*/
/*  overflow-y: auto;*/
/*}*/
</style>