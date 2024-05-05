<template>
    <div>
      <h1>내 프로필</h1>
      <form @submit.prevent="updateProfile">
        <label for="name">이름:</label>
        <input id="name" v-model="name" />
        <label for="email">이메일:</label>
        <input id="email" v-model="email" />
        <button type="submit">프로필 저장</button>
      </form>
      <h2>이전 검사 기록</h2>
      <div v-for="test in testHistory" :key="test.id">
        <p>{{ test.date }} - {{ test.result.description }}</p>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        name: '',
        email: '',
        testHistory: []
      }
    },
    mounted() {
      // 사용자 정보와 이전 검사 기록 가져오기
      this.fetchUserData()
    },
    methods: {
      fetchUserData() {
        // API 호출을 통해 사용자 데이터 가져오기
        this.name = 'John Doe'
        this.email = 'john.doe@example.com'
        this.testHistory = [
          { id: 1, date: '2023-04-01', result: { description: '외향적이고 감성적인 성격' }},
          { id: 2, date: '2023-03-15', result: { description: '내향적이고 논리적인 성격' }}
        ]
      },
      updateProfile() {
        // API 호출을 통해 사용자 프로필 업데이트
        console.log('프로필 정보 업데이트:', { name: this.name, email: this.email })
      }
    }
  }
  </script>
  