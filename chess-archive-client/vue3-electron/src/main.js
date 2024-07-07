import { createApp } from 'vue'
import App from './App.vue'
import { createStore } from './store'
import router from './router'
import axios from 'axios';

axios.defaults.baseURL = process.env.VUE_APP_API_PATH;
console.log(process.env.VUE_APP_API_PATH)
const store = createStore();


const app = createApp(App)
app.use(store);
app.use(router);
app.mount('#app')
