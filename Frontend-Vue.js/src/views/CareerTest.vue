<template>
  <div>
    <h1>심리검사</h1>
    <div v-for="(question, index) in questions" :key="index">
      <h3>{{ question.title }}</h3>
      <div v-for="(option, optionIndex) in question.options" :key="optionIndex">
        <input
          type="radio"
          :name="'question-' + index"
          :value="option.value"
          v-model="userAnswers[index]"
          :checked="userAnswers[index] === option.value"
          id="option{{ index }}{{ optionIndex }}"
        />
        <label :for="'option' + index + optionIndex" @click="selectOption(index, option.value)">
          {{ option.label }}
        </label>
      </div>
    </div>
    <button @click="submitTest">완료</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
  return {
    questions: [
      {
        category: "새로운 환경에 대한 적응성",
        title: "새로운 상황에 대한 적응이 빠른 편인가요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "사회적 활동 선호도",
        title: "다른 사람들과의 상호 작용 및 활동에 대한 선호도는 높은 편인가요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "독립적 업무 선호도",
        title: "여러 사람이 같이 일하는 것을 선호하시나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "자기 계발에 대한 관심",
        title: "자기 계발 및 학습에 대한 관심이 많은가요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "팀 프로젝트 참여 선호도",
        title: "팀 프로젝트에 참여하는 것을 선호하십니까?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "고도의 스트레스 관리 능력",
        title: "고도의 스트레스를 관리하고 대처하는 능력이 있나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "빠른 의사 결정 능력",
        title: "빠른 시간 내에 의사 결정을 내릴 수 있는 능력이 있나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "문제 해결능력",
        title: "문제 해결능력을 갖추고 있나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "창의성 및 혁신성",
        title: "창의적인 생각과 혁신적인 아이디어를 개발하는 것을 선호하나요?",
        options: [
        { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
      },
      {
        category: "업무에 대한 열정",
        title: "해당 분야의 업무에 대한 열정이 있나요?",
        options: [
          { label: "그렇다", value: 3 }, { label: "보통이다", value: 2},{ label: "아니다", value: 1}
        ]
        },
      ],
      userAnswers: []
    };
  },
  methods: {
    selectOption(index, value) {
      // 라디오 버튼의 값을 선택된 값으로 설정
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
