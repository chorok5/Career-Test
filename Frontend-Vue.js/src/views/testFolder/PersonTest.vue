<template>
    <div class="career-test">
      <MenuPage />
      <div class="boundary">
        <h1 class="title">심리 상담</h1>
        <SimriSecMenu />
        <div class="container my-5">
          <div class="row justify-content-center">
            <div class="col-lg-10">
              <h1 class="mb-4 font-weight-bold">심리상담 신청</h1>
              <div class="intro-section p-4 rounded shadow-sm mb-4">
                <p class="mb-0 text-center">대학생 심리검사는 학생들이 자신의 심리적 상태를 이해하고, 필요한 경우 적절한 도움을 받을 수 있도록 지원하기 위한 도구입니다. 이 검사는 스트레스, 불안, 우울증 등의 문제를 조기에 발견하고 해결하는 데 도움이 됩니다.</p><br>
              </div>
              <div>
                <h3 class="font-weight-bold mb-3 text-left">심리검사 종류</h3>
                <ul class="instructions mb-4 text-left">
                  <li><strong>성격 검사:</strong> MBTI, Big Five 등 다양한 성격 검사를 통해 자신을 더 깊이 이해할 수 있습니다.</li>
                  <li><strong>스트레스 검사:</strong> 현재의 스트레스 수준을 평가하여 적절한 대처 방안을 마련할 수 있습니다.</li>
                  <li><strong>우울증/불안 검사:</strong> BDI, GAD-7 검사를 통해 우울증 및 불안 상태를 평가하고 필요한 도움을 받을 수 있습니다.</li>
                  <li><strong>진로 검사:</strong> Holland Code (RIASEC) 검사를 통해 자신의 적성에 맞는 진로를 탐색할 수 있습니다.</li>
                </ul>
                <hr class="hr">
                <div class="test-results mb-4 text-left">
                  <h3 class="font-weight-bold mb-3 text-left">검사 절차</h3>
                  <ul class="text-left">
                    <li><strong>예약 및 신청:</strong> 온라인 또는 전화로 예약을 신청합니다.</li>
                    <li><strong>검사 진행:</strong> 약속된 시간에 검사를 진행하며, 소요 시간은 약 30분에서 1시간입니다.</li>
                    <li><strong>결과 상담:</strong> 검사 결과는 전문 상담가와의 상담을 통해 자세히 설명됩니다.</li>
                </ul>
                </div>
                <hr class="hr">
                <div class="participation-guidelines mb-4 text-left">
                  <h3 class="font-weight-bold mb-3 text-left">검사의 장점</h3>
                  <ul class="text-left">
                    <li>자기 이해 증진: 자신의 성격과 심리 상태를 명확히 이해할 수 있습니다.</li>
                    <li>문제 해결: 심리적 문제를 조기에 발견하고 해결책을 찾을 수 있습니다.</li>
                    <li>진로 탐색: 자신의 적성과 흥미에 맞는 진로를 탐색하는 데 도움이 됩니다.</li>
                  </ul>
                </div>
                <hr class="hr">
                <div class="privacy-notice mb-4 text-left">
                  <h3 class="font-weight-bold mb-3 text-left">기밀 유지</h3>
                  <ul class="text-left">
                    <li>모든 검사 결과와 개인 정보는 철저히 기밀로 유지됩니다. </li>
                    <li>안전한 데이터 보호 조치를 통해 개인 정보가 외부로 유출되지 않도록 합니다.</li></ul>
                </div>
                <hr class="hr">
                <div class="privacy-notice mb-4 text-left">
                  <h3 class="font-weight-bold mb-3 text-left">추가 지원 및 자원</h3>
                  <ul class="text-left">
                    <li><strong>상담 센터:</strong> 필요시 학교 내 상담 센터를 통해 추가 상담을 받을 수 있습니다.</li>
                    <li><strong>온라인 자료:</strong> 심리 건강 관련 다양한 자료와 리소스를 제공합니다.</li>
                  </ul>
                </div>
                <hr class="hr">
                <div class="privacy-notice mb-4 text-left">
                  <h3 class="font-weight-bold mb-3 text-left">연락처 정보</h3>
                  <ul class="text-left">
                    <li><strong>📞 문의처 : </strong> 02-123-4567<br></li>
                <li><strong>🏤 위치 : </strong> 학생회관 2층 심리상담센터</li>
                  </ul>
                </div>
              </div>
              <div class="text-center mt-5">
                <button @click="contactCounselor" class="btn btn-primary btn-lg btn-block mt-4 shadow-sm" style="margin-bottom:40px;">상담 요청</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>

<script>
import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
    name: 'PersonTest',
    components: {
        MenuPage,
        SimriSecMenu
    },
    methods: {
  contactCounselor() {
    this.$router.push('/rsrvTest4?selectedCounselingType=심리상담');
  }
}

}
</script>

<style scoped>

.title {
  text-align: left;
  font-family: 'sj';
  margin-left: 15px;
  margin-bottom: 40px;
}

.font-weight-bold {
  font-weight: bold;
}

.intro-section {
  background-color: #f8f9fa;
}

.instructions,
.test-results ul,
.privacy-notice ul,
.participation-guidelines ul {
  list-style-type: none;
  padding-left: 0;
}

.hr {
  margin: 30px 0 30px 0;
}

.instructions li,
.test-results li,
.participation-guidelines li,
.privacy-notice li {
  margin-left: 20px;
  text-align: left;
  position: relative;
  padding-left: 1.5rem;
  margin-bottom: 0.5rem;
}

.instructions li::before,
.test-results li::before,
.participation-guidelines li::before{
  margin-left: 0px;
  content: "\2705";
  font-family: "Font Awesome 5 Free";
  font-weight: 900;
  position: absolute;
  left: 0;
  color: #007bff;
}
.privacy-notice li::before{
    margin-left: 0px;
  content: "\2705";
  font-family: "Font Awesome 5 Free";
  font-weight: 900;
  position: absolute;
  left: 0;
}

.instructions li,
.test-results li,
.participation-guidelines li,
.privacy-notice li {
  padding-left: 2.0rem;
}

.text-left {
  text-align: left;
}
.title{
  text-align: left;
  font-family: 'sj';
  margin-left: 15px;
  margin-bottom: 40px;
}
</style>
