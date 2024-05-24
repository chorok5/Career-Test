<template>
  <div class="faqwrite rounded bg-light">
    <h3 class="mb-4 text-center" style="font-weight: bolder;">공지사항 게시판</h3><br>
    <form @submit.prevent="write" class="form-horizontal">
      <div class="mb-3 row align-items-center">
        <label for="title" class="col-sm-2 col-form-label" style="font-weight: bold;">제목</label>
        <div class="col-sm-9">
          <input type="text" class="form-control" id="title" v-model="title" required>
        </div>
      </div>
      <div class="mb-4 row align-items-center">
        <label for="content" class="col-sm-2 col-form-label" style="font-weight: bold;">내용</label>
        <div class="col-sm-9">
          <textarea class="form-control" id="content" rows="5" v-model="content" required></textarea>
        </div>
      </div>
      <div class="row">
        <div class="col-sm-8 offset-sm-2" style="margin-bottom:40px;">
          <button type="submit" class="btn btn-success"  style="background-color: #67BF4E;">글쓰기</button>
        </div>
      </div>
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
      writer: '관리자', // 임의의 작가 이름 설정
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
  width: 1000px;
  margin: 0 auto;
  background-color: rgb(205, 255, 211);
}
.btn{
  border-color: #67BF4E !important;
  color: white;
  margin: 0 auto;
}

</style>