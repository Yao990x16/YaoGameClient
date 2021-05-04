package pres.yao.yaogame.client.model.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object{

        fun <T> getService(url: String, service: Class<T>): T{
            return createRetrofit(url).create(service)
        }
        fun <T> getService(service: Class<T>): T{
            return createRetrofit(BASE_URL).create(service)
        }
        private fun createRetrofit(url: String): Retrofit{
            val okHttpClient = OkHttpClient.Builder()
                //请求超时,限定app在某个指定的时间内得到响应结果,如果未得到则超时
                //callTimeout的计时器横跨整个请求,直到客户端完全读取响应内容
                .callTimeout(30,TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                    .baseUrl(url)
                    //JSON数据转换
                    .addConverterFactory(GsonConverterFactory.create())
                    //Rxjava
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build()
        }

        private const val BASE_URL = "http://192.168.123.161:8181/"
    }
}