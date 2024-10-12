package com.alibaba.csp.sentinel.dashboard.test;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class IPBasedRateLimiterController {

    // 获取客户端 IP 方法
    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    // 核心业务逻辑，被 Sentinel 保护
    @GetMapping("/test")
    //@SentinelResource(value = "ipLimitResource", blockHandler = "handleBlock")
    @SentinelResource(value = "ipLimitResource",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "handleBlock")
    public String testMethod(HttpServletRequest request) {
        String clientIp = getClientIp(request);  // 获取客户端 IP
        String resourceName = "ipLimitResource";  // Sentinel 资源名

        Entry entry = null;
        try {
            // 尝试进入 Sentinel 保护的资源，基于 IP 作为热点参数限流
            entry = SphU.entry(resourceName, EntryType.IN, 1, clientIp);

            // 业务逻辑
            return "Success! Client IP: " + clientIp;
        } catch (BlockException ex) {
            // 处理限流情况
            return "Request blocked! Client IP: " + clientIp;
        } finally {
            // 释放资源
            if (entry != null) {
                entry.exit(1, clientIp);  // exit 参数和 entry 中一致
            }
        }
    }

    // 处理限流时的逻辑
/*    public String handleBlock(HttpServletRequest request, BlockException ex) {
        return "Request blocked! Client IP: " + getClientIp(request);
    }*/
}
