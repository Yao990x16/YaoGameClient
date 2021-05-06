package pres.yao.yaogame.client.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import pres.yao.client.databinding.ActivityLoginBinding
import pres.yao.yaogame.client.model.data.User
import pres.yao.yaogame.client.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityLoginBinding
    private lateinit var mViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        mViewModel = UserViewModel()
        viewBinding.login.setOnClickListener {
            mViewModel.login(viewBinding.username.text.toString(),viewBinding.password.text.toString())
                .subscribe{
                    if (it.msg == "error"){
                        Log.e("LoginActivityError",it.toString())
                        Toast.makeText(this, "登录失败,请检查用户名或密码$it", Toast.LENGTH_LONG).show()
                    }else if (it.msg == "ok"){
                        Log.e("LoginActivityOk", it.toString())
                        val intent = Intent(this, MainActivity::class.java)
                        val userInfo: User = it
                        intent.putExtra("fragment_flag",3)
                        intent.putExtra("User",userInfo)
                        startActivity(intent)
                        finish()
                    }
                }
        }

        viewBinding.register.setOnClickListener {
            val intent  = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}