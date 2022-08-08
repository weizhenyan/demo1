<!--1模板：html-->
<template>
  <div id="home">
    <home-header v-on:titleChanged="updateTitle($event)" v-bind:title="title"></home-header>
    <home-content></home-content>
  </div>
</template>
<!--2行为：处理逻辑-->
<script>
  //组件：一个小功能
  //产生关联

  //局部注册组件
import HomeHeader from './home/HomeHeader'
import Footer from './Footer'
import HomeContent from './home/HomeContent'
export default {
  //注册
  name: 'home',
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
    "home-header":HomeHeader,
    "app-footer":Footer,
    "home-content":HomeContent
    //不能跟系统标签相同的名字
  },
  created(){
  }
}
</script>
<!--3样式-->
<style>
#home,#home-content {
  width: 100%;
  height: 100%;
}
#home-content {
  min-height: 1000px;
  width: 100%;
}
</style>