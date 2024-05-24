<template>
  
  <div class="container">
    <footer class="py-3 my-4">
      <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="#" class="nav-link px-2 text-body-secondary">Home</a></li>
        <li class="nav-item"><router-link to="/contact" class="nav-link px-2 text-body-secondary" active-class="active" exact>센터 소개</router-link></li>
        <li class="nav-item"><router-link to="/faq" class="nav-link px-2 text-body-secondary" active-class="active" exact>FAQs</router-link></li>
      </ul>
      <p class="text-center text-body-secondary">© 2024 Company, Inc</p>
    </footer>
  </div>
</template>

<script>

export default {
  name:"Footer"
}
</script>





export default {
  name: 'ContactMap',
  components: {
    MenuPage
  }
<style>

</style>