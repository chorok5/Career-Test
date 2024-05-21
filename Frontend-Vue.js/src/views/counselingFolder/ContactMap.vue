<template>
  <div>
    <MenuPage />
    <SimriSecMenu />
    <h1>Contact 게시판 입니다. 위치 지도 불러오기</h1>
  </div>
</template>

<script>
import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
  name: 'ContactMap',
    components: {
    MenuPage,
    SimriSecMenu
  }
  
};
</script>
<style scoped>

</style>