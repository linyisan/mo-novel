import Vue from 'vue';
import VueRouter from 'vue-router';
import Bookdetail from '../views/Bookdetail';
import Test from '../views/Test';

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: Bookdetail
  },
  {
    path: '/bookdetail',
    name: 'BookDetail',
    component: Bookdetail
  },
  {
    path: '/test',
    name: 'Test',
    component: Test
  }
];

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
});

export default router;
