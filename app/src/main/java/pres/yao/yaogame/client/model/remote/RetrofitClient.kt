package pres.yao.yaogame.client.model.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    val okHttpClient = OkHttpClient.Builder()
            //请求超时,限定app在某个指定的时间内得到响应结果,如果未得到则超时
            //callTimeout的计时器横跨整个请求,直到客户端完全读取响应内容
        .callTimeout(30,TimeUnit.SECONDS)
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL+"user/")
            //JSON数据转换
        .addConverterFactory(GsonConverterFactory.create())
            //Rxjava
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    val apiService  = retrofit.create(ApiService::class.java)
}