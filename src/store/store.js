import { createStore } from "vuex";
//import createPersistedState from "vuex-persistedstate";

const store = createStore({
    state: {
        mname: '', // 이름 저장,
        mid: '', // id 저장,
        token: '' //토큰 값 저장
    },
    getters:{
        
    },
    mutations: {
        login(state, userInfo) {
            alert('save')
            state.mid = userInfo.mid
            state.mname = userInfo.mname
            state.token = userInfo.token
        },
        logout(state) {
            state.mname = null
            state.mid = null
            state.token = null
        },
        loginCheck(state) {
            if (!state.mname) {
                this.$router.push({
                    name: '/login'
                }).catch(error => {
                    console.log(error)
                })
            }
        }
    },
    actions: {

    },
    modules:{}
});
export default store;