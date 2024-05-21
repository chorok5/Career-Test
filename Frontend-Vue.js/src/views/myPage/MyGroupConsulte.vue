<template>
  <div>
    <HeaderMenu></HeaderMenu>
    <div class="container-fluid mt-5 pt-4">
      <div class="row">
        <MySidebar></MySidebar>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <h1> 아래는 예시입니다. 수정 필수! </h1><br><hr>
          <h1 class="mb-4">비교과(집단 상담) 내역</h1>
          <div class="mt-4">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col">상담 제목</th>
                  <th scope="col">상담 날짜</th>
                  <th scope="col">상담 상태</th>
                  <th scope="col">상담 내용 요약</th>
                  <th scope="col">작업</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="consultation in consultations" :key="consultation.id">
                  <td>{{ consultation.title }}</td>
                  <td>{{ consultation.date }}</td>
                  <td>{{ consultation.status }}</td>
                  <td>{{ consultation.summary }}</td>
                  <td>
                    <button class="btn btn-primary btn-sm" @click="viewConsultation(consultation.id)">보기</button>
                    <button class="btn btn-warning btn-sm" @click="changeConsultation(consultation.id)">변경</button>
                    <button class="btn btn-danger btn-sm" @click="cancelConsultation(consultation.id)">취소</button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </main>
      </div>
    </div>
  </div>
</template>

<script>
import MySidebar from '@/components/MySidebar.vue';

export default {
  name: 'MyGroupConsulte',
  components: {
    MySidebar,
  },
  data() {
    return {
      consultations: [
        { id: 1, title: '집단 상담 1', date: '2023-06-01 10:00', status: '접수 완료', summary: '집단 상담 내용 요약 1' },
        { id: 2, title: '집단 상담 2', date: '2023-06-15 14:00', status: '진행 중', summary: '집단 상담 내용 요약 2' },
      ]
    };
  },
  methods: {
    viewConsultation(id) {
      console.log(`View consultation with id: ${id}`);
    },
    changeConsultation(id) {
      console.log(`Change consultation with id: ${id}`);
    },
    cancelConsultation(id) {
      console.log(`Cancel consultation with id: ${id}`);
    }
  }
}
</script>

<style scoped>
.container-fluid {
  padding-top: 56px; 
}
.table {
  margin-top: 20px;
}
</style>
