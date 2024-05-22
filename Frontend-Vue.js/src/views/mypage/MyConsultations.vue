 <template>
  <div>
        <MySidebar></MySidebar>
    <MenuPage />
    <div class="container-fluid mt-5 pt-4">
      <div class="row">
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
          <h1>아래는 예시입니다. 수정 필수!</h1>
          <br />
          <hr />
          <h1 class="mb-4">나의 상담 내역</h1>
          <div class="mt-4">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>번호</th>
                  <th>신청자 학번</th>
                  <th>신청자 이름</th>
                  <th>예약 일자</th>
                  <th>상담 희망일</th>
                  <th>상담 희망시간</th>
                  <th>상담 상태</th>
                  <th>상담 일지</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="rsv in state.rsvs" v-bind:key="rsv.id">
                  <th>{{ rsv.rsvno }}</th>
                  <td>{{ rsv.sid }}</td>
                  <td>{{ rsv.name }}</td>
                  <td>{{ rsv.rsvdate }}</td>
                  <td>{{ rsv.cdate }}</td>
                  <td>{{ rsv.ctime }}</td>
                  <td>상담 상태 만들어??</td>
                  <td>
                    <button @click="addToCart(rsv.rsvno)">상담일지 작성</button>
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
        .get("http://localhost:3000/api/rsv/test", { withCredentials: true })
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

<style>
.container-fluid {
  padding-top: 56px;
}
.table {
  margin-top: 20px;
}
</style>

<!-- <template>
  <div>
    <br />
    <div class="sbmContainer boundary">
      <table class="subMenu">
        <tr>
          <th :style="select[0]" @click="navigate(0)">나의 정보 관리</th>
          <th :style="select[1]" @click="navigate(1)">나의 상담 내역</th>
          <th :style="select[2]" @click="navigate(2)">나의 심리 검사</th>
          <th :style="select[3]" @click="navigate(3)">나의 문의 내역</th>
        </tr>
      </table>
    </div>
    <section class="section s1" :style="cssAct[0]"></section>
    <section class="section s2" :style="cssAct[1]"></section>
    <section class="section s3" :style="cssAct[2]"></section>
    <section class="section s4" :style="cssAct[3]"></section>

   여기서부터 본문
    <div>
      <h1>나의 상담 내역</h1>
      <table>
      <thead>
          <tr>
              <th>번호</th>
              <th>신청자 학번</th>
              <th>신청자 이름</th>
              <th>예약 일자</th>
              <th>상담 희망일</th>
              <th>상담 희망시간</th>
              <th>상담 상태</th>
              <th>상담 일지</th>
          </tr>
      </thead>
      <tbody>
          <tr v-for="rsv in state.rsvs" v-bind:key="rsv.id">
              <th>{{ rsv.rsvno }}</th>
              <td>{{ rsv.sid }}</td>
              <td>{{ rsv.name }}</td>
              <td>{{ rsv.rsvdate }}</td>
              <td>{{ rsv.cdate }}</td>
              <td>{{ rsv.ctime }}</td>
              <td> 상담 상태 만들어??</td>
              <td><button @click="addToCart(rsv.rsvno)">상담일지 작성</button></td>
          </tr>
      </tbody>
    </table>
    </div>
  </div>
</template>
  
  <script>
import { reactive } from 'vue';
import axios from 'axios';

export default {

  setup(){
    const state = reactive({
    rsvs:[]
    })

    const load = () => {
    axios.get('http://localhost:3000/api/rsv/test', { withCredentials: true })
    .then(response => {
    state.rsvs = response.data;
    console.log(response.data);
    })
    };

    // 컴포넌트가 마운트될 때 초기 데이터를 불러옴
    load();

    return {state};
  },

  data() {
    return {
      act: "none",
      cssAct: [
        { display: "none" },
        { display: "none" },
        { display: "none" },
        { display: "none" },
      ],
      select: [{}, {}, {}, {}],
    };
  },
  mounted() {
    this.cssAct[0] = { display: "block" };
    this.select[1] = {
      // 여기로 아예 이동하게 만드는거야
      border: "2px solid rgb(43, 43, 165",
      "border-bottom": "none",
    };
  },

  methods: {
    displayAct(num) {
      this.cssAct = this.cssAct.map((style, i) => {
        if (i == num) {
          return { display: "block" };
        }
        return { display: "none" };
      });
      this.select = this.select.map((style, i) => {
        if (i == num) {
          return {
            border: "2px solid rgb(43, 43, 165",
            "border-bottom": "none",
          };
        }
        return {};
      });
    },
    navigate(num) {
      this.displayAct(num); // displayAct 메서드 호출

      const routes = [
        "/mypage", // 나의 정보 관리
        "/myconsultations", // 나의 상담 내역
        "/mysimrireserve", // 나의 심리 검사
        "/myqna", // 나의 문의 내역
      ];

      this.$router.push(routes[num]); // Vue 라우터를 사용하여 URL 변경
    },
  },
};
</script>
  
  <style>
.sbmContainer {
  width: 100%;
  margin-bottom: 50px;
}
.subMenu {
  width: 100%;
  margin: auto;
}
.subMenu tr th {
  width: 25%;
  height: 55px;
  border-bottom: 2px solid rgb(43, 43, 165);
}
.boundary {
  width: 100%;
  max-width: 1200px;
  padding: 0 auto;
}
</style> -->
