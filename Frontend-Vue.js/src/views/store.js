import {createStore} from 'vuex'

const store = createStore({
    //스토어의 초기 상태를 반환
    state() {
        return {
            account: {
                id: 0, // 숫자 타입 필드의 초기값
                email: '', // 문자열 타입 필드의 초기값
                name: '' // 문자열 타입 필드의 초기값
            }
        }
    },
    mutations: {
        setAccount(state, payload) {
            // id 값 payload 에 설정하겠다
            console.log('Payload:', payload);
            state.account.id = payload.id;
            state.account.email = payload.email;
            state.account.name = payload.name;
        }
    }
})

export default store;