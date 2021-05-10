package pres.yao.yaogame.client.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserSubs(var userName: String?, var compId: String?, var msg: String?) : Parcelable
