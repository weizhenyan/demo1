<template>
  <div class="blogadd">
    <Header></Header>
    <div class="m-content">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="标题" prop="title">
        <el-input v-model="ruleForm.title"></el-input>
      </el-form-item>
      <el-form-item label="摘要" prop="description">
        <el-input type="textarea" v-model="ruleForm.description"></el-input>
      </el-form-item>
      <el-form-item label="内容" prop="content">
        <mavon-editor v-model="ruleForm.content"></mavon-editor>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
    </div>

  </div>
</template>

<script>
import Header from "../components/Header";
export default {
  name: 'BlogAdd',
  props: {
    msg: String
  },
   data() {
      return {
        ruleForm: {
          id:'',
          title: '',
          description: '',
          content:'',
          userId:''
        },
        rules: {
          title: [
            { required: true, message: '请输入标题', trigger: 'blur' },
            { min: 3, max: 25, message: '长度在 3 到 25 个字符', trigger: 'blur' }
          ],
          description: [
            { required: true, message: '请输入摘要', trigger: 'blur' }
          ],
          content:[
            { required: true, message: '请输入内容', trigger: 'blur' }
          ]
        }
      };
    },
    components:{Header},
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            const _this = this
            this.$axios.post('/blog/add',this.ruleForm,{
              headers: {
                  "Authorization":localStorage.getItem("token")
              }
            }).then(res=>{
              console.log(res)
               this.$alert('操作成功', '提示', {
                confirmButtonText: '确定',
                callback: action => {
                  console.log(action)
                  _this.$router.push("/")
                }
               });
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
        if(!this.$store.getters.getUser) {
            this.$message({
                showClose: true,
                message: '请先登录再发表文章！！！',
                type: 'warning'
            });
            this.$router.push({ path: '/login' })
        }
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

#blogedit {
  background-color: #42b983
}
.m-content {
  margin: 0 auto;
  margin-right: 20px;
}
</style>
