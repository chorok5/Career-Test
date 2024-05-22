<template>
    <div class="custom-container">
        <MenuPage />
        <SimriSecMenu />
        <div class="ems_cnts">
            <div class="intro_box psyexam mb-4">
                <p>
                    진로검사 및 직업심리검사는 개인의 능력과 흥미, 성격 등의 심리적인 특성들이 각 직업에서 욕구하는 능력수준 및 특성에 얼마나 적합한지를 과학적인 방법으로 측정하여 성공가능성이 높고 만족할 만한 직업들을 탐색하도록 도와드립니다.
                </p>
            </div>
            <h4 class="dot_tit mb-4">직업심리검사 종류</h4>
            <div class="row justify-content-center">
                <div class="col-12 mb-4" v-for="(item, index) in items" :key="index">
                    <div class="card h-100 mx-auto" style="max-width: 1000px;">
                        <div class="card-body d-flex flex-column justify-content-between">
                            <div>
                                <h5 class="card-title text-left">{{ item.title }}</h5>
                                <p class="card-text text-left" v-html="item.description"></p>
                            </div>
                            <div class="text-right">
                                <a :href="item.link" target="_blank" class="btn btn-primary">바로가기</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import MenuPage from '@/components/MenuPage.vue';
import SimriSecMenu from '@/views/counselingFolder/SimriSecMenu.vue';

export default {
    name: 'PersonTest',
    components: {
        MenuPage,
        SimriSecMenu
    },
    data() {
        return {
            items: [
                {
                    title: '직업선호도검사 S형',
                    description: '직업선호도 검사는 좋아하는 활동, 관심 있는 직업, 선호하는 분야를 탐색하여<br>여러분의 직업흥미유형에 적합한 직업들을 제공해 줍니다.',
                    link: 'https://www.work.go.kr/consltJobCarpa/jobPsyExam/aduPreSNewDetail.do'
                },
                {
                    title: '직업선호도검사 L형',
                    description: '직업선호도 검사는 좋아하는 활동, 관심 있는 직업, 선호하는 분야를 탐색하여<br>여러분의 직업흥미유형에 적합한 직업들을 제공해 줍니다.',
                    link: 'https://www.work.go.kr/consltJobCarpa/jobPsyExam/aduPreLNewDetail.do'
                },
                {
                    title: '대학생 진로준비도 검사',
                    description: '대학생 및 취업을 준비하는 대졸 청년층 구직자들을 대상으로 하며, 진로발달수준과 취업준비 행동수준에 대한 객관적인 정보를 바탕으로 효과적인 진로 및 취업선택을 지원하고자 개발된 검사입니다.',
                    link: 'https://www.work.go.kr/consltJobCarpa/jobPsyExam/univJobPreDetail.do'
                },
                {
                    title: '구직준비도검사',
                    description: '구직을 희망하는 사람들이 성공적인 구직을 할 준비가 되어 있는가를 알아보고, 이를 토대로 적합한 취업지원 서비스를 선택할 수 있도록 해주는 검사입니다.',
                    link: 'https://www.work.go.kr/consltJobCarpa/jobPsyExam/aduEquipDetail.do'
                }
            ]
        };
    }
};
</script>

<style scoped>
.intro_box {
    background-color: #f3f8fd;
    padding: 20px;
    border-radius: 5px;
    width: 100%;
    max-width: 1000px;
    margin: 0 auto;
}
.dot_tit {
    font-weight: bold;
}
.custom-container {
    flex-direction: column;
    align-items: center;
    padding-top: 0; /* 상단 패딩 제거 */
}
.card {
    width: 100%;
    max-width: 1000px;
}
.card-body {
    padding: 20px;
}
.text-left {
    text-align: left;
}
.text-right {
    text-align: right;
}
</style>
