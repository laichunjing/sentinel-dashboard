/*
package gateway;

import com.alibaba.csp.sentinel.adapter.gateway.common.param.ConfigurableRequestItemParser;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.ServerWebExchangeItemParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // 确保此过滤器在 Sentinel 之前执行
        return -1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 获取真实 IP
        String clientIp = getClientIp(request);
        log.info("Client IP: " + clientIp);

        // 创建自定义的 RequestItemParser
        ConfigurableRequestItemParser<ServerWebExchange> parser = new ConfigurableRequestItemParser<>(new ServerWebExchangeItemParser());
        // 覆盖默认的解析逻辑
        parser.addRemoteAddressExtractor(req -> clientIp);

        // 创建 Sentinel Gateway 过滤器并确保限流只基于真实 IP
        GatewayFilter sentinelFilter = new SentinelGatewayFilter(parser);

        // 执行过滤器
        return sentinelFilter.filter(exchange, chain);
    }

    private String getClientIp(ServerHttpRequest request) {
        String ip = request.getHeaders().getFirst("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress() != null ? request.getRemoteAddress().getAddress().getHostAddress() : null;
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}
*/
