<template>
    <nav id="sidebar" class="col-md-3 col-lg-2 d-md-block bg-light sidebar">
      <div class="position-sticky pt-3">
        <div class="user-welcome mb-3">
        '{{ $store.state.account.name }}' 님 반갑습니다.
      </div>
        <ul class="nav flex-column">
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'myinfo' }">나의 정보 관리</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'myconsultations' }">나의 상담 내역</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'mygroup' }">비교과(집단 상담)내역</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'mysimri' }">나의 심리검사 결과</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'myqna' }">나의 문의 내역</router-link>
          </li>
          <li class="nav-item">
            <router-link class="nav-link" :to="{ name: 'myreviews' }">나의 리뷰</router-link>
          </li>

        </ul>
      </div>
    </nav>
  </template>
  
  <script>
  export default {
    name: 'Sidebar'
  };
  </script>
  
  <style scoped>
  .sidebar {
    position: -webkit-sticky;
    position: sticky;
    top: 56px; /* Adjust based on the height of HeaderMenu */
    height: calc(100vh - 56px); /* Adjust based on the height of HeaderMenu */
    padding-top: 1rem;
    overflow-x: hidden;
    overflow-y: auto; /* Enable scrolling if the content exceeds the viewport height */
  }
  .sidebar .nav-link {
    font-weight: 500;
    color: #333;
  }
  .sidebar .nav-link:hover {
    color: #007bff;
  }
  </style>
  