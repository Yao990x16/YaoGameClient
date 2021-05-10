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
import pres.yao.client.databinding.HomeFragmentBinding
import pres.yao.yaogame.client.adapter.CompetitionDescAdapter
import pres.yao.yaogame.client.model.data.Competition
import pres.yao.yaogame.client.viewmodel.CompetitionViewModel
import pres.yao.yaogame.client.viewmodel.SubscribeViewModel

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }


    private var viewBinding: HomeFragmentBinding? = null
    private val binding get() = viewBinding!!
    private lateinit var compViewModel: CompetitionViewModel
    private lateinit var subviewModel: SubscribeViewModel
    private lateinit var userSubsRcv: RecyclerView
    private var adapter: CompetitionDescAdapter? = null
    private val competitionSchedulers = ArrayList<Competition>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        compViewModel = CompetitionViewModel()
        subviewModel = SubscribeViewModel()
        userSubsRcv = binding.rcvHomeFrg
    }

    override fun onResume() {
        super.onResume()
        competitionSchedulers.clear()
        getScheduler()
        LoginActivity.userName?.let { Log.e("userNameonResume", it) }
    }

    private fun getScheduler(){
        userSubsRcv.layoutManager = LinearLayoutManager(context)
        LoginActivity.userName?.let {
            subviewModel.getUserSubsList(it)
                .subscribe {
                    if(competitionSchedulers.isEmpty())
                        for(value in it){
                            competitionSchedulers.add(value)
                            Log.e("esports",value.toString())
                        }
                    adapter = CompetitionDescAdapter(competitionSchedulers)
                    userSubsRcv.adapter = adapter
                    //adapter!!.notifyDataSetChanged()
                    adapter!!.setOnItemClickListener(MyItemClickListener)
                }
        }
    }

    private val MyItemClickListener: CompetitionDescAdapter.OnItemClickListener =
        object : CompetitionDescAdapter.OnItemClickListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onItemClick(v: View?, viewName: CompetitionDescAdapter.ViewName?, position: Int) {
                //viewName
                // 可区分item及item内部控件
                val competitionId = competitionSchedulers[position].competition_id
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
                                            adapter?.notifyDataSetChanged()
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

    override fun onDestroyView() {
        super.onDestroyView()
        /**
         * Fragment的存在时间比其视图长,所以要在onDestroyView方法中消除对绑定类实例的所有引用
         */
        viewBinding = null
    }
}