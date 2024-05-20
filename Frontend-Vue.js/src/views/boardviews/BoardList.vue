<template>
  <div class="container py-5 mt-5">
    <table class="table">
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
      </tr>
      <tr v-for="row in list" v-bind:key="row.bno">
        <td v-text="row.no"></td>
        <td
          v-if="row.depth == 0"
          @click="detail(row.bno)"
          style="text-align: left"
        >
          {{ row.btitle }}
        </td>
        <td v-else @click="detail(row.bno)" style="text-align: left">
          &nbsp;ㄴ[:Re]{{ row.btitle }}
        </td>
        <td>{{ row.writer }}</td>
        <td>{{ row.bdate }}</td>
      </tr>
    </table>
    <div class="d-flex justify-content-center">
      <ul class="pagination" style="">

        <li class="page-item" v-if="pageMap.currentpage != 1">
          <button class="page-link" aria-label="Previous" @click="changeboard(1)">&laquo;</button>
        </li>
        <li v-else>
          <span>
            <button class="page-link" disabled="disabled" aria-label="Previous">&nbsp;</button>
          </span>
        </li>

        <li class="page-item" v-if="pageMap.currentpage != 1">
          <button class="page-link" aria-label="Previous" @click="changeboard(pageMap.currentpage - 1)">&lt;</button>
        </li>
        <li v-else>
          <span>
            <button class="page-link" disabled="disabled" aria-label="Previous">&nbsp;</button>
          </span>
        </li>

        <li class="page-item" v-for="n in pageMap.pagelist" :key="n">
          <button class="page-link active" @click="changeboard(n)" v-if="pageMap.currentpage==n">{{ n }}</button>
          <button class="page-link" @click="changeboard(n)" v-else>{{ n }}</button>
        </li>

        <li class="page-item" v-if="pageMap.currentpage != pageMap.totalpage">
            <button class="page-link" @click="changeboard(pageMap.currentpage + 1)" aria-label="Next" aria-hidden="true">&gt;</button>
        </li>
        <li v-else>
          <span>
            <button class="page-link" disabled="disabled" aria-label="Previous">&nbsp;</button>
          </span>
        </li>


        <li class="page-item" v-if="pageMap.currentpage != pageMap.totalpage">
            <button class="page-link" @click="changeboard(pageMap.totalpage)" aria-label="Next" aria-hidden="true">&raquo;</button>
        </li>
        <li v-else>
          <span>
            <button class="page-link" disabled="disabled" aria-label="Previous">&nbsp;</button>
          </span>
        </li>

      </ul>
    </div>
    <button @click="$router.push('/boardwrite')">글쓰기</button>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "BoardList",
  data() {
    return {
      requestBody: this.$route.query,
      list: [],
      pageMap: [],
    };
  },
  mounted() {
    axios
      .get("/api/BoardList?currentpage=1")
      .then((res) => {
        this.list = res.data.list;
        this.pageMap = res.data.pageMap;
      })
      .catch((err) => {
        alert("에러 발생 : " + err);
      });
  },
  methods: {
    detail(bno) {
      this.requestBody.bno = bno;
      this.$router.push({ path: "./boardDetail", query: this.requestBody });
    },

    changeboard(pageNo) {
      axios
        .get("/api/BoardList?currentpage=" + pageNo)
        .then((res) => {
          this.list = res.data.list;
          this.pageMap = res.data.pageMap;
        })
        .catch((err) => {
          alert(err);
        });
    },
  },
};
</script>

<style scope>
 .pagination{
  --bs-pagination-border-color:none;
 }

</style>