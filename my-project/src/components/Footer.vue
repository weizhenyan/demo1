<template>
   <footer>
   	  <p>{{ copyright }} {{title}}</p>
   </footer>
</template>

<script>
export default {
    name:'app-footer',
    props:{
    	title:{
    		type:String
    	}
    },
    data(){
    	return {
    		   copyright:"copyright By 2018-5-21"
    	       }
    }
}
</script>
<style scoped>
   footer{
   	background: #222;
   	padding: 6px;
   }
   footer p{
   	 color:lightgreen;
   	 text-align: center;
   }
</style> 