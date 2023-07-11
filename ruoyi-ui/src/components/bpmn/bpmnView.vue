<template>
  <div ref="bpmnViewContainer" class="viewContainerClass"/>
</template>

<script>
import BpmnViewer from 'bpmn-js';

export default {
  name: "bpmnView",
  props: {
    xml: String
  },
  data() {
    return {
      bpmnViewer: undefined,
      loadFinish: false
    }
  },
  mounted() {
    this.loadFinish = true
  },
  computed: {
    loadXml() {
      return this.xml + this.loadFinish
    }
  },
  watch: {
    loadXml: {
      async handler(n, o) {
        if (n && n !== o && this.loadFinish) {
          if (!this.bpmnViewer) {
            const bpmnViewer = await this.initBpmn(this.$refs.bpmnViewContainer)
            this.bpmnViewer = bpmnViewer
          }
          await this.loadBpmn(this.bpmnViewer, this.xml);
        }
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    initBpmn(bpmnViewContainer) {
      return new Promise(resolve => {
            const bpmnViewer = new BpmnViewer({
              container: bpmnViewContainer,
            })
            resolve(bpmnViewer)
          }
      );
    },
    async loadBpmn(bpmnViewer, xml) {
      // 加载BPMN文件
      try {
        const result = await bpmnViewer.importXML(xml);
        const {warnings} = result;
        console.log('bpmnView', warnings);
      } catch (err) {
        console.log('bpmnView', err.message, err.warnings);
      }
    },
  }
}
</script>

<style scoped lang="scss">
.viewContainerClass {
  width: 100%;
  height: 100%;
}
</style>
