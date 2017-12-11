package com.wk.androideffect

import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.TextView

class SystemUiVisibilityActivity : AppCompatActivity() {
    private lateinit var pair: Pair<String, Int>
    private lateinit var mtvTitle: TextView
    private lateinit var mtvDesc: TextView
    private lateinit var mToolBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
//        window.requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
//        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY)
        setContentView(R.layout.activity_system_ui_visibility)
        pair = intent.getSerializableExtra("pair") as Pair<String, Int>
        window.decorView.systemUiVisibility = pair.second
        inidViews()
        initData()
    }

    private fun inidViews() {
        mtvTitle = findViewById(R.id.tv_title)
        mtvDesc = findViewById(R.id.tv_desc)
        mToolBar = findViewById(R.id.toolBar)

        mToolBar.title = "SYSTEM_UI_FLAG 的使用"
        setSupportActionBar(mToolBar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setBackgroundDrawable(resources.getDrawable(R.drawable.ic_launcher_background))

    }

    private fun initData() {
        mtvTitle?.text = pair.first

        var desc = ""
        when (pair.second) {
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION -> {
                desc = "隐藏导航栏，当交互时，导航栏又会显示。可以用来与 SYSTEM_UI_FLAG_FULLSCREEN， FLAG_LAYOUT_IN_SCREEN" +
                        " 一同使用来全屏显示。  当与SYSTEM_UI_FLAG_FULLSCREEN一起使用时，交互时会重新显示"
            }
            View.SYSTEM_UI_FLAG_LOW_PROFILE -> {
                desc = "状栏和导航栏的图标会变暗淡。主要用于游戏，阅读,视频等沉浸式的app,使用户专注在内容上"
            }
            View.SYSTEM_UI_FLAG_FULLSCREEN -> {
                desc = "非重要的屏幕装饰(比如：状态条)将会被隐藏以专注于View的内容，与WindowManager.LayoutParams.FLAG_FULLSCREEN拥有相同的效果。\n" +
                        "不同于window flag,如果你以Window.FEATURE_ACTION_BAR_OVERLAY模式使用action bar ，则actionBar也会被隐藏"
            }

            View.SYSTEM_UI_FLAG_IMMERSIVE -> {

            }
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY -> {

            }

            View.SYSTEM_UI_FLAG_LAYOUT_STABLE -> {

            }
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION -> {

            }
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN -> {

            }
        }
        mtvDesc.text = desc
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemId = item.itemId
        if (itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)

    }
}
