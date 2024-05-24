<template>
  <div style="width: 1000px; margin: 0 auto;">
    <table class="table ">
      <thead>
        <tr class="table-title text-center">
          <th style="width: 10%; color:white;">번호</th>
          <th style="width: 60%; color:white;">제목</th>
          <th style="width: 10%; color:white;">글쓴이</th>
          <th style="width: 20%; color:white;">날짜</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(question, index) in questions" :key="question.id" @click="goToDetail(question.id)">
          <td class="text-center">{{ index + 1 + (currentPage * pageSize) }}</td>
          <td>{{ question.title }}</td>
          <td>{{ question.writer }}</td>
          <td>{{ question.date }}</td>
        </tr>
      </tbody>
    </table>
    <div class="d-flex justify-content-between align-items-center my-4">
      <div>
        <button @click="previousPage" :disabled="currentPage === 0" class="btn btn-outline-primary custom-outline-btn mr-3">
          <i class="bi bi-chevron-left"></i> Page {{ currentPage + 1 }}
        </button>
      </div>
      <div>
        <button v-if="isAdmin" class="btn btn-success" style="background-color: #67BF4E;" @click="faqwrite">등록</button>
      </div>
      <div>
        <button @click="nextPage" :disabled="!hasMore"  class="btn btn-outline-primary custom-outline-btn ml-3">
          Page {{ currentPage + 2 }} <i class="bi bi-chevron-right"></i>
        </button>
      </div>

    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      questions: [],
      currentPage: 0,
      pageSize: 10,
      hasMore: true,
      dept: null,
      isAdmin: false,
    };
  },
  mounted() {
    axios.post("/api/getMemberType", { withCredentials: true }).then((res) => {
      this.dept = res.data.dept;
      this.isAdmin = this.dept === '관리자';
    }).catch(() => {
      this.dept = null;
    });

    this.fetchQuestions();
  },
  methods: {
    fetchQuestions() {
      axios.get(`http://localhost:3000/api/faqquestions/paged?page=${this.currentPage}&size=${this.pageSize}`)
        .then(response => {
          this.questions = response.data.map(question => ({
            ...question,
            date: new Date(question.date).toLocaleDateString('ko-KR', {
              year: 'numeric',
              month: '2-digit',
              day: '2-digit',
              hour: '2-digit',
              minute: '2-digit'
            })
            
          })).reverse(); // 데이터를 역순으로 정렬합니다.
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
    },
    faqwrite() {
      if (!this.isAdmin) {
        alert('관리자만 접근 가능');
      } else {
        this.$router.push('/faqwrite');
      }
    }
  }
};
</script>

<style scoped>
.table-title {
  background-color: #67BF4E;
}
.table-title:hover {
  background-color: #67BF4E;
}
.btn{
  border-color: #67BF4E !important;
  color: white;

}
.custom-outline-btn {
  border-color: #67BF4E !important;
  color: #67BF4E;
}
.custom-outline-btn:hover {
  background-color: #67BF4E !important; 
  border-color: #67BF4E !important; 
  color: white !important; 
}
.custom-outline-btn:disabled {
  border-color: #dcdcdc !important;
  color: #dcdcdc;
}


</style>