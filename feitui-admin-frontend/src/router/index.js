import { createRouter, createWebHashHistory } from 'vue-router'
import { useAuthStore } from '@/store/auth'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue'),
    meta: { title: '登录' }
  },
  {
    path: '/',
    component: () => import('@/layout/Layout.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/Dashboard.vue'),
        meta: { title: '数据看板' }
      },
      {
        path: 'contacts',
        name: 'Contacts',
        component: () => import('@/views/Contacts.vue'),
        meta: { title: '在线咨询管理' }
      },
      {
        path: 'users',
        name: 'Users',
        component: () => import('@/views/Users.vue'),
        meta: { title: '用户管理', requiresSuper: true }
      }
    ]
  },
  { path: '/:pathMatch(.*)*', redirect: '/login' }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  document.title = (to.meta && to.meta.title ? to.meta.title + ' · ' : '') + '飞推引流后台管理'
  const auth = useAuthStore()
  // 登录页始终放行，避免 / -> /login 无限重定向导致白屏
  if (to.path === '/login') {
    return next()
  }
  // 默认访问 / 且未登录一律先跳到登录页
  if (to.path === '/') {
    if (auth.token) {
      return next('/dashboard')
    }
    return next('/login')
  }
  if (to.meta && to.meta.requiresSuper && auth.role !== 'SUPER') {
    return next('/dashboard')
  }
  if (!auth.token) {
    return next('/login')
  }
  next()
})

export default router