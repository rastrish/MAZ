package com.zerone.maz.ui.main

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.zerone.maz.R
import com.zerone.maz.ui.base.BaseActivity
import com.zerone.maz.ui.detailed.DetailActivity
import com.zerone.maz.util.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : BaseActivity<MainViewModel>(
    MainViewModel::class
) {

    companion object{
        const val INTENT_DETAILED_IMAGE =  "detailedImage"
        const val INTENT_DETAILED_TEXT = "detailedText"
        const val INTENT_DETAILED_TITLE = "detailedTitle"
    }

    // using Koin
    private val photoAdapter: MovieAdapter by inject()

    private lateinit var detailActivity: Intent

    override fun provideLayoutId(): Int =
        R.layout.activity_main

    override fun setupView(savedInstanceState: Bundle?) {

        detailActivity = Intent(this, DetailActivity::class.java)
        viewModel.getPhotos()
       recyclerView.layoutManager =  GridLayoutManager(this,3)
        recyclerView.adapter = photoAdapter


       photoAdapter.apply {
           itemClick = {
               detailActivity.putExtra(INTENT_DETAILED_TEXT,it.overview)
               detailActivity.putExtra(INTENT_DETAILED_IMAGE,it.url)
               detailActivity.putExtra(INTENT_DETAILED_TITLE,it.title)
               startActivity(detailActivity)
           }
       }

        photoAdapter.apply {
            showDialouge = {
               val dialog = AlertDialog.Builder(this@MainActivity).create()
               dialog.setMessage(it)
                dialog.show()
            }
        }

    }


    override fun setupObservers() {
        super.setupObservers()
        viewModel.photos.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    recyclerView.visibility = GONE
                    progressBar.visibility = VISIBLE
                }
                Status.ERROR -> {
                    progressBar.visibility = GONE
                }
                Status.SUCCESS -> {
                    recyclerView.visibility = VISIBLE
                    progressBar.visibility = GONE
                }
            }
        })

        viewModel.list.observe(this, Observer {
            photoAdapter.setAdapterList(it)
            photoAdapter.notifyDataSetChanged()
        })

    }




}