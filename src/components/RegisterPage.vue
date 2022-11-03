<template>
  <el-main>
    <div id="div">
      <h1>注册你的账号</h1>
      <h4>如果你已经有账号, 点击
        <el-button link id="login" @click="login">登录</el-button>
      </h4>
      <el-form label-width="70px" :model="form" :rules="rules" ref="form">
        <el-form-item label="账号" prop="userName">
          <el-input v-model="form.userName"/>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.userPwd" type="password" autocomplete="off" show-password/>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100px;" type="primary" @click="register('form')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-main>
</template>

<script>
import axios from "axios";

export default {
  name: "RegisterPage",
  data() {

    const validator_nick_name = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('输入用户名'))
      } else if (value.length > 10) {
        callback(new Error('长度在10个字符以内'))
      } else {
        callback()
      }
    };
    const validator_password = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('输入密码'))
      } else if (value.length < 10 || value.length > 16) {
        callback(new Error('长度在10~16个字符'))
      } else {
        callback()
      }
    };
    return {
      user_id:0,
      form: {
        userName: "",
        userPwd: "",
      },
      rules: {
        userName: [{validator: validator_nick_name, trigger: "blur"}],
        userPwd: [{validator: validator_password, trigger: "blur"}],
      }
    }
  },
  methods: {
    //登录
    login() {
      this.$emit('changePage', 'LoginPage');
    },
    //注册
    register(form) {
      this.$refs[form].validate((valid) => {
        if (valid) {
          axios.post('user/register', {
            userName: this.form.userName,
            userPwd: this.form.userPwd
          }).then(response => {
            console.log(response);

            if (response.data.code === 1) {
              this.user_id = response.data.data.userId;
              this.$emit('loginSuccess', this.user_id);
              this.$emit('changePage', 'UserPage');
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

#login {
  color: deepskyblue;
  font-size: large;
}
</style>