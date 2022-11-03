<template>
  <div>
    <div>
      <input type="text" placeholder="题目" ref="myName">
    </div>
    <el-table
        :data="answer"
        border
        style="width: 100%; height: 450px"
    >
      <el-table-column prop="answerId"  width="2" v-if="false">
      </el-table-column>
      <el-table-column prop="answerDescription" label="答案描述" width="1000">
      </el-table-column>
      <el-table-column prop="userName" label="回答者" width="100">
      </el-table-column>
      <el-table-column prop="answerLike" label="答案点赞" width="100">
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="400">
        <template v-slot="scope">
          <el-button
              @click="ansDel(scope.row)"
              type="text" size="small">删除</el-button>
          <el-button
              @click="updateAns(scope.row)"
              type="text" size="small">修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--    分页组件-->
    <div style="text-align: left">
      <el-row>
        <el-col :span="12">
          <el-pagination
              style="margin: 15px 0px"
              background
              layout="prev, pager, next, jumper, total, sizes"
              :current-page="currentPage"
              :page-sizes="[2, 4, 6, 8,10]"
              @current-change="findPage"
              @size-change="findSize"
              :total="total"
          >
          </el-pagination>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "MyAnsPage",
  data() {

    return {
      answer: [],
      total:0,
      currentPage: 1,
      pageSize: 10
    }
  },
  props:{
    queId: Number,
    user_id:Number
  },
  methods:{
    //删除答案
    ansDel(answer){
      axios
        .post("answer/delete",{
          answerId: answer.answerId
        })
      this.showAllUserInfo(this.currentPage, this.pageSize);
    },
    //修改答案
    updateAns(answer){
      axios
          .post("answer/update",{
            answerId: answer.answerId,
            answerDescription:this.$refs.myName.value
          })
      this.showAllUserInfo(this.currentPage, this.pageSize);
    },
    // 第n页信息
    findPage(now_page) {
      //用来处理当前页的方法,page为当前页
      this.currentPage = now_page;
      this.showAllUserInfo(now_page, this.pageSize);
    },
    findSize(now_size) {
      //用来处理每页条数的方法,size为当前页条数
      this.pageSize = now_size;
      this.showAllUserInfo(this.now, now_size);
    },

    // 展示所有的用户信息
    showAllUserInfo(currentPage, pageSize) {
      //异步请求显示所有数据
      currentPage = currentPage ? currentPage : this.currentPage;
      // eslint-disable-next-line no-unused-vars
      pageSize = pageSize ? pageSize : this.pageSize;

      axios
          .get("answer/page3", {
            params: {
              page: this.currentPage,
              pageSize: this.pageSize,
              userId:this.user_id
            }
          })

          .then((res) => {
            console.log(res);
            this.answer = res.data.data.records;
            this.total = res.data.data.total;
          });
    },
  },

  created() {
    console.log("解答页面：",this.queId)
    axios
        .get("answer/page3", {
          params: {
            page: 1,
            pageSize: this.pageSize,
            userId:this.user_id
          }
        })

        .then((res) => {
          console.log(res);
          this.answer = res.data.data.records;
          this.total = res.data.data.total;
        });
  },
}
</script>

<style scoped>

</style>