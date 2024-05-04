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
        />
        <label>{{ option.label }}</label>
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
          title: "당신은 어떤 유형의 사람이라고 생각하십니까?",
          options: [
            { label: "외향적", value: "extrovert" },
            { label: "내향적", value: "introvert" }
          ]
        },
        {
          title: "새로운 상황에 적응하는 것에 어려움을 느끼십니까?",
          options: [
            { label: "네, 어려움을 느낍니다", value: "difficulty_adapting" },
            { label: "아니오, 적응하는 데 문제가 없습니다", value: "no_difficulty_adapting" }
          ]
        },
        {
          title: "팀 프로젝트를 좋아하십니까?",
          options: [
            { label: "네, 좋아합니다", value: "likes_teamwork" },
            { label: "아니오, 선호하지 않습니다", value: "dislikes_teamwork" }
          ]
        }
      ],
      userAnswers: []
    };
  },
  methods: {
    submitTest() {
      console.log("Submit test button 클릭됨");
      axios.post('/api/submitTest', this.userAnswers)
        .then(response => {
          console.log(response.data);
          // 결과 페이지로 이동합니다.
          this.$router.push({ name: 'ResultPage', query: { userAnswers: this.userAnswers } });
        })
        .catch(error => {
          console.error('Error submitting test:', error);
        });
    }
  }
}
</script>
