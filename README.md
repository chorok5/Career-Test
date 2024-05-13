## [프로젝트] 대학생을 대상으로 하는 심리 검사 페이지 만들기

### 🏆 완료
- 심리 검사 10문항 & 선택지 3개 : 그렇다(3점), 보통이다(2점), 아니다(1점)
- 결과 페이지 : 직업 추천, 성향 분석
- 검사 신청 페이지
- FAQ 게시판 (CRUD)

### 📌 작업 중
1. DB 연결

### ✔ 만들어야 할 것
- 어드민 페이지
- 통계 (구글 차트)
- 검사 안내, 상담사 소개, Contact
- 모든 페이지의 css는 나중에 할 예정
- 문서 작업

<br><br>

✒ 사용한 기술 스택 ✒
<div style="display: flex; flex-wrap: wrap;">
  <img src="https://img.shields.io/badge/JAVA-3766AB?style=flat-square&logo=java&logoColor=black">
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=flat-square&logo=javascript&logoColor=black">
</div>
<br>
<div style="display: flex; flex-wrap: wrap;">
  <img src="https://img.shields.io/badge/vue.js-4FC08D?style=flat-square&logo=vue.js&logoColor=white">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=flat-square&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=flat-square&logo=css3&logoColor=white">
  <img src="https://img.shields.io/badge/Bootstrap-7952B3?style=flat-square&logo=bootstrap&logoColor=white">
</div>
<br>
<div style="display: flex; flex-wrap: wrap;">
<img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=flat-square&logo=springboot&logoColor=white">
</div>
<br>
<div style="display: flex; flex-wrap: wrap;">
<img src="https://img.shields.io/badge/eclipseIDE-2C2255?style=flat-square&logo=eclipseide&logoColor=white">
<img src="https://img.shields.io/badge/VS%20Code-007ACC?style=flat-square&logo=visualstudiocode&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=flat-square&logo=github&logoColor=white">
</div>
<br>
<div style="display: flex; flex-wrap: wrap;">
<img src="https://img.shields.io/badge/mariaDB-1F305F?style=flat-square&logo=mariadb&logoColor=white">
</div>
<br><br>

#### ☹ axios 에러
* 문제 :
  백엔드 API 주소가 정확하고, 프론트에서도 정확한 주소로 보내고 있는데 계속 연결이 안 되는 에러가 생김.
  백엔드 포트로 들어가서 확인해봤을 때는 db 데이터가 제대로 나옴. (http://localhost:3000/api/faqquestions)
* 해결 :
  팀원들과 fork로 merge하는 과정에서 무언가가 잘못되었을 수도...
  api 주소를 처음부터 적어준다. 이렇게... axios.get('http://localhost:3000/api/faqquestions')

