package pres.yao.yaogame.client.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pres.yao.client.R
import pres.yao.client.databinding.ESportsFragmentBinding
import pres.yao.yaogame.client.adapter.CompetitionDescAdapter
import pres.yao.yaogame.client.model.data.Competition
import pres.yao.yaogame.client.utils.GetStartTime
import pres.yao.yaogame.client.viewmodel.CompetitionViewModel
import pres.yao.yaogame.client.viewmodel.SubscribeViewModel

class ESportsFragment : Fragment() {

    companion object {
        fun newInstance() = ESportsFragment()
    }

    private var viewBinding: ESportsFragmentBinding? = null
    private val binding get() = viewBinding!!
    private lateinit var compViewModel: CompetitionViewModel
    private lateinit var subviewModel: SubscribeViewModel
    private lateinit var esportsRcv: RecyclerView
    private var adapter: CompetitionDescAdapter? = null
    private val esportsScheduler = ArrayList<Competition>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding = ESportsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        compViewModel = CompetitionViewModel()
        subviewModel = SubscribeViewModel()
        esportsRcv = binding.rcvEsportsFrg
        LoginActivity.userName?.let { Log.e("userNameonActivityCreated", it) }
    }

    override fun onResume() {
        super.onResume()
        esportsScheduler.clear()
        getScheduler()
        LoginActivity.userName?.let { Log.e("userNameonResume", it) }
    }

    /**
     * 请求数据
     */
    @SuppressLint("CheckResult", "NotifyDataSetChanged")
    fun getScheduler(){
        esportsRcv.layoutManager = LinearLayoutManager(context)
        compViewModel.getESportsScheduleByDateAndType(GetStartTime.getTodayDate(),"电子竞技")
            .subscribe{ it ->
                if(esportsScheduler.isEmpty())
                for(value in it){
                    esportsScheduler.add(value)
                    Log.e("esports",value.toString())
                }
                adapter = CompetitionDescAdapter(esportsScheduler)
                esportsRcv.adapter = adapter
                //adapter!!.notifyDataSetChanged()
                adapter!!.setOnItemClickListener(MyItemClickListener)
            }
    }
    /**
     * item＋item里的控件点击监听事件
     */
    private val MyItemClickListener: CompetitionDescAdapter.OnItemClickListener =
        object : CompetitionDescAdapter.OnItemClickListener {
            override fun onItemClick(v: View?, viewName: CompetitionDescAdapter.ViewName?, position: Int) {
                //viewName
                // 可区分item及item内部控件
                val competitionId = esportsScheduler[position].competition_id
                val buttonSubscribe = v?.findViewById<Button>(R.id.button_subscribe)
                when (v!!.id) {
                    R.id.button_subscribe ->
                        LoginActivity.userName?.let { it ->
                            if (competitionId != null) {
                                Log.e("compId",competitionId)
                            }
                            if(buttonSubscribe?.text=="订阅"){
                                subviewModel.userSubscribe(it, competitionId.toString())
                                    .subscribe{
                                        Log.e("UserSubs",it.toString())
                                        if (it.msg=="订阅成功"){
                                            buttonSubscribe.text = "取消订阅"
                                            Toast.makeText(
                                                context,
                                                "订阅成功" + (position + 1),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }else{
                                            Toast.makeText(
                                                context,
                                                "订阅失败" + (position + 1),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }
                            if (buttonSubscribe?.text=="取消订阅"){
                                subviewModel.userCancelSubscribe(it,competitionId.toString())
                                    .subscribe {
                                        if (it.msg=="取消成功"){
                                            buttonSubscribe.text = "订阅"
                                        }else{
                                            Toast.makeText(
                                                context,
                                                it.msg + (position + 1),
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            }
                        }
                }
            }
            override fun onItemLongClick(v: View?) {}
        }

    @SuppressLint("NotifyDataSetChanged")
    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * Fragment的存在时间比其视图长,所以要在onDestroyView方法中消除对绑定类实例的所有引用
         */
        viewBinding = null
    }
}