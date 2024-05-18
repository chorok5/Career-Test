<template>
  <div class="container">
    <h2>Test 페이지</h2><br><br>
    <div class="row">
      <div class="col-md-6">
        <button @click="navigateToTest('career')" class="btn btn-primary btn-lg btn-block">Career Test로 이동하기</button>
      </div>
      <div class="col-md-6">
        <router-link to="/test/person" class="btn btn-success btn-lg btn-block">Person Test로 이동하기</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import store from "@/views/store";

export default {
  name: 'TestPage',
  methods: {
    navigateToTest(testType) {
      // 로그인 상태 확인
      const isLoggedIn = store.state.account.id !== 0 && store.state.account.id !== undefined; // ID가 0이거나 정의되지 않은 경우를 제외하고 로그인된 것으로 간주
      if (isLoggedIn && testType === 'career') {
        // 로그인한 사용자이고, Career Test를 클릭한 경우에만 해당 심리검사로 이동
        this.$router.push({ name: 'CareerTest' });
      } else if (isLoggedIn && testType === 'person') {
        // 로그인한 사용자이고, Person Test를 클릭한 경우에만 해당 심리검사로 이동
        this.$router.push({ name: 'PersonTest' });
      } else {
        // 로그인하지 않은 사용자인 경우, 로그인 페이지로 이동
        alert(`로그인이 필요한 페이지입니다.`);
        this.$router.push({ name: 'login' });
      }
    }
  }
};
</script>
