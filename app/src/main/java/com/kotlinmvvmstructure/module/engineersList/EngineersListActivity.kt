package com.kotlinmvvmstructure.module.engineersList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kotlinmvvmstructure.base.BaseActivity
import com.kotlinmvvmstructure.module.engineersList.viewModel.EngineersListViewModel
import com.kotlinmvvmstructure.R
import com.kotlinmvvmstructure.databinding.ActivityEngineersListBinding
import com.kotlinmvvmstructure.module.schedule.scheduleIntent
import kotlinx.android.synthetic.main.toolbar.view.*
import java.util.*

class EngineersListActivity : BaseActivity() {
    lateinit var viewModel: EngineersListViewModel
    lateinit var activityBinding: ActivityEngineersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_engineers_list)
        activityBinding.toolbar.toolbar_title.text = resources.getString(R.string.engineers)
            .toUpperCase(Locale.ROOT)
        viewModel = ViewModelProvider(this).get(EngineersListViewModel::class.java)
        activityBinding.viewModel = viewModel

        if (savedInstanceState == null) {}

        setSupportActionBar(activityBinding.toolbar as Toolbar)
        (activityBinding.toolbar as Toolbar).setNavigationOnClickListener { onBackPressed() }
        hideBackButton()

        activityBinding.rvNotification.adapter = EngineersListAdapter(this)

        setupObserver()

        setupButtonClickListener()

        viewModel.fetchEngineersList()
    }

    private fun setupButtonClickListener(){
        activityBinding.btnGenerateSchedule.setOnClickListener {
            startActivity(scheduleIntent(viewModel.getEngineers()))
        }
    }

    private fun setupObserver(){
        viewModel.statusMessage.observe(this, Observer<String> {
            if(it != "")
                showAlertDialog(it, null)
        })
    }
}

fun Context.engineersListIntent(): Intent {
    return Intent(this, EngineersListActivity::class.java)
}