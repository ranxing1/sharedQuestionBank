<template>
  <el-main>
    <!--    <el-tabs v-model="queAndAns">-->
    <!--      <el-tab-pane label="题目" name="题目">-->
    <div>
      </div>
      <el-table
          :data="question"
          border
          style="width: 100%; height: 450px"
      >
        <el-table-column prop="queId"  width="2" v-if="false">
        </el-table-column>
        <el-table-column prop="queName" label="题目名称" width="100">
        </el-table-column>
        <el-table-column prop="queDescription"  label="题目描述" width="1000">
        </el-table-column>
        <el-table-column prop="categoryName" label="种类" width="150">
        </el-table-column>
        <el-table-column prop="userName" label="提出者" width="100">
        </el-table-column>
        <el-table-column prop="queLike" label="题目点赞" width="100">
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="400">
          <template v-slot="scope">
            <el-button
                @click="handleClick(scope.row)"
                type="text"
                size="small"
            >查看</el-button>
            <el-button
                @click="queDel(scope.row)"
                type="text"
                size="small"
            >删除</el-button>
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

  </el-main>
</template>

<script>
import axios from "axios";
export default {
  name: "MyQuePage",
  props:{
    user_id:Number
  },

  data() {
    return {
      question: [],
      total:0,
      currentPage: 1,
      pageSize: 10
    }
  },
  methods: {
    //查看问题
    handleClick(question) {
      console.log(question.queId)
      this.$emit('setQueId',question.queId)
    },
    //删除问题
    queDel(question){
      axios
          .post("que/delete",{
            queId: question.queId
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
          .get("que/page3", {
            params: {
              page: this.currentPage,
              pageSize: this.pageSize,
              userId:this.user_id
            }
          })

          .then((res) => {
            console.log(res);
            this.question = res.data.data.records;
            this.total = res.data.data.total;
          });
    },
  },
  created() {
    axios
        .get("que/page3", {
          params: {
            page: 1,
            pageSize: this.pageSize,
            userId:this.user_id
          }
        })

        .then((res) => {
          console.log(res);
          this.question = res.data.data.records;
          this.total = res.data.data.total;
        });
  },

}
</script>

<style scoped>

</style>