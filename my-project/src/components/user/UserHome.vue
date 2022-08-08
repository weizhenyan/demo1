<!--1模板：html-->
<template>
  <div id="UserHome">
    <app-header v-on:titleChanged="updateTitle($event)" v-bind:title="title"></app-header>
    <!-- <users v-bind:users="users"></users>
    <users v-bind:users="users"></users> -->
    <app-content class="app-content"></app-content>
  </div>
</template>
<!--2行为：处理逻辑-->
<script>
  //组件：一个小功能
  //产生关联

  //局部注册组件
import Users from '../Users'
import AppHeader from '../AppHeader'
import Footer from '../Footer'
import AppContent from '../AppContent'
export default {
  //注册
  name: 'UserHome',
  data(){
    return{
          users:[
     //      {name:"Henry",position:"web开发",show:false},
     // {name:"Henry",position:"web开发",show:false},
     // {name:"Henry",position:"web开发",show:false},
     // {name:"Henry",position:"web开发",show:false},
     // {name:"Henry",position:"web开发",show:false},
     // {name:"Henry",position:"web开发",show:false},
     // {name:"Henry",position:"web开发",show:false},
     // {name:"Henry",position:"web开发",show:false}
     ],
     title:"传递一个值"
    }
    
  },
  methods:{
      updateTitle(title)
      {
        this.title=title;
      }
  },
  components:{
    "users":Users,
    "app-header":AppHeader,
    "app-footer":Footer,
    "app-content":AppContent
    //不能跟系统标签相同的名字
  },
  created(){
    // this.$http.get("http://jsonplaceholder.typicode.com/users").then((data) => {
      
    //   // console.log(data);
    //   this.users=data.body;
    // })
  }
}
</script>
<!--3样式-->
<style>
.app-content {
  height: 100%;
  width: 100%;
}
</style>