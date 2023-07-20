package com.ruoyi.activiti.service;

import com.ruoyi.activiti.domain.dto.ActivitiDeploy;
import com.ruoyi.activiti.domain.vo.result.ActivitiDeployResult;
import org.activiti.engine.repository.Deployment;

public interface ActivitiService {
    Deployment deployProcessByXml(ActivitiDeploy activitiDeploy);
    boolean saveProcessXml(ActivitiDeploy activitiDeploy);
}
