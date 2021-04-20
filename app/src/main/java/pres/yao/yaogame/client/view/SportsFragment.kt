package pres.yao.yaogame.client.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pres.yao.client.R
import pres.yao.client.databinding.SportsFragmentBinding
import pres.yao.yaogame.client.viewmodel.SportsViewModel

class SportsFragment : Fragment() {

    companion object {
        fun newInstance() = SportsFragment()
    }

    private lateinit var viewModel: SportsViewModel
    private var viewBinding: SportsFragmentBinding? = null
    private val binding get() = viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = SportsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SportsViewModel::class.java)
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