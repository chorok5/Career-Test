<template>
  <div>
    <Main></Main>
    <div class="main1">
      <div class="cardsBox boundary">
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        <div class="cards">
          <a href="" class="block">
            <img src="@/assets/sec01_chat.png" alt="">
            <h3 class="subtit">채팅상담
              <span>박시호가 테스트중</span>
            </h3>
            <span class="togo">바로가기</span>
          </a>
        </div>
        
      
      </div>
    </div>
  </div>
</template>

<script>
import Main from '@/components/Main.vue';

export default {
  components:{
    Main
  }
}
</script>

<style>
.bannerImg{
  width: 100%;
  height: 250px;
  overflow: hidden;
}
.bannerImg img{
  max-width: 100%;
  height: auto;
  object-fit: cover;
}
.boundary{
  width: 100%;
  max-width: 1200px;
  padding: 0;
  margin: 0 auto;
}
.cardsBox{
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.main1{
  margin: 5em 0 3em;
}
.cards{
  position: relative;
  height: 260px;
  width: 32.33%;
  box-sizing: border-box;
  margin-bottom: 1em;
  text-align: center;
  background: #f0f4ff;
  border-radius: 15px;
  transition: all 0.5s;
  background-color: white;
}
.cards:nth-child(1):hover{
  background-color: #edbf47;
}
.cards:hover {
	color:#fff;
	transition:all 0.5s;
}
.cards:hover a,
.cards:hover h3 span {
	color:#fff;
	text-shadow:0px 0px 3px rgba(0,0,0,0.5);
}
.cards:nth-child(1):hover .togo {color:#edbf47;}
.cards:hover .togo {
	font-weight:bold;
	background:#fff;
	text-shadow:none;
}
.block{
  display: block;
}
.cards a{
  padding: 2em;
  text-decoration: none;
}
.block img{
  display: inline-block;
  max-width: 100%;
  vertical-align: center;
}
.cards h3{ 
  padding-top: 0.8em;
  font-size: 1.1em;
  font-family: 'NanumSquareRound', 'NotoSansKR', 'NanumGothic', sans-serif;
  font-weight: bold;
}
.cards h3 span{
  display: block;
  margin-top: 0.3em;
  padding-top: 0.2em;
  font-size: 0.75em;
  color: #666;
  font-family: 'Noto Sans KR';
  font-weight: 400;
}
.togo{
  display: inline-block;
  padding: 0.2em 2em;
  margin-top: 1em;
  color: #333;
  background-color: #fff;
  border: 2px solid black;
  border-radius: 30px;
}
</style>