<template>
  <div>
    <br />
    <div class="sbmContainer boundary">
      <table class="subMenu">
        <tr>
          <th :style="select[0]">
            <router-link :to="{ name: 'Counseling' }" class="router-link" @click="handleClick(0, 'Counseling', $event)">심리상담 안내</router-link>
          </th>
          <th :style="select[1]">
            <router-link :to="{ name: 'CounselorShow' }" class="router-link" @click="handleClick(1, 'CounselorShow', $event)">상담사 소개</router-link>
          </th>
          <th :style="select[2]">
            <router-link :to="{ name: 'PersonTest' }" class="router-link" @click="handleClick(3, 'PersonTest', $event)">오프라인 심리검사</router-link>
          </th>
          <th :style="select[3]">
            <router-link :to="{ name: 'Test' }" class="router-link" @click="handleClick(2, 'Test', $event)">온라인 심리검사</router-link>
          </th>
        </tr>
      </table>
    </div>
    <section class="section s1" :style="cssAct[0]"></section>
    <section class="section s2" :style="cssAct[1]"></section>
    <section class="section s3" :style="cssAct[2]"></section>
    <section class="section s4" :style="cssAct[3]"></section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      cssAct: [
        { display: "none" },
        { display: "none" },
        { display: "none" },
        { display: "none" },
      ],
      select: [
        {},
        {},
        {},
        {}
      ],
    };
  },
  mounted() {
    this.updateDisplay(this.$route.name);
  },
  watch: {
    '$route.name'(newRoute) {
      this.updateDisplay(newRoute);
    }
  },
  methods: {
    handleClick(num, routeName) {
      this.$router.push({ name: routeName });
    },
    updateDisplay(routeName) {
      const routeIndexMap = {
        'Counseling': 0,
        'CounselorShow': 1,
        'PersonTest': 2,
        'Test': 3,
      };
      const index = routeIndexMap[routeName] || 0;
      this.cssAct = this.cssAct.map((style, i) => i === index ? { display: "block", backgroundColor: "#333", color: "#fff" } : { display: "none" });
      this.select = this.select.map((style, i) => i === index ? { border: "2px solid #67BF4E", 'border-bottom':"none", color: "#67BF4E" } : {});
      
    }
  }
};
</script>

<style scoped>
.sbmContainer {
  width: 100%;
  margin-bottom: 50px;
  /* border-bottom: 2px solid #67BF4E */
}
.subMenu {
  width: 100%;
  margin: auto;
  table-layout: fixed;
  
}
.subMenu tr th {
  width: 20%;
  height: 55px;
  border-bottom: 2px solid #67BF4E;
  text-align: center;
}
.boundary {
  width: 100%;
  max-width: 1200px;
  margin: auto; 
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
