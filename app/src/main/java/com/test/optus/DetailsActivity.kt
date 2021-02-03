package com.test.optus

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class DetailsActivity :  AppCompatActivity() {
    var title: String = ""
    var url: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        if (extras != null) {
            title = extras.get("title").toString()
            url = extras.get("url").toString()
        }
        setContentView(R.layout.activity_details)
        displayDetails(title, url)
    }

    fun displayDetails(title: String, url: String){
        val desc = findViewById<TextView>(R.id.nameTextView) as TextView
        desc.text = title
        val view = findViewById<View>(R.id.fullImage) as ImageView
        Picasso.get()
            .load(url)
            .into(view)
    }
}