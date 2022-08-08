<template>
  <div id="Header" class="m-content">
    <div class="block">
        <el-avatar :size="50" :src="user.avatar"></el-avatar>
        <div>{{user.username}}</div>
    </div>
    <div class="maction">
        <span><el-link type="info"  @click = "goHome">主页</el-link></span>
        <el-divider direction="vertical"></el-divider>
        <span><el-link type="success" @click = "addBlog">发表文章</el-link></span>
        <el-divider direction="vertical"></el-divider>
        <span v-show="!hasLogin"><el-link type="primary" @click="login">登录</el-link></span>
        <span v-show="hasLogin"><el-link type="danger" @click="logout">退出</el-link></span>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Header',
  components: {

  },
  data() {
      return {
          user:{
              username:'请先登录',
              avatar:"https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
          },
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
      goHome() {
          this.$router.push({ path: '/' })
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
.m-content {
    margin: 0 auto;
    padding-top: 20px;
    background-color:  rgba(3, 3, 44,0.8);
    color: aliceblue;
}
.maction {
    margin-top: 10px;
    margin-bottom: 20px;

}
</style>