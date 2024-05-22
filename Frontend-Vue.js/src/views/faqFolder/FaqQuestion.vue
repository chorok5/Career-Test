<template>
  <div style="width: 1000px; margin: 0 auto;">
    <table class="table table-hover">
      <thead>
        <tr>
      <th style="width: 10%;">번호</th>
      <th style="width: 40%;">제목</th>
      <th style="width: 20%;">글쓴이</th>
      <th style="width: 30%;">날짜</th>
    </tr>
      </thead>
      <tbody>
        <tr v-for="(question, index) in questions" :key="question.id" :track-by="question.id" @click="goToDetail(question.id)">
          <td>{{ index + 1 + (currentPage * pageSize) }}</td>
          <td>{{ question.title }}</td>
          <td>{{ question.writer }}</td>
          <td>{{ question.date }}</td>
        </tr>
      </tbody>
    </table>
    <div class="d-flex justify-content-between align-items-center my-4">
      <div>
        <button @click="previousPage" :disabled="currentPage === 0" class="btn btn-outline-primary mr-3">
          <i class="bi bi-chevron-left"></i> Page {{ currentPage + 1 }}
        </button>
      </div>
      <div>
        <button @click="nextPage" :disabled="!hasMore" class="btn btn-outline-primary ml-3">
          Page {{ currentPage + 2 }} <i class="bi bi-chevron-right"></i>
        </button>
      </div>
    </div>
    <button @click="$router.push('/faqwrite')" class="btn btn-primary mt-3">글쓰기</button>
  </div>
</template>

<script>
//import 'bootstrap-icons/font/bootstrap-icons.css'
import axios from 'axios';

export default {
  data() {
    return {
      questions: [],
      currentPage: 0,
      pageSize: 10,
      hasMore: true
    }
  },
  created() {
    this.fetchQuestions();
  },
  methods: {
    fetchQuestions() {
  axios.get(`http://localhost:3000/api/faqquestions/paged?page=${this.currentPage}&size=${this.pageSize}`)
    .then(response => {
      console.log('Fetched response:', response);
      this.questions = response.data.map(question => ({
        ...question, // 기존 질문 정보 유지
        date: new Date(question.date).toLocaleDateString('ko-KR', {
          year: 'numeric',
          month: '2-digit',
          day: '2-digit',
          hour: '2-digit',
          minute: '2-digit'
        })
      }));
      this.hasMore = response.data.length === this.pageSize;
    })
    .catch(error => {
      console.error('Error fetching questions:', error);
    });
},
    goToDetail(questionId) {
      this.$router.push(`/faq/${questionId}`);
    },
    nextPage() {
      if (this.hasMore) {
        this.currentPage++;
        this.fetchQuestions();
      }
    },
    previousPage() {
      if (this.currentPage > 0) {
        this.currentPage--;
        this.fetchQuestions();
      }
  }
  
  }
}
</script>
