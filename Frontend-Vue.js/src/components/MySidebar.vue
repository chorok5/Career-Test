<template>
  <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block custom-sidebar">
    <div class="py-4 rounded-top text-white pl-3 mb-0" style="background-color: #67BF4E;">
      <h4 class="mb-0" style="margin: 0;">메뉴</h4>
    </div>
    <div class="menu-items text-center justify-content-center  align-items-center">
      <div class="menu-item text-center">
        <router-link class="nav-link text-dark" :to="{ name: 'myinfo' }">나의 정보 관리</router-link>
      </div>
      <div class="menu-item text-center" v-if="grade>=2">
        <router-link class="nav-link text-dark" :to="{ name: 'myconsultations' }">나의 상담 내역</router-link>
      </div>
      <div class="menu-item text-center" v-if="grade<2">
        <router-link class="nav-link text-dark" :to="{ name: 'StudentConsultations' }">나의 상담 내역(학생용)</router-link>
      </div>
      <div class="menu-item text-center" v-if="id=='24102003'">
        <router-link class="nav-link text-dark" :to="{ name: 'regRev' }">정기상담 관리</router-link>
      </div>
      <div class="menu-item text-center">
        <router-link class="nav-link text-dark" :to="{ name: 'mygroup' }">비교과(집단 상담)내역</router-link>
      </div>
      <div class="menu-item text-center">
        <router-link class="nav-link text-dark" :to="{ name: 'mysimrireserve' }">심리검사 예약 내역</router-link>
      </div>
      <div class="menu-item text-center">
        <router-link class="nav-link text-dark" :to="{ name: 'mysimri' }">나의 심리검사 결과</router-link>
      </div>
      <div class="menu-item text-center">
        <router-link class="nav-link text-dark" :to="{ name: 'myqna' }">나의 문의 내역</router-link>
      </div>
      <div class="menu-item text-center">
        <router-link class="nav-link text-dark" :to="{ name: 'myreviews' }">나의 리뷰</router-link>
      </div>
      <div class="menu-item text-center" v-if="grade==3">
        <router-link class="nav-link text-dark" :to="{ name: 'admin' }">관리자 페이지</router-link>
      </div>
    </div>
  </nav>
</template>

<script>
import axios from 'axios';
export default {
  name: 'Sidebar',
  data() {
    return{
      id:'',
      grade:'',
    }    
  },
  mounted(){
    this.id = this.$store.state.account.id;
    const params = new URLSearchParams();
    params.append('id', this.$store.state.account.id);
    axios.get('http://localhost:3000/memberDetail', {params: params}).then((res) => {      
      this.grade = res.data[0].grade;
    }).catch((err) => {
      console.log(err);
    })
  }

};
</script>

<style>
#sidebar {
  width: 230px;
}
.custom-sidebar {
  position: fixed;
  top: 150px; /* 조금 아래로 내려줍니다. */
  left: 20px;
  width: 200px;
  height: auto;
  padding-top:0;
  overflow-x: hidden;
  overflow-y: auto;
  background-color: #fff; /* 흰 배경으로 변경합니다. */
  border-radius: 30px; /* 테두리를 둥글게 만듭니다. */
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); /* 그림자 추가 */
}

.custom-sidebar .menu-item {
  padding-top: 1rem;
  padding: 0.5rem 1rem;
  height: 50px;
  margin : 0 auto;
  text-align: center;
  align-content: center;
}

.custom-sidebar .menu-item:hover {
  background-color: #dadee1;
  /* Added styles for hover effect */
  font-weight: bold;
  color: midnightblue;
}

.custom-sidebar .menu-item:not(:first-child) {
  border-top: 1px solid #dee2e6;
}

.custom-sidebar .menu-item:last-child {
  border-radius: 0 0 10px 10px;
}
</style>