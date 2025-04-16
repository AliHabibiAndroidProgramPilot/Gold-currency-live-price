package com.sample.ali.goldprice.mvp.presenter

import com.sample.ali.goldprice.mvp.ext.ActivityLifecycle
import com.sample.ali.goldprice.mvp.model.ModelMainActivity
import com.sample.ali.goldprice.mvp.view.ViewMainActivity

class PresenterMainActivity(
    private val view: ViewMainActivity,
    private val model: ModelMainActivity
) : ActivityLifecycle {

    override fun presenterOnCreate() {

    }

    override fun presenterOnDestroy() {}

}