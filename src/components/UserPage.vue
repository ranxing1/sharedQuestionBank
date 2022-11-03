<template>
  <el-main>
    <h1><span id="id">{{ user.id }}</span> 的个人空间</h1>
    <div>
      <el-row :gutter="20">
        <el-col :span="10">
          <div>
            <div class="label-div">
              <span class="label">用户名</span> {{ user.name }}
            </div>
            <div class="label-div">
              <div class="label">个性签名</div>
              <span class="signature">{{ user.signature }}</span>
            </div>
            <div class="label-div">
              <span class="label">注册时间</span> {{ user.registration_time }}
            </div>
            <el-button type="primary" @click="change_info">修改信息</el-button>
            <el-button type="primary" @click="change_pass">修改密码</el-button>
          </div>
        </el-col>
        <el-col :span="7" v-if="is_info">
          <el-form label-width="100px" :model="user" :rules="info_rules" ref="user">
            <el-form-item label="用户名" prop="name">
              <el-input v-model="user.name"/>
            </el-form-item>
            <el-form-item label="个性签名" prop="signature">
              <el-input type="textarea" :rows="3" v-model="user.signature" clearable/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="confirm_info('user')">确认修改</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="7" v-if="is_pass">
          <el-form label-width="100px" :model="password" :rules="pass_rules" ref="password">
            <el-form-item label="旧密码" prop="old">
              <el-input type="password" v-model="password.old" autocomplete="off" show-password/>
            </el-form-item>
            <el-form-item label="新密码" prop="new">
              <el-input type="password" v-model="password.new" autocomplete="off" show-password/>
            </el-form-item>
            <el-form-item label="确认新密码" prop="confirm">
              <el-input type="password" v-model="password.confirm" autocomplete="off" show-password/>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="confirm_pass('password')">确认修改</el-button>
              <el-button @click="cancel">取消</el-button>
            </el-form-item>
          </el-form>
        </el-col>
        <el-col :span="7" :offset="col_offset">
          <el-tabs v-model="tab_name">
            <el-tab-pane label="当前连续打卡时间" name="当前连续打卡时间">{{ user.old_clock_in_time }}</el-tab-pane>
            <el-tab-pane label="历史最长连续打卡时间" name="历史最长连续打卡时间">{{ user.now_clock_in_time }}</el-tab-pane>
            <el-tab-pane label="总消耗卡路里" name="总消耗卡路里">{{ user.total_consume_calories }}</el-tab-pane>
          </el-tabs>
        </el-col>
      </el-row>
    </div>
  </el-main>
</template>

<script>
import axios from "axios";

export default {
  name: "UserPage",
  methods: {
    change_info() {
      this.is_info = true;
      this.is_pass = false;
      this.col_offset = 0;
    },
    change_pass() {
      this.is_info = false;
      this.is_pass = true;
      this.col_offset = 0;
    },
    cancel() {
      this.is_info = false;
      this.is_pass = false;
      this.col_offset = 7;
    },
    confirm_info(user) {
      this.$refs[user].validate((vaild) => {
        if (vaild) {
          axios.post('api/change_info', {
            id: this.user.id,
            name: this.user.name,
            signature: this.user.signature
          }).then(response => {
            if (response.data.code === 200) {
              alert('修改成功');
              this.cancel();
            } else {
              alert(response.data.message);
            }
          }).catch(error => {
            console.log(error);
          })
        } else {
          return false;
        }
      })
    },
    confirm_pass(password) {
      this.$refs[password].validate((vaild) => {
        if (vaild) {
          axios.post('api/change_password', {
            id: this.user.id,
            old: this.password.old,
            new: this.password.new
          }).then(response => {
            console.log(response);
            if (response.data.code === 200) {
              alert('修改成功');
              this.cancel();
            } else {
              alert(response.data.message);
            }
          }).catch(error => {
            console.log(error);
          })
        } else {
          return false;
        }
      })
    }
  },
  data() {
    const validator_name = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('输入用户名'))
      } else if (value.length > 10) {
        callback(new Error('长度在10个字符以内'))
      } else {
        callback()
      }
    };
    const validator_signature = (rule, value, callback) => {
      if (value.length > 160) {
        callback(new Error('长度在160个字符以内'))
      } else {
        callback()
      }
    };
    const validator_old = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('输入旧密码'))
      } else if (value.length < 10 || value.length > 16) {
        callback(new Error('长度在10~16个字符'))
      } else {
        callback()
      }
    };
    const validator_new = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('输入新密码'))
      } else if (value.length < 10 || value.length > 16) {
        callback(new Error('长度在10~16个字符'))
      } else {
        callback()
      }
    };
    const validator_confirm = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('再次输入新密码'))
      } else if (value !== this.password.new) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    };
    return {
      user: {
        id: "",
        name: "",
        signature: "",
        registration_time: "yyyy-MM-dd",
        old_clock_in_time: 0,
        now_clock_in_time: 0,
        total_consume_calories: 0
      },
      info_rules: {
        name: [{validator: validator_name, trigger: "blur"}],
        signature: [{validator: validator_signature, trigger: "blur"}]
      },
      pass_rules: {
        old: [{validator: validator_old, trigger: "blur"}],
        new: [{validator: validator_new, trigger: "blur"}],
        confirm: [{validator: validator_confirm, trigger: "blur"}]
      },
      password: {
        old: "",
        new: "",
        confirm: ""
      },
      tab_name: "当前连续打卡时间",
      is_info: false,
      is_pass: false,
      col_offset: 7
    }
  },
  props: {
    user_id: String
  },
  created() {
    axios.get('api/user/' + this.user_id)
        .then(response => {
          if (response.data.code === 200) {
            this.user.id = response.data.data.user_id;
            this.user.name = response.data.data.user_name;
            this.user.signature = response.data.data.signature;
            this.user.registration_time = response.data.data.registration_time.slice(0, 10);
            this.user.now_clock_in_time = response.data.data.now_clock_in_time;
            this.user.old_clock_in_time = response.data.data.old_clock_in_time;
            this.user.total_consume_calories = response.data.data.total_consume_calories;
          } else {
            alert(response.data.message);
          }
        })
        .catch(error => {
          console.log(error);
        });
  }
}
</script>

<style scoped>
#id {
  font-size: xxx-large;
  color: lightskyblue;
  font-family: "Comic Sans MS", 华文新魏, serif;
}

.label-div {
  padding-bottom: 20px;
}

.label {
  font-size: large;
  font-weight: bold;
}

.signature {
  margin-top: 10px;
  width: 500px;
  display: inline-block;
  border: 1px solid var(--el-border-color);
  border-radius: 4px;
}
</style>