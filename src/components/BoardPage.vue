<template>
  <el-main>
    <el-tabs v-model="tab_name">
      <el-tab-pane label="最长连续打卡时间" name="最长连续打卡时间">
        <el-table class="el-table" :data="time">
          <el-table-column width="200" prop="user_name" label="用户名"/>
          <el-table-column prop="signature" label="个性签名"/>
          <el-table-column width="200" prop="old_clock_in_time" label="打卡天数/天"/>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="总消耗卡路里" name="总消耗卡路里">
        <el-table class="el-table" :data="calories">
          <el-table-column width="200" prop="user_name" label="用户名"/>
          <el-table-column prop="signature" label="个性签名"/>
          <el-table-column width="200" prop="total_consume_calories" label="总消耗卡路里数/千卡"/>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </el-main>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardPage",
  data() {
    return {
      tab_name: "最长连续打卡时间",
      time: [],
      calories: []
    }
  },
  created() {
    axios.get('api/get_calories')
        .then(response => {
          if (response.data.code === 200) {
            this.calories = response.data.data;
          }
        })
        .catch(error => {
          console.log(error);
        });
    axios.get('api/get_clock_in_time')
        .then(response => {
          if (response.data.code === 200) {
            this.time = response.data.data;
          }
        })
        .catch(error => {
          console.log(error);
        });
  }
}
</script>

<style scoped>
.el-table {
  margin: 0 auto;
}
</style>