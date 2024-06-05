<template>
  <div>
    <MySidebar></MySidebar>
    <MenuPage />
    <div class="mainContainer boundary">
      <div class="row">
        <main class="">
          <h2 class="mb-4 title">나의 상담 내역</h2>
          <hr class="line">
          <div class="mt-4">
            <table class="table">
              <thead>
                <tr class="tr">
                  <th>상담 예약일</th>
                  <th>상담 희망시간</th>
                  <th>상담 종류</th>
                  <th>신청자 이름</th>
                  <th>신청자 학번</th>
                  <th>상담 일지</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="rsv in state.rsvs" v-bind:key="rsv.id">
                  <th>{{ rsv.cdate }}</th>
                  <td>{{ rsv.time }}</td>
                  <td>{{ rsv.ctype }}</td>
                  <td>{{ rsv.name }}</td>
                  <td>{{ rsv.sid }}</td>
                  <td>
                    <router-link v-if="rsv.writed === 1" :to="`/ProResult/${rsv.rsvno}`">
                      <button class="done">상담일지 보기</button>
                    </router-link>
                    <router-link v-else :to="`/ProResultWrite/${rsv.rsvno}`">
                      <button class="save">상담일지 작성</button>
                    </router-link>
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
import MenuPage from "@/components/MenuPage.vue";
import MySidebar from "@/components/MySidebar.vue";
import { reactive } from "vue";
import axios from "axios";

export default {
  name: "MyConsultations",
  components: {
    MenuPage,
    MySidebar,
  },

  setup() {
    const state = reactive({
      rsvs: [],
    });

    const load = () => {
      axios
        .get("http://localhost:3000/api/rsv/list", { withCredentials: true })
        .then((response) => {
          state.rsvs = response.data;
          console.log(response.data);
        });
    };

    // 컴포넌트가 마운트될 때 초기 데이터를 불러옴
    load();

    return { state };
  },
};
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

.tr {
  font-family: 'sjl';
}

table thead :hover {
  background-color: inherit !important;
}

table {
  text-align: center;
}

.done {
  color: black;
  background-color: white;
  border: 1px solid #c0c0c0;
}

.btn-view {
  background-color: rgb(155, 155, 175);
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
}

.btn-write {
  background-color: green;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
}

.mainContainer {
  padding-left: 20px;
}
</style>
