package pres.yao.yaogame.client.model.remote

import io.reactivex.Observable
import pres.yao.yaogame.client.model.data.Competition
import pres.yao.yaogame.client.model.data.NBAForecast
import pres.yao.yaogame.client.model.data.User
import pres.yao.yaogame.client.model.data.UserSubs
import retrofit2.http.*

interface ApiService {

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

    interface Subscribe{
        /**
         * 用户订阅比赛
         */
        @FormUrlEncoded
        @POST("subscription/subsComp")
        fun toUserSubscribe(
            @Field("userName")userName: String,
            @Field("competitionId")competitionId: String
        ): Observable<UserSubs>

        /**
         * 用户取消比赛订阅
         */
        @FormUrlEncoded
        @POST("subscription/deleteByCompId")
        fun toUserCancelSubscribe(
            @Field("userName")userName: String,
            @Field("competitionId")competitionId: String
        ): Observable<UserSubs>

        /**
         *
         */
        @GET("subscription/getByUserNameAndCompId")
        fun getByUserNameAndCompId(
            @Query("userName")userName: String,
            @Query("competitionId")competitionId: String
        ): Observable<UserSubs>

        /**
         * 用户查看订阅列表
         */
        @GET("subscription/getCompsByUserName")
        fun getUserSubsList(
            @Query("userName")userName: String
        ): Observable<List<Competition>>

        /**
         * 根据开始时间(yy-mm-dd)和比赛队伍获取对应的预测信息
         */
        @GET("NBAForecast/getBySTimeAndTeam")
        fun getForecastInfoBySTimeAndTeam(
            @Query("startTime")startTime: String,
            @Query("teamName")teamName: String,
        ): Observable<NBAForecast>
    }

    interface CompetitionService{
        /**
         * 根据日期(yy-mm-dd)和赛事类型获取比赛的赛程
         */
        @GET("competition/getByCompTypeAndSTimeLike")
        fun getScheduleByDateAndType(
            @Query("sTime")startTime: String,
            @Query("compType")gameType: String
        ): Observable<List<Competition>>
    }
}