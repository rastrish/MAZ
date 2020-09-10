package com.zerone.maz.ui.detailed

import android.os.Bundle
import com.bumptech.glide.Glide
import com.zerone.maz.R
import com.zerone.maz.ui.base.BaseActivity
import com.zerone.maz.ui.main.MainActivity
import kotlinx.android.synthetic.main.details_activty.*

class DetailActivity : BaseActivity<DetailViewModel>(
    DetailViewModel::class) {

    var url = String()
    var title = String()
    var overview = String()

    override fun provideLayoutId(): Int = R.layout.details_activty


    override fun setupView(savedInstanceState: Bundle?) {
        val bundle: Bundle = intent.extras!!
        overview = bundle.get(MainActivity.INTENT_DETAILED_TEXT).toString()
        title = bundle.get(MainActivity.INTENT_DETAILED_TITLE).toString()
        url = bundle.get(MainActivity.INTENT_DETAILED_IMAGE).toString()

        Glide.with(this).load(url).into(detailsImageView)
        detailTextView.text = title
        detailedOverview.text = overview
    }


}