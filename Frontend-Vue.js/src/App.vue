<template>
  <Header></Header>
  <component :is="$route.meta.layout || 'div'">
    <Main></Main>
    <router-view></router-view>
  </component>
  </template>
  
  <script>
import Header from './components/Header.vue';
import Main from './components/Main.vue'
  import axios from 'axios';
  import store from './views/store';
  import { useRoute } from 'vue-router';
  import { watch } from 'vue';
  
  export default {
    name: 'App',
    components: {
      Header,
      Main
    },
    setup() {
      const check = () => {
        axios.get('http://localhost:3000/api/account/check', { withCredentials: true }).then((res) => {
          console.log("체크합니다 : ", JSON.stringify(res.data));
          // data 값이 있으면 setAccount 에 data 넣고, 없으면 0
          store.commit("setAccount", res.data || 0);
        })
      };
      // url 정보 가져오기
      const route = useRoute();
      // 경로가 바뀔 때 마다 감시(check 실행)
      watch(route, () => {
        check();
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
}


.bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
      }

      .bd-mode-toggle {
        z-index: 1500;
      }

      .bd-mode-toggle .dropdown-menu .active .bi {
        display: block !important;
      }
    
</style>


    <!--  이 부분 있으면 사이트 접속 시 에러 남. 
      setup() {
      const check = () => {
        axios.get('http://localhost:3000/api/account/check', { withCredentials: true }).then(({data}) => {
          console.log(data+"안녕안녕");
          // data 값이 있으면 setAccount 에 data 넣고, 없으면 0
          store.commit("setAccount", data || 0);
        })
      };
      // url 정보 가져오기
      const route = useRoute();
      // 경로가 바뀔 때 마다 감시(check 실행)
      watch(route, () => {
        check();
      }) -->