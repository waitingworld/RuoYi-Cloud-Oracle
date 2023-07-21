package com.ruoyi.activiti.controller;

import com.ruoyi.activiti.domain.dto.ActivitiDeploy;
import com.ruoyi.activiti.security.util.ActivitiSecurityUtil;
import com.ruoyi.activiti.service.ActivitiService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.api.task.runtime.conf.TaskRuntimeConfiguration;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 工作流
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/activiti")
public class ActivitiController extends BaseController {
    @Autowired
    private ActivitiService activitiService;
    @Autowired
    private TaskRuntime taskRuntime;
    @Autowired
    private ActivitiSecurityUtil activitiSecurityUtil;

    /**
     * 通过xml部署工作流
     */
    @RequiresPermissions("activiti:process:edit")
    @PostMapping("/deployProcessByXml")
    public AjaxResult deployProcessByXml(ActivitiDeploy activitiDeploy) {
        activitiSecurityUtil.logInAs(SecurityUtils.getUsername());
        Deployment deployment = activitiService.deployProcessByXml(activitiDeploy);
        return success(deployment);
    }

    /**
     * 保存xml
     */
    @RequiresPermissions("activiti:process:edit")
    @PostMapping("/saveProcessXml")
    public AjaxResult saveProcessXml(@RequestBody ActivitiDeploy activitiDeploy) {
        activitiSecurityUtil.logInAs(SecurityUtils.getUsername());
        boolean res = activitiService.saveProcessXml(activitiDeploy);
        return success(res);
    }
}
