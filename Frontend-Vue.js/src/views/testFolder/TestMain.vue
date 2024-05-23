<template>
  <div class="career-test">
    <MenuPage />
    <SimriSecMenu />
    <div class="container my-5">
      <div class="row justify-content-center">
        <div class="col-lg-10">
          <h1 class="mb-4 font-weight-bold">온라인 심리검사</h1>
          <div class="intro-section p-4 rounded shadow-sm mb-4">
            <h5 class="mb-0">안녕하세요! 온라인 심리검사에 참여하신 여러분을 환영합니다.</h5><br>
            <p class="mb-0">온라인 심리검사는 다양한 항목으로 구성되어 있으며, </p>
              <p class="mb-0">각 항목은 새로운 환경에 대한 적응성, 사회적 활동 선호도, 독립적 업무 선호도 등 여러 가지 주제를 다룹니다.</p>
            <p class="mb-0">각 항목에 따라 여러 가지 질문이 제공되며, 사용자는 해당 질문에 대한 답변을 선택하여 진행합니다.</p>
          </div>
          <div>
          <h3 class="font-weight-bold mb-3 text-left">안내 사항</h3>
          <ul class="instructions mb-4 text-left">
            <li>검사 결과는 개인적인 용도로만 사용됩니다.</li>
            <li>진실된 답변을 선택하여 주세요.</li>
            <li>외부의 방해요소를 최소화하고 조용한 환경에서 검사를 진행해 주세요.</li>
            <li>검사 결과는 학업 및 진로 선택에 참고용으로 활용됩니다.</li>
          </ul>
          <hr class="hr">
          <div class="test-results mb-4 text-left">
            <h3 class="font-weight-bold mb-3 text-left">검사 결과</h3>
            <p class="text-left">검사를 완료하신 후에는 다음과 같은 정보를 제공해 드립니다:</p>
            <ul class="text-left">
              <li>총점에 따른 추천 직업 목록</li>
              <li>총점에 따른 성향 분석</li>
            </ul>
            <p class="text-left">검사 결과는 사용자가 선택한 답변을 기반으로 총점이 계산됩니다. 총점에 따라 추천 직업 목록과 성향 분석이 제공됩니다.</p>
            <p class="text-left">이를 통해 여러분은 자신의 성향과 능력을 더 잘 이해할 수 있습니다.</p>
          </div>
          <hr class="hr">
          <div class="participation-guidelines mb-4 text-left">
            <h3 class="font-weight-bold mb-3 text-left">참여 시 주의사항</h3>
            <ul class="text-left">
              <li>진실된 답변을 선택해 주세요. 검사 결과는 여러분의 실제 성향을 파악하기 위해 중요합니다.</li>
              <li>각 질문에 대한 답변을 신중하게 선택해 주세요. 자신의 생각과 경험을 고려하여 답변을 선택하십시오.</li>
              <li>검사를 진행하는 동안 외부의 방해요소를 최소화하고 조용한 환경에서 진행해 주세요.</li>
            </ul>
          </div>
          <hr class="hr">
          <div class="privacy-notice mb-4 text-left">
            <h3 class="font-weight-bold mb-3 text-left">기타 사항</h3>
            <p class="text-left">검사는 개인적인 용도로만 사용되며, 어떠한 정보도 제3자에게 공개되지 않습니다.</p>
            <p class="text-left">여러분의 응답은 비밀로 처리되며, 결과는 개인적인 자료로만 활용됩니다.</p>
          </div>
          </div>
          <div class="text-center mt-5">
            <button @click="navigateToTest('career')" class="btn btn-primary btn-lg">온라인 심리검사 시작하기</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>


<script>
import store from "@/store";
import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
  name: 'TestPage',
  components: {
    MenuPage,
    SimriSecMenu
  },

  methods: {
  navigateToTest(testType) {
    const isLoggedIn = store.state.account.id !== 0 && store.state.account.id !== undefined;
    if (!isLoggedIn) {
      // 로그인 페이지로 리디렉션하기 전에 목적지 저장
      this.$store.commit('setRedirectAfterLogin', this.$route.path);
      alert(`로그인이 필요한 페이지입니다.`);
      this.$router.push({ name: 'login' });
    } else {
      // 로그인 후에 심리검사 페이지로 이동
      if (testType === 'career') {
        this.$router.push({ name: 'CareerTest' });
      }
    }
  }
}

};
</script>
@/store@/views/store@/store

<style>
.font-weight-bold {
  font-weight: bold;
}
.intro-section {
  background-color: #f8f9fa;
}
.instructions,
.test-results ul,
.participation-guidelines ul {
  list-style-type: none;
  padding-left: 0;
}
.hr{
  margin: 30px 0 30px 0;
}
.instructions li,
.test-results li,
.participation-guidelines li,
.privacy-notice li {
  margin-left:50px;
  text-align: left;
  position: relative;
  padding-left: 1.5rem;
  margin-bottom: 0.5rem;
}

.instructions li::before,
.test-results li::before,
.participation-guidelines li::before,
.privacy-notice li::before {
  margin-left:0px;
  content: "\2705";
  font-family: "Font Awesome 5 Free";
  font-weight: 900;
  position: absolute;
  left: 0;
  color: #007bff;
}
.instructions li,
.test-results li,
.participation-guidelines li,
.privacy-notice li {
  padding-left: 2.0rem; /* li 내용과 유니코드 아이콘 사이의 여백 조정 */
}

.text-left {
  text-align: left;
}

.career-test .btn-primary {
  background-color: #007bff;
  border-color: #007bff;
}

.career-test .btn-primary:hover {
  background-color: #0069d9;
  border-color: #0062cc;
}
</style>