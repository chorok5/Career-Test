<template>
  <div>
    <div v-if="modelValue" class="modal fade show" style="display: block;" aria-modal="true" role="dialog">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">{{ event.title }}</h5>
            <button type="button" class="btn-close" @click="close"></button>
          </div>
          <div class="modal-body">
            시작 시간: {{ new Date(event.start).toLocaleString() }}
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" @click="close">닫기</button>
          </div>
        </div>
      </div>
    </div>
    <div v-if="modelValue" class="modal-backdrop fade show"></div>
  </div>
  </template>
  
  <script>
  export default {
    props: {
      
      modelValue: {
      type: Boolean,
      default: false
    },
      event: {
        type: Object,
        required: false,
        default: () => ({})
      }
    },
    methods: {
      close() {
        this.$emit('update:modelValue', false);
      }
    }
  }
  </script>
  