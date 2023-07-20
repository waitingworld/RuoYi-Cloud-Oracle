<template>
  <div class="processEditClass">
    <bpmn-edit :xml="xml"/>
  </div>
</template>
<script>
import xml from "@/components/bpmn/xml";
import BpmnEdit from "@/components/bpmn/bpmnEdit.vue";

export default {
  name: "ProcessEdit",
  components: {BpmnEdit},
  computed: {
    xml() {
      return xml
    }
  }
};
</script>
<style scoped lang="scss">
.processEditClass {
  width: 100%;
  height: calc(100vh - 84px);
}
</style>