<template>
  <div>
        <MySidebar></MySidebar>
    <MenuPage/>
    <div class="mainContainer">
      <div class="row">
        <main class="">
          <h2 class="mb-4 title">나의 심리 상담 예약 내역</h2>
          <hr class="line">
          <div class="mt-4">
            <table class="table table-striped">
              <thead>
                <tr class="tr">
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
import MySidebar from '@/components/MySidebar.vue';
import MenuPage from '@/components/MenuPage.vue';

export default {
  name: 'MySimriReserve',
  components: {
    MySidebar,
    MenuPage
  },

  data() {
    return {
      consultations: [
        { id: 1, type: '심리 상담', date: '2023-05-01', status: '상담완료', summary: '진로 상담' },
        { id: 2, type: '심리 상담', date: '2023-05-15', status: '접수진행', summary: '교우 관계 상담' },
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
.line{
  width: 40%;  
}
.title{
  text-align: left;
  font-family: 'sj';
}
.mainContainer{
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
}
.tr{
  font-family: 'sjl';
}
</style>