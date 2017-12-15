package com.wk.permissionsdispatcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ArrayAdapter
import com.wk.permissionsdispatcher.adaters.StrAdapter

class TestActivity : AppCompatActivity() {
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        mRecyclerView = findViewById(R.id.recycler)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val list = ArrayList<String>()
        (0..50).forEach { list.add("$it") }
        val adapter = StrAdapter(list)

        val manager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = manager
        mRecyclerView.adapter = adapter
    }


}
