package pres.yao.yaogame.client.model.remote

import io.reactivex.Observable
import pres.yao.yaogame.client.model.data.User
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    companion object{
        const val BASE_URL = "http://127.0.0.1:8181/"
    }
    interface UserService{
        /**
         * 注册
         */
        @FormUrlEncoded
        @POST("user/register")
        fun toRegister(
            @Field("username")username: String,
            @Field("password")password: String,
            @Field("email")email: String
        ): Observable<User>

        /**
         * 登录
         */
        @FormUrlEncoded
        @POST("user/login")
        fun toLogin(
            @Field("username")username: String,
            @Field("password")password: String
        ): Observable<User>
    }
}