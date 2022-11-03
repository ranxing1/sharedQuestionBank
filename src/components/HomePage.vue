<template>
  <el-main>
    <div style="text-align: center; height: 300px; position: relative">
      <div style="height: 300px; position: absolute; left: 0; right: 0; bottom: 0">
        <span id="welcome">Welcome to</span>
        <el-image style="width: 300px; height: 300px; vertical-align: middle" :src="require('../assets/logo.png')"
                  :fit="'fill'"/>
        <span id="title">Q&A</span>
      </div>
    </div>
    <div style="text-align: center; height: 200px; padding-top: 100px">
      <span id="introduction">有问题，就会有答案</span>
    </div>
    <div>
      <el-button
          @click="delUser()"
      >
        注销帐号
      </el-button>
    </div>
  </el-main>
</template>

<script>
import axios from "axios";
export default {
  props:{
    user_id : Number
  },
  name: "HomePage",
  methods:{
    delUser(){
      axios
        .post("user/delete",{
          userId : this.user_id
        })
    }
  }
}

</script>

<style scoped>
#welcome {
  height: 60px;
  line-height: 60px;
  color: deepskyblue;
  font-family: 'sans-serif';
  font-style: italic;
  font-size: xx-large;
  font-weight: bold
}

#title {
  height: 60px;
  line-height: 60px;
  color: red;
  font-family: 'Comic Sans MS', serif;
  font-style: italic;
  font-size: xxx-large;
  font-weight: bold
}

#introduction {
  height: 60px;
  line-height: 60px;
  color: black;
  font-family: '华文行楷', serif;
  font-style: normal;
  font-size: xx-large;
  font-weight: lighter;
}
</style>