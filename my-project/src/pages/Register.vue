<!-- 用户注册页面 -->
<template>
  <div class="register">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="register-form">
    <el-form-item label="用户名" prop="username">
        <el-input v-model="ruleForm.username"></el-input>
    </el-form-item>
    <el-form-item label="电话号码" prop="phone">
        <el-input v-model="ruleForm.phone"></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        ruleForm: {
          username: '',
          phone: ''
        },
        rules: {
          username: [
            { required: true, message: '请输入名称', trigger: 'blur' },
            { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
          ],
          phone: [
            { required: true, message: '请输入电话号码', trigger: 'change' },
            { pattern:/^((0\d{2,3}-\d{7,8})|(1[3584]\d{9}))$/,
              message:'请输入正确的手机号码或者座机号',
            }
          ]
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const _this = this
            //双向数据绑定
            this.$axios.post('/user/register',this.ruleForm).then(res=>{
                const userInfo = res.data.data
                const initPassword=res.data.data.password;
                console.log(res);
                this.$alert('初始密码：'+initPassword+",请及时更新", '用户初始密码', {
                confirmButtonText: '确定',
                });
                //登录成功，跳转到博客页面
                _this.$router.push("/login")
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>
<style  scoped>
.register {
    text-align: center;
    padding-top: 20px;
}
.register-form{
    background-color: beige;
    width: 600px;
    margin: 0 auto;
    border: 1px solid #000;
    height:200px;
    padding: 10px 10px 10px 10px;
}
</style>