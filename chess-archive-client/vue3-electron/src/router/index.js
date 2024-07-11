import { createRouter as createRouter, createWebHistory } from 'vue-router'

import OpeningView from '../view/OpeningView.vue';
import CategoryView from '../view/CategoryView.vue';
import CategorySearchView from '../view/CategorySearchView.vue';


const routes = [
  {
    path: '/',
    name: 'home',
    component: OpeningView,
  },
  {
    path: '/:isPush',
    name: 'home-push',
    component: OpeningView,
  },
  {
    path: '/category/:categoryId',
    name: 'category-detail',
    component: CategoryView
  },
  {
    path: '/category',
    name: 'category',
    component: CategoryView
  },
  {
    path: '/category/search',
    name: 'category-search',
    component: CategorySearchView
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

export default router;
