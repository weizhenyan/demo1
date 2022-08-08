<template>
  <div class="blogsdetail">
    <Header></Header>
    <div class="mblog">
      <h2>{{blog.title}}</h2>
      <el-link icon="el-icon-edit" v-if="ownBlog">
        <router-link  :to="{name:'BlogEdit',params:{blogId:blog.id}}">
        编辑
        </router-link>
        </el-link>
      <el-divider></el-divider>
      <div class="markdown-body" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
import "github-markdown-css"
export default {
  name: 'BlogDetail',
  props: {
    msg: String
  },
  data() {
    return {
      blog:{
        id:'',
        title:"moren",
        content:'nierong'
      },
      ownBlog:false
    }
  },
  components:{Header},
      created() {
        const blogId = this.$route.params.blogId
        console.log("================");
        console.log(blogId)
          const _this = this
          this.$axios.get('/blog/'+blogId).then(res=>{
            const blog = res.data.data
            _this.blog.id = blog.id
            _this.blog.title = blog.title
            var MarkdownIt = require("markdown-it")
            var md = new MarkdownIt()
            
            var result = md.render(blog.content)
            _this.blog.content = result
            if(this.$store.getters.getUser) {
              console.log(blog.userId+"=========fffffffff========="+ this.$store.getters.getUser.id)
              //本人的博客可以编辑
               _this.ownBlog=(blog.userId === this.$store.getters.getUser.id)
            }
          })
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.mblog {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  width: 100%;
  height: 700px;
  padding: 20px 24px;
}
</style>
