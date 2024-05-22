<template>
  <div>
    <MenuPage />
    <SimriSecMenu />
    <div class="container py-5 px-lg-7 rounded shadow-lg">
      <div class="row justify-content-center">
        <div class="col-lg-9">
          <div class="profile-header text-white py-4 rounded-top" style="background-color: cadetblue;">
            <div class="row align-items-center">
              <div class="col-md-4 order-md-2 text-center">
                <img
                  :src="require('@/assets/simri2.png')"
                  alt="Counselor Image"
                  class="img-fluid rounded-circle mb-3 shadow"
                  style="width: 250px; height: 250px; object-fit: cover;"
                />
              </div>
              <div class="col-md-8 order-md-1 text-white">
                <h1 class="mb-1">{{ counselor.name }}</h1>
                <p class="mb-3">{{ counselor.title }}</p>
                <p class="mb-4">{{ counselor.bio }}</p>
                
              </div>
            </div>
          </div>
          <div class="profile-body bg-white rounded shadow-sm p-4 mt-n5 position-relative">
            
            <p class="mb-4">{{ counselor.greeting }}</p>
            <div class="card mb-4">
                <div class="card-body">
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item d-flex justify-content-between align-items-center bg-light">
                      <strong>전문분야</strong>
                      <span class="text-muted">{{ counselor.expertise }}</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center bg-light">
                      <strong>경력</strong>
                      <span class="text-muted">{{ counselor.experience }}년</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center bg-light">
                      <strong>자격증</strong>
                      <span class="text-muted">{{ counselor.certifications }}</span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between align-items-center bg-light">
                      <strong>연락처</strong>
                      <span class="text-muted">
                        <a :href="'mailto:' + counselor.email" class="text-decoration-none">{{ counselor.email }}</a>
                      </span>
                  </li>
                </ul>
                      </div>
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
  components: {
    MenuPage,
    SimriSecMenu
  },
  data() {
    return {
      counselor: {
        name: '김 상담',
        title: '임상 심리사',
        image: 'https://via.placeholder.com/250',
        bio: '10년 경력의 임상 심리 전문가로 대학생들의 다양한 심리 문제를 상담합니다.',
        expertise: '우울증, 불안장애, 학업 스트레스',
        experience: 10,
        certifications: '임상심리사 1급, 상담심리사 2급',
        email: 'kimcounselor@example.com',
        greeting: '안녕하세요. 임상 심리사 김상담입니다! 대학생 시절의 어려움과 성장을 체험해 왔기에 학생들과의 상담을 통해 그들의 성장과 행복을 돕고자 합니다. 저는 존중과 이해로운 태도로 항상 듣고자 하며, 함께 어려움을 극복해 나가는 것에 대해 열정을 가지고 있습니다. 언제든지 편안하게 상담을 요청해주세요!',
      }
    }
  }
}
</script>

<style scoped>
.container {
  padding: 30px;
  background-color: #f9f9f9;
  border-radius: 8px;
  max-width : 1200px;
}

.card {
  margin-bottom: 20px;
}

.card-body {
  padding: 20px;
}

.list-unstyled {
  margin: 0;
  padding: 0;
}

.row {
  display: flex;
  align-items: center;
}

.col-sm-4 {
  font-weight: bold;
}

.img-fluid {
  max-width: 100%;
  height: auto;
}
</style>
