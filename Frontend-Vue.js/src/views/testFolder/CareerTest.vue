<template>
  <div class="career-test">
    <MenuPage />
  <div class="container my-5">
    <SimriSecMenu />
    <h1 class="title-box text-center mt-5 mb-4" style="color:darkgreen; font-weight: bolder;">  🙋‍♀️ 직업 및 성향 심리검사 🙋‍♂️  </h1>
    <div v-for="(question, index) in questions" :key="question.qid" class="card mb-4 shadow-sm">
      <div class="card-body test-box" v-if="index !== 10">
        <h3 class="card-title" style="font-weight: bold;">{{ question.ttitle }}</h3>
        <div class="form-check d-flex flex-row flex-wrap justify-content-center">
          <div
            v-for="(option, optionIndex) in question.options"
            :key="optionIndex"
            class="radio-container d-flex align-items-center mb-3" 
          >
          <input
              type="radio"
              :name="'question-' + question.qid"
              :value="option.value"
              v-model="userAnswers[question.qid]"
              class="custom-radio" 
              :id="'option' + question.qid + '-' + optionIndex"
            />
            <label
              :for="'option' + question.qid + '-' + optionIndex"
              class="custom-label w-100 rounded-pill px-5 py-3" style="font-size:18px; font-weight:550;"
              :class="{
                'selected-option': userAnswers[question.qid] === option.value,
                
              }"
            >
              {{ option.label }}
            </label>
          </div>
        </div>
      </div>
    </div>

    <!-- 결과 출력 -->
    <div class="card mb-4 shadow-sm" v-if="result.totalScore !== null">
      <div class="card-body">
        <h3 class="card-title">최종 결과</h3>
        <p>총 점수: <span class="font-weight-bold">{{ result.totalScore }}</span></p>
        <p>추천 직업: <span class="font-weight-bold">{{ result.recommendedJobs }}</span></p>
        <p>개인 성향: <span class="font-weight-bold">{{ result.personalTraits }}</span></p>
      </div>
    </div>
    <button @click="submitTest" class="btn btn-success btn-block btn-lg mt-4 shadow-sm">완료</button>
  </div>
</div>
</template>


<script>
import axios from 'axios';

import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
  name: 'CareerTest',
    components: {
    MenuPage,
    SimriSecMenu
  },
  data() {
    return {
      questions: [
        {
          qid: 1,
          tcategory: "새로운 환경에 대한 적응성",
          ttitle: "새로운 상황에 대한 적응이 빠른 편인가요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 2,
          tcategory: "사회적 활동 선호도",
          ttitle: "다른 사람들과의 상호 작용 및 활동에 대한 선호도는 높은 편인가요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 3,
          tcategory: "독립적 업무 선호도",
          ttitle: "여러 사람이 같이 일하는 것을 선호하시나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 4,
          tcategory: "자기 계발에 대한 관심",
          ttitle: "자기 계발 및 학습에 대한 관심이 많은가요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 5,
          tcategory: "팀 프로젝트 참여 선호도",
          ttitle: "팀 프로젝트에 참여하는 것을 선호하십니까?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 6,
          tcategory: "고도의 스트레스 관리 능력",
          ttitle: "고도의 스트레스를 관리하고 대처하는 능력이 있나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 7,
          tcategory: "빠른 의사 결정 능력",
          ttitle: "빠른 시간 내에 의사 결정을 내릴 수 있는 능력이 있나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {

          qid: 8,
          tcategory: "문제 해결능력",
          ttitle: "문제 해결능력을 갖추고 있나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 9,
          tcategory: "창의성 및 혁신성",
          ttitle: "창의적인 생각과 혁신적인 아이디어를 개발하는 것을 선호하나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
        {
          qid: 10,
          tcategory: "업무에 대한 열정",
          ttitle: "해당 분야의 업무에 대한 열정이 있나요?",
          options: [
            { label: "그렇다", value: 3 }, { label: "보통이다", value: 2 }, { label: "아니다", value: 1 }
          ]
        },
      ],
      userAnswers: {}, // userAnswers 객체 초기화
      result: {
        totalScore: null,
        recommendedJobs: "",
        personalTraits: ""
      }
    };
  },
  created() {
    this.fetchQuestions();
  },
  methods: {
    fetchQuestions() { // fetchQuestions 함수 정의 : 서버에서 질문을 가져와서 화면에 표시함.(axios를 사용)
      axios.get('/api/questions')
        .then(response => {
          this.questions = response.data;
          this.userAnswers = Object.fromEntries( // 질문 개수만큼 userAnswers 객체 초기화
            this.questions.map(question => [question.qid, null])
          );
        })
        .catch(error => {
          console.error('Error fetching questions:', error);
          // 오류를 적절히 처리하는 코드 추가 가능
        });
    },

    submitTest() {
      console.log(this.userAnswers);
      console.log('네비게이션 전에 로그 출력');

      const isAllAnswered = Object.keys(this.userAnswers).length === this.questions.length;
      if (!isAllAnswered) {
        alert("모든 질문에 답해주세요.");
        return; // 모든 질문에 답하지 않았다면 여기서 함수 종료
      }

      // 총점 계산을 위해 각 질문의 답변을 배열에 담기
      const userAnswersArray = this.questions.map(question => {
        return parseInt(this.userAnswers[question.qid]);
      })

      console.log('test page 전송하는 데이터:', userAnswersArray);

      axios.post('http://localhost:3000/api/submitTest', {
        userAnswersArray: userAnswersArray,
        totalScore: userAnswersArray.reduce((acc, cur) => acc + cur, 0)}, { withCredentials: true }) // id값 받아오게?
        .then(response => {
          console.log('성공적으로 제출됨:', response.data);
          const { totalScore, recommendedJobs, personalTraits } = response.data;

          this.result.totalScore = totalScore;
          this.result.recommendedJobs = recommendedJobs;
          this.result.personalTraits = personalTraits;

          this.$router.push({ name: 'ResultPage', query: { userAnswers: JSON.stringify(userAnswersArray) } });
        })
        .catch(error => {
          console.error('Error submitting test:', error);
        });
    }

  }
}
</script>

<style lang="scss" scoped >
.container {
  max-width: 1200px;
  margin: auto;
  padding: 0 15px;
}

.card {
  width: 100%;
  width: 1000px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin: 0 auto;
}
.card-body {
  flex-direction: column;
}

.card-title {
  padding: 10px;
  margin-bottom: 2rem;
}

.btn {
  font-size: 1rem;
  padding: 0.75rem 1.5rem;
}
// .title-box {
//             background-color: #369f3a; /* 진한 배경색 (예: 어두운 회색) */
//             color: #ffffff; /* 글자색 흰색 */
//             padding: 10px;
//             text-align: center;
//             margin: 50px auto 40px; /* 위쪽과 아래쪽 간격 조정 */
//             border-radius: 3px; /* 모서리 둥글게 */
//             width: fit-content;
//         }
.test-box {
  // background: linear-gradient(180deg, rgb(243, 250, 245), #fffeff);
  border-left: 4px solid #2fbb6e;
  padding-left: 20px;
}
.form-check {
  margin-top: 1rem;
}

.custom-radio {
  display: none;
}

.custom-label {
  display: flex;
  align-items: center;
  padding: 5px 10px;
  margin-left: 10px;
  cursor: pointer;
  transition: background-color 0.3s ease;
  border: 1px solid #ced4da; // 테두리 추가
  width: 100%; // 라벨이 옵션 영역을 모두 차지하도록 설정
}
.custom-label:hover {
  background-color: hsl(210, 17%, 98%);
}
.radio-container:hover .custom-label {
  background-color: #189521;
  color:white;
}

.custom-radio:checked + .custom-label {
  background-color: #90c6ff;
  border-color: #90c6ff; // 체크된 라벨의 테두리 색상 변경
  font-weight: bold;
}

.selected-option {
  background-color: #8cceff;
  font-weight: bold;
}
</style>