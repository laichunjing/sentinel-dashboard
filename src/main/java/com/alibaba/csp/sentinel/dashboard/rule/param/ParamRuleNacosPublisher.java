package com.alibaba.csp.sentinel.dashboard.rule.param;

import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleCorrectEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.ParamFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.rule.DynamicRulePublisher;
import com.alibaba.csp.sentinel.dashboard.rule.NacosConfigUtil;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.util.AssertUtil;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 实现将熔断规则持久化到Nacos中
 * @author gang.wang
 * 2021年11月15日
 */
@Service
public class ParamRuleNacosPublisher implements DynamicRulePublisher<List<ParamFlowRuleEntity>> {

    private static Logger logger = LoggerFactory.getLogger(ParamRuleNacosPublisher.class);

    //@Autowired
    //private Converter<List<ParamFlowRuleEntity>, String> converter;
    @Autowired
    private Converter<List<ParamFlowRuleCorrectEntity>, String> converter;
    
    @Autowired
    private ConfigService configService;
    
    @Override
    public void publish(String app, List<ParamFlowRuleEntity> rules) throws Exception {
        
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
        //  转换
        List<ParamFlowRuleCorrectEntity> list = rules.stream().map(rule -> {
            ParamFlowRuleCorrectEntity entity = new ParamFlowRuleCorrectEntity();
            BeanUtils.copyProperties(rule, entity);
            return entity;
        }).collect(Collectors.toList());
        configService.publishConfig(app + NacosConfigUtil.PARAM_FLOW_DATA_ID_POSTFIX,
                NacosConfigUtil.GROUP_ID, converter.convert(list));
    }

}