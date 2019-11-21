package com.koa.tvmaze.ui

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.koa.tvmaze.R
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_image.*


class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val uri: Uri =
            Uri.parse("https://raw.githubusercontent.com/facebook/fresco/master/docs/static/logo.png")
        //row_iv_header.setImageURI("http://static.tvmaze.com/uploads/images/medium_portrait/213/533527.jpg")
        //row_iv_header.setImageURI("https://raw.githubusercontent.com/facebook/fresco/master/docs/static/logo.png")
        //  row_iv_header.setImageURI(uri)

        Glide.with(this)  //2
            .load("http://static.tvmaze.com/uploads/images/medium_portrait/213/533527.jpg") //3
            .centerCrop() //4
            .into(row_iv_header) //8
    }
}
