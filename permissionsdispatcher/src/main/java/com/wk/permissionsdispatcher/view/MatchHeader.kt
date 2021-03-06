package com.wk.permissionsdispatcher.view

import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.PointF
import android.support.constraint.ConstraintLayout
import android.support.design.widget.AppBarLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.blankj.utilcode.util.ConvertUtils
import com.wk.permissionsdispatcher.R
import kotlin.math.abs

/**
 * 数据中心 header
 */
class MatchHeader(context: Context, attrs: AttributeSet) : AppBarLayout(context, attrs) {
    private lateinit var mWholeView: ConstraintLayout
    private lateinit var mIvBack: ImageView
    private lateinit var mTvTitle: TextView
    private lateinit var mIvFavorite: ImageView
    private lateinit var mIvShare: ImageView
    private lateinit var mTvMatchInfo: TextView
    private lateinit var mTvGuessType: TextView
    private lateinit var mTvScrollStatus: TextView
    private lateinit var mTvHostScore: TextView
    private lateinit var mScoreDivider: View
    private lateinit var mTvGuestScore: TextView
    private lateinit var mTvMatchCurrentTime: TextView
    private lateinit var mIvHost: ImageView
    private lateinit var mTvHostName: TextView
    private lateinit var mTvHostRank: TextView
    private lateinit var mIvGuest: ImageView
    private lateinit var mTvGuestName: TextView
    private lateinit var mTvGuestRank: TextView



    override fun onFinishInflate() {
        super.onFinishInflate()
        initViews()
        initData()
    }


    private fun initViews() {

        mWholeView = findViewById(R.id.wholeView)
        mIvBack = findViewById(R.id.iv_back)
        mTvTitle = findViewById(R.id.tv_title)
        mIvFavorite = findViewById(R.id.iv_favorite)
        mIvShare = findViewById(R.id.iv_share)
        mTvMatchInfo = findViewById(R.id.tv_match_info)
        mTvGuessType = findViewById(R.id.tv_guess_type)
        mTvScrollStatus = findViewById(R.id.tv_scroll_status)
        mTvHostScore = findViewById(R.id.tv_host_score)
        mScoreDivider = findViewById(R.id.score_divider)
        mTvGuestScore = findViewById(R.id.tv_guest_score)
        mTvMatchCurrentTime = findViewById(R.id.tv_match_current_time)
        mIvHost = findViewById(R.id.iv_host)
        mTvHostName = findViewById(R.id.tv_host_name)
        mTvHostRank = findViewById(R.id.tv_host_rank)
        mIvGuest = findViewById(R.id.iv_guest)
        mTvGuestName = findViewById(R.id.tv_guest_name)
        mTvGuestRank = findViewById(R.id.tv_guest_rank)

    }

    private val mOriginXY = mutableMapOf<View, PointF>()
    private val mPaths = mutableMapOf<View, Path>()
    private val mFadeViews = mutableListOf<View>()
    private fun initData() {

//        mFadeViews.add(mTvHostName)
//        mFadeViews.add(mTvGuestName)
//        mFadeViews.add(mTvHostRank)
//        mFadeViews.add(mTvGuestRank)

        post {

            (0..(mWholeView.childCount - 1)).forEach {
                val childView = mWholeView.getChildAt(it)
                mOriginXY[childView] = PointF(childView.x, childView.y)
                Log.d("origin", "${childView.javaClass.simpleName}: ${childView.x}, ${childView.y}")
            }


            //主队图标
            val resultW = ConvertUtils.dp2px(32f)


            generateMovePath(mIvBack)
            generateMovePath(mIvFavorite)
            generateMovePath(mIvShare)
            generateMovePath(mIvHost)
            generateMovePath(mIvGuest)
            generateMovePath(mTvHostScore)
            generateMovePath(mTvGuestScore)
            generateMovePath(mTvMatchCurrentTime)


            val distance = mWholeView.height - mWholeView.minHeight //可偏移的最大距离
            var rate: Float
            addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                Log.d("way", "offset: $verticalOffset, x: mWholeViewH${mWholeView.height}, minHeight: ${mWholeView.minHeight}")

                rate = verticalOffset * 1f / distance * 1f
                Log.d("way", "rate: $rate, $verticalOffset, $distance")


                doMove(mIvBack, rate)
                doMove(mIvFavorite, rate)
                doMove(mIvShare, rate)
                doMove(mTvMatchCurrentTime, rate)
                doTextMoveAndScale(mTvHostScore, rate)
                doTextMoveAndScale(mTvGuestScore, rate)
                doMoveAndScale(mIvHost, resultW, rate)
                doMoveAndScale(mIvGuest, resultW, rate)

                (0..(mWholeView.childCount - 1)).forEach{
                    val childAt = mWholeView.getChildAt(it)
                    if (!mPaths.containsKey(childAt)) {
                        doFade(childAt, rate)
                    }
                }
            }
        }
    }


    private val dp10InPx = ConvertUtils.dp2px(10f)

    /**
     * 生成各view移动的路径
     */
    private fun generateMovePath(view: View) {
        val path = Path()
        path.moveTo(view.x, view.y)     //起点

        when (view.id) {
            R.id.iv_back,
            R.id.iv_share,
            R.id.iv_favorite,
            R.id.tv_match_current_time -> {
                path.lineTo(view.x, mWholeView.height.toFloat() - mWholeView.minHeight / 2 - view.height / 2)
            }

            R.id.iv_host -> {
                val resultW = ConvertUtils.dp2px(32f)

                //根据 mTvHostScore mIvHost之间的间距为10dp, 得到mIvHost坐标
                val resultX = mWholeView.width / 2 - mTvMatchCurrentTime.width / 2 - dp10InPx - mTvHostScore.width - view.width / 2 - resultW / 2 - dp10InPx

                path.lineTo(resultX.toFloat(), mWholeView.height.toFloat() - mWholeView.minHeight / 2 - view.height / 2)
            }

            R.id.iv_guest -> {
                val resultW = ConvertUtils.dp2px(32f)
                //根据 mTvGuestScore mIvGuest之间的间距为10dp, mIvGuest之间的间距为10dp
                val resultX = mWholeView.width / 2 + mTvMatchCurrentTime.width / 2 + dp10InPx + mTvGuestScore.width - (view.width - resultW) / 2 + dp10InPx

                path.lineTo(resultX.toFloat(), mWholeView.height.toFloat() - mWholeView.minHeight / 2 - view.height / 2)
            }

            R.id.tv_host_score -> {
                val resultX = mWholeView.width / 2 - mTvMatchCurrentTime.width / 2 - dp10InPx - mTvHostScore.width
                path.lineTo(resultX.toFloat(), mWholeView.height.toFloat() - mWholeView.minHeight / 2 - view.height / 2)
            }

            R.id.tv_guest_score -> {
                val resultX = mWholeView.width / 2 + mTvMatchCurrentTime.width / 2 + dp10InPx
                path.lineTo(resultX.toFloat(), mWholeView.height.toFloat() - mWholeView.minHeight / 2 - view.height / 2)
            }
        }

        if (!path.isEmpty) {
            mPaths[view] = path
        }
    }



    private fun doMoveAndScale(view: View, resultW: Int, rate: Float) {
        doTeamIconScale(view, resultW, rate)
        doMove(view, rate)
    }

    /**
     * 主客队图标在移动路线上的缩放
     */
    private fun doTeamIconScale(view: View, resultW: Int, rate: Float) {
        val deltaW = view.width - resultW
        val scaleDelta = deltaW * 1.0f / view.width * abs(rate)

        view.scaleX = 1 - scaleDelta
        view.scaleY = 1 - scaleDelta
    }

    /**
     * 比分的文字缩放，按设计图35dp --> 22dp
     */
    private fun doTextMoveAndScale(view: TextView, rate: Float) {
        doMove(view, rate)

        val deltaW = 35 - 22
        val scaleDelta = deltaW * 1.0f / 35 * abs(rate)

        view.scaleX = 1 - scaleDelta
        view.scaleY = 1 - scaleDelta
    }

    /**
     * 淡入淡出
     */
    private fun doFade(view: View, rate: Float) {

        Log.d("way", "fade: $rate")
        view.alpha = 1 + rate
    }

    /**
     * 移动
     */
    private fun doMove(view: View, rate: Float) {
        val pointF = getHostIconPathPoints(view, rate)
        view.x = pointF.x
        view.y = pointF.y
    }

    /**
     * 获取移动路线上的各点
     */
    private fun getHostIconPathPoints(view: View, rate: Float): PointF {

        val pathMeasure = PathMeasure(mPaths[view], false)
        val pathPoints = floatArrayOf(0f, 1f)
        val tans = floatArrayOf(1f, 1f)

        pathMeasure.getPosTan(pathMeasure.length * abs(rate), pathPoints, tans)

        return PointF(pathPoints[0], pathPoints[1])
    }

    inner class PointEvaluator : TypeEvaluator<PointF> {
        private val mPoint: PointF? = null
        override fun evaluate(fraction: Float, startValue: PointF?, endValue: PointF?): PointF {
            val x = startValue!!.x + fraction * (endValue!!.x - startValue!!.x)
            val y = startValue!!.y + fraction * (endValue!!.y - startValue!!.y)

            if (mPoint != null) {
                mPoint.set(x, y)
                return mPoint
            } else {
                return PointF(x, y)
            }
        }
    }

}
