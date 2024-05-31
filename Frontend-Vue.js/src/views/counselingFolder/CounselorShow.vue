<template>
  <div>
    <MenuPage />
    <div class="boundary">
      <h1 class="title">심리 상담</h1>
      <SimriSecMenu />
      <div class="container py-6 px-lg-7 rounded shadow-lg mt-5">
        <div class="row justify-content-center">
          <div class="col-lg-11">
            <div class="profile-header text-white py-4 rounded-top" style="margin-top:50px; background-color: #67BF4E;">
              <div class="row align-items-center">
                <div class="col-md-4 order-md-2 text-center">
                  <img :src="require('@/assets/157563710.jpg')" alt="Counselor Image"
                    class="img-fluid rounded-circle mb-3 shadow"
                    style="width: 250px; height: 250px; object-fit: cover;" />
                </div>
                <div class="col-md-8 order-md-2 text-white" style="text-align: left; padding-left: 50px;">
                  <h1 class="mb-1">{{ counselor.name }}</h1>
                  <p class="mb-3">{{ counselor.title }}</p>
                  <br>
                  <p class="mb-4">{{ counselor.bio }}</p><br>
                  <p :href="'mailto:' + counselor.email" class="text-decoration-none">{{ counselor.email }}</p>
                </div>
              </div>
            </div>

            <div class="profile-body bg-white rounded p-4 mt-n5 position-relative">
              <ul class="lead mb-4"
                style="font-weight: 500; font-size: small; text-align: left; list-style: none; padding-left: 0;">
                <li v-for="item in counselor.greeting.split('?')" :key="item" v-html="item.trim()"></li>
              </ul><br>

              <div class="row">
                <div class="col-md-4 mb-8">
                  <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body d-flex flex-column align-items-center justify-content-center">
                      <h5 class="card-title font-weight-bold" style="font-weight: bold;">경력</h5>
                      <p class="card-text text-center">{{ counselor.experience }} 년</p>
                    </div>
                  </div>
                </div>

                <div class="col-md-4 mb-8">
                  <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body d-flex flex-column align-items-center justify-content-center">
                      <h5 class="card-title font-weight-bold" style="font-weight: bold;">전문분야</h5>
                      <ul class="card-text text-center list-unstyled">
                        <li v-for="item in counselor.expertise.split(',')" :key="item">{{ item.trim() }}</li>
                      </ul>
                    </div>
                  </div>
                </div>

                <div class="col-md-4 mb-8">
                  <div class="card h-100 border-0 shadow-sm">
                    <div class="card-body d-flex flex-column align-items-center justify-content-center">
                      <h5 class="card-title font-weight-bold" style="font-weight: bold;">자격증</h5>
                      <ul class="card-text text-center list-unstyled">
                        <li v-for="cert in counselor.certifications.split(',')" :key="cert">{{ cert.trim() }}</li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <button @click="contactCounselor" class="btn btn-success btn-lg btn-block mt-2 shadow-sm"
      style="margin-bottom:40px;">상담 요청</button>
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
        name: '김상담',
        title: '임상 심리사',
        image: 'https://via.placeholder.com/250',
        bio: '10년 경력의 임상 심리 전문가로 대학생들의 다양한 심리 문제를 상담합니다.',
        expertise: '우울증, 불안장애, 학업 스트레스',
        experience: 10,
        certifications: '임상심리사 1급, 상담심리사 2급',
        email: 'kimcounselor@example.com',
        greeting: `
          안녕하세요. 임상 심리사 김상담입니다!<br>
          대학생 시절의 어려움과 성장을 체험해 왔기에 학생들과의 상담을 통해 그들의 성장과 행복을 돕고자 합니다.<br>
          저는 존중과 이해로운 태도로 항상 듣고자 하며, 함께 어려움을 극복해 나가는 것에 대해 열정을 가지고 있습니다.<br>
          언제든지 편안하게 상담을 요청해주세요!
        `,
      }
    }
  },
  methods: {
    contactCounselor() {
      this.$router.push('rsrvTest4?selectedCounselingType=심리상담');
    }
  }
}
</script>

<style scoped>
.container {
  max-width: 1200px;
  background-color: #f9f9f9;
  border-radius: 8px;
  margin-bottom: 20px;
}

.profile-body {
  background-color: white;
  padding: 30px;
  border-radius: 8px;
}

.card {
  border: none;

}

.card-body {
  padding: 0;
  height: 150px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(238, 238, 238, 0.3), 0 6px 20px rgba(202, 202, 202, 0.19);
}

.row>.col-lg-3,
.row>.col-md-6 {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.bg-light {
  background-color: #e7f3ff !important;
}

.btn {
  font-size: 1.2rem;
  background-color: #67BF4E;
  border: #67BF4E;
}

.title {
  text-align: left;
  font-family: 'sj';
  margin-left: 15px;
  margin-bottom: 40px;
}

.card-text {
  font-size: small;
}

.card-title {
  width: 100%;
}
</style>