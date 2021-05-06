package pres.yao.yaogame.client.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pres.yao.client.R
import pres.yao.yaogame.client.model.data.Competition

class CompetitionRootAdapter(private val dateList: List<Competition>)
    : RecyclerView.Adapter<CompetitionRootAdapter.CompetitionViewHolder>() {

    inner class CompetitionViewHolder(view: View,context: Context, dateList: List<Competition>)
        : RecyclerView.ViewHolder(view) {
        val competitionDateTV: TextView = view.findViewById(R.id.competition_date)
        val rcvDesc: RecyclerView = view.findViewById(R.id.rcv_desc)
        val descAdapter: CompetitionDescAdapter = CompetitionDescAdapter(dateList)
        val linearLayoutManager: LinearLayoutManager = LinearLayoutManager(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.competition_rv_row_root_item, parent, false)
        val dateList = dateList
        return CompetitionViewHolder(view, parent.context, dateList)
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        val date = dateList[position]
        holder.rcvDesc.layoutManager = holder.linearLayoutManager
        holder.rcvDesc.adapter = holder.descAdapter
        holder.competitionDateTV.text = date.start_time?.substring(0,10)
    }

    override fun getItemCount(): Int {
        return dateList.size
    }
}