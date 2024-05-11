<template>
<div>
  <header>
    <nav class="nav">
        <li><router-link to="/testVue">테스트뷰</router-link></li>
        <li><router-link to="/listTest">DB테스트</router-link></li>
        <li><router-link to="/BoardList">박선우</router-link></li>
        <li><router-link to="/insertTest">insert 테스트</router-link></li>
        <li><router-link to="/regRev">박시호</router-link></li>
        <li><router-link to="/rsrvTest4">예약테스트</router-link></li>
        <li><router-link to="/GroupList">박선우 집단</router-link></li>
        <li><router-link to="/jobconsulting">취업상담</router-link></li>
        <li><router-link to="/mypage">[name]님 마이페이지</router-link></li>
        <li><router-link to="/login">로그인</router-link></li>
      <li><router-link to="/">Home</router-link></li>
      <li><router-link to="/counseling">심리상담 안내</router-link></li>
      <li><router-link to="/counselorshow">상담사 소개</router-link></li>
      <li>
        <router-link to="/test">Test</router-link>
      </li>
      <li><router-link to="/offlinesubmit">오프라인 상담 신청</router-link></li>
      <li><router-link to="/faq">FAQ</router-link></li>
      <li><router-link to="/contact">Contact</router-link></li>
    </nav>
  </header>
  <slot />
</div>
</template>


<script>
export default {
  name: 'MenuPage'
}
</script>

<style>
header {
  height: 50px;
}

nav {
  position: fixed;
  top: 0;
  width: 100%;
  height: 30px;
  background-color: gray;
}

nav ul {
  margin: 0;
  padding: 0;
}

nav li {
  list-style: none;
  float: left;
  width: 100px;
  height: 30px;
  line-height: 30px;
  text-align: center;
}

nav li:hover {
  font-weight: bold;
}

a {
  text-decoration: none;
  color: white
}

.lir {
  float: right;
  width: 80px;
}
</style>