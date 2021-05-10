package pres.yao.yaogame.client.viewmodel

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pres.yao.yaogame.client.model.data.Competition
import pres.yao.yaogame.client.model.remote.ApiService
import pres.yao.yaogame.client.model.remote.RetrofitClient

class CompetitionViewModel{
    private val remote = RetrofitClient.getService(ApiService.CompetitionService::class.java)

    fun getESportsScheduleByDateAndType(startTime: String, gameType: String): Observable<List<Competition>> {
        return remote.getScheduleByDateAndType(startTime, gameType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
    fun getSportsScheduleByDateAndType(startTime: String, gameType: String): Observable<List<Competition>> {
        return remote.getScheduleByDateAndType(startTime, gameType)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}