package com.kotlinmvvmstructure.module.schedule

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.kotlinmvvmstructure.base.BaseActivity
import com.kotlinmvvmstructure.customViews.GridSpacingItemDecoration
import com.kotlinmvvmstructure.ApplicationConstants
import com.kotlinmvvmstructure.R
import com.kotlinmvvmstructure.databinding.ActivityScheduleBinding
import com.kotlinmvvmstructure.module.engineersList.model.EngineerModel
import com.kotlinmvvmstructure.module.schedule.viewModel.ScheduleViewModel
import kotlinx.android.synthetic.main.toolbar.view.*
import java.util.*
import kotlin.collections.ArrayList

class ScheduleActivity : BaseActivity() {
    lateinit var viewModel: ScheduleViewModel
    lateinit var activityBinding: ActivityScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_schedule)
        activityBinding.toolbar.toolbar_title.text =
            resources.getString(R.string.schedule).toUpperCase(Locale.ROOT)
        viewModel = ViewModelProviders.of(this).get(ScheduleViewModel::class.java)
        activityBinding.viewModel = viewModel

        setSupportActionBar(activityBinding.toolbar as Toolbar)
        (activityBinding.toolbar as Toolbar).setNavigationOnClickListener { onBackPressed() }

        if (savedInstanceState == null) {
            viewModel.setEngineersData(
                intent.getSerializableExtra(ApplicationConstants.ENGINEERS_LIST_MODEL) as ArrayList<EngineerModel>
            )

            viewModel.generateSchedule()
        }

        activityBinding.rvSchedules.adapter = ScheduleRvAdapter(this)
        activityBinding.rvSchedules.layoutManager = GridLayoutManager(this, 3)
        activityBinding.rvSchedules.addItemDecoration(GridSpacingItemDecoration(3,10,true,0))

        setupObserver()
    }

    private fun setupObserver(){
        viewModel.statusMessage.observe(this, Observer {
            if(it != "")
                showAlertDialog(it, null)
        })
    }
}

fun Context.scheduleIntent(engineersListModel: ArrayList<EngineerModel>): Intent {
    return Intent(this, ScheduleActivity::class.java).apply {
        putExtra(ApplicationConstants.ENGINEERS_LIST_MODEL, engineersListModel)
    }
}