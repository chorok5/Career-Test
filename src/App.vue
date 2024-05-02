<template>
  <div id="app">
    <MenuPage/>
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <router-view/>
    <Footer />
    
  </div>
</template>

<script>
import Footer from './components/common/AppFooter.vue'; //import 풋터 추가
import MenuPage from './components/MenuPage.vue';

export default {
  name: 'App',
  components: {
    Footer,
    MenuPage
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
