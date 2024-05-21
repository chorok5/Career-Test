<template>
  <div class="container mt-5">
    <h1 class="mb-4">심리검사 결과</h1>
    <div v-if="isValidResult">
      <div class="mb-3">
        <h2>{{ result.title }}</h2>
        <h3>{{ result.description }}</h3>
        <p>총점: {{ result.totalScore }} / 30</p>
      </div>

      <h3 class="mb-3 title-highlight section-title">추천 직업</h3>
<div class="row flex-nowrap flex-wrap justify-content-center">
  <div class="col-md-2 mb-2 recommended-job" v-for="job in result.recommendedJobs.split(',')" :key="job.trim()">
    <p class="text-center">{{ job.trim() }}</p>
  </div>
</div>

<h3 class="mb-3 title-highlight section-title">당신의 성향</h3>
<div class="row">
  <div class="col-md-4 mb-3" v-for="(trait, index) in result.personalTraits" :key="index">
    <div class="trait-card h-100">
      <div class="card-body">
        <h5 class="card-title">{{ traitTitles[index] }}</h5>
        <p class="card-text">{{ trait }}</p>
      </div>
    </div>
  </div>
</div>

      <div class="text-center">
        <button class="btn btn-primary" @click="retakeTest">다시 검사하기</button>
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

.recommended-job {
    background: linear-gradient(45deg, #49a6ea, #377dff);
    color: #fff;
    padding: 20px;
    margin-bottom: 20px;
    text-align: center;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
    transition: transform 0.2s;
  }

  .recommended-job:hover {
    transform: scale(1.05);
  }

  .trait-card {
    background: #fff;
    padding: 20px;
    margin-bottom: 20px;
    border-radius: 10px;
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s;
  }

  .trait-card:hover {
    transform: scale(1.05);
  }

  .trait-card .card-title {
    font-weight: bold;
    color: #343a40;
  }

  .trait-card .card-text {
    color: #868e96;
  }

  .title-highlight {
    color: #377dff;
    font-weight: bold;
    margin-bottom: 20px;
  }

  .section-title:before {
    content: '\f0c3';
    font-family: 'FontAwesome';
    display: inline-block;
    margin-right: 10px;
    font-size: 1.2em;
    color: #377dff;
  }


</style>
