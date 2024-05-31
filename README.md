## [프로젝트] 대학 상담 솔루션 시스템

[![Video Label](http://img.youtube.com/vi/KenYt3PLRZo/0.jpg)](https://www.youtube.com/watch?v=KenYt3PLRZo)

### 개요
- 대학생을 대상으로 하는 종합 상담 시스템
- 교수 상담, 취업 상담, 집단 상담, 심리 상담 중 <ins>심리 상담 구현 담당</ins>
- 총 5팀 중 1위를 차지해 **대상** 수상
- 주요 기술 : Vue.js, Spring boot, JPA, MariaDB

### 담당 업무
- 온라인 심리검사 문항 및 결과 설계
- CRUD 기능을 위한 게시판 RESTful API 구현
- 마이페이지 DB 연동 및 UI, Modal 구현
- 상담사 소개 페이지 제작
- 카카오맵 API를 이용한 지도 구현
- 최신 취업 정보를 자동으로 수집하고 정제하는 웹 크롤러 기능 구현

#### 비기술적 기여
- 프레젠테이션 자료 제작
- 프로젝트 소개 및 데모 영상을 편집하여 최종 제출물로 사용 (https://youtu.be/KenYt3PLRZo?si=Cw5v6rE-VefPzTGm)

### 특징
- 온라인으로 직업 심리 검사를 실시할 수 있음.
- 검사 결과에 따른 추천 직업과 성향에 대해 알 수 있음.
- 온라인 심리검사에 대한 접근 제한 : 로그인 필수
- 오프라인 검사가 필요할 시 예약할 수 있음.
- 자주 묻는 질문 게시판을 확인 할 수 있음. (관리자만 글 게시 가능)
- 유저 로그인 후 심리검사 결과를 확인할 수 있음. (과거 기록까지)
- 찾아오는 길 : 카카오맵 api 사용.
- 취업정보 페이지에서 인크루트의 최신 취업정보를 확인할 수 있음.


### 기술 스택
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
<br>

---
#### 스크린샷
<img src="https://github.com/chorok5/Career-Test/assets/157563710/5128d183-d43e-4c0d-abed-5f7b7374c195" width="700"/><br><br>
<img src="https://github.com/chorok5/Career-Test/assets/157563710/554f6891-6897-4ad3-9f63-d0f1978b5fa4" width="700"/><br><br>
<img src="https://github.com/chorok5/Career-Test/assets/157563710/e4a598ed-ab44-4018-a804-f8a17f1e46c5" width="700"/><br><br>
---

### axios 에러 ☹

<문제1>
* 백엔드 API 주소가 정확하고, 프론트에서도 정확한 주소로 보내고 있는데 계속 연결이 안 되는 에러가 생김.
* 백엔드 포트로 들어가서 확인해봤을 때는 db 데이터가 제대로 나옴. (http://localhost:3000/api/faqquestions)
* @CrossOrigin(origins = "*")으로 모든 출처의 요청을 허용했는데도 안 됨.
  
<해결>
* fork로 merge하는 과정에서 무언가가 잘못되었을 수도 있다.
* api 주소를 처음부터 적어준다. 이렇게 = axios.get('http://localhost:3000/api/faqquestions')
---
<문제2>
* 게시판 글 쓰기가 갑자기 안 됨. axios 에러.


<해결>
* FaqWrite.vue의 withCredentials: true를 삭제하니 cors 오류가 해결되었는지 글이 제대로 작성되기 시작했다.
---
<문제3>
* AxiosError 415 : 클라이언트와 서버 간의 응답 타입과 요청 타입이 다른 경우 발생

<해결>
```
headers: {
    'Content-Type': 'application/json'
  }
```
---

<문제4>
* 문제3과 비슷하게 클라이언트에서 보내는 형식을 서버에서 읽지 못한다. 계속 [object, Object]로 넘어감. 서버에서는 배열로 받고 있음.

<해결>
* 사용자 응답을 배열로 변환 JSON.stringify 사용, 전송하는 데이터 형식을 맞춰준다.
 ---


