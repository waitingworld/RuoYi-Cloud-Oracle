package com.ruoyi.activiti.domain.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Locale;

@Data
public class ActivitiDeploy {
    @NotBlank
    private String processName;
    @NotBlank
    private String processVersion;
    @NotBlank
    private String XML;
    private String creatorId;
    private String creatorName;
    private LocalDateTime creatTime;
}
