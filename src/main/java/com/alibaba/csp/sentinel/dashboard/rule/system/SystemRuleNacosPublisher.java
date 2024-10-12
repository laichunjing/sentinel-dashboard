package com.alibaba.csp.sentinel.dashboard.rule.system;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.SystemRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 实现将熔断规则持久化到Nacos中
 * @author gang.wang
 * 2021年11月15日
 */
@Service
public class SystemRuleNacosPublisher implements DynamicRulePublisher<List<SystemRuleEntity>> {

    private static Logger logger = LoggerFactory.getLogger(SystemRuleNacosPublisher.class);

    @Autowired
    private Converter<List<SystemRuleEntity>, String> converter;
    
    @Autowired
    private ConfigService configService;
    
    @Override
    public void publish(String app, List<SystemRuleEntity> rules) throws Exception {
        
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
        configService.publishConfig(app + NacosConfigUtil.SYSTEM_FLOW_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, converter.convert(rules));
    }

}