package pres.yao.yaogame.client.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pres.yao.yaogame.client.model.data.User
import pres.yao.yaogame.client.model.remote.ApiService

class LoginViewModel(private val remote: ApiService.UserService) {
    var loginMsg: String = ""
    var userEmail: String = ""
    @SuppressLint("CheckResult")
    fun login(username: String, password: String) {
        //Log.e("username:", "$username $password")
        remote.toLogin(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.msg=="error"){
                    loginMsg="error"
                    Log.e("login","登录失败")
                }else{
                    it.username=username
                    it.password=password
                    loginMsg=it.msg
                    userEmail=it.email
                    Log.e("loginMSg", loginMsg)
                    Log.e("useremail", userEmail)
                    Log.e("login","登陆成功")
                }
            },{
                Log.e("login", "发生错误")
            })
    }
}