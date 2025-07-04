/*
package gateway.filter;

import com.alibaba.csp.sentinel.adapter.gateway.common.param.ConfigurableRequestItemParser;
import com.alibaba.csp.sentinel.adapter.gateway.sc.ServerWebExchangeItemParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
@Slf4j
public class RealIpRequestItemParser extends ConfigurableRequestItemParser<ServerWebExchange> {
    
    public RealIpRequestItemParser() {
        super(new ServerWebExchangeItemParser());
        // 覆盖远程地址提取器
        addRemoteAddressExtractor(this::extractRealIp);
    }

    private String extractRealIp(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        String clientIp = getClientIp(request);
        log.info("Client IP: " + clientIp);
        return clientIp;
    }

    private String getClientIp(ServerHttpRequest request) {
        String ip = request.getHeaders().getFirst("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeaders().getFirst("X-Real-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddress() != null ? 
                 request.getRemoteAddress().getAddress().getHostAddress() : null;
        }
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }
}*/
