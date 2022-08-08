import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)

const store = new Vuex.Store({
  namespaced: true,
  state: {
          //全局存储信息,不能直接修改
          token:'',
          userInfo:JSON.parse(sessionStorage.getItem('userInfo'))
  },
  mutations:{
      //通过这个方法修改
      //set
    SET_TOKEN:(state,token) =>{
        state.token=token
        localStorage.setItem("token",token)
    },
    SET_USERINFO:(state,userInfo) =>{
        state.userInfo=userInfo
        sessionStorage.setItem("userInfo",JSON.stringify(userInfo))
    },
    REMOVE_INFO:(state) =>{
        state.token=''
        state.userInfo={}
        localStorage.setItem('token','')
        sessionStorage.setItem("userInfo",JSON.stringify(''))
    },
  },
  getters:{
      //get
      getUser:state=>{
          return state.userInfo
      }

  },
  actions:{

  },
  modules:{

  }
})
//默认使用，不能少
export default store