import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/Home.vue'),
    meta: { title: '首页' }
  },
  {
    path: '/features',
    name: 'Features',
    component: () => import('@/views/Features.vue'),
    meta: { title: '功能特点' }
  },
  {
    path: '/video',
    name: 'Video',
    component: () => import('@/views/Video.vue'),
    meta: { title: '视频演示' }
  },
  {
    path: '/custom',
    name: 'Custom',
    component: () => import('@/views/Custom.vue'),
    meta: { title: '定制开发' }
  },
  {
    path: '/contact',
    name: 'Contact',
    component: () => import('@/views/Contact.vue'),
    meta: { title: '联系我们' }
  },
  {
    path: '/wechat-qr',
    name: 'WechatQr',
    component: () => import('@/views/WechatQr.vue'),
    meta: { title: '企业微信' }
  },
  {
    path: '/tg-qr',
    name: 'TgQr',
    component: () => import('@/views/TgQr.vue'),
    meta: { title: 'Telegram' }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    } else {
      return { top: 0 }
    }
  }
})

router.afterEach((to) => {
  if (to.meta.title) {
    document.title = `${to.meta.title} - 赛博飞推`
  }
})

export default router
