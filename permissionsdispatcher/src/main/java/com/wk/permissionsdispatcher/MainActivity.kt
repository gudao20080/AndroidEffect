package com.wk.permissionsdispatcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import com.wk.permissionsdispatcher.beans.GlideApp
import kotterknife.bindView
import permissions.dispatcher.RuntimePermissions

class MainActivity : AppCompatActivity() {
    private val mBtnLoad: Button by bindView(R.id.btn_load)
    private val mIv: ImageView by bindView(R.id.iv)

    private val imagePath = "http://d.hiphotos.baidu.com/image/h%3D300/sign=f892fb65f81fbe09035ec5145b600c30/00e93901213fb80ef8b5a5c43cd12f2eb93894b9.jpg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBtnLoad.setOnClickListener{
//            Glide.with(this).load(imagePath).into(iv)
//            Glide.with(this).load(imagePath).apply(RequestOptions.circleCropTransform()).into(iv)
            GlideApp.with(this).load(imagePath).placeholder(R.mipmap.ic_launcher_round).circleCrop().into(mIv)
        }


    }


}
