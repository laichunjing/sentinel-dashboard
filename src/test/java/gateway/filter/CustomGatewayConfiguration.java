/*
package gateway;

import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
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
        return null;
    }
}
*/
