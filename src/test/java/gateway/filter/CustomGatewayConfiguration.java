/*
package com.ugreen.cloud.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.ugreen.cloud.gateway.filter.RealIpRequestItemParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class CustomGatewayConfiguration {

    @Bean
    @ConditionalOnProperty(
            prefix = "spring.cloud.sentinel.scg.filter",
            name = {"enabled"},
            havingValue = "false"
    )
    @ConditionalOnMissingBean(SentinelGatewayFilter.class)
    public SentinelGatewayFilter customSentinelGatewayFilter() {
        // 返回 null 或不创建 SentinelGatewayFilter 的实例
        // return null;
        log.info("Initializing custom Sentinel filter with real IP support");
        return new SentinelGatewayFilter(new RealIpRequestItemParser());
    }
}
*/
