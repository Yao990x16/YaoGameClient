package pres.yao.yaogame.client.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import pres.yao.client.databinding.ActivityRegisterBinding
import pres.yao.yaogame.client.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRegisterBinding
    private lateinit var mViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        mViewModel = UserViewModel()

        viewBinding.register.setOnClickListener {
            val username = viewBinding.username.text.toString()
            val password = viewBinding.password.text.toString()
            val email = viewBinding.userEmail.text.toString()
            if (username!="" && password !="" && email!=""){
                mViewModel.register(username, password, email)
                    .subscribe{
                        when (it.msg) {
                            "ok" -> {
                                Toast.makeText(this,"注册成功$it",Toast.LENGTH_LONG).show()
                                Log.e("Register",it.toString())
                                val intent  = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            "用户名已存在" -> {
                                Toast.makeText(this,"用户名已存在",Toast.LENGTH_LONG).show()
                            }
                            else -> {
                                Toast.makeText(this,"注册功能出现问题",Toast.LENGTH_LONG).show()
                            }
                        }
                    }
            }else{
                Toast.makeText(this,"输入框不能为空",Toast.LENGTH_LONG).show()
            }
        }
    }
}