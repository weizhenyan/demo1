<template>
<el-container>
  <el-aside width="260px">
    <el-row :gutter="20">
      <el-col :span="16"><div class="grid-content bg-purple myavatar">
       <el-avatar :size="100" :src="url"></el-avatar>
       <span>{{user.username}}</span>
       </div></el-col>
      <el-col :span="16"><div class="grid-content bg-purple mymenu">
        <el-menu
          default-active="/blogManage"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          router="true"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="/blogManage">
            <i class="el-icon-menu"></i>
            <span slot="title">博客信息管理</span>
          </el-menu-item>
          <el-menu-item index="/userInfoManage">
            <i class="el-icon-menu"></i>
            <span slot="title">用户信息管理</span>
          </el-menu-item>
          <el-menu-item index="/blogMenuManage">
            <i class="el-icon-menu"></i>
            <span slot="title">博客目录管理</span>
          </el-menu-item>
        </el-menu>
    </div></el-col>
    </el-row>
  </el-aside>
  <el-container>
    <el-header height="150px">
      <div class="block">
        <el-carousel height="150px">
          <el-carousel-item v-for="item in 4" :key="item">
            <h3 class="small">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
        <!-- <el-carousel trigger="click" height="150px" :interval="3000" arrow="always" style="width:100%">
          <el-carousel-item v-for="item in imgList" :key="item.name">
            <img :src="item.src" style="height:100%;width:100%;" alt="图片丢失了" :title="item.title" />
          </el-carousel-item>
        </el-carousel> -->
      </div>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</el-container>
</template>

<!--传值：string number boolean
  传引用:array object-->
<script>
window.usermap_center = [111, 30.03]
window.usermap_zoom = 0
window.usermap_extend = [0, 0, 180, 90]
export default {
	name: 'adminhome',
	data(){
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
	      fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
        url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
        imgList: [
        {
          name: "maweixiu_seven",
          src: require("@/assets/images/maweixiu_seven.png"),
          title: "马尾绣"
        },
        {
          name: "maweixiu_six",
          src: require("@/assets/images/maweixiu_six.png"),
          title: "马尾绣"
        },
        {
          name: "maweixiu_five",
          src: require("@/assets/images/maweixiu_five.png"),
          title: "马尾绣"
        },
        {
          name: "maweixiu_three",
          src: require("@/assets/images/maweixiu_three.png"),
          title: "马尾绣"
        }
      ],
       ruleForm: {
          username: 'xiaoming',
          email: ''
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
          ],
          email: [
            { required: false, validator:checkEmail, trigger: 'blur' }
          ],
        },
        user:{
          username:'请先登录',
          avatar:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
        },
        tableData: [],
        blogs:{},
        currentPage: 1,
        total: 0,
        pagesize: 5,
        multipleSelection: []
     } 
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
        _this.blogs = res.data.data.records
         console.log(res.data.data.records)
         _this.tableData = res.data.data.records
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
    }
  },
   created() {
      if(this.$store.getters.getUser) {
          this.user.username = this.$store.getters.getUser.username
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
      background-color: rgb(196, 47, 47);
      height: 50px;
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