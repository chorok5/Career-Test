<template>
    <div data-role="context" data-type="module" data-module="career" data-context="worknet_psyexam_intro"
        data-base-url="/ko/counsel/jobs/job/intro" class="career-module-container">
        <div class="ems_cnts">
            <div class="intro_box psyexam">
                <p>진로검사 및 직업심리검사는 개인의 능력과 흥미, 성격 등의 심리적인 특성들이 각 직업에서 욕구하는 능력수준 및 특성에 얼마나 적합한지를 과학적인 방법으로 측정하여 성공가능성이 높고
                    만족할 만한 직업들을 탐색하도록 도와드립니다.</p>
            </div>
            <h4 class="dot_tit">직업심리검사 종류</h4>
            <ul class="quick_list">
                <li class="career-item">
                    <div class="career-item-box">
                        <strong class="dot_tit"  style="text-align: left;">대학생 진로준비도 검사</strong>
                        <p  style="text-align: left;">대학생 진로준비도 검사는ddddd.</p>
                        <a href="https://www.work.go.kr/consltJobCarpa/jobPsyExamNew/jobPsyExamAdultList.do"
                            target="_blank" class="career-item-button">바로가기</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</template>

<script>
export default {
    name: 'TestPage'
}
</script>

<style scoped>
.career-module-container {
  /* 컨테이너 스타일 */
}

.career-item {
  border: 2px solid #ccc;
  border-radius: 10px;
  margin-bottom: 20px;
  padding: 20px;
}

.career-item-box {
  display: flex;
  flex-direction: column;
}

.career-item-text {
  text-align: left;
}

.career-item-button {
  margin-top: auto; /* 상단 여백을 자동으로 설정하여 버튼을 컨테이너 아래로 이동시킵니다. */
  text-align: right; /* 버튼을 우측 정렬합니다. */
}
</style>