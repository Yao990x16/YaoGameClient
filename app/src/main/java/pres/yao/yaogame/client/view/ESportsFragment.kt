package pres.yao.yaogame.client.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pres.yao.client.R
import pres.yao.client.databinding.ESportsFragmentBinding
import pres.yao.yaogame.client.adapter.CompetitionDescAdapter
import pres.yao.yaogame.client.adapter.MyRecyclerViewAdapter
import pres.yao.yaogame.client.model.data.Competition
import pres.yao.yaogame.client.viewmodel.ESportsViewModel

class ESportsFragment : Fragment() {

    companion object {
        fun newInstance() = ESportsFragment()
    }

    private lateinit var viewModel: ESportsViewModel
    private var viewBinding: ESportsFragmentBinding? = null
    private val binding get() = viewBinding!!
    private lateinit var esportsRcv: RecyclerView
    private var adapter: CompetitionDescAdapter? = null
    private val esportsScheduler = ArrayList<Competition>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = ESportsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ESportsViewModel::class.java)
        // TODO: Use the ViewModel
        esportsRcv = binding.rcvEsportsFrg
        esportsRcv.layoutManager = LinearLayoutManager(context)
        esportsScheduler.add(Competition(1,"电子竞技","春季赛","","RNG",3,"","EDG",2,"2021-04-30 20:00:00",""))
        esportsScheduler.add(Competition(2,"电子竞技","春季赛","","RNG",3,"","EDG",2,"2021-04-30 20:00:00",""))
        esportsScheduler.add(Competition(3,"电子竞技","春季赛","","RNG",3,"","EDG",2,"2021-04-30 20:00:00",""))
        esportsScheduler.add(Competition(4,"电子竞技","NBA","","RNG",3,"","EDG",2,"2021-04-30 20:00:00",""))

        adapter = CompetitionDescAdapter(esportsScheduler)
        esportsRcv.adapter = adapter
        adapter!!.setOnItemClickListener(MyItemClickListener)
    }

    /**
     * item＋item里的控件点击监听事件
     */
    private val MyItemClickListener: CompetitionDescAdapter.OnItemClickListener =
        object : CompetitionDescAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, viewName: CompetitionDescAdapter.ViewName?, position: Int) {
                //viewName
                // 可区分item及item内部控件
                when (v!!.id) {
                    R.id.button_subscribe -> Toast.makeText(
                        context,
                        "你点击了订阅按钮" + (position + 1),
                        Toast.LENGTH_SHORT
                    ).show()
                    R.id.button_show_predict -> Toast.makeText(
                        context,
                        "你点击了预测信息按钮" + (position + 1),
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> Toast.makeText(context, "你点击了item按钮" + (position + 1), Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onItemLongClick(v: View?) {}
        }

    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * Fragment的存在时间比其视图长,所以要在onDestroyView方法中消除对绑定类实例的所有引用
         */
        viewBinding = null
    }
}