package pres.yao.yaogame.client.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pres.yao.client.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}