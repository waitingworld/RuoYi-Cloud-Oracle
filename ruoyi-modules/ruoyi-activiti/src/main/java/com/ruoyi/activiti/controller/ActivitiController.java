package com.ruoyi.activiti.controller;

import com.ruoyi.activiti.domain.dto.ActivitiDeploy;
import com.ruoyi.activiti.security.util.ActivitiSecurityUtil;
import com.ruoyi.activiti.service.ActivitiService;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.common.security.utils.SecurityUtils;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


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
    public AjaxResult deployProcessByXml(@RequestBody ActivitiDeploy activitiDeploy) {
        if (StringUtils.isEmpty(activitiDeploy.getXML())) {
            return error("缺失XML");
        }
        activitiSecurityUtil.logInAs(SecurityUtils.getUsername());
        activitiDeploy.setCreatorName(SecurityUtils.getLoginUser().getSysUser().getNickName());
        activitiDeploy.setCreatorId(String.valueOf(SecurityUtils.getUserId()));
        activitiDeploy.setCreatTime(LocalDateTime.now());
        Deployment deployment = activitiService.deployProcessByXml(activitiDeploy);
        return success(deployment);
    }

    /**
     * 保存xml
     */
    @RequiresPermissions("activiti:process:edit")
    @PostMapping("/saveProcessXml")
    public AjaxResult saveProcessXml(@RequestBody ActivitiDeploy activitiDeploy) {
        if (StringUtils.isEmpty(activitiDeploy.getXML())) {
            return error("缺失XML");
        }
        activitiSecurityUtil.logInAs(SecurityUtils.getUsername());
        boolean res = activitiService.saveProcessXml(activitiDeploy);
        return success(res);
    }
}
