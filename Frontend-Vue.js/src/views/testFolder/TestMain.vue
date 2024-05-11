<template>
    <div class="container">
      <h2>Test 페이지</h2><br><br>
      <div class="row">
        <div class="col-md-6">
          <router-link to="/test/career" class="btn btn-primary btn-lg btn-block">Career Test로 이동하기</router-link>
        </div>
        <div class="col-md-6">
          <router-link to="/test/person" class="btn btn-success btn-lg btn-block">Person Test로 이동하기</router-link>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    name: 'TestPage'
  };
  </script>
  