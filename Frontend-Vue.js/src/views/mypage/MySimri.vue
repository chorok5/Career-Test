<template>
    <div>
      <HeaderMenu></HeaderMenu>
      <div class="container-fluid mt-5 pt-4">
        <div class="row">
          <MySidebar></MySidebar>

        </div>
      </div>
      <Footer></Footer>
    </div>
  </template>
  
  <script>
  import HeaderMenu from '@/components/HeaderMenu.vue';
  import MySidebar from '@/components/MySidebar.vue';
  
  export default {
    name: 'MySimri',
    components: {
      HeaderMenu,
      MySidebar,
    },
    data() {
      return {
        isValidResult: true,
        result: {
          title: '심리검사 결과 제목',
          description: '심리검사 결과 설명',
          totalScore: 28,
          testDate: '2024-05-18', // 심리검사 테스트 날짜
          recommendedJobs: '의사, 엔지니어, 교사',
          personalTraits: [
            '친절하고 사려 깊음',
            '논리적이고 분석적',
            '창의적이고 예술적'
          ]
        },
        traitTitles: [
          '성격 특성 1',
          '성격 특성 2',
          '성격 특성 3'
        ]
      };
    },
    methods: {
      retakeTest() {
        // 다시 검사하기 로직 구현
        console.log('다시 검사하기');
      }
    }
  }
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
  