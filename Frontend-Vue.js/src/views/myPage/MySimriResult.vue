<template>
  <div class="container-fluid">
    <div class="row">
      <MySidebar class="col-md-3 bg-light" />

      <div class="col-md-9">
        <div v-if="testResult" class="card mt-4">
          <div class="card-header">
            <h2>테스트 결과</h2>
          </div>
          <div class="card-body">
            <ul class="list-group list-group-flush">
              <li class="list-group-item">
                <p><strong>ID : </strong> {{ testResult.memberId }}</p>
                <p><strong>이름 : </strong>{{ $store.state.account.name }}'</p>
                <p><strong>검사 실시일 : </strong> {{ formatDate(testResult.tdate) }}</p>
              </li>
            </ul>
            <div v-if="recommendedJobs" class="mt-4">
              <h3>추천 직업</h3>
              <p class="lead">{{ recommendedJobs }}</p>
            </div>
            <div v-if="personalTraits && personalTraits.length" class="mt-4">
              <h3>성향 분석</h3>
              <ul class="list-group">
                <li v-for="trait in personalTraits" :key="trait" class="list-group-item">{{ trait }}</li>
              </ul>
            </div>
          </div>
        </div>
        <div v-else class="alert alert-info mt-4">
          <p>테스트 결과를 불러오는 중...</p>
        </div>
      </div>
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
    const testResult = ref(null);
    const recommendedJobs = ref([]);
    const personalTraits = ref([]);

    const loadTestResult = () => {
      console.log('loadTestResult 호출됨'); // 요청 전 로그
      axios.get('http://localhost:3000/api/mysimri', { withCredentials: true })
        .then(response => {
          console.log('응답 받음:', response); // 응답 후 로그
          if (response.data) {
            testResult.value = response.data;
            console.log('Test Result:', testResult.value); // 디버깅용 로그
            loadRecommendations(response.data.totalScore);
          }
        })
        .catch(error => {
          console.error('Error fetching test result:', error);
        });
    };
    const loadRecommendations = async (totalScore) => {
      try {
        console.log(`loadRecommendations 호출됨, totalScore: ${totalScore}`);
        const response = await axios.get(`http://localhost:3000/api/mysimri/recommendations?totalScore=${totalScore}`);
        console.log('응답 받음:', response);
        if (response.data) {
          recommendedJobs.value = response.data.recommendedJobs;
          personalTraits.value = response.data.personalTraits;
          console.log('Recommended Jobs:', recommendedJobs.value);
          console.log('Personal Traits:', personalTraits.value);
        }
      } catch (error) {
        console.error('ERROR 에러~ : ', error);
      }
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
      personalTraits,
      recommendedJobs,

    };
  },
};
</script>


<style scoped>
.container-fluid {
  padding-top: 56px;
  /* Adjust based on the height of HeaderMenu */
}

.card {
  margin-top: 20px;
}
</style>
