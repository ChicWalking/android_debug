package com.example.yunxi.debug_rzx;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class https_text {

    public static void okhttp_demo(){

        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                OkHttpClient mOkHttpClient = new OkHttpClient.Builder().build();
                final Request request = new Request.Builder()
                        .url("https://www.baidu.com")
                        .build();

                Response response = null;
                try {
                    response = mOkHttpClient.newCall(request).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Log.d("https",response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(runnable).start();
    }

    public static void ssl_text(OkHttpClient client,Context context) {
        /**
         1、验证系统中信任的根证书（默认）
         */

        /**
         2、验证本地证书（certificate pinning），cer 和 pem 格式都可以
         */
        //client = OkHttpClientUtil.getSSLClient(client,this,"12306.cer");

        /**
         3、不验证任何证书
         */
        //client = OkHttpClientUtil.getTrustAllSSLClient(client);

        /**
         4、验证系统中信任的根证书 和 证书域名
         */
//        OkHttpClient.Builder builder = client.newBuilder();
//        builder.hostnameVerifier(new HostnameVerifier() {
//            @Override
//            public boolean verify(String hostname, SSLSession session) {
//                Log.e("verify",hostname);
//                return true;
//            }
//        });

        /**
         5、验证系统中信任的根证书 和 本地证书但忽略过期时间
         */
        client = OkHttpClientUtil.getSSLClientIgnoreExpire(client, context, "toutiao.pem");

        String url2 = "https://kyfw.12306.cn/otn/";
        String url3 = "https://toutiao.io";
        try {
            Request request = new Request.Builder()
                    .url(url3)
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("sssss", e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("sssss", response.body().string());
                }
            });

        } catch (Exception e) {
            Log.e("sssss", e.toString());
        }
    }

    //    public class MySSLSocketFactory {
//
//        private static final String KEY_STORE_TYPE_BKS = "bks";//证书类型
//        private static final String KEY_STORE_TYPE_P12 = "PKCS12";//证书类型
//
//
//        private static final String KEY_STORE_PASSWORD = "****";//证书密码（应该是客户端证书密码）
//        private static final String KEY_STORE_TRUST_PASSWORD = "***";//授信证书密码（应该是服务端证书密码）
//
//        public static SSLSocketFactory getSocketFactory(Context context) {
//
//
//            InputStream trust_input = context.getResources().openRawResource(R.raw.trust);//服务器授信证书
//            InputStream client_input = context.getResources().openRawResource(R.raw.client);//客户端证书
//            try {
//                SSLContext sslContext = SSLContext.getInstance("TLS");
//                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
//                trustStore.load(trust_input, KEY_STORE_TRUST_PASSWORD.toCharArray());
//                KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE_P12);
//                keyStore.load(client_input, KEY_STORE_PASSWORD.toCharArray());
//                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//                trustManagerFactory.init(trustStore);
//
//                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//                keyManagerFactory.init(keyStore, KEY_STORE_PASSWORD.toCharArray());
//                sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
//                SSLSocketFactory factory = sslContext.getSocketFactory();
//                return factory;
//            } catch (Exception e) {
//                e.printStackTrace();
//                return null;
//            } finally {
//                try {
//                    trust_input.close();
//                    client_input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


}
