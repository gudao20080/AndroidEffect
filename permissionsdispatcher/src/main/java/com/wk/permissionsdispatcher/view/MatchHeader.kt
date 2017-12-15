package com.wk.permissionsdispatcher.view

import android.content.Context
import android.graphics.Path
import android.graphics.PathMeasure
import android.graphics.Point
import android.graphics.PointF
import android.support.annotation.FloatRange
import android.support.constraint.ConstraintLayout
import android.support.design.widget.AppBarLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wk.permissionsdispatcher.R

/**
 * Created by lifan on 2016/5/18.
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


    init {
//        LayoutInflater.from(context).inflate(R.layout.fb_match_detail_header, this, true)
//        LayoutInflater.from(context).inflate(R.layout.fb_data_header_tab, this, true)

//        initViews()

    }

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

    private fun initData() {

            mWholeView.post{

                (0..(mWholeView.childCount - 1)).forEach {
                    val childView = mWholeView.getChildAt(it)
                    mOriginXY[childView] = PointF(childView.x, childView.y)
                    Log.d("origin", "${childView.javaClass.simpleName}: ${childView.x}, ${childView.y}")
                }


                val path = Path()
                path.lineTo(mIvHost.x, mIvHost.y)
                path.lineTo(mIvBack.x + 100, mIvBack.y)

                val pathMeasure = PathMeasure(path, false)
                pathMeasure.getPosTan()



                val distance = mWholeView.height - mWholeView.minHeight //可偏移的最大距离
                var rate: Float
                addOnOffsetChangedListener { appBarLayout, verticalOffset ->
                    Log.d("way", "appBarH: ${appBarLayout.height}, offset: $verticalOffset, x: ${x}, minHeight: ${mWholeView.minHeight}")

                    rate = verticalOffset*1f / distance * 1f
                    Log.d("way", "rate: $rate, $verticalOffset, $distance")

                    mIvBack.y = mOriginXY[mIvBack]!!.y - verticalOffset
                    mIvShare.y = mOriginXY[mIvShare]!!.y - verticalOffset

                    doScale(mIvHost, 1f, 0.3f, rate)

            }
        }
    }

    private fun doScale(view: View, from: Float, to: Float, rate: Float) {
        val s = from + (from - to) * rate
        view.scaleX = s
        view.scaleY = s

    }



    //    /**
    //     * @param var  能否滑出的最大高度, appbarLayout与标题栏的距离
    //     * @param var1 Math.abs(verticalOffset) appbarLayout垂直方向上偏移的绝对值
    //     * @param var2 从完全展开到完全收缩的比率 范围为：1 --> 0
    //     */
    //    protected void onHeaderOffsetChanged(int var, int var1, float var2) {
    //
    //
    //        initXY();
    //
    //        back_img.setY((float) var1);
    //        title.setY((float) var1);
    //
    //        float iconS = yLen[0] - (var1 * perDpi * 7f) / (float) var;
    //        hostIcon.setY(iconS + (float) (var1 / 1.1));
    //        guestIcon.setY(iconS + (float) (var1 / 1.1));
    //
    //        float scoreS = yLen[1] - (var1 * perDpi * 7.7f) / (float) var;
    //        hostScore.setY(scoreS + (float) (var1 / 1.15));
    //        guestScore.setY(scoreS + (float) (var1 / 1.15));
    //
    //
    //        float var3 = 1.0F - 0.5F * (1.0F - var2);
    //        hostIcon.setScaleX(var3);
    //        hostIcon.setScaleY(var3);
    //        guestIcon.setScaleX(var3);
    //        guestIcon.setScaleY(var3);
    //
    //        hostName.setX(xLen[0] - (float) (var1 * 0.6));
    //        hostRank.setX(xLen[1] - (float) (var1 * 0.6));
    //        guestName.setX(xLen[2] + (float) (var1 * 0.6));
    //        guestRank.setX(xLen[3] + (float) (var1 * 0.6));
    //
    //        hostScore.setX(xLen[4] - ((1f - var2) * perDpi * 4f));
    //        guestScore.setX(xLen[5] + ((1f - var2) * perDpi * 4f));
    //        hostScore.setTextSize(32 - ((1f - var2) * 8));
    //        guestScore.setTextSize(32 - ((1f - var2) * 8));
    //        /**
    //         * 设置控件透明度
    //         */
    //        hostName.setAlpha(((perDpi * 10 - var1) / (perDpi * 10)));
    //        hostRank.setAlpha(((perDpi * 10 - var1) / (perDpi * 10)));
    //        guestName.setAlpha(((perDpi * 10 - var1) / (perDpi * 10)));
    //        guestRank.setAlpha(((perDpi * 10 - var1) / (perDpi * 10)));
    //
    //        num.setAlpha(((perDpi * 2 - var1) / (perDpi * 2)));
    //        beginTime.setAlpha(((perDpi * 3.5f - var1) / (perDpi * 3.5f)));
    //        timeRL.setAlpha(((perDpi * 10 - var1) / (perDpi * 10)));
    //        halfScore.setAlpha(((perDpi * 13 - var1) / (perDpi * 13)));
    //        timeRLTop.setAlpha(((perDpi * 13 - var1) / (perDpi * 13)));
    //
    //    }


}
