package com.pickth.postpay.view.main

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.pickth.postpay.R
import com.pickth.postpay.utils.GridSpacingItemDecoration
import com.pickth.postpay.utils.LinearSpacingItemDecoration
import com.pickth.postpay.view.delivery.DeliveryActivity
import com.pickth.postpay.view.dialog.SavingSettingDialog
import com.pickth.postpay.view.main.adapter.MenuAdapter
import com.pickth.postpay.view.main.adapter.NavAdapter
import com.pickth.postpay.view.receiving.ReceivingActivity
import com.pickth.postpay.view.saving.SavingActivity
import com.pickth.postpay.view.donation.DonationActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_toolbar.*
import kotlinx.android.synthetic.main.view_bottom_sheet.*
import kotlinx.android.synthetic.main.view_navigation.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter: MainContract.Presenter
    private lateinit var mSavingSettingDialog: SavingSettingDialog

    private val images = ArrayList<String>().apply {
        add("https://image.epost.go.kr/epostshop/banner/201705/16/20170516034932555.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/05/20180405070208946.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/06/20180406115020186.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/05/20180405065315580.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/05/20180405065903608.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/05/20180405065219327.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/06/20180406040205205.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/05/20180405065949604.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/05/20180405070337980.jpg")
        add("https://image.epost.go.kr/epostshop/banner/201804/05/20180405070059241.jpg")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // actionbar
        setSupportActionBar(toolbar_base)

        val mDrawerToggle = ActionBarDrawerToggle(this, dl_main, tb_main, R.string.nav_open, R.string.nav_close)
        dl_main.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

        mPresenter = MainPresenter(this)

        initBottomSheet()

        ArrayList<String>().apply {
            while (size < 4) {
                val url = images[(Math.random()*images.size).toInt()]
                if(!contains(url)) {
                    add(url)
                }
            }
            ims_main.addItems(this)
        }

        mSavingSettingDialog = SavingSettingDialog(this, mPresenter.getSavingPercentage(), { mPresenter.setSavingPercentage(it) })

        rv_main_menu.run {
            adapter = MenuAdapter({
                when (it) {
                    2 -> startActivity<DonationActivity>()
                    4 -> startActivity<ReceivingActivity>()
                    5 -> startActivity<DeliveryActivity>()
                }
            })
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(GridSpacingItemDecoration(context, 3, 1, false))
        }

        rv_main_nav.run {
            adapter = NavAdapter({
                when (it) {
                    2 -> startActivity<DonationActivity>()
                    4 -> startActivity<ReceivingActivity>()
                    5 -> startActivity<DeliveryActivity>()
                }
            })
            layoutManager = LinearLayoutManager(context)

            addItemDecoration(LinearSpacingItemDecoration(context, 1, false))
        }

        cv_start_saving.setOnClickListener {
            startActivity<SavingActivity>()
        }
        iv_saving_setting.setOnClickListener {
            showSavingSettingDialog()
        }
        ll_nav.setOnClickListener { }
    }

    private fun initBottomSheet() {
        val behavior = BottomSheetBehavior.from(bottom_sheet)

        iv_fab.setOnClickListener { _ ->
            if (behavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else {
                behavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        behavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                }
            }
        })
    }

    override fun showSavingSettingDialog() {
        mSavingSettingDialog.show()
    }

    override fun getContext(): Context = applicationContext

    override fun showToast(input: String) {
        toast(input)
    }

    override fun updateUi() {
        tv_main_saving_percentage.text = "${mPresenter.getSavingPercentage()}%"
        tv_main_saving_money.text = "${mPresenter.getSavingMoney()}원"
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                dl_main.openDrawer(GravityCompat.START)
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {

        if (dl_main.isDrawerOpen(GravityCompat.START)) {
            dl_main.closeDrawer(GravityCompat.START)
            return
        }

        super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        updateUi()
    }

}
