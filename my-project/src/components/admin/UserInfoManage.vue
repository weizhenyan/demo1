<!-- 用户信息管理 -->
import { readFile } from 'fs';
import { log } from 'util';
<template>
  <div>
    <div class="head-con">
      <div class="head-con-left">
      </div>
      <div class="head-con-right">
         <strong>用户名：</strong>
      <input type="text" name="" id="username" placeholder="用户名" v-model="username" class="input-with-select"/>
          <strong>性别：</strong>
          <select v-model="sex">
            <option>1</option>
            <option>0</option>
          </select>
          <button @click="btn" class="btn btn-default">搜索</button> <!--@click="btn"这部分为vue语法-->
          <button @click="btn_cl" class="btn btn-default">清空</button> <!--@click="btn"这部分为vue语法-->
      </div>
    </div>
      <el-table
        ref="multipleTable"
        :data="searchData"
        border
        @selection-change="handleSelectionChange"
        :default-sort = "{prop: 'created', order: 'descending'}"
        style="width: 100%">
        <el-table-column
        type="selection"
        width="55">
        </el-table-column>
        <el-table-column
        type="index"
        width="50">
        </el-table-column>
        <el-table-column
        prop="id"
        label="用户名称">
        <template slot-scope="scope">
            <router-link :to="{name:'UserDetail',params:{id:scope.row.id}}">
                {{scope.row.username}}
            </router-link>
        </template>
        </el-table-column>
        <el-table-column prop="sex" label="性别" :formatter="sexFormate"/>
        <el-table-column prop="age" label="年龄"/>
        <el-table-column prop="phone" label="电话"/>
        <el-table-column prop="email" label="邮箱"/>
        <el-table-column prop="avatar" label="头像">
          <template  slot-scope="scope">
            <img :src="scope.row.avatar" min-width="70" height="70"/>
          </template>
        </el-table-column>
        <el-table-column
        prop="created"
        label="创建日期"
        sortable
        width="180">
        </el-table-column>
        <el-table-column
        label="操作列"
        width="300">
        <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="editBlog(scope.row.id)">编辑</el-button>
            <el-button size="mini" type="danger">删除</el-button>
            <el-button size="mini" type="info">解绑</el-button>
            <el-button size="mini" type="warning">锁定</el-button>
        </template>
        </el-table-column>
    </el-table>
    <el-pagination
    background
    layout="prev, pager, next"
    :current-page="currentPage"
    :page-size="pagesize"
    :total="total"
    @current-change=page
    >
</el-pagination>
  </div>
</template>

<script>
export default {
  data () {
    return {
      username:'',
      sex:'',
      searchData: [],
      products:[
      ],
      currentPage: 1,
      total: 0,
      pagesize: 10
    }
  },
  computed: {
      // 计算数学，匹配搜索
      filteredArticles: function () {
          var articles_array = this.articles,
              searchString = this.searchString;

          if(!searchString){
              return articles_array;
          }

          searchString = searchString.trim().toLowerCase();

          articles_array = articles_array.filter(function(item){
              if(item.title.toLowerCase().indexOf(searchString) !== -1){
                  return item;
              }
          })
          // 返回过来后的数组
          return articles_array;
      }
  },
  components: {},

  mounted: {},

  methods: {
    sexFormate:function(row,index){
      if(row.sex==1) {
        return "女";
      }else if(row.sex==0) {
        return "男";
      }
    },
    btn_cl:function(){
      this.username = '';//用户名
      this.sex = '';//性别
    },
    btn:function(){
      var username = this.username;//用户名
      var sex = this.sex;//性别
      console.log(username)
      if( username != '' || sex != '' ) {
        console.log("带参搜索")
        const _this = this
        _this.$axios.get("/user/getUserByParam",{
          headers: {
            "Authorization":localStorage.getItem("token")
          },
          params:{
            username:username,
            sex:sex,
            currentPage:_this.currentPage,
            pageSize:_this.pagesize
          }
        })
        .then(res=> {
          console.log("====================2")
          console.log(res.data.data)
          _this.products = res.data.data
          _this.currentPage = parseInt(res.data.data.current)
          _this.total = parseInt(res.data.data.total)
          _this.pagesize = parseInt(res.data.data.size)
            if(_this.products[0]==null ) {alert("没有对应的数据")}
            if (true) {
              _this.searchData = _this.products.filter(function (product) {
                return Object.keys(product).some(function (key) {
                  return String(product[key]).toLowerCase().indexOf(username) > -1
                })
              })
            }
        }).catch(function (err) {
            console.log(err);
          })
      }else {
        this.page(1);
      }
    },
    handleSelectionChange(val) {
        this.multipleSelection = val;
    },
    page(currentPage) {
      const _this = this
      console.log("=================================")
      _this.$axios.get("/user/getUserList?currentPage="+currentPage,{
        headers: {
           "Authorization":localStorage.getItem("token")
        }
      })
      .then(res=> {
        console.log("============================"+currentPage)
          _this.products = res.data.data.records
          // if (true) {
          //   _this.searchData = _this.products.filter(function (product) {
          //     return Object.keys(product).some(function (key) {
          //       return String(product[key]).toLowerCase().indexOf(username) > -1
          //     })
          //   })
          // }
        _this.searchData=_this.products
        _this.currentPage = parseInt(res.data.data.current)
        _this.total = parseInt(res.data.data.total)
        _this.pagesize = parseInt(res.data.data.size)
      })
    },
  },
  created() {
     //登录才能查看
      if(this.$store.getters.getUser) {
          this.page(1)
      }
   }
}
</script>
<style scoped>
  .head-con {
      background-color:  rgb(39, 122, 189);
      height: 50px;
      line-height: 50px;
  }
 .head-con-left {
      /* background-color: rgb(196, 47, 47); */
      height: 50px;
      line-height: 50px;
      float: left;
      width: 60%;
  }
  .head-con-right {
      background-color: rgb(39, 122, 189);
      height: 50px;
      line-height: 50px;
  }
</style>
