package com.alibaba.csp.sentinel.dashboard.test;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;

import javax.servlet.http.HttpServletRequest;

/**
 * 此类型用来处理限流自定义逻辑
 */
public class CustomerBlockHandler {
    public static String handlerException1(HttpServletRequest request, BlockException exception){
        if (exception instanceof DegradeException){
            return "服务已被降级或熔断";
        }else if(exception instanceof FlowException){
            return "服务已被限流";

        }else { //可以继续根据blockException的类型,配置对应的返回对象
            return "handlerException1：系统异常，请稍后重试！";
        }
    }
    public static String handlerException2(HttpServletRequest request,BlockException exception){
        return  "handlerException2：网络崩溃了，请稍后重试！";
    }

    // 处理限流时的逻辑
    public static String handleBlock(HttpServletRequest request, BlockException ex) {
        return   "Request blocked! Client IP: " + getClientIp(request);
    }

    // 获取客户端 IP 方法
    private static String getClientIp(HttpServletRequest request) {
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
}
