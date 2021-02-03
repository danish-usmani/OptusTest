package com.test.optus.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.optus.R
import com.test.optus.databinding.AlbumviewRowBinding
import com.test.optus.models.AlbumItem

class AlbumViewAdapter : RecyclerView.Adapter<AlbumViewAdapter.MyViewHolder>() {
    var items = ArrayList<AlbumItem>()

    fun setDataList(data: ArrayList<AlbumItem>, iUserId: String) {
        val iId = iUserId.toInt()
        val filteredList: ArrayList<AlbumItem> = data.filter { s -> s.albumId == iId  } as ArrayList<AlbumItem>
        this.items = filteredList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AlbumviewRowBinding.inflate(layoutInflater)
        return MyViewHolder(binding)
    }

    override fun getItemCount() = items.size

    class MyViewHolder(val binding: AlbumviewRowBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: AlbumItem) {
            binding.album = data
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setTag(items[position])
    }

    companion object {
       @JvmStatic
       @BindingAdapter("loadImage")
       fun loadImage(thumbImage: ImageView, url: String) {
           Picasso.get()
               .load(url)
               .placeholder(R.drawable.ic_launcher_foreground)
               .error(R.drawable.ic_launcher_foreground)
               .into(thumbImage)
       }
   }
}