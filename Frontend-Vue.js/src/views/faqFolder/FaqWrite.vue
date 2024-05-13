<template>
  <div class="faqwrite">
    <h1>글쓰기</h1>
    <form @submit.prevent="write">
      <div class="form-group">
        <label for="title">제목</label>
        <input type="text" class="form-control" id="title" v-model="title" required>
      </div>
      <div class="form-group">
        <label for="content">내용</label>
        <textarea class="form-control" id="content" rows="5" v-model="content" required></textarea>
      </div>
      <button type="submit" class="btn btn-primary">글쓰기</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "WritePage",
  data() {
    return {
      title: null,
      content: null,
      writer: 'Anonymous', // 임의의 작가 이름 설정
      date: null // 초기화하지 않음
    }
  },
  methods: {
    write() {
      axios.post('http://localhost:3000/api/faqquestions', {
        title: this.title,
        content: this.content,
        writer: this.writer,
        date: new Date().toISOString() // 현재 시간을 ISO 형식으로 설정
      }, 
      {
      })
        .then(response => {
          console.log('글쓰기 성공:', response.data);
          this.$router.push('/faq');
        })
        .catch(error => {
          console.error('글쓰기 오류:', error);
        });
    }
  }
};
</script>


<style>
.faqwrite {
  width: 100%;
  height: 500px;
  background-color: rgb(205, 255, 211);
  padding: 10px;
}

.faqwrite input {
  width: 100%;
  height: 30px;
  margin-bottom: 5px;
  box-sizing: border-box;
}

.faqwrite textarea {
  width: 100%;
  height: 300px;
  box-sizing: border-box;
}
</style>