package pres.yao.yaogame.client.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NBAForecast(var startTime: String?, var winTeam: String?, var loseTeam: String?,
                       var porbability: String?, var msg: String?) : Parcelable
