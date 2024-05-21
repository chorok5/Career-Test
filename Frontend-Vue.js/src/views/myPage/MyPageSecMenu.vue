<template>
  <div>
    <br />
    <div class="sbmContainer boundary">
      <table class="subMenu">
        <tr>
          <th :style="select[0]">
            <router-link :to="{ name: 'mypage' }" class="router-link" @click="handleClick(0, 'mypage', $event)">나의 정보 관리</router-link>
          </th>
          <!-- <th :style="select[1]" @click="displayAct(1)">나의 상담 내역</th> -->
          <th :style="select[1]">
            <router-link :to="{ name: 'myconsultations' }" class="router-link" @click="handleClick(1, 'myconsultations', $event)">나의 상담 내역</router-link>
          </th>
          <!-- <th :style="select[2]" @click="displayAct(2)">비교과(집단 상담)내역</th> -->
          <th :style="select[2]">
            <router-link :to="{ name: 'mysimri' }" class="router-link" @click="handleClick(2, 'mysimri', $event)">나의 심리검사 결과</router-link>
          </th>
          <!-- <th :style="select[3]" @click="displayAct(3)">나의 심리검사 결과</th> -->
          <th :style="select[3]">
            <router-link :to="{ name: 'mysimri' }" class="router-link" @click="handleClick(3, 'mysimri')">나의 심리검사 결과</router-link>
          </th>
          <!-- <th :style="select[4]" @click="displayAct(4)">나의 문의 내역</th> -->
          <th :style="select[4]">
            <router-link :to="{ name: 'myqna' }" class="router-link" @click="handleClick(4, 'myqna', $event)">나의 문의 내역</router-link>
          </th>
        </tr>
      </table>
    </div>
    <section class="section s1" :style="cssAct[0]"></section>
    <section class="section s2" :style="cssAct[1]"></section>
    <section class="section s3" :style="cssAct[2]"></section>
    <section class="section s4" :style="cssAct[3]"></section>
    <section class="section s4" :style="cssAct[4]"></section>
  </div>
</template>
  
  <script>
export default {
  data() {
    return {
      act: "none",
      cssAct: [
        { display: "none" },
        { display: "none" },
        { display: "none" },
        { display: "none" },
        { display: "none" },
      ],
      select: [{}, {}, {}, {}, {}],
    };
  },
  mounted() {
    this.cssAct[0] = { display: "block" };
    this.select[0] = {
      border: "2px solid rgb(43, 43, 165",
      "border-bottom": "none",
    };
  },
  methods: {
    handleClick(num, routeName) {
      this.displayAct(num);
      this.$router.push({ name: routeName });
    },
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
      });
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
  table-layout: fixed;
}
.subMenu tr th {
  width: 20%;
  height: 55px;
  border-bottom: 2px solid rgb(43, 43, 165);
  text-align: center;
}
.boundary {
  width: 100%;
  max-width: 1200px;
  padding: 0 auto;
}
.router-link {
  display: block;
  height: 70%;
  width: 100%;
  text-decoration: none;
  color: inherit;
}
</style>