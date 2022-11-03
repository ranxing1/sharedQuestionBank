<template>


  <div>
    <input type="text" placeholder="答案描述" ref="myAns">
    <el-button
        @click="putAnswer()"
    >
      回答问题
    </el-button>
  </div>
  <div>
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
              @click="answerLike(scope.row)"
              type="text" size="small">点赞</el-button>
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
  name: "AnswerPage",
  data() {

    return {
      answer: [],
      total:0,
      currentPage: 1,
      pageSize: 10
    }
  },
  props:{
    queId: Number
  },
  methods:{
    //发布答案
    putAnswer(){
      axios
          .post("que/answer",{
            queId:this.queId,
            answerDescription:this.$refs.myAns.value
          })
      this.showAllUserInfo(this.currentPage, this.pageSize);
    },
    //喜欢回答
    answerLike(answer) {
      axios.post('answer/like', {
        answerId: answer.answerId
      }).then(response => {
        console.log(response);
      }).catch(err => {
        console.log(err);
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
        .get("answer/page", {
          params: {
            page: this.currentPage,
            pageSize: this.pageSize,
            queId:this.queId
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
      .get("answer/page", {
        params: {
          page: 1,
          pageSize: this.pageSize,
          queId:this.queId
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