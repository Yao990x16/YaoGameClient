package pres.yao.yaogame.client.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import pres.yao.client.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}