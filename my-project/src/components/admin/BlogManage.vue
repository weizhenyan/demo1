<!-- 博客信息管理 -->
<template>
  <div>
    <el-row type="flex" class="row-bg">
        <div class="head-con">
            <div class="head-con-left">
              <el-select
                  v-model="value"
                  multiple
                  filterable
                  remote
                  reserve-keyword
                  placeholder="请输入关键词"
                  :remote-method="remoteMethod"
                  class="search"
                  :loading="loading">
                  <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                  </el-option>
              </el-select>
            </div>
            <div class="head-con-right">
                  <el-button type="primary" size="mini" @click= "exportExcel" icon="el-icon-download">导出数据</el-button>
            </div>
        </div>
        <el-col :span="6"><div class="grid-content bg-purple-light">
            <el-table
                ref="multipleTable"
                :data="blogDatas"
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
                label="博客名称">
                <template slot-scope="scope">
                    <!-- <router-link v-bind:to="'/blog/'+scope.row.id"> -->
                    <router-link :to="{name:'BlogDetail',params:{blogId:scope.row.id}}">
                        {{scope.row.title}}
                    </router-link>
                </template>
                </el-table-column>

                <el-table-column
                prop="created"
                label="创建日期"
                sortable
                width="180">
                </el-table-column>
                <el-table-column
                prop="username"
                label="创建人"
                sortable
                width="180">
                </el-table-column>
                <el-table-column
                label="操作列"
                width="160">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" @click="editBlog(scope.row.id)">编辑</el-button>
                    <el-button size="mini" type="danger">删除</el-button>
                </template>
                </el-table-column>
            </el-table>
        </div></el-col>
        <el-pagination
            background
            layout="prev, pager, next"
            :current-page="currentPage"
            :page-size="pagesize"
            :total="total"
            @current-change=page
            >
        </el-pagination>
    </el-row>
  </div>
</template>

<script>
export default {
  data () {
         // 邮箱校验规则
     const checkEmail = (rule, value, callback) =>{
      let emailReg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;            
      if (emailReg.test(value) || value == '') {
            return callback();                
      } else {
            callback(new Error("邮箱格式错误"));
          }
    };
	   return {
        blogDatas: [],
        currentPage: 1,
        total: 0,
        pagesize: 5,
        multipleSelection: [],
        options: [],
        value: [],
        list: [],
        loading: false,
     } 
  },
  mounted() {
    this.list = this.blogDatas.map(item => {
      return { value: `value:${item.title}`, label: `label:${item.title}` };
    });
  },
  methods:{
    submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            alert('submit!');
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      handleOpen(key, keyPath) {
        console.log(key, keyPath);
      },
      handleClose(key, keyPath) {
        console.log(key, keyPath);
      },
      reloadRouter(path) {
        this.$router.replace({
          path:"/redirect",
          query:{
            nextPath:path
          }
        })
      },
      page(currentPage) {
      const _this = this
      _this.$axios.get("/list?currentPage="+currentPage).then(res=> {
        console.log("============================"+currentPage)
         _this.blogDatas = res.data.data.records
        _this.currentPage = parseInt(res.data.data.current)
        _this.total = parseInt(res.data.data.total)
        _this.pagesize = parseInt(res.data.data.size)
      })
    },
    handleSelectionChange(val) {
        this.multipleSelection = val;
    },
    exportExcel() {
            ///blog/exportExcel
        console.log("ooooooooooo")
        const _this = this
        _this.$axios.post("/blog/exportExcel",this.multipleSelection,{
              headers: {
                  "Authorization":localStorage.getItem("token")
              }
        }).then(res=>{
              console.log(res)
               this.$alert('操作成功', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  console.log(action)
                }
               });
            })
    //      _this.$axios.post("/blog/exportExcel").then(res=> {
    //          console.log("============================")
    //          console.log(res) 
    //   })
    },
    editBlog(blogId) {
      alert(blogId)
    },
    remoteMethod(query) {
      const _this = this
        if (query !== '') {
          this.loading = true;
          setTimeout(() => {
            _this.loading = false;
            _this.options = _this.list.filter(item => {
              return item.label.toLowerCase()
                .indexOf(query.toLowerCase()) > -1;
            });
          }, 200);
        } else {
          _this.options = [];
        }
      }
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
  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
     width: 100%;
  }
  
  .el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
    width: 100%;
  }
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 260px;
    /* margin:0 auto; */
    overflow: hidden;
  }
  .el-header {
    /* background-color: aqua; */
    height: 150px;
    padding: 0 0;
  }
  .el-main {
    background-color: #E9EEF3;
    color: rgb(85, 11, 11);
    text-align: center;
    min-height: 500px;
    width: 100%;
    padding: 0 0;
    margin: 0 0;
    align-self: stretch;
  }
  .myavatar {
    width: 260px;
    height: 150px;
    background: #000;
    /* margin:0 auto; */
    color:#fff;
  }
  .mymenu {
     width: 260px;
  }
   .el-row {
    margin-bottom: 20px;
    align-self: stretch;
    display: inline-block;
    width: 100%;
        /* align-self: stretch;
    height: 100%;
    display: flex; 
    flex-wrap: wrap; */
  }
  .el-col {
    border-radius: 4px;
    width: 100%;
  }
  .head-con {
      background-color: #000;
      height: 50px;
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
  .el-pagination {
      display: inline;
  }
  .el-table {
      min-height: 400px;
  }
</style> 