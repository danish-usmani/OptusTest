package com.test.optus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.test.optus.databinding.ActivityMainBinding
import com.test.optus.viewModels.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = makeApiCall()
        setupBinding(viewModel)
    }

    fun setupBinding(viewModel: MainActivityViewModel) {

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.viewModel = viewModel

        activityMainBinding.recyclerView.apply {

            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration  = DividerItemDecoration(this@MainActivity, VERTICAL)
            addItemDecoration(decoration)
        }

    }

    fun makeApiCall(): MainActivityViewModel {
       val viewModel =  ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, {
            activityMainBinding.progressbar.visibility = GONE
            if(it != null) {
                viewModel.setAdapterData(it)
            } else {
                Toast.makeText(this@MainActivity, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeAPICall()

        return viewModel
    }

    fun onLayoutClick(view: View){
        val intent = Intent(this, AlbumActivity::class.java).apply {
            putExtra("id", view.getTag().toString())
        }
        startActivity(intent)
    }
}