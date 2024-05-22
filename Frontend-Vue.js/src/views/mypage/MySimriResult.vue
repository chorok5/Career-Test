<template>
  <div>
    <MySidebar></MySidebar>
    <MenuPage />
    <div class="container-fluid mt-5 pt-4">
      <div class="row">
        <div class="col-md-2">
        </div>
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

          <!-- 과거 심리검사 결과 리스트 -->
          <div v-if="previousResults.length" class="card mt-4">
            <div class="card-header">
              <h2>이전 심리검사 결과</h2>
            </div>
            <div class="card-body">
              <ul class="list-group list-group-flush">
                <li class="list-group-item" v-for="result in previousResults" :key="result.id"
                  @click="openModal(result)">
                  <p><strong>ID : </strong>{{ result.memberId }}</p>
                  <p><strong>검사 실시일 : </strong>{{ formatDate(result.tdate) }}</p>
                </li>
              </ul>
            </div>
          </div>

          <!-- 모달 컴포넌트 추가 -->
          <div v-if="showModal" class="modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">심리검사 결과</h5>
                  <button type="button" class="close" @click="closeModal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                  </button>
                </div>
                <div class="modal-body">
                  <!-- 선택한 심리검사 결과 표시 -->
                  <p><strong>ID : </strong>{{ selectedResult.memberId }}</p>
                  <p><strong>검사 실시일 : </strong>{{ formatDate(selectedResult.tdate) }}</p>
                  <!-- 추천 직업 및 성향 분석 표시 -->
                  <div v-if="modalRecommendedJobs" class="mt-4">
                    <h3>추천 직업</h3>
                    <p class="lead">{{ modalRecommendedJobs }}</p>
                  </div>
                  <div v-if="modalPersonalTraits && modalPersonalTraits.length" class="mt-4">
                    <h3>성향 분석</h3>
                    <ul class="list-group">
                      <li v-for="trait in modalPersonalTraits" :key="trait" class="list-group-item">{{ trait }}</li>
                    </ul>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" @click="closeModal">Close</button>
                </div>
              </div>
            </div>
          </div>

          <!-- 테스트 결과 로딩 중인 경우 표시할 메시지 -->
          <div v-if="isLoading" class="alert alert-info mt-4">
            <p>테스트 결과를 불러오는 중... (기록없음)</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref, onMounted } from 'vue';
import { useStore } from 'vuex';
import MySidebar from '@/components/MySidebar.vue';
import MenuPage from '@/components/MenuPage.vue';

export default {
  name: 'MySimri',
  components: {
    MySidebar,
    MenuPage
  },
  setup() {
    const testResult = ref(null);
    const recommendedJobs = ref([]);
    const personalTraits = ref([]);
    const previousResults = ref([]);
    const isLoading = ref(true);
    const showModal = ref(false);
    const selectedResult = ref(null);
    const modalRecommendedJobs = ref('');
    const modalPersonalTraits = ref([]);

    const loadTestResult = () => {
      isLoading.value = true;
      console.log('loadTestResult 호출됨'); // 요청 전 로그
      axios.get('http://localhost:3000/api/mysimri', { withCredentials: true })
        .then(response => {
          console.log('응답 받음:', response); // 응답 후 로그
          if (response.data) {
            testResult.value = response.data;
            console.log('Test Result:', testResult.value); // 디버깅용 로그
            loadRecommendations(response.data.totalScore);
          }
          isLoading.value = false;
        })
        .catch(error => {
          console.error('Error fetching test result:', error);
          isLoading.value = false;
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

    const loadPreviousResults = () => {
      axios.get(`http://localhost:3000/api/mysimri/history`, { withCredentials: true })
        .then(response => {
          console.log('이전 결과 응답?:', response);
          // 빈 리스트를 받았을 때 처리 추가 
          if (response.data && response.data.length > 0) {
            previousResults.value = response.data;
          } else {
            previousResults.value = []; // 빈 배열 할당
          }
        })
        .catch(error => {
          console.error('error 입니다. : ', error);
        });
    };

    const formatDate = (dateString) => {
      const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' };
      return new Date(dateString).toLocaleDateString('en-US', options);
    };

    const store = useStore();

    const openModal = async (result) => {
      selectedResult.value = result;
      showModal.value = true;
      await loadModalRecommendations(result.totalScore);
    };

    const loadModalRecommendations = async (totalScore) => {
      try {
        const response = await axios.get(`http://localhost:3000/api/mysimri/recommendations?totalScore=${totalScore}`);
        if (response.data) {
          modalRecommendedJobs.value = response.data.recommendedJobs; // 서버에서 반환되는 데이터 타입에 따라 수정 필요
          modalPersonalTraits.value = response.data.personalTraits; // 서버에서 반환되는 데이터 타입에 따라 수정 필요
        }
      } catch (error) {
        console.log('에러 히어~ : ', error);
      }
    };
    const closeModal = () => {
      showModal.value = false;
    };

    onMounted(() => {
      loadTestResult();
      loadPreviousResults();
    });

    return {
      testResult,
      formatDate,
      store,
      personalTraits,
      recommendedJobs,
      previousResults,
      isLoading,
      showModal,
      selectedResult,
      openModal,
      closeModal,
      modalRecommendedJobs,
      modalPersonalTraits,
    };
  },
};
</script>


<style scoped>
.card {
  margin-top: 20px;
}

.container-fluid {
  padding-top: 56px;
}

.table {
  margin-top: 20px;
}

.modal {
  display: block;
  background-color: rgba(0, 0, 0, 0.5);
}
</style>
