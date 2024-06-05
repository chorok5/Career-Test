<template>
  <div class="container mt-5">
    <h1 class="mb-4 text-center text-primary">심리검사 결과</h1>
    <div v-if="isValidResult" class="result-container shadow-lg">
      <div class="mb-4">
        <h2 class="mb-2 text-center text-primary">{{ result.title }}</h2>
        <h3 class="mb-3 text-center text-muted">{{ result.description }}</h3>
        <p class="mb-2 text-center" style="font-size: 18px;">
          총점: <span class="badge badge-info" style="color:cadetblue; font-size: 18px;">{{ result.totalScore }} /30</span>
        </p>
      </div>
      <br>
      <h3 class="mb-3 title-highlight text-center">추천 직업</h3>
      <div class="row flex-nowrap flex-wrap justify-content-center">
        <div class="col-md-2 mb-2 recommended-job d-flex align-items-center justify-content-center"
          v-for="job in result.recommendedJobs.split(',')" :key="job.trim()" style="margin-right: 10px;">
          <p class="mb-0">{{ job.trim() }}</p>
        </div>
      </div>
      <br><br>
      <h3 class="mb-3 title-highlight text-center">당신의 성향</h3>
      <div class="row">
        <div class="col-md-4 mb-3" v-for="(trait, index) in result.personalTraits" :key="index">
          <div class="trait-card h-100">
            <div class="card-body">
              <h5 class="card-title">{{ traitTitles[index] }}</h5><br>
              <ul class="card-text" style="text-align: left;">
                <li v-for="(item, i) in trait.split(',')" :key="i" style="margin-left: 10px;">
                  {{ item.trim() }}
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
      <div class="text-center mt-4">
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
      const answersArray = Object.values(answers).map(answer => parseInt(answer));

      axios.post('http://localhost:3000/api/getTestResult', answersArray)
        .then(response => {
          if (!response.data || !response.data.totalScore) {
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
  background-color: #fff;
  padding: 30px;
  border-radius: 10px;
}

.recommended-job {
  background: linear-gradient(45deg, #1166c1, #931ed6);
  color: #fff;
  padding: 15px;
  margin-bottom: 20px;
  text-align: center;
  border-radius: 50px;
  /* 원형 모양으로 변경 */
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
  transition: transform 0.2s;
  font-weight: bold;
  /* 글자 두껍게 */
  text-transform: uppercase;
  /* 모든 글자 대문자로 */
}

.recommended-job:hover {
  transform: scale(1.05);
}

.trait-card {
  background: #f8f9fa;
  padding: 20px;
  margin-bottom: 20px;
  border-radius: 5px;
  box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
}

.trait-card:hover {
  transform: scale(1.05);
}

.trait-card .card-title {
  font-weight: bold;
}

.title-highlight {
  position: relative;
  display: inline-block;
  padding-bottom: 5px;
}

.title-highlight::after {
  content: "";
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
  height: 2px;
  background: linear-gradient(to right, #007bff, #6c757d);
}
</style>
