package com.wk.permissionsdispatcher

import android.app.Application
import com.blankj.utilcode.util.Utils

/**
 * Created by WangKai on 2017/12/18.
 */
class PermissionApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
    }
}