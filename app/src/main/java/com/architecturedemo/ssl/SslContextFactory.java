package com.architecturedemo.ssl;

import android.content.Context;

import com.architecturedemo.Constant;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * HTTPs证书管理器
 */
public class SslContextFactory {

    /**
     * 获取bks文件的sslsocketfactory
     *
     * @param context
     * @return
     */
    public static SSLSocketFactory getSSLSocketFactory(Context context) {

        SSLContext sslContext = null;
        try {
            //取得SSL的SSLContext实例
            sslContext = SSLContext.getInstance(Constant.FINALVALUE.CLIENT_AGREEMENT);
            //取得TrustManagerFactory的X509密钥管理器实例
            TrustManagerFactory trustManager = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            //取得BKS密库实例
            KeyStore tks = KeyStore.getInstance(Constant.FINALVALUE.CLIENT_TRUST_KEYSTORE);
            InputStream is = context.getResources().openRawResource(Constant.FINALVALUE.CERTIFICATE);
            try {
                tks.load(is, Constant.FINALVALUE.CLIENT_TRUST_PASSWORD.toCharArray());
            } finally {
                is.close();
            }
            //初始化密钥管理器
            trustManager.init(tks);
            //初始化SSLContext
            sslContext.init(null, trustManager.getTrustManagers(), null);
        } catch (Exception e) {
        }
        return sslContext.getSocketFactory();
    }
}