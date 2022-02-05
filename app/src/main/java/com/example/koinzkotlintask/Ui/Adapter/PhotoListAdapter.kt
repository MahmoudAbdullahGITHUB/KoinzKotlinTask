package com.example.koinzkotlintask.Ui.Adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.koinzkotlintask.R
import com.example.koinzkotlintask.Ui.Activity.InfoActivity.InfoActivity
import com.example.koinzkotlintask.databinding.PhotoItemBinding
import com.example.koinzkotlintask.model.Photo
import com.squareup.picasso.Picasso


class PhotoListAdapter(val context: Context, val photosList: List<Photo>) :
    RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        var inflater: LayoutInflater =
            LayoutInflater.from(parent.context)

//                .inflate(R.layout.photo_item, parent, false)
        var binding: PhotoItemBinding
        binding = DataBindingUtil.inflate(inflater, R.layout.photo_item, parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {

        holder.itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent(context, InfoActivity::class.java)
                intent.putExtra(
                    "image_link",
                    "https://farm${photosList[holder.absoluteAdapterPosition].farm}." +
                            "static.flickr.com/${photosList[holder.absoluteAdapterPosition].server}/" +
                            "${photosList[holder.absoluteAdapterPosition].id}_" +
                            "${photosList[holder.absoluteAdapterPosition].secret}.jpg"
                )
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
        })

        Log.d("TAG", "onBindViewHolder: " + photosList[position].title)

        Picasso
            .get()
            .load(
                "https://farm${photosList[position].farm}." +
                        "static.flickr.com/${photosList[position].server}/" +
                        "${photosList[position].id}_${photosList[position].secret}.jpg"
            )
            .into(holder.binding.photoID)


    }

    override fun getItemCount(): Int {
        return photosList.size
    }


    inner class PhotoViewHolder(binding: PhotoItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var binding: PhotoItemBinding


//        var photoImage: ImageView

        init {
            this.binding = binding

//            photoImage = itemView.findViewById(R.id.image_id)


//            itemView.setOnClickListener(context)
        }

//        override fun onClick(p0: View?) {
//            val position = absoluteAdapterPosition
//            if (position != RecyclerView.NO_POSITION) {
//                context.onItemClick(position)
//            }
//        }

    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }


}