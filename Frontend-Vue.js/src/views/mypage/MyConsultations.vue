<template>
  <div>
    <HeaderMenu></HeaderMenu>
    <div class="container-fluid mt-5 pt-4">
      <div class="row">
        <MySidebar></MySidebar>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <h1> 아래는 예시입니다. 수정 필수! </h1><br><hr>
          <h1 class="mb-4">나의 상담 내역</h1>
          <div class="mt-4">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col">상담 종류</th>
                  <th scope="col">상담 날짜</th>
                  <th scope="col">상담 상태</th>
                  <th scope="col">상담 내용 요약</th>
                  <th scope="col">작업</th>
                </tr>
              </thead>
              <tbody>
                
                <tr v-for="consultation in consultations" :key="consultation.id">
                  <td>{{ consultation.type }}</td>
                  <td>{{ consultation.date }}</td>
                  <td>{{ consultation.status }}</td>
                  <td>{{ consultation.summary }}</td>
                  <td>
                    <button class="btn btn-danger btn-sm" @click="cancelConsultation(consultation.id)">취소</button>
                    <button class="btn btn-primary btn-sm" @click="rescheduleConsultation(consultation.id)">변경</button>
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
import HeaderMenu from '@/components/HeaderMenu.vue';
import MySidebar from '@/components/MySidebar.vue';

export default {
  name: 'MyConsultations',
  components: {
    HeaderMenu,
    MySidebar,
  },
  data() {
    return {
      consultations: [
        { id: 1, type: '개인 상담', date: '2023-05-01', status: '상담완료', summary: '진로 상담' },
        { id: 2, type: '집단 상담', date: '2023-05-15', status: '접수진행', summary: '스터디 그룹 상담' },
      ]
    };
  },
  methods: {
    cancelConsultation(id) {
      console.log(`Cancel consultation with id: ${id}`);
    },
    rescheduleConsultation(id) {
      console.log(`Reschedule consultation with id: ${id}`);
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
