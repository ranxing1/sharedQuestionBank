<template>
  <div class="common-layout">
    <el-container>
      <el-header style="height: 70px">
        <el-menu :default-active="'0'" mode="horizontal" :ellipsis=false @select="menu_select">
          <el-image style="width: 60px; height: 60px" :src="require('./assets/logo.png')" :fit="'fill'"/>
          <span id="title">Que And Ans</span>
          <div style="flex-grow: 1"></div>
          <el-menu-item index="0">主页</el-menu-item>
          <el-menu-item index="2" :disabled="!login_status">提问=</el-menu-item>
          <el-menu-item index="5" :disabled="!login_status">解答=</el-menu-item>
          <el-menu-item index="1" :disabled="!login_status">我的提问</el-menu-item>
          <el-menu-item index="3" :disabled="!login_status">我的回答</el-menu-item>
          <el-menu-item index="4" :disabled="login_status">登录/注册</el-menu-item>
        </el-menu>
      </el-header>

      <component :is="page_type" @changePage="change_page" @loginSuccess="login_success" @setQueId="setQueId" :user_id="user_id" :queId="queId"/>

    </el-container>
  </div>
</template>


<script>
import HomePage from "@/components/HomePage";
import LoginPage from "@/components/LoginPage";
import RegisterPage from "@/components/RegisterPage";
import QuePage from "@/components/QuePage";
import AnswerPage from "@/components/AnswerPage";
import MyAnsPage from "@/components/MyAnsPage";
import MyQuePage from "@/components/MyQuePage";
// import BoardPage from "@/components/BoardPage";


export default {
  name: 'App',
  data() {
    return {
      page_type: "HomePage",
      login_status: false,
      user_id: "",
      queId:""
    }
  },
  components: {
    HomePage,
    QuePage,
    AnswerPage,
    MyAnsPage,
    MyQuePage,
    LoginPage,
    RegisterPage
  },
  methods: {
    menu_select(index) {
      switch (index) {
        case '0':
          this.page_type = "HomePage";
          break;
        case '1':
          this.page_type = "MyQuePage";
          break;
        case '2':
          this.page_type = "QuePage";
          break;
        case '3':
          this.page_type = "MyAnsPage";
          break;
        case '4':
          this.page_type = "LoginPage";
          break;
        case '5':
          this.page_type = "AnswerPage";
          break;
        default:
          break;
      }
    },
    //换页
    change_page(page) {
      this.page_type = page;
    },
    //成功登录
    login_success(user_id) {
      this.login_status = true;
      this.user_id = user_id;
    },
    //保存问题id
    setQueId(queId){
      this.queId = queId;
    }
  },
  watch: {
    login_status: {
      immediate: true,
      handler(new_status) {
        console.log("当前登录状态 + " + new_status);
        if (new_status) {
          console.log("登录的用户是 " + this.userName);
        }
      }
    }
  }
}
</script>

<style>
#title {
  height: 60px;
  line-height: 60px;
  color: red;
  font-family: 'Comic Sans MS', serif;
  font-style: italic;
  font-size: x-large;
  font-weight: bold
}
</style>
