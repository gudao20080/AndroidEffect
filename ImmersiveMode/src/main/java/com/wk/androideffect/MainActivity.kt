package com.wk.androideffect

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.wk.androideffect.adapters.UiVisibilityAdapter
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration

class MainActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN  //全屏(状态栏一直隐藏，显示导航栏)
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //隐藏导航栏，点击屏幕后显示
//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION //隐藏导航栏，点击屏幕后显示

        initViews()
        initSystemUiVisibilityRv()
    }

    private fun initViews() {
        mRecyclerView = findViewById(R.id.recyclerView)
    }

    private fun initSystemUiVisibilityRv() {
        val manager = FlexboxLayoutManager(this, FlexDirection.ROW)
        mRecyclerView.layoutManager = manager
        mRecyclerView.addItemDecoration(HorizontalDividerItemDecoration.Builder(this@MainActivity)
                .size(10)
                .color(Color.WHITE)
                .build())

        val flagList = arrayListOf<Pair<String, Int>>()

        flagList.add(Pair("SYSTEM_UI_FLAG_HIDE_NAVIGATION", View.SYSTEM_UI_FLAG_HIDE_NAVIGATION))
        flagList.add(Pair("SYSTEM_UI_FLAG_LOW_PROFILE", View.SYSTEM_UI_FLAG_LOW_PROFILE))
        flagList.add(Pair("SYSTEM_UI_FLAG_FULLSCREEN", View.SYSTEM_UI_FLAG_FULLSCREEN))
        flagList.add(Pair("SYSTEM_UI_FLAG_LAYOUT_STABLE", View.SYSTEM_UI_FLAG_LAYOUT_STABLE))
        flagList.add(Pair("SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION", View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION))
        flagList.add(Pair("SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN", View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN))
        flagList.add(Pair("SYSTEM_UI_FLAG_IMMERSIVE", View.SYSTEM_UI_FLAG_IMMERSIVE))
        flagList.add(Pair("SYSTEM_UI_FLAG_IMMERSIVE_STICKY", View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY))
        //组合
        flagList.add(Pair("SYSTEM_UI_FLAG_HIDE_NAVIGATION|SYSTEM_UI_FLAG_FULLSCREEN", View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        or View.SYSTEM_UI_FLAG_FULLSCREEN))


        val adapter = UiVisibilityAdapter(flagList)
        mRecyclerView.adapter = adapter
    }
}
