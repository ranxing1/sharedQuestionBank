<template>
  <el-main>
    <div id="div">
      <h1>登录你的账号</h1>
      <h4>如果你没有账号, 点击
        <el-button link id="register" @click="register">注册</el-button>
      </h4>
      <el-form label-width="70px" :model="form" :rules="rules" ref="form">
        <el-form-item label="账号" prop="userName">
          <el-input v-model="form.userName"/>
        </el-form-item>
        <el-form-item label="密码" prop="userPwd">
          <el-input v-model="form.userPwd" type="password" autocomplete="off" show-password/>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100px;" type="primary" @click="login('form')">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-main>
</template>

<script>

import axios from "axios";

export default {
  name: "LoginPage",
  data() {
    const validator_id = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('输入账号'));
      } else {
        callback();
      }
    };
    const validator_password = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('输入密码'));
      } else {
        callback();
      }
    };
    return {
      user_id: 0,
      form: {
        userName: "",
        userPwd: ""
      },
      rules: {
        userName: [{validator: validator_id, trigger: 'blur'}],
        userPwd: [{validator: validator_password, trigger: 'blur'}]
      }
    }
  },
  methods: {
    register() {
      this.$emit('changePage', 'RegisterPage');
    },
    login(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          axios.post('user/login', {
            userName: this.form.userName,
            userPwd: this.form.userPwd
          }).then(response => {
            console.log(response);
            if (response.data.code === 1) {
              this.user_id = response.data.data.userId;
              console.log(this.user_id)
              this.$emit('loginSuccess', this.user_id);
              this.$emit('changePage', 'UserPage');
            }
            else if(response.data.code === 0){
              console.log("用户注销");
            }
          }).catch(err => {
            console.log(err);
          })
        } else {
          return false;
        }
      })
    }
  }
}
</script>

<style scoped>
#div {
  width: 300px;
  margin: 0 auto;
  padding: 10px 30px 30px 30px;
  box-shadow: var(--el-box-shadow);
}

h4 {
  font-weight: lighter;
}

#register {
  color: deepskyblue;
  font-size: large;
}
</style>