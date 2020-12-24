package com.national.qinggong.request;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.national.qinggong.BuildConfig;
import com.national.qinggong.MyApplication;
import com.national.qinggong.factory.CustomGsonConverterFactory;
import com.national.qinggong.util.NetworkUtils;
import com.national.qinggong.util.StringUtils;


import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**

 */

public class RetrofitClient {

    private Retrofit retrofit;
    private ApiService apiService;
    private OkHttpClient okHttpClient;
    private OkHttpClient.Builder builder;
    private final int CONNECT_TIME_OUT = 15;//链接超时
    private final int READ_TIME_OUT =  20;//读取数据超时
    private final int WRITE_TIME_OUT =  20;//写入数据超时

    //private static SparseArray<RetrofitClient> sRetrofitManager = new SparseArray<>(API.API_NHOST_NUMBER);

    private static ConcurrentHashMap<String,RetrofitClient> sRetrofitManager = new ConcurrentHashMap<>();

    /*************************缓存设置*********************/
   /*
   1. noCache 不使用缓存，全部走网络
   2. noStore 不使用缓存，也不存储缓存
    3. onlyIfCached 只使用缓存
    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合
    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言
    6. minFresh 设置有效时间，依旧如上
    7. FORCE_NETWORK 只走网络
    8. FORCE_CACHE 只走缓存
    */

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    /*
    //私有构造方法
    private RetrofitClient(int hostType){
        //缓存
        File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "cache");
        if(!cacheFile.exists()){
            cacheFile.mkdirs();
        }
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        Interceptor headerInterceptor =new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request().newBuilder()
                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                       // .addHeader("Content-Type", "application/json; charset=utf-8")
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(build);
            }
        };

        builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.addInterceptor(headerInterceptor);
        builder.addInterceptor(mRewriteCacheControlInterceptor);
        builder.addNetworkInterceptor(mRewriteCacheControlInterceptor);
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIME_OUT,TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);//错误重连

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        okHttpClient = builder.build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(API.getHost(hostType))
                .build();
        apiService = retrofit.create(ApiService.class);
    }
    */

    //私有构造方法
    private RetrofitClient(String hostName){
        //缓存
        File cacheFile = new File(MyApplication.getInstance().getCacheDir(), "cache");
        if(!cacheFile.exists()){
            cacheFile.mkdirs();
        }
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        Interceptor headerInterceptor;
        if (!StringUtils.isEmpty(struuid)){
             headerInterceptor =new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request build = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("uuid",struuid)
                            .build();
                    return chain.proceed(build);
                }
            };
        }else {
            headerInterceptor =new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request build = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .build();
                    return chain.proceed(build);
                }
            };
        }





        builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.addInterceptor(headerInterceptor);
        builder.addInterceptor(mRewriteCacheControlInterceptor);
        builder.addNetworkInterceptor(mRewriteCacheControlInterceptor);
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(WRITE_TIME_OUT,TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);//错误重连
        //start
        builder.sslSocketFactory(createSSLSocketFactory());
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });
        //end

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);
        }

        okHttpClient = builder.build();

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(CustomGsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(hostName)
                .build();
        apiService = retrofit.create(ApiService.class);
    }



    public static ApiService getApiService(String hostName) {
        RetrofitClient retrofitClient = sRetrofitManager.get(hostName);
//        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient(hostName);
            sRetrofitManager.put(hostName, retrofitClient);
//        }
        return retrofitClient.apiService;
    }


    static String struuid;

    public static ApiService getApiService(String hostName, String uuid) {
        struuid = uuid;
        ApiService apiserver = getApiService(hostName);
        return apiserver;
    }







    /*
    public static ApiService getApiService(int hostType) {
        RetrofitClient retrofitClient = sRetrofitManager.get(hostType);
        if (retrofitClient == null) {
            retrofitClient = new RetrofitClient(hostType);
            sRetrofitManager.put(hostType, retrofitClient);
        }
        return retrofitClient.apiService;
    }
    */

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private static final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            if (!NetworkUtils.isNetConnected(MyApplication.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl)? CacheControl.FORCE_NETWORK: CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);

            if (originalResponse.isSuccessful()){

            }


            if (NetworkUtils.isNetConnected(MyApplication.getInstance())) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)

                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + RetrofitClient.CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    /**
     * 根据网络状况获取缓存的策略
     */
    @NonNull
    public static String getCacheControl() {
        return NetworkUtils.isNetConnected(MyApplication.getInstance()) ? CACHE_CONTROL_AGE : CACHE_CONTROL_CACHE;
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }
        return ssfFactory;
    }

}


