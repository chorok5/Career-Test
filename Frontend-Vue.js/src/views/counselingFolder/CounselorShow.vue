<!-- src/components/CounselorProfile.vue -->
<template>
  <div class="container my-5 p-lg-5 bg-white rounded shadow-lg">
    <h2 class="text-center mb-4">상담사 소개</h2>
    <div class="row align-items-center mb-4">
      <div class="col-md-4 text-center">
        <img
          :src="counselor.image"
          alt="Counselor Image"
          class="img-fluid rounded-circle mb-3 shadow"
          style="width: 250px; height: 250px; object-fit: cover;"
        />
      </div>
      <div class="col-md-8">
        <h3 class="mb-1">{{ counselor.name }}</h3>
        <p class="text-muted mb-3">{{ counselor.title }}</p>
        <p class="mb-4">{{ counselor.bio }}</p>
        <ul class="list-unstyled">
          <li class="mb-3 row">
            <strong class="col-sm-4">전문 분야:</strong>
            <span class="col-sm-8">{{ counselor.expertise }}</span>
          </li>
          <li class="mb-3 row">
            <strong class="col-sm-4">경력:</strong>
            <span class="col-sm-8">{{ counselor.experience }}년</span>
          </li>
          <li class="mb-3 row">
            <strong class="col-sm-4">자격증:</strong>
            <span class="col-sm-8">{{ counselor.certifications }}</span>
          </li>
          <li class="mb-3 row">
            <strong class="col-sm-4">연락처:</strong>
            <span class="col-sm-8">
              <a :href="'mailto:' + counselor.email">{{ counselor.email }}</a>
            </span>
          </li>
        </ul>
      </div>
    </div>
    <hr class="my-4" />
    <div>
      <h4>인사말</h4>
      <blockquote class="blockquote">
        <p class="mb-0">{{ counselor.greeting }}</p>
      </blockquote>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      counselor: {
        name: '김철수',
        title: '임상 심리사',
        image: 'https://via.placeholder.com/250',
        bio: '10년 경력의 임상 심리 전문가로 대학생들의 다양한 심리 문제를 상담합니다.',
        expertise: '우울증, 불안장애, 학업 스트레스',
        experience: 10,
        certifications: '임상심리사 1급, 상담심리사 2급',
        email: 'kimcounselor@example.com',
        greeting: '안녕하세요, 저는 대학생들의 성장과 행복을 돕고자 노력하고 있습니다. 언제든지 편안하게 상담을 요청해주세요.',
      },
    }
  },
}
</script>

<style scoped>
/* 추가 스타일을 작성하세요 */
</style>