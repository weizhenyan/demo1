<template>
  <div class="login">
    <el-container>
      <el-header>
        <router-link to="/blogs">
          <img :src="logourl" style="height:60px;margin-top:10px;">
        </router-link>
      </el-header>
      <el-main>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="ruleForm.username" type="text"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input v-model="ruleForm.password" type="password"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
          <el-form-item class="register">
            <el-link :underline="false"  @click = "goRegister" type="primary">注册</el-link>
            <div class="interactive"></div>
            <el-link :underline="false" type="primary">忘记密码</el-link>
          </el-form-item>
        </el-form>
      </el-main>
      <el-footer>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: 'Login',
  props: {
    msg: String
  },
    data() {
      return {
        ruleForm: {
          username: '',
          password: ''
        },
      rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' },
            { min: 3, max: 16, message: '长度在 3 到 16 个字符', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'change' }
          ]
        },
        logourl:require("@/assets/images/logo.png")
      }
    },
    methods: {
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const _this = this
            //双向数据绑定
            this.$axios.post('/login',this.ruleForm).then(res=>{
              const jwt = res.headers['authorization'] 
              const userInfo = res.data.data
              
              _this.$store.commit("SET_TOKEN",jwt)
              //将用户信息放到sessionStorage
              _this.$store.commit("SET_USERINFO",userInfo)
              console.log(_this.$store.getters.getUser)
              //登录成功，跳转到博客页面
              _this.$router.push("/")
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      goRegister() {
         this.$router.push({ path: '/register' }) 
      },
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
}
.el-main {
  min-height: 600px;
}
body>.el-container {
  margin-bottom: 40px;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6).el-aside {
  line-height: 260px;
}
.demo-ruleForm {
  max-width: 500px;
  height: 300px;
  margin: 0 auto;
  text-align: center;
  background-color: rgba(114, 110, 110,0.5);
  padding-top: 50px;
  padding-right: 45px;
}
.interactive {
display: inline-block;
height: 20px;
margin-bottom: -5px;
width: 10px;
margin-right: 10px;
border-right: 2px dotted #cecece; 
}
.register {
  float: right;
}
</style>
