<template>
    <div>
      <MenuPage />
      <div class="boundary">
        <h1 class="title">취업 상담</h1>
        <jobSecMenu />
        <div class="container">
          <div v-if="jobs.length">
            <table class="job-table">
              <thead>
                <tr>
                  <th>채용 제목</th>
                  <th>기관</th>
                  <th>근무 지역</th>
                  <th>접수 기간</th>
                  <th>상세 보기</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="job in jobs" :key="job.detailLink">
                  <td>{{ job.title }}</td>
                  <td>{{ job.company }}</td>
                  <td>{{ job.location }}</td>
                  <td>{{ job.date }}</td>
                  <td><a :href="job.detailLink" target="_blank">상세보기</a></td>
                </tr>
              </tbody>
            </table>
          </div>
          <div v-else>
            <p>채용 정보를 불러오는 중...</p>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import MenuPage from "@/components/MenuPage.vue";
  import jobSecMenu from "@/views/jobs/jobSecMenu.vue";
  import axios from 'axios';
  
  export default {
    name: "Crawling",
    components: {
      MenuPage,
      jobSecMenu,
    },
    data() {
      return {
        jobs: []
      };
    },
    computed: {
    sortedJobs() {
      return this.removeDuplicates(this.jobs)
        .filter(job => this.isValidJob(job))
        .sort((a, b) => new Date(a.date) - new Date(b.date)); // 날짜 기준 정렬
    }
  },
    methods: {
      fetchJobs() {
        axios.get('http://localhost:3000/api/jobslists')
          .then(response => {
            this.jobs = response.data;
          })
          .catch(error => {
            console.error('Error fetching jobs:', error);
          });
      }
    },
    removeDuplicates(jobs) {
      const seen = new Set();
      return jobs.filter(job => {
        const duplicate = seen.has(job.detailLink);
        seen.add(job.detailLink);
        return !duplicate;
      });
    },
    created() {
      this.fetchJobs();
    }
  };
  </script>
  
  <style scoped>
  .container {
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .job-table {
    width: 100%;
    border-collapse: collapse;
  }
  
  .job-table th, .job-table td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
  }
  
  .job-table th {
    background-color: #f2f2f2;
  }
  
  .job-table a {
    color: blue;
    text-decoration: underline;
  }
  
  .job-table td:last-child {
    width: 90px; /* 상세 정보 칸 크기 조정 */
    word-wrap: break-word; /* 단어가 길 경우 줄 바꿈 처리 */
  }
  </style>
  