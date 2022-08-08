import axios from 'axios'
import Element from 'element-ui'
import { Promise } from 'q';
import router from './router'
import store from '@/store/store'

// 存储是否正在更新token 的状态
let isRefreshing = false
// 存储因为token刷新而挂起的请求
let requests = []

//加前置拦截和后置拦截
//  axios.defaults.baseURL = "http://localhost:8083"
if (process.env.NODE_ENV == "development") { //如果是开发环境
    axios.defaults.baseURL = 'http:' + process.env.BASE_API;
  } else { //线上生产环境
    axios.defaults.baseURL = 'http:' + process.env.BASE_API;
    console.log("yes..............................")
  }
// axios.defaults.baseURL = "/api";
 axios.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8' // 配置请求头
 axios.interceptors.request.use(config => {
     //携带token
    //  let uToken = localStorage.getItem("token");
    //  if(uToken){
    //      config.headers['Authorization']=uToken;
    //  }
    //  config.withCredentials = false // 允许携带token ,这个是解决跨域产生的相关问题
     //前置拦截
     return config
 },error=>{
     Promise.reject(error)
 })

 //后置拦截
 axios.interceptors.response.use(response => {
     let res = response.data
     let {success,result} = response.data;
    //  //用户没有登录
    //  if(!success&&result==="noUser") {
    //      //跳转到登录页面
    //      location.href="/login";
    //      return;
    //  }
    //  //后台redis用户已经过期了
    //  if(!success&&result==="expireUser"){
    //     //跳转到登录页面
    //     this.$store.commit("REMOVE_INFO")
    //     this.$router.push("/login")
    //     return;
    //  }
     if(res.code === 200) {
         //正确时
         return response
     }else {
        Element.Message.error('错了哦，密码或用户名输入错误',{duration:2000});
        return Promise.reject(response.data.msg)
     }
 },
 error => {
     if(error.response.data) {
         //设置后台的错误信息到前端
         error.message = error.response.data.msg
     }
     if(error.response) {
        //判断请求失败状态码情况
        const {status} = error.response
        let errorMessage=''
        if(status===400) {
            errorMessage='请求参数错误'
        }
        else if(error.response.status===401) {
            store.commit("REMOVE_INFO")
            router.push('/login')
            // 封装函数后更改为调用
           // redirectLogin()
        }else if(status===403) {
            errorMessage="没有权限，请联系管理员"
        }
        else if(status===404) {
            errorMessage="请求资源不存在"
        }else if(status>=500) {
            errorMessage="服务器错误，请联系管理员"
        }
        Element.Message.error(errorMessage)
     }else if(error.request){
        Element.Message.error("请求超时请重试")
     }
     //报错对应的信息
    Element.Message.error(error.message,{duration:2000});
    return Promise.reject(error)
 }
)
// .then(res=>{
//     if(res.data.status!==1) {
//         //清除无效的用户信息
//         store.commit("REMOVE_INFO")
//         // 封装函数后更改为调用
//         redirectLogin()
//     }
// }).catch(() => {
//     store.commit("REMOVE_INFO")
//     // 封装函数后更改为调用
//     redirectLogin()
//     return Promise.reject(error)
//   })
// 封装跳转登录页面的函数
function redirectLogin () {
    router.push({
      name: 'login',
      query: {
        // router.currentRoute 用于获取当前路由对应的路由信息对象
        redirect: router.currentRoute.fullPath
      }
    })
  }
