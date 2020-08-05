package com.example.techmassignment

import DataListViewAdapter
import DataViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androiduitestcaseapp.EspressoIdlingResource
import com.example.androiduitestcaseapp.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.progress_layout.*
import kotlinx.android.synthetic.main.toolbar.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

/**
 * This is HomeActivity which shows data in listview
 */
class HomeActivity : AppCompatActivity() {
    private var titleOfScreen: String = "About Canada"
    private lateinit var dataListViewAdapter: DataListViewAdapter
    private lateinit var viewModel: DataViewModel
    val rowsList = ArrayList<DataOfImagesBean.Row>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
        initializeViewModel()
        observeData()
        setLoaderObserver()
        getData()
    }

    private fun initView() {
        dataListViewAdapter = DataListViewAdapter(rowsList, this)
        data_listview.adapter = dataListViewAdapter
    }

    /**
     * This method is used to set observer on the loader.
     */
    private fun setLoaderObserver() {
        viewModel.loadingState.observe(this, Observer {
            showProgress(it!!)
        })
    }

    private fun observeData() {
        viewModel.liveData.observe(this, Observer {
            if (it != null) {
                it.rows?.let { it1 ->
                    rowsList.addAll(it1)
                }
                dataListViewAdapter.notifyDataSetChanged()
                setTitleOfScreen(titleOfScreen)
                EspressoIdlingResource.decrement()
            }
        })
    }

    private fun setTitleOfScreen(title: String) {
        toolbar_title_tv.setText(title)
    }

    private fun getData() {
        EspressoIdlingResource.increment()
        val job = GlobalScope.launch(IO) {
            delay(1000L)
        }
        job.invokeOnCompletion {
            GlobalScope.launch(Dispatchers.Main) {
                EspressoIdlingResource.decrement()
                viewModel.getDataFromAsset()
            }
        }
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(this).get(DataViewModel::class.java)
    }

    /**
     * This method is responsible to show progress bar when background task is performed
     * @param show This is the boolean param which shows flag to show progress bar or not
     */
    fun showProgress(show: Boolean) {
        if (a_progress_bar != null) {
            a_progress_bar?.visibility = if (show) View.VISIBLE else View.GONE
        }
    }

}