<template>
  <div>
    <table class="table table-hover">
      <thead>
        <tr>
          <th>번호</th>
          <th>제목</th>
          <th>글쓴이</th>
          <th>날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(question, index) in questions" :key="question.id" @click="goToDetail(question.id)">
          <td>{{ index + 1 }}</td>
          <td>{{ question.title }}</td>
          <td>{{ question.writer }}</td>
          <td>{{ question.date }}</td>
        </tr>
      </tbody>
    </table>
    <button @click="$router.push('/faqwrite')">글쓰기</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      questions: []
    }
  },
  created() {
    this.fetchQuestions();
  },
  methods: {
    fetchQuestions() {
      axios.get('http://localhost:3000/api/faqquestions')
        .then(response => {
          this.questions = response.data;
        })
        .catch(error => {
          console.error('Error fetching questions:', error);
        });
    },
    goToDetail(questionId) {
      this.$router.push(`/faq/${questionId}`);
    }
  }
}
</script>
