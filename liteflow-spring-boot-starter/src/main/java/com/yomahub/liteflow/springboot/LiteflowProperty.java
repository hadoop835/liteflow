package com.yomahub.liteflow.springboot;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 执行流程主要的参数类
 * @author Bryan.Zhang
 */
@ConfigurationProperties(prefix = "liteflow", ignoreUnknownFields = true)
public class LiteflowProperty {

    //流程定义资源地址
    private String ruleSource;

    //slot的数量
    private int slotSize;

    //异步线程最大等待描述
    private int whenMaxWaitSeconds;

    public String getRuleSource() {
        return ruleSource;
    }

    public void setRuleSource(String ruleSource) {
        this.ruleSource = ruleSource;
    }

    public int getSlotSize() {
        return slotSize;
    }

    public void setSlotSize(int slotSize) {
        this.slotSize = slotSize;
    }

    public int getWhenMaxWaitSeconds() {
        return whenMaxWaitSeconds;
    }

    public void setWhenMaxWaitSeconds(int whenMaxWaitSeconds) {
        this.whenMaxWaitSeconds = whenMaxWaitSeconds;
    }
}
