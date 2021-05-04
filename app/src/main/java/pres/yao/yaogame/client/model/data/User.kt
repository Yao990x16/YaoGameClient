package pres.yao.yaogame.client.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
    用户实体类
 */
@Parcelize
data class User(var username: String?, var password: String?, var email: String?, var msg: String?) :
    Parcelable {
}