package com.alibaba.csp.sentinel.dashboard.controller.degrade;

import java.util.List;

import com.alibaba.csp.sentinel.dashboard.rule.FlowRuleNacosPublisher;
import com.alibaba.csp.sentinel.dashboard.rule.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.DegradeRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.nacos.api.config.ConfigService;

/**
 * 实现将熔断规则持久化到Nacos中
 * @author gang.wang
 * 2021年11月15日
 */
@Service
public class DegradeRuleNacosPublisher implements DynamicRulePublisher<List<DegradeRuleEntity>> {

    private static Logger logger = LoggerFactory.getLogger(FlowRuleNacosPublisher.class);

    @Autowired
    private Converter<List<DegradeRuleEntity>, String> converter;
    
    @Autowired
    private ConfigService configService;
    
    @Override
    public void publish(String app, List<DegradeRuleEntity> rules) throws Exception {
        
        if(StringUtils.isBlank(app)) {
            logger.error("传入的AppName为Null");
            return ;
        }
        
        if(null == rules) {
            logger.error("传入的熔断规则数据为null");
            return ;
        }
        AssertUtil.notEmpty(app, "app name cannot be empty");
        if (rules == null) {
            return;
        }
        configService.publishConfig(app + NacosConfigUtil.DEGRADE_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, converter.convert(rules));
    }

}