package pres.yao.yaogame.client.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import okhttp3.internal.notify
import pres.yao.client.databinding.ActivityLoginBinding
import pres.yao.yaogame.client.model.remote.ApiService
import pres.yao.yaogame.client.model.remote.RetrofitClient
import pres.yao.yaogame.client.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityLoginBinding
    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        //val view = viewBinding.root
        setContentView(viewBinding.root)
        val url = RetrofitClient.BASE_URL
        val remote = RetrofitClient.getService(url, ApiService.UserService::class.java)
        mViewModel = LoginViewModel(remote)
        viewBinding.login.setOnClickListener{
            Log.e("mdajldv",mViewModel.loginMsg)
            if (viewBinding.username.text.toString() == "" || viewBinding.password.text.toString() == ""){
                Toast.makeText(this,"用户名或不能为空",Toast.LENGTH_LONG).show()
            }else{
                mViewModel.login(viewBinding.username.text.toString(),viewBinding.password.text.toString())
                Log.e("ActivityLogin",mViewModel.loginMsg)
                if(mViewModel.loginMsg=="error"){
                    Toast.makeText(this,"登录失败,请检查用户名或密码",Toast.LENGTH_LONG).show()
                }else if (mViewModel.loginMsg=="ok"){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        viewBinding.register.setOnClickListener {
            Log.e("msg", "2222")
            val intent  = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}