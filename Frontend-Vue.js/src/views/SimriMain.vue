<template>
  <div>
    <MenuPage />
    <SimriSecMenu />
  </div>
</template>

<script>
import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
    components: {
    MenuPage,
    SimriSecMenu
  }};
</script>

<style>
.sbmContainer{
    width: 100%;
    margin-bottom: 50px;
}
.subMenu{    
    width: 100%;
    margin: auto;
}
.subMenu tr th{
    width: 25%;
    height: 55px;
    border-bottom: 2px solid rgb(43, 43, 165);
}
.boundary{
    width: 100%;
    max-width: 1200px;
    padding: 0 auto;
}

</style>