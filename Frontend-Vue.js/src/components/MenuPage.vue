<template>
<div>
  <header>

    <div class="nav">
      <nav class="nav-top">
        <span class="top-left">
          <a href="">home</a>
          <a href="">facebook</a>
        </span>
        <span class="mainImg">
          <h1>
            <router-link to="/">
              대충 대표 이미지
            </router-link>
          </h1>
        </span>
        <span class="top-right">
          <a href="">로그인</a>
          <a href="">회원가입</a>
        </span>
      </nav>
      <nav class="nav-bot">
        <ul>
          <li><router-link to="/testVue">테스트뷰</router-link></li>
          <li><router-link to="/listTest">DB테스트</router-link></li>
          <li><router-link to="/BoardList">박선우</router-link></li>
          <li><router-link to="/insertTest">insert 테스트</router-link></li>
          <li><router-link to="/regRev">박시호</router-link></li>
          <li><router-link to="/rsrvTest4">예약테스트</router-link></li>
          <li><router-link to="/GroupList">박선우 집단</router-link></li>
          <li><router-link to="/jobConsulting">취업상담</router-link></li>
          <li v-if="$store.state.account.id"><router-link to="/mypage">마이페이지</router-link></li>
          <li>
          <router-link to="/login" v-if="!$store.state.account.id">로그인</router-link>
          <a to="/login" @click="logout()" v-else>로그아웃</a>
          </li>
          <li><router-link to="/">Home</router-link></li>
          <li><router-link to="/counseling">심리상담 안내</router-link></li>
          <li><router-link to="/counselorshow">상담사 소개</router-link></li>
          <li><router-link to="/test">Test</router-link></li>
          <li><router-link to="/faq">FAQ</router-link></li>
          <li><router-link to="/contact">Contact</router-link></li>
          <li><router-link to="/admin">관리자페이지</router-link></li>
        </ul>
      </nav>
    </div>
  </header>
  <div class="mainContainer">
    <main>
      <slot />
    </main>
  </div>
</div>
</template>


<script>
import store from "@/views/store";
import router from '@/router'
import axios from 'axios';

export default {
    name:'MenuPage',
    setup() {
      const logout = () => {
        axios.post("http://localhost:3000/api/account/logout", {}, { withCredentials: true })
        .then(()=>{
        store.commit('setAccount', 0);
        sessionStorage.removeItem("id");
        router.push({path: '/'});
        });
      }
      return {logout}
    }
}
</script>

<style scoped>
.nav{
  top: 0;
  width: 100%;
  background-color: skyblue;
}
.nav-top{
  height: 80px;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.nav-bot{
  height: 55px;
  width: 100%;
  background-color: grey;
  float: hide;
}
.nav-bot ul li{
  list-style-type: none;
  text-decoration: none;
  float: left;
  margin: 0 5px;
}
.top-left{
  margin-right: auto;
}
.top-right{
  margin-left: auto;
}
.nav-top a{
  margin: 0 10px;
  text-decoration: none;
}
.mainImg{
  flex: 1;
  text-align: center;
}

a{
  text-decoration: none;
  cursor: pointer; /* 커서 스타일 추가 */
}
a:link {
  color : black;
}
a:visited {
  color : black;
}
a:hover {
  color : black;
}
a:active {
  color : black;
}
</style>