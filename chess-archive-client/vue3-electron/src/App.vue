<template>
  <img alt="Vue logo" src="./assets/logo.png">
  <HelloWorld msg="Welcome to Your Vue.js App"/>
  <RouterView />
</template>

<script>
import HelloWorld from './components/HelloWorld.vue'
import OpeningService from './service/OpeningService';

export default {
  name: 'App',
  components: {HelloWorld},
  created() {
    OpeningService.getAllOpenings().then( 
          (response) => {
            this.$store.commit('LOAD_OPENINGS', response.data);

          })
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
