<template>
  <header>
    <nav class="nav">
        <li><router-link to="/testVue">테스트뷰</router-link></li>
        <li><router-link to="/listTest">DB테스트</router-link></li>
        <li><router-link to="/BoardList">박선우</router-link></li>
        <li><router-link to="/insertTest">insert 테스트</router-link></li>
        <li><router-link to="/PersonTest">person 테스트</router-link></li>
        <li><router-link to="/CareerTest">심리검사</router-link></li>
    </nav>
  </header>
</template>

<script>
export default {
    name:'MenuPage'
}
</script>

<style>
header{height: 50px;}
nav{position: fixed;top: 0;width: 100%;height:30px;background-color: gray;}
nav ul{margin: 0;padding: 0;}
nav li{list-style: none;float: left;width: 100px;height:30px;
line-height:30px;text-align: center;}
nav li:hover {font-weight: bold;}
a{text-decoration:none; color: white}
.lir{float:right; width: 80px;}
</style>