import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
  },
  {
    path: '/post',
    name: 'post',
    component: () => import('../views/PostView.vue')
  },
  {
    path: '/stacks',
    name: 'stacks',
    component: () => import('../views/FileView.vue')
  },
  {
    path: '/talk',
    name: 'talk',
    component: () => import('../views/TalkView.vue')
  },
  {
    path: '/links',
    name: 'links',
    component: () => import('../views/LinkView.vue')
  },
  {
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
