<template>
  <div class="container mt-5">
    <div class="card">
      <div class="card-header">
        <h2 class="card-title">{{ question.title }}</h2>
      </div>
      <div class="card-body">
        <p class="card-text">내용: {{ question.content }}</p>
        <p class="card-text">작성자: {{ question.writer }}</p>
        <p class="card-text">날짜: {{ new Date(question.date).toLocaleDateString('ko-KR', {
          year: 'numeric', month:
            '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' }) }}</p>
      </div>
      <div class="card-footer">
        <div class="d-flex justify-content-end">
          <div v-if="isUpdating">
            <button @click="updateQuestion" class="btn btn-primary">수정 완료</button>
            <button @click="cancelUpdate" class="btn btn-secondary">취소</button>
          </div>
          <div v-else>
            <button @click="goBack" class="btn btn-info">돌아가기</button>
            <button @click="startUpdate" class="btn btn-primary">수정하기</button>
            <button @click="deleteQuestion" class="btn btn-danger">삭제하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      question: {},
      isUpdating: false
    }
  },
  created() {
    this.fetchQuestion();
  },
  methods: {
    fetchQuestion() {
      const questionId = this.$route.params.id;
      axios.get(`http://localhost:3000/api/faqquestions/${questionId}`)
        .then(response => {
          this.question = response.data;
        })
        .catch(error => {
          console.error('Error fetching question:', error);
        });
    },
    startUpdate() {
      this.isUpdating = true;
    },
    cancelUpdate() {
      this.isUpdating = false;
      this.fetchQuestion(); // 원래 데이터 가져오기
    },
    updateQuestion() {
      if (confirm('수정하시겠습니까?')) {
        const questionId = this.$route.params.id;
        axios.put(`http://localhost:3000/api/faqquestions/${questionId}`, this.question)
          .then(() => {
            console.log('Question updated successfully');
            this.isUpdating = false;
          })
          .catch(error => {
            console.error('Error updating question:', error);
          });
      }
    },
    deleteQuestion() {
      if (confirm('정말 삭제하시겠습니까?')) {
        const questionId = this.$route.params.id;
        axios.delete(`http://localhost:3000/api/faqquestions/${questionId}`)
          .then(() => {
            console.log('Question deleted successfully');
            this.$router.push('/faq');
          })
          .catch(error => {
            console.error('Error deleting question:', error);
          });
      }
    },
    goBack() {
      this.$router.go(-1); // 이전 페이지로 이동
    }
  }
}
</script>

<style scoped>
</style>
