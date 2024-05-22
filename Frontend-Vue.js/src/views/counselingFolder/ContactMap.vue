<template>
  <div>
    <MenuPage />
    <SimriSecMenu />
    <h1>Contact 게시판 입니다. 위치 지도 불러오기</h1>

    <div class="mt-2">
    <div id="map" style="width: 500px; height: 400px"></div>
  </div>
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
        center: new window.kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표.
        level: 3 // 지도의 레벨(확대, 축소 정도)
      }

      this.map = new window.kakao.maps.Map(container, options) // 지도 생성 및 객체 리턴
      this.loadMaker()
    },
    loadMaker() {
      // 마커가 표시될 위치입니다
      const markerPosition = new window.kakao.maps.LatLng(33.450701, 126.570667)

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

