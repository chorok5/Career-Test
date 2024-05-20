<template>
  <div>
    <MySidebar />
    <div v-if="testResult">
      <h2>테스트 결과</h2>
      <ul>
        <li>
          <p>테스트 번호: {{ testResult.tno }}</p>
          <p>SID: {{ testResult.sid }}</p>
          <p>QID: {{ testResult.qid }}</p>
          <p>답변: {{ testResult.tanswer }}</p>
          <p>총 점수: {{ testResult.totalScore }}</p>
          <p>회원 ID: {{ testResult.memberId }}</p>
          <p>날짜: {{ formatDate(testResult.tdate) }}</p>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>테스트 결과를 불러오는 중...</p>
    </div>
    </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import MySidebar from '@/components/MySidebar.vue';
import { useStore } from 'vuex';

export default {
  name: 'MySimri',
  components: {
    MySidebar,
  },

  setup() {
    const testResult = ref([]);

    const loadTestResult = () => {
      axios.get('http://localhost:3000/api/mysimri', { withCredentials: true })
        .then(response => {
          testResult.value = response.data;
        })
        .catch(error => {
          console.error('Error fetching test result:', error);
        });
    };

    const formatDate = (dateString) => {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
      return new Date(dateString).toLocaleDateString('en-US', options);
    };
    
    const store = useStore();

    onMounted(() => {
      loadTestResult();
    });

    return {
      testResult,
      formatDate,
      store,
    };
  },
};
</script>


<style scoped>
.container-fluid {
  padding-top: 56px; /* Adjust based on the height of HeaderMenu */
}
.result-container {
  margin-top: 20px;
}
.action-buttons {
  margin-top: 20px;
}
</style>
