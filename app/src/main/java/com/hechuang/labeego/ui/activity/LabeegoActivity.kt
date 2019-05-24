package com.hechuang.labeego.ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.Button
import android.widget.CompoundButton
import android.widget.Toast
import com.hechuang.labeego.R
import com.hechuang.labeego.R.id.*
import com.hechuang.labeego.adapter.LabeegoViewPager
import com.hechuang.labeego.api.ApiFactify
import com.hechuang.labeego.base.BaseActivity
import com.hechuang.labeego.data.UserData
import com.hechuang.labeego.persenter.activity.LabeegoPersenter
import com.hechuang.labeego.tools.Util.AppManager
import com.hechuang.labeego.tools.Util.Utils
import com.hechuang.labeego.tools.ui.Eyes
import com.hechuang.labeego.ui.fragment.AllianceShopFragment
import com.hechuang.labeego.ui.fragment.GoodsHomeFragment
import com.hechuang.labeego.ui.fragment.MineFragment
import com.hechuang.labeego.view.activity.ILabeegoVeiw
import kotlinx.android.synthetic.main.activity_labeego.*

/**
 * 首页
 */
class LabeegoActivity : BaseActivity<LabeegoPersenter>(), ILabeegoVeiw, ViewPager.OnPageChangeListener, CompoundButton.OnCheckedChangeListener {

    @JvmField
    var labeego: LabeegoActivity? = null

    fun getthis(): LabeegoActivity {
        return this@LabeegoActivity
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            when (buttonView!!.id) {
                R.id.labeego_home -> {
                    var intent = Intent(this@LabeegoActivity, WebsActivity::class.java)
                    intent.putExtra("web", ApiFactify.WEB_HOST)
                    startActivity(intent)
                    finish()
                }
                R.id.labeego_goods -> {

                    setTabSelection(1)
//                    labeego_viewpager.currentItem = 0
                }
                R.id.labeego_shop -> {
//                    labeego_viewpager.currentItem = 1
                    setTabSelection(2)
                }
                R.id.labeego_mine -> {
                    if (UserData.USERISLOGIN) {
                        setTabSelection(3)
//                        labeego_viewpager.currentItem = 2
                    } else {
                        startActivity(Intent(this@LabeegoActivity, LoginActivity::class.java))
                        finish()
//                        labeego_viewpager.currentItem = 0
                    }
                }
            }


        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                labeego_goods.isChecked = true
            }
            1 -> labeego_shop.isChecked = true

            2 -> {
                labeego_mine.isChecked = true
            }
        }
    }

    override fun initlayout(): Int {
        return R.layout.activity_labeego
    }

    private var weizhi = 0
    var broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            weizhi = intent.extras!!.getInt("weizhi")
            when (weizhi) {
                0 -> labeego_home.isChecked = true
                1 -> {
                    labeego_goods.isChecked = true
//                    setTabSelection(1)
                }
                3 -> {
                    labeego_shop.isChecked = true
//                    setTabSelection(3)
                }
                2 -> {
                    labeego_mine.isChecked = true
//                    setTabSelection(2)
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        var filler = IntentFilter()
        filler.addAction(WebsActivity.WEBSACTION)
        filler.addAction(JLWebActivity.JLWEBSACTION)
        filler.addAction(LoginActivity.LOGINACTION)
        filler.addAction(MainActivity.MAINACTION)
        filler.addAction(ConfirmAnOrderAcitivty.CONFIRMANORDER)
        registerReceiver(broadcastReceiver, filler)
    }

    var chushiweihzi = 1
    override fun initView() {
        Eyes.setStatusBarLightMode(this, ContextCompat.getColor(this, R.color.white))
        this.labeego = this@LabeegoActivity
//        var goodshomefragment = GoodsHomeFragment()
//        var minefragment = MineFragment()
//        var shopFragment = AllianceShopFragment()
//        var fragments = arrayListOf<Fragment>()
//        fragments.add(goodshomefragment)
//        fragments.add(shopFragment)
//        fragments.add(minefragment)
//        var adapter = LabeegoViewPager(supportFragmentManager, fragments)
//        labeego_viewpager.adapter = adapter
//        labeego_viewpager.addOnPageChangeListener(this)
        labeego_home.setOnCheckedChangeListener(this)
        labeego_goods.setOnCheckedChangeListener(this)
        labeego_shop.setOnCheckedChangeListener(this)
        labeego_mine.setOnCheckedChangeListener(this)
        setr_img()
        chushiweihzi = intent.getIntExtra("weizhi", 1)
        when (chushiweihzi) {
            0 -> {
            }
            1 -> {
                labeego_goods.isChecked = true
                setTabSelection(1)
            }
            2 -> {
                labeego_mine.isChecked = true
                setTabSelection(3)
            }
            3 -> {
                labeego_shop.isChecked = true
                setTabSelection(2)
            }
        }
        var filler = IntentFilter()
        filler.addAction(WebsActivity.WEBSACTION)
        filler.addAction(JLWebActivity.JLWEBSACTION)
        filler.addAction(LoginActivity.LOGINACTION)
        filler.addAction(MainActivity.MAINACTION)
        filler.addAction(ConfirmAnOrderAcitivty.CONFIRMANORDER)
        registerReceiver(broadcastReceiver, filler)

    }

    var goodshomefragment: GoodsHomeFragment? = null
    var minefragment: MineFragment? = null
    var shopFragment: AllianceShopFragment? = null
    private fun setTabSelection(i: Int) {
        var fragmentmanger = supportFragmentManager
        var tramsaction = fragmentmanger.beginTransaction()
        fragmenthider(tramsaction)
        when (i) {
            0 -> {
            }
            1 -> {
                if (goodshomefragment == null) {
                    goodshomefragment = GoodsHomeFragment()
                    tramsaction.add(R.id.labeego_viewpager, goodshomefragment)
                } else {
                    tramsaction.show(goodshomefragment)
                }
            }
            3 -> {
                if (minefragment == null) {
                    minefragment = MineFragment()
                    tramsaction.add(R.id.labeego_viewpager, minefragment)
                } else {
                    tramsaction.show(minefragment)
                }
            }
            2 -> {
                if (shopFragment == null) {
                    shopFragment = AllianceShopFragment()
                    tramsaction.add(R.id.labeego_viewpager, shopFragment)
                } else {
                    tramsaction.show(shopFragment)
                }
            }
        }
        tramsaction.commitAllowingStateLoss()
    }

    private fun fragmenthider(tramsaction: FragmentTransaction?) {
        if (goodshomefragment != null) {
            tramsaction!!.hide(goodshomefragment)
        }
        if (minefragment != null) {
            tramsaction!!.hide(minefragment)
        }
        if (shopFragment != null) {
            tramsaction!!.hide(shopFragment)
        }
    }

    private fun setr_img() {
        val top = 20
        val right = 20

        val scale = this.resources.displayMetrics.density.toInt()
        var lafeng = resources.getDrawable(R.drawable.goods_lafeng)
        lafeng.setBounds(0, 0, (top * scale + 0.5f).toInt(), (right * scale + 0.5f).toInt())
        labeego_home.setCompoundDrawables(null, lafeng, null, null)

        var huayuan = resources.getDrawable(R.drawable.goods_huayuan)
        huayuan.setBounds(0, 0, (top * scale + 0.5f).toInt(), (right * scale + 0.5f).toInt())
        labeego_goods.setCompoundDrawables(null, huayuan, null, null)

        var shop = resources.getDrawable(R.drawable.goods_shop)
        shop.setBounds(0, 0, (top * scale + 0.5f).toInt(), (right * scale + 0.5f).toInt())
        labeego_shop.setCompoundDrawables(null, shop, null, null)

        var mine = resources.getDrawable(R.drawable.goods_mine)
        mine.setBounds(0, 0, (top * scale + 0.5f).toInt(), (right * scale + 0.5f).toInt())
        labeego_mine.setCompoundDrawables(null, mine, null, null)
    }

    override fun initPersenter() {
        mPersenter = LabeegoPersenter(this, this)
    }

    override fun showloading() {
        mLoading!!.show()
    }

    override fun dismissloading() {
        mLoading!!.dismiss()
    }

    override fun onResume() {
        super.onResume()

        var filler = IntentFilter()
        filler.addAction(WebsActivity.WEBSACTION)
        filler.addAction(JLWebActivity.JLWEBSACTION)
        filler.addAction(ConfirmAnOrderAcitivty.CONFIRMANORDER)
        registerReceiver(broadcastReceiver, filler)
    }

    private var exitTime: Long = 0
    var adia: AlertDialog? = null
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis() - exitTime > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(applicationContext, "再按一次退出程序", Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                val view = LayoutInflater.from(this).inflate(R.layout.view_out, null)
                val sure = view.findViewById<Button>(R.id.out_title_queding)
                sure.setOnClickListener {
                    AppManager.getAppManager().AppExit(this@LabeegoActivity)
                    adia!!.dismiss()
                }
                val quxiao = view.findViewById<Button>(R.id.out_title_quxiao)
                quxiao.setOnClickListener { adia!!.dismiss() }
                adia = AlertDialog.Builder(this)
                        .setView(view).show()
                Utils.setdialotwidth(this, adia!!)
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }
}