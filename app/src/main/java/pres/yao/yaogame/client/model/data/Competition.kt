package pres.yao.yaogame.client.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Competition(var competition_id: String?, var competition_type: String?, var game_stage: String?,
                       var left_icon: String?, var left_name: String?, var left_score: Int?,
                       var right_icon: String?, var right_name: String?, var right_score: Int?,
                       var start_time: String?, var msg: String?): Parcelable
