import { createRouter as createRouter, createWebHistory } from 'vue-router'

import OpeningView from '../view/OpeningView.vue';


const routes = [
  {
    path: '/',
    name: 'home',
    component: OpeningView,
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

export default router;
