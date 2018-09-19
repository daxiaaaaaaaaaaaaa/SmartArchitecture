package com.architecturedemo.ssl;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/**
 * 自定义了HostnameVerifier。在握手期间，如果 URL 的主机名和服务器的标识主机名不匹配，
 * 则验证机制可以回调此接口的实现程序来确定是否应该允许此连接。如果回调内实现不恰当，
 * 默认接受所有域名，则有安全风险。代码示例。
 */
    public class SafeHostnameVerifier implements HostnameVerifier {
        String ip ="";
    @Override
    public boolean verify(String hostname, SSLSession session) {
        if (ip.equals(hostname)) {//校验hostname是否正确，如果正确则建立连接
            return true;
        }
        return true;
    }
}