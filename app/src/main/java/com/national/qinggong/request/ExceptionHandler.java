package com.national.qinggong.request;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;



public class ExceptionHandler {

    private static final String SOCKET_TIMEOUT_EXCEPTION = "服务器响应超时，稍后重试";
    private static final String CONNECT_EXCEPTION = "网络连接异常，稍后重试";
    private static final String UNKNOWN_HOST_EXCEPTION = "网络错误，稍后重试";
    private static final String JSON_PARSE_EXCEPTION = "数据解析出错";
    private static final String HTTP_EXCEPTION = "服务器异常,请稍后重试";
    private static final String UNKNOWN_EXCEPTION = "未知异常";
    private static final String SSL_HANDSHAKE_EXCEPTIPN = "证书验证失败";

    public static String handleException(Throwable t){
        String error;
        if (t instanceof SocketTimeoutException) {
            error = SOCKET_TIMEOUT_EXCEPTION;
        } else if (t instanceof ConnectException) {
            error = CONNECT_EXCEPTION;
        } else if (t instanceof UnknownHostException) {
            error = UNKNOWN_HOST_EXCEPTION;
        } else if(t instanceof JsonParseException
                ||  t instanceof JSONException){
            error = JSON_PARSE_EXCEPTION;
        }else if(t instanceof HttpException){
            error = HTTP_EXCEPTION;
        }else if(t instanceof javax.net.ssl.SSLHandshakeException) {
            error = SSL_HANDSHAKE_EXCEPTIPN;
        }else {
            error = UNKNOWN_EXCEPTION;
        }
        return error;
    }


}
