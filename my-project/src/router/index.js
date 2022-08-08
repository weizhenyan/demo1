import Vue from 'vue'
import Router from 'vue-router'
import Blogs from '../pages/Blogs'
import Login from '../pages/Login'
import Register from '../pages/Register'
import BlogEdit from '../pages/BlogEdit'
import BlogAdd from '../pages/BlogAdd'
import BlogDetail from '../pages/BlogDetail'
import Home from '../components/Home'
import UserEdit from '../components/UserEdit'
import UserHome from '../components/user/UserHome'
import BrowsingHistory from '../components/user/BrowsingHistory'
import ChangePassword from '../components/user/ChangePassword'
import UserEssentialInfo from '../components/user/UserEssentialInfo'
import UserDetail from '../components/user/UserDetail'
import AdminHome from '../components/admin/AdminHome'
import BlogManage from '../components/admin/BlogManage'
import BlogMenuManage from '../components/admin/BlogMenuManage'
import UserInfoManage from '../components/admin/UserInfoManage'

Vue.use(Router)

const routes= [
    // {
    //     path:'/',
    //     redirect:'/index'

    // },
    {
        path:'/',
        name:'Home',
        component:Home,
    },
    {
        path:'/blogs',
        name:'Blogs',
        component:Blogs
    },
    {
        path:'/login',
        name:'Login',
        component:Login
    },
    {
        path:'/register',
        name:'Register',
        component:Register
    },
    {
        path:'/blog/add',
        name:'BlogAdd',
        component:BlogAdd,
    },
    {
        path:'/blog',
        name:'BlogDetail',
        component:BlogDetail
    },
    {
        path:'/blog/edit',
        name:'BlogEdit',
        component:BlogEdit
    },
    {
        path:'/user/home',
        name:'UserHome',
        component:UserHome
    },
    {
        path:'/user/edit',
        name:'UserEdit',
        component:UserEdit,
        children:[
            {
                path:'/change/password',
                name:'ChangePassword',
                component:ChangePassword,
            },
            {
                path:'/browsing/history',
                name:'BrowsingHistory',
                component:BrowsingHistory,
            },
            {
                path:'/user/baseinfo',
                name:'UserEssentialInfo',
                component:UserEssentialInfo,
            }
        ]
    },
    {
        path:'/user/detail',
        name:'UserDetail',
        component:UserDetail
    },
    {
        path:'/admin',
        name:'AdminHome',
        component:AdminHome,
        children:[
            {
                path:'/blogManage',
                name:'BlogManage',
                component:BlogManage,
            },
            {
                path:'/blogMenuManage',
                name:'BlogMenuManage',
                component:BlogMenuManage,
            },
            {
                path:'/userInfoManage',
                name:'UserInfoManage',
                component:UserInfoManage,
            },
            {
                path:"",
                component:BlogManage
            }
        ]
    }
]
const createRouter = () => new Router({
    mode: 'hash',
    base:process.env.BASE_URL,
    routes
  })
  const router = createRouter()
  
  // 退出系统后，重设置路由
  export const resetRouter = () => {
    console.log('重设了路由')
    const newRouter = createRouter()
    router.matcher = newRouter.matcher // the relevant part
  }
  /** 捕获到错误重新加载路由 */
  router.onError((err) => {
    const pattern = /Loading chunk (\d)+ failed/g;
    const isChunkLoadFailed = err.message.match(pattern);
    if (isChunkLoadFailed) {
      let chunkBool = sessionStorage.getItem('chunkError');
      let nowTimes = Date.now();
      if (chunkBool === null || chunkBool && nowTimes - parseInt(chunkBool) > 60000) {//路由跳转报错,href手动跳转
        sessionStorage.setItem('chunkError', 'reload');
        const targetPath = routers.history.pending.fullPath;
        window.location.href = window.location.origin + targetPath;
      }else if(chunkBool === 'reload'){ //手动跳转后依然报错,强制刷新
        sessionStorage.setItem('chunkError', Date.now());
        window.location.reload(true);
      }
    }
  });
  // 初始化
  export default router