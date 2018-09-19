package com.architecturedemo.ssl;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
 * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
 * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
 * 重要的事情说三遍，以下代码不要直接使用
 */
public class SafeTrustManager implements X509TrustManager {
    /**
     * 该方法检查客户端的证书，若不信任该证书则抛出异常。由于我们不需要对客户端进行认证，
     * 因此我们只需要执行默认的信任管理器的这个方法。JSSE中，默认的信任管理器类为TrustManager。
     * @param chain
     * @param authType
     * @throws CertificateException
     */
    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        //do nothing，接受任意客户端证书
    }

    /**
     * 该方法检查服务器的证书，若不信任该证书同样抛出异常。
     * 通过自己实现该方法，可以使之信任我们指定的任何证书。
     * 在实现该方法时，也可以简单的不做任何处理，
     * 即一个空的函数体，由于不会抛出异常，它就会信任任何证书。
     * @param chain
     * @param authType
     * @throws CertificateException
     */
    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        try {
            for (X509Certificate certificate : chain) {
                certificate.checkValidity(); //检查证书是否过期，签名是否通过等
            }
        } catch (Exception e) {
            throw new CertificateException(e);
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }


}