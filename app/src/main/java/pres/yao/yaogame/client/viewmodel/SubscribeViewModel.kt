package pres.yao.yaogame.client.viewmodel

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pres.yao.yaogame.client.model.data.Competition
import pres.yao.yaogame.client.model.data.NBAForecast
import pres.yao.yaogame.client.model.data.UserSubs
import pres.yao.yaogame.client.model.remote.ApiService
import pres.yao.yaogame.client.model.remote.RetrofitClient

class SubscribeViewModel {
    private val remote = RetrofitClient.getService(ApiService.Subscribe::class.java)

    fun userSubscribe(userName: String, compId: String): Observable<UserSubs>{
        return remote.toUserSubscribe(userName, compId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun userCancelSubscribe(userName: String, compId: String): Observable<UserSubs>{
        return remote.toUserCancelSubscribe(userName,compId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUserSubsList(userName: String): Observable<List<Competition>>{
        return remote.getUserSubsList(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getForecastInfoBySTimeAndTeam(startTime: String,teamName: String): Observable<NBAForecast>{
        return remote.getForecastInfoBySTimeAndTeam(startTime, teamName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getByUserNameAndCompId(userName: String, compId: String): Observable<UserSubs>{
        return remote.getByUserNameAndCompId(userName, compId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}