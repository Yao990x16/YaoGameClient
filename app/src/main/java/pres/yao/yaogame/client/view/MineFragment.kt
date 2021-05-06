package pres.yao.yaogame.client.view

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pres.yao.client.databinding.MineFragmentBinding
import pres.yao.yaogame.client.model.data.User
import pres.yao.yaogame.client.viewmodel.MineViewModel

class MineFragment : Fragment() {

    companion object {
        fun newInstance() = MineFragment()
    }

    private lateinit var viewModel: MineViewModel

    private var viewBinding: MineFragmentBinding? = null
    private val binding get() = viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = MineFragmentBinding.inflate(layoutInflater, container, false)
        Log.e("fragOncreview",arguments.toString())
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MineViewModel::class.java)
        val bundle = arguments
        var email = bundle?.getParcelable<User>("User")?.email
        var username = bundle?.getParcelable<User>("User")?.username
        binding.mineEmail.setData(email)
        binding.mineUsername.setData(username)
        if (username!=null){
            binding.mineLoginOrOut.text = "注销"
            binding.mineSubscription.setOnClickListener {
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }
        }
        binding.mineLoginOrOut.setOnClickListener {
            if (binding.mineLoginOrOut.text=="登录"){
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
            }else{
                username=null
                email=null
                binding.mineEmail.setData(username)
                binding.mineUsername.setData(email)
                binding.mineLoginOrOut.text="登录"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * Fragment的存在时间比其视图长,所以要在onDestroyView方法中消除对绑定类实例的所有引用
         */
        viewBinding = null
    }
}