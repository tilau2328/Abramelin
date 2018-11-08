import Vue from 'vue'
import App from './App.vue'
import store from './store'
import router from './views/router'
import { sync } from 'vuex-router-sync'

import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.use(BootstrapVue)
sync(store, router)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
