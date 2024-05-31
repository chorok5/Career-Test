<template>
  <div>
    <MySidebar></MySidebar>
    <MenuPage />
    <div class="container-fluid mt-5 pt-4">
      <div class="row">
        <div class="col-md-2"></div>
        <div class="row equal-height-columns justify-content-center">
          <div class="col-md-6">
            <div v-if="testResult" class="card mt-4">
              <div class="card-header text-white">
                <h4 class="m-0">테스트 결과</h4>
              </div>
              <div class="card-body result-card" style="padding-top:0;">
                <table class="table table-bordered table-striped text-center">
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>이름</th>
                      <th>검사 실시일</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>{{ testResult.memberId }}</td>
                      <td>{{ $store.state.account.name }}</td>
                      <td>{{ formatDate(testResult.tdate) }}</td>
                    </tr>
                  </tbody>
                </table>

                <div v-if="recommendedJobs" class="mt-4">
                  <h5><strong>추천 직업</strong></h5><br>
                  <p class="lead"><mark class="mark-bg">{{ recommendedJobs }}</mark></p>
                </div>
                <hr />
                <h5><strong>성향</strong></h5>
                <ul class="list-group">
                  <div class="row">
                    <div class="col-sm-4" v-for="(trait, index) in personalTraits" :key="index">
                      <li class="list-group-item traitgroup">
                        <span v-for="(part, partIndex) in trait.split(',')" :key="partIndex">
                          {{ part.trim() }}
                          <br v-if="partIndex !== trait.split(',').length - 1">
                        </span>
                      </li>
                    </div>
                  </div>
                </ul>

              </div>
            </div>
          </div>


          <div class="col-md-2 history">
            <div v-if="previousResults.length" class="card mt-4">
              <div class="card-header bg-secondary text-white">
                <h4 class="m-0">검사 기록</h4>
                            </div>
              <p style="font-size: 15px; margin:0;">과거 기록은 3건까지만<br>조회가 가능합니다.</p>
              <hr />
              <div class="card-body history-card" style="margin: 0;">
                <ul class="list-group list-group-flush">
                  <li class="list-group-item history-group" v-for="(result, index) in previousResults.slice(0, 3)"
                    :key="index" @click="openModal(result)">
                    <p style="font-size: smaller;"><strong>ID : </strong>{{ result.memberId }}</p>
                    <p style="font-size: smaller;"><strong>검사 실시일 : </strong>{{ formatDate(result.tdate) }}</p>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 모달 컴포넌트 추가 -->
      <div v-if="showModal" class="modal" tabindex="-1" role="dialog">
        <div class="modal-dialog" role="document">
          <div class="modal-content" style="width: 800px;">
            <div class="modal-header bg-success text-white">
              <h5 class="modal-title">심리검사 결과</h5>
              <button type="button" class="close" @click="closeModal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <!-- 선택한 심리검사 결과 표시 -->
              <p style="margin-bottom: 10px; font-size: 15px;">
                ID : {{ selectedResult.memberId }} <span style="margin: 0 10px;">|</span> 검사 실시일 : {{
                  formatDate(selectedResult.tdate) }}
              </p>
              <!-- 추천 직업 및 성향 분석 표시 -->
              <div v-if="modalRecommendedJobs" class="mt-4">
                <h5>추천 직업</h5>
                <p class="lead"><mark class="mark-bg">{{ modalRecommendedJobs }}</mark></p>
              </div>
              <div v-if="modalPersonalTraits && modalPersonalTraits.length" class="mt-4">
                <h5>성향 분석</h5>
                <ul class="list-group" style="font-size: 15px;">
                  <li v-for="trait in modalPersonalTraits" :key="trait" class="list-group-item">{{ trait }}</li>
                </ul>
              </div>
            </div>
            <div class="modal-footer">
              <button type="button" class="btn btn-success" @click="closeModal">Close</button>
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
      const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
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
.history {
  margin: 0px;
}

.equal-height-columns .col-md-6 {
  display: flex;
  flex-direction: column;
  align-items: stretch;
  margin-left: 200px;
}

.card {
  margin-top: 20px;
}

.lead {
  font-size: 20px;
}

.history-card {
  padding: 0;
}

.result-card {
  padding: 10px;

}

.container-fluid {
  padding-top: 56px;
}

.table {
  margin-top: 0px;
  width: 100%;
}

.modal {
  display: block;
  background-color: rgba(0, 0, 0, 0.5);
}

/* 중앙 정렬을 위해 수정된 CSS */
.row.justify-content-center {
  display: flex;
  justify-content: center;
}

.col-md-8 {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.card {
  width: 100%;
  max-width: 800px;
  /* 원하는 최대 너비 설정 */
}

.card-header {
  background-color: #67bf4e;
}

.traitgroup {
  font-size: 15px;
  background-color: #f5fff2;
  border-color: transparent;
  border-radius: 10px;
  text-align: left;
  list-style-type: disc;
}

.history-group {
  font-size: 15px;
}

.mark-bg {
  background-color: #d8ffcd;
  font-weight: 700;
}
</style>
