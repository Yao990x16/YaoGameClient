package pres.yao.yaogame.client.utils

import android.widget.RelativeLayout
import android.widget.TextView
import android.view.LayoutInflater
import pres.yao.client.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.ImageView

/**
 * 自定义个人中心选项控件
 */
class PersonalItemView(context: Context, attrs: AttributeSet?) : RelativeLayout(context, attrs) {
    private val data: TextView

    // 提供设置控件的描述数据
    fun setData(data: String?) {
        this.data.text = data
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.item_personal_menu, this)
        @SuppressLint("CustomViewStyleable") val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.PersonalItemView)
        val icon = findViewById<ImageView>(R.id.icon)
        val more = findViewById<ImageView>(R.id.more)
        val line = findViewById<ImageView>(R.id.line)
        val name = findViewById<TextView>(R.id.name)
        data = findViewById(R.id.data)
        icon.setImageDrawable(typedArray.getDrawable(R.styleable.PersonalItemView_icon))
        more.setImageDrawable(typedArray.getDrawable(R.styleable.PersonalItemView_more))
        name.text = typedArray.getText(R.styleable.PersonalItemView_name)
        if (typedArray.getBoolean(R.styleable.PersonalItemView_show_more, false)) {
            more.visibility = VISIBLE
        }
        if (typedArray.getBoolean(R.styleable.PersonalItemView_show_line, false)) {
            line.visibility = VISIBLE
        }
        typedArray.recycle()
    }
}