<!--1模板：html-->
<template>
  <div id="appcontent">
    <el-container style="height: 500px; border: 1px solid #eee">
      <el-aside width="260px" style="background-color: rgb(238, 241, 246)">
        <el-input class="filter-text"
          placeholder="输入关键字进行过滤"
          v-model="filterText">
        </el-input>

        <el-tree
          class="filter-tree"
          :data="data"
          :props="defaultProps"
          default-expand-all
          :filter-node-method="filterNode"
          :expand-on-click-node="false"
          @node-click="handleNodeClick"
          ref="tree">
        </el-tree>
      </el-aside>
      
      <el-container>
        <el-header style="text-align: right; font-size: 12px">
          <el-dropdown>
            <i class="el-icon-setting" style="margin-right: 15px"></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>查看</el-dropdown-item>
              <el-dropdown-item>新增</el-dropdown-item>
              <el-dropdown-item>删除</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <span>王小虎</span>
        </el-header>
        
        <el-main>
          <!-- <el-table :data="tableData">
            <el-table-column prop="date" label="日期" width="140">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="address" label="地址">
            </el-table-column>
          </el-table> -->
          <blogs></blogs>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<!--2行为：处理逻辑-->
<script>
  //组件：一个小功能
  //产生关联

  //局部注册组件
import Users from './Users'
import AppHeader from './AppHeader'
import Footer from './Footer'
import Blogs from '../pages/Blogs';
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
    }
  },
  components:{
    "users":Users,
    "app-header":AppHeader,
    "app-footer":Footer,
    "blogs":Blogs
    //不能跟系统标签相同的名字
  },
  created(){
  }
}
</script>
<!--3样式-->
<style>
#appcontent {
    /* min-height: 600px; */
}
.el-header {
  background-color: #B3C0D1;
  /* color: #333; */
  line-height: 60px;
}
.el-aside {
  color: #333;
}
.filter-text {
  height: 40px;
  padding-top: 20px;
}
</style>