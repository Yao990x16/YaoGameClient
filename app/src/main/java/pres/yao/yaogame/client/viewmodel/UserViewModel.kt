package pres.yao.yaogame.client.viewmodel

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pres.yao.yaogame.client.model.data.User
import pres.yao.yaogame.client.model.remote.ApiService
import pres.yao.yaogame.client.model.remote.RetrofitClient

class UserViewModel {

    private val remote = RetrofitClient.getService(ApiService.UserService::class.java)
    fun login(username: String, password: String): Observable<User> {
        return remote.toLogin(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun register(username: String,password: String,email: String): Observable<User> {
        return remote.toRegister(username, password, email)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}