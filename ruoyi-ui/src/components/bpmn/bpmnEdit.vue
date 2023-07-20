<template>
  <div class="bpmnEditClass">
    <div ref="bpmnEditContainer" class="containerClass"></div>
    <div ref="bpmnEditPropertiesPanel" class="panelClass"/>
    <div class="btnClass">
      <el-button-group>
        <el-button type="primary" @click="saveXml">预览</el-button>
        <el-button type="primary" @click="submitBpmnXML">提交</el-button>
        <el-button type="primary" @click="saveXml">部署</el-button>
      </el-button-group>
    </div>
  </div>
</template>

<script>
import BpmnModel from "bpmn-js/lib/Modeler";
import {
  BpmnPropertiesPanelModule,
  BpmnPropertiesProviderModule,
  CamundaPlatformPropertiesProviderModule
} from 'bpmn-js-properties-panel';
import CamundaBpmnModdle from 'camunda-bpmn-moddle/resources/camunda.json'
import customTranslateModule from './Translate'
import {saveProcessXml} from "@/api/activiti/activiti";

export default {
  name: "bpmnEdit",
  props: {
    xml: String
  },
  data() {
    return {
      bpmnModel: undefined,
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
          if (!this.bpmnModel) {
            const bpmnModel = await this.initBpmn(this.$refs.bpmnEditContainer, this.$refs.bpmnEditPropertiesPanel)
            this.bpmnModel = bpmnModel
          }
          await this.loadBpmn(this.bpmnModel, this.xml);
        }
      },
      immediate: true,
      deep: true
    }
  },
  methods: {
    initBpmn(bpmnEditContainer, bpmnEditPropertiesPanel) {
      return new Promise(resolve => {
            const bpmnModel = new BpmnModel({
              container: bpmnEditContainer,
              propertiesPanel: {
                parent: bpmnEditPropertiesPanel
              },
              additionalModules: [
                BpmnPropertiesPanelModule,
                BpmnPropertiesProviderModule,
                CamundaPlatformPropertiesProviderModule,
                customTranslateModule,
              ],
              moddleExtensions: {
                camunda: CamundaBpmnModdle
              }
            })
            resolve(bpmnModel)
          }
      );
    },
    async loadBpmn(bpmnModel, xml) {
      // 加载BPMN文件
      try {
        if (xml) {
          const result = await bpmnModel.importXML(xml);
          const {warnings} = result;
          console.log('bpmnEdit warnings', warnings);
        } else {
          const result = bpmnModel.createDiagram(() => {
          });
          const {warnings} = result;
          console.log('bpmnEdit warnings', warnings);
        }
      } catch (err) {
        console.log('bpmnEdit err', err.message, err.warnings);
      }
    },
    async saveXml() {
      try {
        const result = await this.bpmnModel.saveXML({format: true});
        const {xml} = result;
        console.log(xml);
        return xml;
      } catch (err) {
        console.log(err);
      }
    },
    async submitBpmnXML() {
      const xml = await this.saveXml()
      const params = {
        processVersion: '',
        XML: 'xml'
      }
      const result = await saveProcessXml(params)
      debugger
    },
  }
}
</script>

<style scoped lang="scss">
.bpmnEditClass {
  position: relative;
  width: 100%;
  height: 100%;

  .containerClass {
    width: 100%;
    height: 100%;
  }

  .panelClass {
    position: fixed;
    top: 10vh;
    right: 5vw;
    width: 20vw;
    height: 80vh;
    background-color: rgba(54, 163, 247, 0.17);
  }

  .btnClass {
    position: absolute;
    top: 1vh;
    left: 3vw;
  }
}
</style>
