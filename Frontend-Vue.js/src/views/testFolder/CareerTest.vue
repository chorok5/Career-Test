<template>
  <div class="container">
    <h1 class="text-center mt-5 mb-4">심리검사</h1>
    <div v-for="(question, index) in questions" :key="question.qid" class="card mb-4">
      <div class="card-body" v-if="index !== 10">
        <h3 class="card-title">{{ question.ttitle }}</h3>
        <div class="form-check d-flex flex-row flex-wrap" v-for="(option, optionIndex) in question.options" :key="optionIndex">
          <input type="radio" :name="'question-' + question.qid" :value="option.value" v-model="userAnswers[question.qid]" class="form-check-input" :id="'option' + question.qid + '-' + optionIndex" />
          <label :for="'option' + question.qid + '-' + optionIndex" class="form-check-label mr-3">{{ option.label }}</label>
        </div>
      </div>
    </div>
    <!-- 결과 출력 -->
    <div class="card mb-4" v-if="result.totalScore !== null">
      <div class="card-body">
        <h3 class="card-title">최종 결과</h3>
        <p>총 점수: {{ result.totalScore }}</p>
        <p>추천 직업: {{ result.recommendedJobs }}</p>
        <p>개인 성향: {{ result.personalTraits }}</p>
      </div>
    </div>
    <button @click="submitTest" class="btn btn-primary btn-block">완료</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      questions: [
        {
          qid: 1,
          tcategory: "새로운 환경에 대한 적응성",
          ttitle: "새로운 상황에 대한 적응이 빠른 편인가요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 2,
          tcategory: "사회적 활동 선호도",
          ttitle: "다른 사람들과의 상호 작용 및 활동에 대한 선호도는 높은 편인가요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 3,
          tcategory: "독립적 업무 선호도",
          ttitle: "여러 사람이 같이 일하는 것을 선호하시나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 4,
          tcategory: "자기 계발에 대한 관심",
          ttitle: "자기 계발 및 학습에 대한 관심이 많은가요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 5,
          tcategory: "팀 프로젝트 참여 선호도",
          ttitle: "팀 프로젝트에 참여하는 것을 선호하십니까?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 6,
          tcategory: "고도의 스트레스 관리 능력",
          ttitle: "고도의 스트레스를 관리하고 대처하는 능력이 있나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 7,
          tcategory: "빠른 의사 결정 능력",
          ttitle: "빠른 시간 내에 의사 결정을 내릴 수 있는 능력이 있나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {

          qid: 8,
          tcategory: "문제 해결능력",
          ttitle: "문제 해결능력을 갖추고 있나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 9,
          tcategory: "창의성 및 혁신성",
          ttitle: "창의적인 생각과 혁신적인 아이디어를 개발하는 것을 선호하나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 10,
          tcategory: "업무에 대한 열정",
          ttitle: "해당 분야의 업무에 대한 열정이 있나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
      ],
      userAnswers: {},
      result: {
        totalScore: null,
        recommendedJobs: "",
        personalTraits: ""
      }
    };
  },
  created() {
    this.fetchQuestions();
  },
  methods: {
    fetchQuestions() {
      axios.get('/api/questions')
        .then(response => {
          this.questions = response.data;
          this.initializeAnswers();
        })
        .catch(error => {
          console.error('Error fetching questions:', error);
        });
    },
    initializeAnswers() {
      this.questions.forEach(question => {
        this.$set(this.userAnswers, question.qid, null); // Vue.set을 사용하여 리액티브하게 userAnswers 초기화
      });
    },
    submitTest() {
      const isAllAnswered = Object.values(this.userAnswers).every(answer => answer !== null);
      if (!isAllAnswered) {
        alert("모든 질문에 답해주세요.");
        return;
      }
        
      // this.userAnswers를 배열로 변환
       const answersArray = Object.values(this.userAnswers);
  
      axios.post('/api/submitTest', answersArray)
        .then(response => {
          this.result = response.data;
          this.$router.push({ name: 'ResultPage', params: { result: JSON.stringify(this.result) } });

        })
        .catch(error => {
          console.error('Error submitting test:', error);
        });
    }
  }
};
</script>


<style lang="scss">
.container {
  max-width: 600px;
  margin: auto;
  padding: 0 15px;
}

.card {
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-body {
  padding: 1.25rem;
}

.btn {
  font-size: 1rem;
  padding: 0.75rem 1.5rem;
}
</style>