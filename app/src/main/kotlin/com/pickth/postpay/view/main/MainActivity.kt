package com.pickth.postpay.view.main

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pickth.postpay.R
import com.pickth.postpay.view.delivery.DeliveryActivity
import com.pickth.postpay.view.dialog.SavingSettingDialog
import com.pickth.postpay.view.saving.SavingActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.base_toolbar.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var mPresenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // actionbar
        setSupportActionBar(toolbar_base)

        mPresenter = MainPresenter(this)

        btn_start_delivery.setOnClickListener {
            startActivity<DeliveryActivity>()
        }
        fl_start_saving.setOnClickListener {
            startActivity<SavingActivity>()
        }
        iv_saving_setting.setOnClickListener {
            showSavingSettingDialog()
        }
    }

    override fun showSavingSettingDialog() {
        SavingSettingDialog(this, { mPresenter.setSavingPercentage(it) }).show()
    }

    override fun getContext(): Context = applicationContext

    override fun showToast(input: String) {
        toast(input)
    }
}