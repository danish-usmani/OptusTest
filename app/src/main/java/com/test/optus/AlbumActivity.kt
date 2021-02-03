package com.test.optus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.test.optus.databinding.ActivityAlbumBinding
import com.test.optus.models.AlbumItem
import com.test.optus.viewModels.AlbumActivityViewModel

class AlbumActivity : AppCompatActivity() {
    var iUserId: String = ""
    lateinit var activityAlbumBinding : ActivityAlbumBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        iUserId = intent.getStringExtra("id").toString()
        val viewModel = makeApiCall()
        setupBinding(viewModel)
    }

    fun setupBinding(viewModel: AlbumActivityViewModel) {

        activityAlbumBinding = DataBindingUtil.setContentView(this, R.layout.activity_album)
        activityAlbumBinding.viewModel = viewModel

        activityAlbumBinding.recyclerView.apply {

            layoutManager = LinearLayoutManager(this@AlbumActivity)
            val decoration  = DividerItemDecoration(this@AlbumActivity, VERTICAL)
            addItemDecoration(decoration)
        }

    }

    fun makeApiCall(): AlbumActivityViewModel {
        val viewModel =  ViewModelProvider(this).get(AlbumActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, {
            activityAlbumBinding.progressbar.visibility = View.GONE
            if (it != null) {
                viewModel.setAdapterData(it, iUserId)
            } else {
                Toast.makeText(this@AlbumActivity, "Error in fetching data", Toast.LENGTH_LONG)
                    .show()
            }
        })
        viewModel.makeAPICall()

        return viewModel
    }

    fun onLayoutClick(view: View){
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("title", (view.getTag() as AlbumItem).title)
            putExtra("url", (view.getTag() as AlbumItem).url)
        }
       startActivity(intent)
    }
}