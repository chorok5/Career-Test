<template>
  <div>
      <h1>심리검사 결과</h1>
      <div class="result-container">
          <h2>{{ result.title }}</h2>
          <p>{{ result.description }}</p>
          <h3>추천 직업</h3>
          <ul>
              <li v-for="job in result.recommendedJobs" :key="job">{{ job }}</li>
          </ul>
          <h3>당신의 성향</h3>
          <ul>
              <li v-for="trait in result.personalTraits" :key="trait">{{ trait }}</li>
          </ul>
          <div class="action-buttons">
              <button @click="retakeTest">다시 검사하기</button>
              <button @click="shareResult">결과 공유하기</button>
          </div>
      </div>
  </div>
</template>



<script>
import axios from 'axios';

export default {
  data() {
    return {
      result: {}
    };
  },
  created() {
    this.fetchTestResult(this.$route.query.userAnswers);

  },
  methods: {
    fetchTestResult(answers) { // 인자 추가
        // 서버에서 결과 데이터 가져오기
        axios.post('/api/getTestResult', answers) // 엔드포인트 수정 및 데이터 형식 변경
            .then(response => {
                this.result = response.data;
            })
            .catch(error => {
                console.error('Error fetching test result:', error);
            });
    },
    retakeTest() {
      // 재검사 페이지로 이동
      this.$router.push('/CareerTest');
    },
    shareResult() {
      // 결과 공유 기능 구현
    }
  }
}
</script>

<style scoped>
.result-container {
    background-color: #f5f5f5;
    padding: 20px;
    border-radius: 8px;
}

.action-buttons {
    margin-top: 20px;
    display: flex;
    justify-content: space-between;
}
</style>