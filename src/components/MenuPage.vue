<template>
  <header>
    <nav class="nav">
      <ul>
        <li><router-link to="/helloWorld">index</router-link></li>
        <li><router-link to="/boardList">게시판</router-link></li>
        <li><router-link to="/testPage">메뉴1</router-link></li>
      </ul>
    </nav>
  </header>
</template>
<script>

export default {
  name: 'MenuPage'
};
</script>
<style>
header {
  height: 50px;
}

nav {
  position: fixed;
  top: 0;
  width: 100%;
  height: 30px;
  background-color: gray;
}

nav ul {
  margin: 0;
  padding: 0;
}

nav li {
  list-style: none;
  float: left;
  width: 100px;
  height: 30px;
  line-height: 30px;
  text-align: center;
}

nav li:hover {
  font-weight: bold;
}
</style>