import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import '../static/css/common.css'
import router from './router'
import App from './App.vue'
import store from '@/store/store'
import axios from 'axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import "./axios"
import '../static/font/iconfont.css'
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI)
Vue.use(mavonEditor)
new Vue({
  router,
  store:store,
  render: h => h(App),
}).$mount('#app')
