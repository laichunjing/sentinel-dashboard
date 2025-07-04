/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.csp.sentinel.dashboard.rule;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.dashboard.config.NacosPropertiesConfiguration;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.ApiDefinitionEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.gateway.GatewayFlowRuleEntity;
import com.alibaba.csp.sentinel.dashboard.datasource.entity.rule.*;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigFactory;
import com.alibaba.nacos.api.config.ConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Properties;

/**
 * @author Eric Zhao
 * @since 1.4.0
 */
@Configuration
public class NacosConfig {
    private final NacosPropertiesConfiguration nacosPropertiesConfiguration;

    public NacosConfig(NacosPropertiesConfiguration nacosPropertiesConfiguration) {
        this.nacosPropertiesConfiguration = nacosPropertiesConfiguration;
    }

    @Bean
    public Converter<List<FlowRuleEntity>, String> flowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<FlowRuleEntity>> flowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, FlowRuleEntity.class);
    }
    @Bean
    public Converter<List<DegradeRuleEntity>, String> degradeRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<DegradeRuleEntity>> degradeRuleEntityDecoder() {
        return s -> JSON.parseArray(s, DegradeRuleEntity.class);
    }
    @Bean
    public Converter<List<ParamFlowRuleEntity>, String> paramRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<ParamFlowRuleEntity>> paramRuleEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRuleEntity.class);
    }

    @Bean
    public Converter<List<ParamFlowRuleCorrectEntity>, String> paramFlowRuleCorrectEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<ParamFlowRuleCorrectEntity>> paramFlowRuleCorrectEntityDecoder() {
        return s -> JSON.parseArray(s, ParamFlowRuleCorrectEntity.class);
    }
    @Bean
    public Converter<java.util.List<AuthorityRuleCorrectEntity>, String> authorityRuleCorrectEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, java.util.List<AuthorityRuleCorrectEntity>> authorityRuleCorrectEntityDecoder() {
        return s -> JSON.parseArray(s, AuthorityRuleCorrectEntity.class);
    }
    @Bean
    public Converter<java.util.List<SystemRuleEntity>, String> systemRuleCorrectEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, java.util.List<SystemRuleEntity>> systemRuleCorrectEntityDecoder() {
        return s -> JSON.parseArray(s, SystemRuleEntity.class);
    }


    @Bean
    public ConfigService nacosConfigService() throws Exception {
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, nacosPropertiesConfiguration.getServerAddr());
        properties.put(PropertyKeyConst.USERNAME,nacosPropertiesConfiguration.getUsername());
        properties.put(PropertyKeyConst.PASSWORD,nacosPropertiesConfiguration.getPassword());
        if(StringUtils.isNotEmpty(nacosPropertiesConfiguration.getNamespace())){
            properties.put(PropertyKeyConst.NAMESPACE,nacosPropertiesConfiguration.getNamespace());
        }
        return ConfigFactory.createConfigService(properties);
        //return ConfigFactory.createConfigService("localhost:8848");
    }

    /**  需要初始化网关流控规则数据源  **/
    @Bean
    public Converter<List<GatewayFlowRule>, String> gatewayFlowRuleEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<GatewayFlowRule>> gatewayFlowRuleEntityDecoder() {
        return s -> JSON.parseArray(s, GatewayFlowRule.class);
    }

    @Bean
    public Converter<List<ApiDefinitionEntity>, String> gatewayApiEntityEncoder() {
        return JSON::toJSONString;
    }

    @Bean
    public Converter<String, List<ApiDefinitionEntity>> gatewayApiEntityDecoder() {
        return s -> JSON.parseArray(s, ApiDefinitionEntity.class);
    }
}
