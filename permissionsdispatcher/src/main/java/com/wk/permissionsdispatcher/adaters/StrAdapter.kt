package com.wk.permissionsdispatcher.adaters

import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by WangKai on 2017/12/15.
 */
class StrAdapter(data: ArrayList<String>): BaseQuickAdapter<String, BaseViewHolder>(android.R.layout.simple_list_item_1, data) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        var tv =   helper?.itemView as TextView
        tv.text = item

    }
}