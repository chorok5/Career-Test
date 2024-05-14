<template>
  <div>
    <h1>심리검사 결과</h1>
    <div v-if="isValidResult">
      <div class="result-container">
        <h2>{{ result.title }}</h2>
        <h3>{{ result.description }}</h3>
        <p>총점: {{ result.totalScore }} / 30</p>
        <h3>추천 직업</h3>
        <ul>
          <li v-for="job in result.recommendedJobs.split(',')" :key="job.trim()">
            {{ job.trim() }}
          </li>
        </ul>
        <h3>당신의 성향</h3>
        <ul>
          <li v-for="(trait, index) in result.personalTraits" :key="index">
            <strong>{{ traitTitles[index] }}:</strong>
            <ul>
              <li>{{ trait }}</li>
            </ul>
          </li>
        </ul>
      </div>
      <div class="action-buttons">
        <button @click="retakeTest">다시 검사하기</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      result: {
        title: '',
        description: '',
        recommendedJobs: '',
        personalTraits: [],
        totalScore: 0
      },
      traitTitles: ['외향성 여부 분석', '대인관계 성향 분석', '문제 해결 능력 성향 분석']
    };
  },
  created() {
    this.fetchTestResult(this.$route.query.userAnswers);
  },
  computed: {
    isValidResult() {
      return Object.keys(this.result).length > 0;
    }
  },

  methods: {
    fetchTestResult(answers) {
      console.log('result page 전송하는 데이터:', answers);
      const answersArray = Object.values(answers).map(answer => parseInt(answer));

      axios.post('http://localhost:3000/api/getTestResult', answersArray)
        .then(response => {
          if (!response.data || !response.data.totalScore) {
            console.error('Invalid response data:', response.data);
            alert('잘못된 결과 데이터를 받았습니다. 관리자에게 문의하세요.');
            return;
          }
          // this.result.title = '심리검사 결과'; 
          this.result.description = '당신의 성향과 추천 직업을 확인하세요.';
          this.result.totalScore = response.data.totalScore; 
          this.result.recommendedJobs = response.data.recommendedJobs;
          this.result.personalTraits = response.data.personalTraits; 
        })
        .catch(error => {
          console.error('Error fetching test result:', error);
          alert('결과를 불러오는 데 실패했습니다. 다시 시도해주세요.');
        });
    },
    retakeTest() {
      if (this.isValidResult) {
        this.$router.push('/test/career');
      } else {
        alert('결과를 불러오는 중입니다. 잠시 후에 다시 시도해주세요.');
      }
    }
  }
};
</script>


<style scoped>
.result-container {
  background-color: #f5f5f5;
  padding: 20px;
  border-radius: 8px;
}

.action-buttons {
  margin-top: 20px;
  align-items: center;
}
</style>
