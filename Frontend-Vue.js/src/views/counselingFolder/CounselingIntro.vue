<template>
  <div>
    <MenuPage />
    <SimriSecMenu />
    <div class="container py-5">
      <!-- 수정된 이미지 경로 -->
      <img :src="require(`@/assets/simri01.jpg`)" />
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
