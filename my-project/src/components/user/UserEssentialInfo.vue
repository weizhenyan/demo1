<!-- 用户基本信息 -->
<template>
  <div>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户头像" prop="avatar">
          <el-upload
                action="' '" 
                list-type="picture-card"
                :limit="3"
                show-file-list
                :auto-upload="false"
                :on-change="change"
                multiple
                style="float:left"
              >
                <i class="el-icon-plus"></i>
              </el-upload>
              <el-dialog :visible.sync="dialogVisible">
                <img width="100%" :src="dialogImageUrl" alt="" />
          </el-dialog>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="ruleForm.username" type="text"></el-input>
        </el-form-item>
        <el-form-item label="绑定手机号" prop="phone">
          <el-input v-model="ruleForm.phone" type="text"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="ruleForm.sex" size="medium"style="float:left">
            <el-radio label="男" border ></el-radio>
            <el-radio label="女" border ></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="当前住址" prop="address">
          <el-input v-model="ruleForm.address" type="text"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="resetForm('ruleForm')">取消</el-button>
        </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
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
          email: '',
          avatar:''
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
        openeds:['1'],
        uniqueOpened:false
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
      }
  },
   created() {
      if(this.$store.getters.getUser) {
          this.user.username = this.$store.getters.getUser.username
          this.url=this.$store.getters.getUser.avatar
          this.ruleForm=this.$store.getters.getUser
      }
   }

}

</script>
<style  scoped>
</style>