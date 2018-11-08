import Vue from 'vue'
import Router from 'vue-router'
import Home from './Home.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
        name: 'auth',
        path: '/auth',
        component: () => import('./Auth.vue')
    },
    {
        name: 'profile',
        path: '/profile',
        component: () => import('./Profile.vue')
    },
  ]
})
