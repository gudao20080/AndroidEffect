package com.wk.androideffect.adapters;

import android.content.Intent
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.wk.androideffect.R
import com.wk.androideffect.SystemUiVisibilityActivity

/**
 * Created by WangKai on 2017/12/7.
 */
class UiVisibilityAdapter : BaseQuickAdapter<Pair<String, Int>, BaseViewHolder> {
    constructor(data: List<Pair<String, Int>>) : super(R.layout.text_item) {
        setNewData(data)
    }


    override fun convert(helper: BaseViewHolder?, item: Pair<String, Int>?) {
        helper?.setText(R.id.tv_desc, item?.first)
        helper?.getView<View>(R.id.tv_desc)?.setOnClickListener{
            val intent = Intent(mContext, SystemUiVisibilityActivity::class.java)
            intent.putExtra("pair", item)
            mContext.startActivity(intent)
        }
    }


}