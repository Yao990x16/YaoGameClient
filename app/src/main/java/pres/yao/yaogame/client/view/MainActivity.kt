package pres.yao.yaogame.client.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import pres.yao.client.R
import pres.yao.client.databinding.ActivityMainBinding
import pres.yao.yaogame.client.adapter.FragmentAdapter
import pres.yao.yaogame.client.model.data.User

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var fragmentAdapter: FragmentAdapter
    private var fragments: MutableList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //导航栏
        val navigation = viewBinding.navigation
        val viewPager = viewBinding.viewPager

        fragments.add(HomeFragment())
        fragments.add(ESportsFragment())
        fragments.add(SportsFragment())
        fragments.add(MineFragment())

        fragmentAdapter = FragmentAdapter(fragments, supportFragmentManager)
        viewPager.adapter = fragmentAdapter
        val fragmentFlag = intent.getIntExtra("fragment_flag",1)
        val userInfo = intent.getParcelableExtra<User>("User")
        val minefragement= fragments[3]
        val bundle = Bundle()
        if (fragmentFlag==3){
            viewPager.currentItem=3
            bundle.putParcelable("User",userInfo)
            minefragement.arguments=bundle
            Log.e("bundle",bundle.toString())
        }
        navigation.itemIconTintList = null
        navigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home -> viewPager.currentItem = 0
                R.id.esports -> viewPager.currentItem = 1
                R.id.sports -> viewPager.currentItem = 2
                R.id.mine -> viewPager.currentItem = 3
            }
            false
        }
        // ViewPager 滑动事件监听
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(i: Int, v: Float, i1: Int) {}
            override fun onPageSelected(i: Int) {
                //将滑动到的页面对应的 menu 设置为选中状态
                navigation.menu.getItem(i).isChecked = true
            }
            override fun onPageScrollStateChanged(i: Int) {}
        })
    }
}
