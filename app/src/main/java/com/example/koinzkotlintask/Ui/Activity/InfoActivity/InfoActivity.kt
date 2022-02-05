package com.example.koinzkotlintask.Ui.Activity.InfoActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.koinzkotlintask.R
import com.example.koinzkotlintask.databinding.ActivityInfoBinding
import com.squareup.picasso.Picasso

class InfoActivity : AppCompatActivity() {
    lateinit var imageLink: String
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageLink = intent.getStringExtra("image_link")!!

        Picasso
            .get()
            .load(imageLink)
            .into(binding.ImageInfo)


    }
}