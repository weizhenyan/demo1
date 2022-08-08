<!--1模板：html-->
<template>
  <el-row :gutter="20">
    <el-col :span="3" class="left-content"><div class="grid-content bg-purple">
       <ul class="blogmenu-recommend">
          <li v-for="i in count" class="blogmenu-list-item">{{ i }}</li>
        </ul>
      </div></el-col>
    <el-col :span="15" class="main-content">
      <div class="grid-content bg-purple">
        <div>
          <el-carousel height="150px">
            <el-carousel-item v-for="item in 4" :key="item">
              <h3 class="small">{{ item }}</h3>
            </el-carousel-item>
          </el-carousel>
        </div>
        <el-main>
          <ul class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
            <li v-for="i in count" class="infinite-list-item">
              <div class="list_con">
                <div class="title">
                  <h3>你好</h3>
                </div>
                <div class="list_userbar">
                   <dt class="user_icon">
                     <el-avatar src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                   </dt>
                   <dd class="user_name">
                   <a>用户名称</a>
                   </dd>
                   <div class="blog_summari">博客简介</div>
                   <div class="user_dynamic">
                       <el-link type="primary" :underline="false"><i class="el-icon-view el-icon--right"></i>点赞</el-link>
                       <div class="interactive"></div>
                       <el-link type="primary" :underline="false"><i class="el-icon-view el-icon--right"></i>预览</el-link>
                       <div class="interactive"></div>
                       <el-link type="primary" :underline="false"><i class="el-icon-chat-dot-round" ></i> 回复</el-link>
                   </div>
                </div>
              </div>
            </li>
          </ul>
          <p v-if="loading">加载中...</p>
          <p v-if="noMore">没有更多了</p>
        </el-main>
      </div>
    </el-col>
    <el-col :span="6" class="right-content">
      <div class="grid-content bg-purple">
      <el-card class="box-card">
        <div v-for="o in 4" :key="o" class="text item">
          {{'列表内容 ' + o }}
        </div>
      </el-card>
      <el-card class="box-card-blog-expert">
        <div slot="header" class="clearfix">
          <span>卡片名称</span>
          <el-button style="float: right; padding: 3px 0" type="text">操作按钮</el-button>
        </div>
        <div v-for="o in 4" :key="o" class="text item">
          {{'列表内容 ' + o }}
        </div>
      </el-card>
      </div>
    </el-col>
  </el-row>
</template>
<!--2行为：处理逻辑-->
<script>
  //组件：一个小功能
  //产生关联

  //局部注册组件
import Users from '../Users'
import AppHeader from '../AppHeader'
import Footer from '../Footer'
export default {
  //注册
  name: 'appcontent',
  watch: {
      filterText(val) {
        this.$refs.tree.filter(val);
      }
    },
  data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item),
        title:"传递一个值",
        filterText: '',
        count: 30,
        loading: false,
        data: [{
          id: 1,
          label: 'JAVA基础',
          children: [{
            id: 4,
            label: 'JVM',
            children: [{
              id: 9,
              label: 'JVM内存模块'
            }, {
              id: 10,
              label: 'JVM垃圾回收'
            },
             {
              id: 11,
              label: 'JVM垃圾优化'
            }]
          }]
        }, {
          id: 2,
          label: 'Redis知识',
          children: [{
            id: 5,
            label: 'Redis数据类型'
          }, {
            id: 6,
            label: 'Redis持久化机制'
          },
          {
            id: 12,
            label: 'Redis穿透、击穿、雪崩'
          }]
        }, {
          id: 3,
          label: 'MySql',
          children: [{
            id: 7,
            label: '数据库引擎-innodb事务实现'
          }, {
            id: 8,
            label: '索引优化'
          }],
        },
        {
          id: 14,
          label: 'MyBatis',
          children: [{
            id: 15,
            label: '实现原理-个性化SQL、存储过程、高级映射'
          }, {
            id: 16,
            label: 'SqlSessionFactory'
          }]
          },
          {
          id:17,
          label: 'Spring',
          children: [{
            id: 18,
            label: '实现原理'
          }, {
            id: 19,
            label: '注解'
          }]
          
        }],
        defaultProps: {
          children: 'children',
          label: 'label'
        }
      } 
    },
  methods:{
    filterNode(value, data) {
        if (!value) return true;
        return data.label.indexOf(value) !== -1;
    },
    handleNodeClick() {
      alert("点击了左边");
    },
    load () {
      this.loading = true
      setTimeout(() => {
        // this.count += 2
        // this.loading = false
      }, 2000)
    }
  },
  components:{
    "users":Users,
    "app-header":AppHeader,
    "app-footer":Footer,
    //不能跟系统标签相同的名字
  },
  created(){
  },
  computed: {
      noMore () {
      return this.count >= 20
    },
    disabled () {
      return this.loading || this.noMore
    }
  }
}
</script>
<!--3样式-->

<style scoped>
.el-row {
    margin-bottom: 20px;
    min-height: 1000px;
    padding: 5px 5px 5px 5px;
    background: #99a9bf;
  }
  .el-col {
    border-radius: 4px;
    min-height: 1000px;
  }
  .bg-purple-dark {
    background: #99a9bf;
    min-height: 1000px;
  }
  .bg-purple {
    background: #d3dce6;
    min-height: 1000px;
  }
  .bg-purple-light {
    background: #e5e9f2;
    min-height: 1000px;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 1000px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
.el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 150px;
    margin: 0;
  }

.el-carousel__item:nth-child(2n) {
    background-color: #2c76dd;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #afca63;
}
/* .left-content {
  position: fixed;
}
.main-content {
  position: absolute;
} */
.left-content{
   /* height:500px;
   width:150px;
   float:left;
   border:1px red solid;
   background-color: #C36; */
   
   }
.main-content{  

   }   
.right-content{
    /* width:98px;
    height:500px;
    float:right;
    border:1px red solid;
    background-color:#c96; */
    }
.infinite-list-item {
  background-color:#fff;
  border-bottom: 1px solid #f4f4f4;
  padding: 18px 24px 13px 24px;
  position: relative;
  box-shadow:0 1px 2px 0 rgba(0, 0, 0,4%);
}
.el-main {
  padding: 0px;
}
.title {
  text-align: left;
}
.list_userbar {
  text-align: left;
  height: 60px;
  line-height: 60px;
}
.user_icon {
  vertical-align: middle;
}
.blog_summari,.user_icon,.user_name {
  display: inline-block;
}
.user_dynamic {
  display: inline-block;
  float:right;
}
.interactive {
display: inline-block;
height: 20px;
margin-bottom: -5px;
width: 10px;
margin-right: 10px;
border-right: 2px dotted #cecece; 
}
.blogmenu-list-item {
  display: block;
  height: 40px;
  background-color: #f6f8fc;
  margin-bottom: 10px;
  border-radius: 5px;
  line-height: 40px;
}
.blogmenu-list-item:hover {
  background-color:#99CC00;
  color:#CC3399; 
  cursor:pointer;
}
.box-card-blog-expert {
  margin-top: 10px;
}
</style>