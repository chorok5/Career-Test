<template>
  <div class="container">
    <h1 class="text-center mt-5 mb-4">심리검사</h1>
    <div v-for="(question, index) in questions" :key="index" class="card mb-4">
      <div class="card-body">
        <h3 class="card-title">{{ question.ttitle }}</h3>
        <div class="form-check d-flex flex-row flex-wrap" v-for="(option, optionIndex) in question.options" :key="optionIndex">
          <input
            type="radio"
            :name="'question-' + index"
            :value="option.value"
            v-model="userAnswers[index]"
            class="form-check-input"
            :id="'option' + index + optionIndex"
          />
          <label :for="'option' + index + optionIndex" class="form-check-label mr-3">{{ option.label }}</label>
        </div>
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
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        qid: 2,
        tcategory: "사회적 활동 선호도",
        ttitle: "다른 사람들과의 상호 작용 및 활동에 대한 선호도는 높은 편인가요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        qid: 3,
        tcategory: "독립적 업무 선호도",
        ttitle: "여러 사람이 같이 일하는 것을 선호하시나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        qid: 4,
        tcategory: "자기 계발에 대한 관심",
        ttitle: "자기 계발 및 학습에 대한 관심이 많은가요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        qid: 5,
        tcategory: "팀 프로젝트 참여 선호도",
        ttitle: "팀 프로젝트에 참여하는 것을 선호하십니까?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        qid: 6,
        tcategory: "고도의 스트레스 관리 능력",
        ttitle: "고도의 스트레스를 관리하고 대처하는 능력이 있나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        qid: 7,
        tcategory: "빠른 의사 결정 능력",
        ttitle: "빠른 시간 내에 의사 결정을 내릴 수 있는 능력이 있나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {

        qid: 8,
        tcategory: "문제 해결능력",
        ttitle: "문제 해결능력을 갖추고 있나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        qid: 9,
        tcategory: "창의성 및 혁신성",
        ttitle: "창의적인 생각과 혁신적인 아이디어를 개발하는 것을 선호하나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        qid: 10,
        tcategory: "업무에 대한 열정",
        ttitle: "해당 분야의 업무에 대한 열정이 있나요?",
        options: [
          { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
        },
      ],
      userAnswers: []
    };
  },
  methods: {
    selectOption(index, value) { // 라디오 버튼의 값을 선택된 값으로 설정
      this.userAnswers[index] = value;
    },
    submitTest() {
      // console.log("Submit test button 클릭됨");
      axios.post('/api/submitTest', this.userAnswers)
        .then(response => {
          console.log(response.data);
          this.$router.push({ name: 'ResultPage', query: { userAnswers: this.userAnswers } });
        })
        .catch(error => {
          console.error('Error submitting test:', error);
        });
    }
  }
}
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