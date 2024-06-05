<template>
  <div>
    <MySidebar></MySidebar>
    <MenuPage />
    <div class="mainContainer">
      <div class="row">
        <main class="">
          <h2 class="mb-4 title">나의 리뷰</h2>
          <hr class="line">
          <div class="mt-4">
            <table class="table table-striped">
              <thead>
                <tr class="tr">
                  <th scope="col">리뷰 제목</th>
                  <th scope="col">작성 날짜</th>
                  <th scope="col">리뷰 상태</th>
                  <th scope="col">리뷰 내용 요약</th>
                  <th scope="col">작업</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="review in reviews" :key="review.id">
                  <td>{{ review.title }}</td>
                  <td>{{ review.date }}</td>
                  <td>{{ review.status }}</td>
                  <td>{{ review.summary }}</td>
                  <td>
                    <button class="btn btn-primary btn-sm" @click="viewReview(review.id)">보기</button>
                    <button class="btn btn-danger btn-sm" @click="deleteReview(review.id)">삭제</button>
                    <button class="btn btn-secondary btn-sm" @click="editReview(review.id)">수정</button>
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
  name: 'MyReviews',
  components: {
    MySidebar,
  },
  data() {
    return {
      reviews: [
        { id: 1, title: '리뷰 1', date: '2023-05-01', status: '게시됨', summary: '리뷰 내용 요약 1' },
        { id: 2, title: '리뷰 2', date: '2023-05-15', status: '대기 중', summary: '리뷰 내용 요약 2' },
      ]
    };
  },
  methods: {
    viewReview(id) {
      console.log(`View review with id: ${id}`);
    },
    deleteReview(id) {
      console.log(`Delete review with id: ${id}`);
    },
    editReview(id) {
      console.log(`Edit review with id: ${id}`);
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

.line {
  width: 40%;
}

.title {
  text-align: left;
  font-family: 'sj';
}

.mainContainer {
  width: 100%;
  max-width: 1200px;
  margin: 0 auto;
}

.tr {
  font-family: 'sjl';
}
</style>
