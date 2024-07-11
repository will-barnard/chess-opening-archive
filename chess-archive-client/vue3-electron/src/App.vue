<template>
  <div>
    <div class="heading">
      <h1>Chess Opening Archive</h1>
      <div class="spacer"></div>
      <p :class="openingStatus" @click="$router.push({ name: 'home' })">Openings</p>
      <p :class="categoryStatus" @click="$router.push({ name: 'category-search' })">Categories</p>
      <p :class="sourceStatus">Sources</p>
    </div>
    <RouterView />
  </div>
</template>

<script>


export default {
  name: 'App',
  components: {},
  created() {
    this.$store.commit('LOAD_CATEGORIES');
    this.$store.commit('LOAD_SOURCES');
    this.$store.commit('LOAD_OPENINGS');  
  },
  computed: {
    openingStatus() {
      if (this.$store.state.openings.length > 0) {
        return "hp loaded";  
      } else {
        return "hp loading"
      }
    },
    categoryStatus() {
      if (this.$store.state.categories.length > 0) {
        return "hp loaded";  
      } else {
        return "hp loading"
      }
    },
    sourceStatus() {
      if (this.$store.state.sources.length > 0) {
        return "hp loaded";  
      } else {
        return "hp loading"
      }
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  margin-top: 60px;
}
.heading {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.hp {
  cursor: pointer;
  padding: 10px;
  margin-left: 5px;
  border: 1px solid;
  border-radius: 10px;
}
.loading {
  background-color: rgb(150, 73, 73);
}
.loaded {
  background-color: rgb(59, 90, 59);
  transition: background-color 2s;
}
.spacer {
  flex-grow: 1;
}
:root {
    color-scheme: light-only;
    background-color: #3a3a3a;
    color: #cecece;
    border: #cecece;
}
textarea {
    resize: none;
}
.item {
        padding: 5px;
        border: 1px solid;
        border-radius: 10px;
        margin: 5px;
        user-select: none;
        cursor: pointer;
    }
    .item:hover {
        cursor: pointer;
        opacity: 70%;
        transition: opacity .2s;
    }
</style>
