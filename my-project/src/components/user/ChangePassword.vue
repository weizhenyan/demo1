<!-- 修改密码 -->
<template>
  <div>
    <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
    <el-form-item label="旧密码" prop="oldpass">
        <el-input type="password" v-model="ruleForm.oldpass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="pass">
        <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item label="确认密码" prop="checkPass">
        <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
    </el-form-item>
    <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
    </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    var checkOldPassword = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('旧密码不能为空'));
      }else{
        callback();
      }
    };
    var validatePass = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请输入密码'));
    } else {
        if (this.ruleForm.checkPass !== '') {
        this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
    }
    };
    var validatePass2 = (rule, value, callback) => {
    if (value === '') {
        callback(new Error('请再次输入密码'));
    } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
    } else {
        callback();
    }
    };
    return {
        userId:'',
        ruleForm: {
          pass: '',
          checkPass: '',
          oldpass: ''
        },
        rules: {
          pass: [
            { validator: validatePass, trigger: 'blur' }
          ],
          checkPass: [
            { validator: validatePass2, trigger: 'blur' }
          ],
          oldpass: [
            { validator: checkOldPassword, trigger: 'blur' }
          ]
        }
    };
  },

  methods: {
    submitForm(formName) {
    this.$refs[formName].validate((valid) => {
        if (valid) {
        const _this = this
        _this.$axios.get("/user/changePassword",{
          headers: {
            "Authorization":localStorage.getItem("token")
          },
          params:{
            userId:_this.userId,
            newPassword:_this.ruleForm.checkPass,
            oldPassword:_this.ruleForm.oldpass,
          }
        })
        .then(res=> {
          console.log("====================2")
          console.log(res.data.data)
          const user = res.data.data
          if(user!=null||user!=undefined) {
            _this.$store.commit("REMOVE_INFO")
            _this.$router.push({ path: '/login' })
          }
        }).catch(function (err) {
            console.log(err);
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
  },
  created() {
      if(this.$store.getters.getUser) {
          this.userId = this.$store.getters.getUser.id
      }
   }
}

</script>
<style scoped>
</style>