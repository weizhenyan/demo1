<template>
  <div class="blogs">
    <!-- <Header></Header> -->
    <div class="block">
      <el-timeline class="timeline">
        <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs" :key="blog.id">
          <el-card>
            <h4>
              <router-link :to="{name:'BlogDetail',params:{blogId:blog.id}}">{{blog.title}}</router-link>
            </h4>
            <p>{{blog.description}}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <el-pagination
        background
        layout="prev, pager, next"
        :current-page="currentPage"
        :page-size="pagesize"
        :total="total"
        @current-change=page
        >
      </el-pagination>
    </div>
  </div>
</template>

<script>
import Header from "../components/Header";
export default {
  name: 'Blogs',
  props: {
    msg: String
  },
  data(){
    return {
      blogs:{},
      currentPage:1,
      total:0,
      pagesize:5
    }
  },
  components:{Header},
  methods: {
    page(currentPage) {
      const _this = this
      _this.$axios.get("/blogs?currentPage="+currentPage).then(res=> {
        console.log("============================")
        console.log(res)
        _this.blogs = res.data.data.records
        _this.currentPage = parseInt(res.data.data.current)
        _this.total = parseInt(res.data.data.total)
        _this.pagesize = parseInt(res.data.data.size)
      })
    }
  },
  created() {
    this.page(1)
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.blogs {
  background-color: burlywood;
  padding-top: 15px;
}
.block {
  margin-right: 20px;
}
.timeline {
 text-align: left
  
}
</style>
