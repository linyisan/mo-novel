import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
import Layout from '@/layout'

const constantRoutes = [
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }]
  },

  {
    path: '/book',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Book',
        component: () => import('@/views/book/index'),
        meta: { title: '小说管理', icon: 'form' }
      }
    ]
  },

  {
    path: '/bookdetail',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'BookDetail',
        component: () => import('@/views/Bookdetail'),
        meta: { title: '小说详情页', icon: 'form' }
      }
    ]
  },
  {
    path: '/test',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Test',
        component: () => import('@/views/Test'),
        meta: { title: '测试页', icon: 'form' }
      }
    ]
  }
]

const createRouter = () => new VueRouter({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
