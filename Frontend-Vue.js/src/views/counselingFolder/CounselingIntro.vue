<template>
  <div>
    <MenuPage />
    <div class="boundary">
    <h1 class="title">심리 상담</h1>
    <SimriSecMenu />
    <div class="container py-5">
      <!-- 수정된 이미지 경로 -->
      <img :src="require(`@/assets/simri_intro.png`)" />
    </div>
  </div>
  </div>
</template>

<script>
import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
  name: 'CounselingInfo',
  components: {
    MenuPage,
    SimriSecMenu
  }
};
</script>
