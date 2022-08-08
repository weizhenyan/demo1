<template>
   <div class="users">
     <ul>
        <li v-for="user in users">
          <h2>{{user.name}}</h2>
          <h3>{{user.email}}</h3>
        </li>
     </ul>
     <button v-on:click="deleteUser">删除</button>
   </div>
</template>

<!--传值：string number boolean
  传引用:array object-->
<script>
export default {
	name: 'users',
  //props:["users"],//属性传值父属性传子属性
  props:{
    users:{type:Array,
      required:true
    }
  },
	data(){
	   return {
	 
	   }
	},
  methods:{
     deleteUser:function(){
         this.users.pop();
     }
  }
}
</script>
<style scoped>
  h1{
    color:green;
  }
  .users{
    width: 100%;
    max-height: 1200px;
    margin: 40px auto;
    padding: 0 20px;
    box-sizing: border-box;
  }
  ul{
    display: flex;/*每个li摆在一行*/
    flex-wrap: wrap;/*在容器，不超出*/
    list-style-type: none;
    padding: 0;
  }
  li{
    flex-grow: 1;/*一个就一个充满整行，两个就是两个充满整行*/
    flex-basis: 200px;
    text-align: center;
    padding: 30px;
    border: 1px solid #222;
    margin: 10px;
  }
</style>   