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
import pres.yao.client.databinding.SportsFragmentBinding
import pres.yao.yaogame.client.adapter.CompetitionDescAdapter
import pres.yao.yaogame.client.model.data.Competition
import pres.yao.yaogame.client.utils.GetStartTime
import pres.yao.yaogame.client.viewmodel.CompetitionViewModel
import pres.yao.yaogame.client.viewmodel.SubscribeViewModel

class SportsFragment : Fragment() {

    companion object {
        fun newInstance() = SportsFragment()
    }

    private var viewBinding: SportsFragmentBinding? = null
    private val binding get() = viewBinding!!
    private lateinit var viewModel: CompetitionViewModel
    private lateinit var subviewModel: SubscribeViewModel
    private lateinit var esportsRcv: RecyclerView
    private var adapter: CompetitionDescAdapter? = null
    private val sportsScheduler = ArrayList<Competition>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = SportsFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        subviewModel = SubscribeViewModel()
        viewModel = CompetitionViewModel()
        esportsRcv = binding.rcvSportsFrg
    }
    override fun onResume() {
        super.onResume()
        sportsScheduler.clear()
        getScheduler()
    }
    /**
     * 请求数据
     */
    @SuppressLint("CheckResult", "NotifyDataSetChanged")
    fun getScheduler(){
        esportsRcv.layoutManager = LinearLayoutManager(context)
        viewModel.getSportsScheduleByDateAndType(GetStartTime.getTodayDate(),"传统体育")
            .subscribe{
                if(sportsScheduler.isEmpty())
                    for(value in it){
                        sportsScheduler.add(value)
                        Log.e("sports:",value.toString())
                        Log.e("SFrag",sportsScheduler.toString())
                    }
                adapter = CompetitionDescAdapter(sportsScheduler)
                Log.e("adapter",adapter.toString())
                esportsRcv.adapter = adapter
                adapter!!.notifyDataSetChanged()
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
                val competitionId = sportsScheduler[position].competition_id
                val startTime = sportsScheduler[position].start_time?.substring(0,10)
                val leftTeam = sportsScheduler[position].left_name
                Log.e("Forecast",startTime+leftTeam)
                val buttonSubscribe = v?.findViewById<Button>(R.id.button_subscribe)
                when (v!!.id) {
                    R.id.button_subscribe ->
                        LoginActivity.userName?.let { it ->
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

                    R.id.button_show_predict ->
                        subviewModel.getForecastInfoBySTimeAndTeam(startTime.toString(),leftTeam.toString())
                            .subscribe {
                                Toast.makeText(
                                    context,
                                    it.winTeam+"赢的概率:"+it.probability,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
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