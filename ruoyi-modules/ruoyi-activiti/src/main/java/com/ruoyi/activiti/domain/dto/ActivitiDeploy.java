package com.ruoyi.activiti.domain.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ActivitiDeploy {
    private String processName;
    private String processVersion;
    private String XML;
    private String creatorId;
    private String creatorName;
    private LocalDateTime creatTime;

    public void setXML(String XML) {
        this.XML = XML;
    }
}
