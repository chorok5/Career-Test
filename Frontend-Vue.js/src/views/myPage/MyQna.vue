<template>
  <div>
    <MenuPage />
    <MyPageSecMenu />
    <div class="container-fluid mt-5 pt-4">
      <div class="row">
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <h1> 아래는 예시입니다. 수정 필수! </h1><br><hr>
          <h1 class="mb-4">나의 문의 내역</h1>
          <div class="mt-4">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th scope="col">문의 제목</th>
                  <th scope="col">문의 날짜</th>
                  <th scope="col">문의 상태</th>
                  <th scope="col">문의 내용 요약</th>
                  <th scope="col">답변</th>
                  <th scope="col">작업</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="inquiry in inquiries" :key="inquiry.id">
                  <td>{{ inquiry.title }}</td>
                  <td>{{ inquiry.date }}</td>
                  <td>{{ inquiry.status }}</td>
                  <td>{{ inquiry.summary }}</td>
                  <td>{{ inquiry.response || '답변 대기 중' }}</td>
                  <td>
                    <button class="btn btn-danger btn-sm" @click="deleteInquiry(inquiry.id)">삭제</button>
                    <button class="btn btn-primary btn-sm" @click="editInquiry(inquiry.id)">수정</button>
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
import MenuPage from '@/components/MenuPage.vue';
import MyPageSecMenu from '@/views/mypage/MyPageSecMenu.vue';

export default {
  name: 'MyQna',
  components: {
    MenuPage,
    MyPageSecMenu
  },
  data() {
    return {
      inquiries: [
        { id: 1, title: '문의 1', date: '2023-05-01', status: '답변 완료', summary: '문의 내용 요약 1', response: '답변 내용 1' },
        { id: 2, title: '문의 2', date: '2023-05-15', status: '답변 대기 중', summary: '문의 내용 요약 2', response: '' },
      ]
    };
  },
  methods: {
    deleteInquiry(id) {
      console.log(`Delete inquiry with id: ${id}`);
    },
    editInquiry(id) {
      console.log(`Edit inquiry with id: ${id}`);
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
