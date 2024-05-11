<template>
  <div>
    <h1>제목 : {{ question.title }}</h1>
    <p>내용 : {{ question.content }}</p>
    <p>작성자: {{ question.writer }}</p>
    <p>날짜: {{ question.date }}</p>
    <div v-if="isUpdating">
      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" class="form-control" id="title" v-model="question.title" required>
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea class="form-control" id="content" rows="5" v-model="question.content" required></textarea>
      </div>
      <div class="form-group">
        <label for="writer">작성자</label>
        <input type="text" class="form-control" id="writer" v-model="question.writer" required>
      </div>
      <button @click="updateQuestion" class="btn btn-primary">수정 완료</button>
      <button @click="cancelUpdate" class="btn btn-secondary">취소</button>
    </div>
    <div v-else>
      <button @click="startUpdate" class="btn btn-primary">수정하기</button>
      <button @click="deleteQuestion" class="btn btn-danger">삭제하기</button>
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
      axios.get(`/api/faqquestions/${questionId}`)
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
      axios.put(`/api/faqquestions/${questionId}`, this.question)
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
      axios.delete(`/api/faqquestions/${questionId}`)
        .then(() => {
          console.log('Question deleted successfully');
          this.$router.push('/faq');
        })
        .catch(error => {
          console.error('Error deleting question:', error);
        });
    }
  }
}
}
</script>
<style scoped>

</style>