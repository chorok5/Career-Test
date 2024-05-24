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
          <h1><br>
            <router-link to="/">
              <img src="@/assets/kidwiz1.png" alt="" style="width:257px; height:80px;">
            </router-link>
          </h1><br>
        </span>
        <span class="top-right">
          <router-link to="/login" v-if="!$store.state.account.id">로그인</router-link>
          <a to="/login" @click="logout()" v-else>로그아웃</a>
          <router-link to="/mypage" v-if="$store.state.account.id">| 마이페이지</router-link>          
          <a href="">| English</a>
        </span>
      </nav>
      <nav class="nav-bot boundary">
        <ul @mouseover="navAct()" @mouseleave="navDead()">
          <li>센터소개</li>
          <li>상담사소개</li>
          <li>상담실</li>
          <li>상담백과</li>
          <li>공지사항</li>
        </ul>
      </nav>
    <div class="secNav boundary" :style="{display: navDisplay}" @mouseover="navAct()" @mouseleave="navDead()">
      <nav class="nav-sec">
        <ul name="introduce">        
          <li><router-link to="/admin">관리자페이지</router-link></li>
          <li> </li>
          <li> </li>
        </ul>
        <ul name="counselors">
          <li><router-link to="/counselorList">상담사 소개</router-link></li>
          <li><router-link to="/rsrvTest4">예약테스트</router-link></li>          
          <li><router-link to="/secMenuTest">다증레이아웃</router-link></li>
          <li></li>
          <li></li>
        </ul>
        <ul name="counsel">
          <li><router-link to="/GroupList">집단상담</router-link></li>
          <li><router-link to="/jobs/intro">취업상담</router-link></li>
          <li><router-link to="/counseling">심리상담</router-link></li>          
          <li><router-link to="/regIntroduce">전문상담</router-link></li>           
          <li><router-link to="/boardlist">게시판상담</router-link></li>
          <li></li>
        </ul>
        <ul name="alpha">
          <li><router-link to="/cardNews">카드뉴스</router-link></li>
          <li><router-link to="/applyForm3">applyForm3</router-link></li>
          <li><router-link to="/resultWrite">resultWrite</router-link></li>
          <li><router-link to="/regResult">regResult</router-link></li>
          <li></li>
        </ul>
        <ul name="notice">
          <li><router-link to="/faq">FAQ</router-link></li>
          <li><router-link to="/contact">Contact</router-link></li>
          <li></li>
          <li></li>
        </ul>
      </nav>
    </div>
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
import store from "@/store.js";
import router from '@/router'
import axios from 'axios';
import { onMounted, ref } from 'vue';

export default {
    name:'MenuPage',

    data(){
      return{
        navDisplay : 'none'
      };
    },
    methods : {
      navAct(){
        this.navDisplay = 'block';
      },
      navDead(){
        this.navDisplay = "none";
      }
    },
    setup() {

      const check = ref()

      const logout = () => {
        axios.post("http://localhost:3000/api/account/logout", {}, { withCredentials: true })
        .then(()=>{
        store.commit('setAccount', 0);
        sessionStorage.removeItem("id");
        router.push({path: '/'});
        });
      }

      onMounted(()=>{
        axios.post("/api/admin/admincheck",{withCredentials:true}).then((res)=>{
          if(res.data==1){
            check.value = true
          }else{
            check.value = false
          }
        })
      })
      return {logout,check}
    }
}
</script>

<style scoped>
.nav{
  top: 0;
  width: 100%;
  background-color: #fff;
  border-bottom: 1px solid #c0c0c0;
  position: fixed;
  z-index: 999;
}
.nav-top{
  height: 80px;
  width: 70%;
  margin: 0 auto; /* 가운데 정렬 */
  display: flex;
  align-items: center;
  justify-content: space-between;
}
.nav-bot{
  height: 55px;
  width: 100%;
  /* background-color: #fff; */
  float: hide;
}
.nav-bot ul{
  width: 100%;
  height: 100%;
  padding: 0;
  display: flex;
}
.nav-bot ul li{
  width: 20%;
  list-style-type: none;
  text-decoration: none;
  justify-content: center;
  align-content: center;
}
.secNav{
  width: 100%;
}
.nav-sec ul{
  width: 20%;
  padding: 0;
  float: left;
  border-left: 1px solid #d6d6d6;
}
.nav-sec ul li{
  list-style-type: none;
  text-decoration: none;
  margin-top: 10px;  
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
.mainContainer{
  margin-top: 205px;
}

</style>@/store@/store.js