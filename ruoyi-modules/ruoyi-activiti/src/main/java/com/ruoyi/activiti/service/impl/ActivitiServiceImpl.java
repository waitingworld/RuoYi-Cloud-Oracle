package com.ruoyi.activiti.service.impl;

import com.ruoyi.activiti.domain.dto.ActivitiDeploy;
import com.ruoyi.activiti.mapper.ActivitiMapper;
import com.ruoyi.activiti.service.ActivitiService;
import com.ruoyi.activiti.util.ActivitiUtils;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivitiServiceImpl implements ActivitiService {
    @Autowired
    private ActivitiMapper activitiMapper;

    @Autowired
    private RepositoryService repositoryService;


    /**
     * 通过xml部署流程
     *
     * @param activitiDeploy
     * @return
     */

    @Override
    public Deployment deployProcessByXml(ActivitiDeploy activitiDeploy) {
        String xml = activitiDeploy.getXML();
        xml = ActivitiUtils.camundaToActiviti(xml, true);
        activitiDeploy.setXML(xml);
        BpmnModel bpmnModel = ActivitiUtils.convertToBpmnModel(xml);
        activitiDeploy.setProcessName(bpmnModel.getProcesses().get(0).getName());
        String processXMLFileName = activitiDeploy.getProcessName() + activitiDeploy.getProcessVersion() + ".bpmn20.xml";
        activitiDeploy.setProcessName(processXMLFileName);
        this.saveProcessXml(activitiDeploy);
        Deployment deployment = repositoryService.createDeployment()
                .addString(processXMLFileName, activitiDeploy.getXML())
                .deploy();
        return deployment;
    }

    /* *
     * 保存流程的xml
     * @param activitiDeploy
     * @return*/

    @Override
    public boolean saveProcessXml(ActivitiDeploy activitiDeploy) {
        return true;
    }
}
