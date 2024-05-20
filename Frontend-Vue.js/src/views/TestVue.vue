<template>
    <div>
        <h1>테스트에요!</h1>
        <h1>테스트에요!</h1>
        <h1>테스트에요!</h1>
        <p>현재 로그인한 사용자 id: {{ $store.state.account.id }}</p>
        <p>현재 로그인한 사용자 email: {{ $store.state.account.email }}</p>
        <p>현재 로그인한 사용자 name: {{ $store.state.account.name }}</p>
    </div>
</template>

<script>
export default {
    name:'TestVue'
}
</script>

<style>

</style>