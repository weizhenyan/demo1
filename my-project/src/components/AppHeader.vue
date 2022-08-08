<template>
   <header>
    <div class="logo">
        <el-row>
            <el-col :span="12">
                <div class="grid-content bg-purple">
                    <el-avatar :size="50" :src="logoimg"></el-avatar></div></el-col>
            <el-col :span="12"><div class="icon iconfont grid-content bg-purple-light">{{logo}}</div></el-col>
        </el-row>
    </div>
    <div class="maction">
      <el-link icon="el-icon-s-home" type="primary" @click = "goHome">主页</el-link>&nbsp;
      <el-link icon="el-icon-folder-add" type="success" @click = "addBlog">发表文章</el-link>&nbsp;
      <el-link type="danger" @click= "goAdminHome">系统管理</el-link>&nbsp;
      <el-link icon="el-icon-setting" type="warning"  @click= "editUser">个人设置</el-link>&nbsp;
      <el-link href="http://www.baidu.com" icon="el-icon-search" type="info">百度搜索</el-link>
      <span v-show="!hasLogin"><el-link type="primary" @click= "login">登录</el-link></span>
      <span v-show="hasLogin"><el-link type="danger" @click= "logout">退出</el-link></span>
    </div>
   </header>
</template>
<script>
export default {
   name:'app-header',
   props:{
      title:{
        type:String
      }
    },
   data() {
      return {
          user:{
              username:'请先登录',
              avatar:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
          },
          logo:"blog@Mzy",
          logoimg:"https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png",
          hasLogin:false
      }
  },
  methods: {
      logout() {
          //退出先清除用户信息，因为token无状态的，不需要请求后端
          const _this = this
          _this.$axios.get("/userLogout",{
              headers: {
                  "Authorization":localStorage.getItem("token"),
              },
            //   withCredentials:false
          }).then(res => {
              //清掉本地缓存
              _this.$store.commit("REMOVE_INFO")
              _this.$router.push("/login")
              console.log(res)
          })
      },
      login() {
          this.$router.push({ path: '/login' })
      },
      addBlog() {
            if(this.$store.getters.getUser) {
              this.$router.push({ path: '/blog/add' })
            }else{
                this.$message({
                    showClose: true,
                    message: '请先登录再发表文章！！！',
                    type: 'warning'
                });
            }
      },
      editUser() {
        if(this.$store.getters.getUser) {
            this.$router.push({ path: '/user/edit' })
        }else{
            this.$message({
                showClose: true,
                message: '请先登录！！！',
                type: 'warning'
            });
        }
      },
      goHome() {
          this.$router.push({ path: '/' })
      },
      goAdminHome() {
        if(this.$store.getters.getUser) {
            this.$router.push({ path: '/admin' })
        }else{
            this.$message({
                showClose: true,
                message: '请先登录！！！',
                type: 'warning'
            });
        }
      }
  },
  created() {
      if(this.$store.getters.getUser) {
          this.user.username = this.$store.getters.getUser.username
          //头像
          this.user.avatar = this.$store.getters.getUser.avatar
          //显示退出
          this.hasLogin = true
      }
  }
}
</script>
<style scoped>
  header{
    /* padding: 10px; */
    background-color:  rgba(3, 3, 44,0.8);
    color: aliceblue;
    text-align: center;
    height: 60px;
  }
  h1{
    color:#222;
    text-align: center;
  }
  .maction {
    margin-top: 20px;
    margin-bottom: 20px;
    float: right;
    margin-right: 50px;
}
.logo {
    margin-left: 20px;
    float: left;
    height: 50px;
    /* margin-top: 10px; */
}
 .el-row {
    margin-bottom: 20px;
    height: 50px;
    line-height: 50px;
  }
</style> 