package com.architecturedemo;

/**
 * 全局常量
 *
 * @author weishixiong
 * @Time 2018-03-16
 */

public class Constant {
    /**
     * 请求后台的所有API接口都在这里配置;统一管理
     */
    public static class Server {
        public final static int SUCCESS_CODE = 0;
        //服务器超时时间 16 秒
        public final static int TIME_OUT = 16;
        //测试服务器地址
        public static final String ROOT_URL = "https://" + Param.IP + ":" + Param.PORT + "/mobile/";

    }
    /**
     * 全局静态变量
     */
    public static class Param {
        //阿里
        public static String IP = "39.108.60.233";

        public static String PORT = "2061";


    }

    /**
     * sp 常量
     */
    public static class SP {

        public static final String SP = "sp";//sp
        public static final String LOGIN_KEY = "login_key";//登录实体key
        public static final String SESSION_ID = "session_id";//
    }
    /**
     * 全局常量
     */
    public static class FINALVALUE {
        public static  final String CLIENT_TRUST_PASSWORD = "证书的密码";
        public static   final String CLIENT_AGREEMENT = "TLS";//使用协议
        public static  final String CLIENT_TRUST_KEYSTORE = "pkcs12";//bks pkcs12
        //证书
        public static final int CERTIFICATE =0 ;//R.raw.formal_environment (把证书文件放到 raw 目录下)
    }
}
