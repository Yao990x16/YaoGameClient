package pres.yao.yaogame.client.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pres.yao.client.databinding.RegisterFragmentBinding
import pres.yao.yaogame.client.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel
    private var viewBinding: RegisterFragmentBinding? = null
    private val binding get() = viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = RegisterFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * Fragment的存在时间比其视图长,所以要在onDestroyView方法中消除对绑定类实例的所有引用
         */
        viewBinding = null
    }
}