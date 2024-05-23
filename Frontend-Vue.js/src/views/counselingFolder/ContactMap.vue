<template>
  <div>
    <MenuPage />
    <h1>상담센터 오시는 길</h1><br>
    <hr class="hr"><br><br>
    <div class="container">
      <div class="row" style="display: flex; justify-content: center;"> 
        <div class="col-md-3 left-side">
          <h2>Kidwiz 센터</h2><br>
          <p>🏤 서울 금천구 가산디지털2로 70</p>
          <p>📞 012-3456-7890</p>
        </div>
        <div class="col-md-7">
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
  setup() {},
  created() {},
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.loadMap()
    } else {
      this.loadScript()
    }
  },
  unmounted() {},
  methods: {
    loadScript() {
      const script = document.createElement('script')
      script.src =
        '//dapi.kakao.com/v2/maps/sdk.js?appkey=a78861db663d003ff9447110474d0506&autoload=false' // autoload=false를 해줘야 밑에 시점에 실행됨.
      script.onload = () => window.kakao.maps.load(this.loadMap) // window.kakao.maps.load : 맵을 로드할 준비가 다 끝난 상태
      // script.onload는 script.addEventListener('load', () => {}) 와 같다.

      document.head.appendChild(script)
    },
    loadMap() {
      const container = document.getElementById('map') // 지도를 담을 영역의 DOM 레퍼런스
      const options = {
        // 지도를 생성할 때 필요한 기본 옵션
        center: new window.kakao.maps.LatLng(37.474706676466, 126.882341560662), // 지도의 중심좌표.
        level: 3 // 지도의 레벨(확대, 축소 정도)
      }

      this.map = new window.kakao.maps.Map(container, options) // 지도 생성 및 객체 리턴
      this.loadMaker()
    },
    loadMaker() {
      // 마커가 표시될 위치입니다
      const markerPosition = new window.kakao.maps.LatLng(37.474706676466, 126.882341560662)

      // 마커를 생성합니다
      const marker = new window.kakao.maps.Marker({
        position: markerPosition
      })

      // 마커가 지도 위에 표시되도록 설정합니다
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
  width: 800px;
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
  width: 100%; /* 수평선의 너비를 1200px로 설정합니다. */
  border: none; /* 기본적인 테두리 제거 */
  height: 1px; /* 수평선의 높이를 1px로 설정합니다. */
  background-color: #8a8a8a; /* 수평선의 색상을 설정합니다. */
  margin: 0 auto; /* 가운데 정렬을 위해 좌우 마진을 auto로 설정합니다. */
}

</style>