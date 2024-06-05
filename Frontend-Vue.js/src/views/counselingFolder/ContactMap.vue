<template>
  <div>
    <MenuPage />
    <!-- 센터 소개 -->
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-lg-10 p-5 bg-light rounded">
          <h1 class="mb-4 text-center" style="font-weight: bolder;">센터 소개</h1>
          <hr class="hr" />
          <p class="lead mb-4" style="font-weight: 500;">
            안녕하세요, 대학교 상담센터에 오신 것을 환영합니다.<br>
            저희 상담센터는 학생들이 <mark class="mark-bg">학업, 진로, 인간관계, 정신건강</mark> 등<br>
            다양한 문제를 해결할 수 있도록 전문적인 상담 서비스를 제공하고 있습니다.
          </p>
          |<br>
          <br>
          <p style="font-weight: 400;">
            현대 사회에서 대학생들은 여러 가지 도전에 직면하게 되며, 이러한 도전들은 적절한 지원이 없으면 큰 스트레스를 유발할 수 있습니다.<br>
            저희 상담센터는 학생들의 정신적, 정서적 안정을 돕고, 전반적인 삶의 질을 향상시키기 위해 최선을 다하고 있습니다.
          </p>
          <p style="font-weight: 400;">
            저희 상담센터는 언제나 학생들의 편에서 고민을 듣고, 문제를 해결할 수 있는 길을 함께 모색하고 있습니다.<br>
            필요한 도움을 받고자 한다면 언제든지 방문해 주세요. 여러분의 건강한 대학 생활을 위해 최선을 다하겠습니다.
          </p>
        </div>
      </div>
    </div>

    <!-- 상담센터 오시는 길 -->
    <div class="container mt-5" style="width:1100px;">
      <h2 style="font-weight: bolder;">위치 안내</h2>
      <hr class="hr" />
      <div class="row justify-content-center mt-5">
        <div class="col-md-4 text-left">
          <h2 style="font-weight: bold;">Kidwiz 상담센터</h2><br><br>
          <p>🏤 서울 금천구 가산디지털2로 70</p>
          <p>📞 012-3456-7890</p>
        </div>
        <div class="col-md-8">
          <div id="map" class="map"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MenuPage from '@/components/MenuPage.vue';

export default {
  name: 'ContactMap',
  components: {
    MenuPage,
  },
  data() {
    return {
      map: null
    }
  },
  setup() { },
  created() { },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap()
    } else {
      this.loadScript()
    }
  },
  unmounted() { },
  methods: {
    loadScript() {
      const script = document.createElement('script')
      script.src =
        '//dapi.kakao.com/v2/maps/sdk.js?appkey=a78861db663d003ff9447110474d0506&autoload=false'
      script.onload = () => window.kakao.maps.load(this.loadMap)

      document.head.appendChild(script)
    },
    loadMap() {
      const container = document.getElementById('map') // 지도를 담을 영역의 DOM 레퍼런스
      const options = {
        // 지도를 생성할 때 필요한 기본 옵션
        center: new window.kakao.maps.LatLng(37.474706676466, 126.882341560662), // 지도의 중심좌표.
        level: 3 // 지도의 레벨(확대, 축소 정도)
      }

      this.map = new window.kakao.maps.Map(container, options) 
      this.loadMaker()
    },
    loadMaker() {
      const markerPosition = new window.kakao.maps.LatLng(37.474706676466, 126.882341560662)

      const marker = new window.kakao.maps.Marker({
        position: markerPosition
      })

      marker.setMap(this.map)
    }
  }
}
</script>

<style>
.left-side {
  text-align: left;
  padding: 0 20px;
}

.map {
  width: 700px;
  height: 400px;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
  margin-top: 50px;
  margin-bottom: 100px;
}

.hr {
  display: block;
  width: 100%;
  border: none;
  height: 1px;
  background-color: #8a8a8a;
  margin: 0 auto;
}

.mark-bg {
  background-color: #C4FDB4;
  font-weight: 700;
}

.text-decoration-underline {
  border: #C4FDB4;
}
</style>
