package com.space307.navtest.views

import android.content.Context
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.space307.navtest.R
import com.space307.navtest.data.NavigationBarTabType

class NavigationBarView  @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val tradingImageView: ImageView
    private val dealsImageView: ImageView
    private val marketplaceImageView: ImageView
    private val helpImageView: ImageView
    private val moreImageView: ImageView

    private val dealsCountTextView: TextView
    private val helpCountTextView: TextView
    private val moreCountTextView: TextView

    private val marketplaceContainerView: View

    private lateinit var tabSelectedListener: (selectedTab: NavigationBarTabType) -> Unit

    init {
        View.inflate(context, R.layout.view_bottom_navigation, this)

        tradingImageView = findViewById(R.id.navigation_trading_image_view)
        dealsImageView = findViewById(R.id.navigation_deals_image_view)
        marketplaceImageView = findViewById(R.id.navigation_marketplace_image_view)
        helpImageView = findViewById(R.id.navigation_help_image_view)
        moreImageView = findViewById(R.id.navigation_more_image_view)

        dealsCountTextView = findViewById(R.id.navigation_deals_counter_text_view)
        helpCountTextView = findViewById(R.id.navigation_help_counter_text_view)
        moreCountTextView = findViewById(R.id.navigation_more_counter_text_view)

        marketplaceContainerView = findViewById(R.id.navigation_marketplace_container)

        initViews()
    }

    private fun initViews() {
        findViewById<View>(R.id.navigation_trading_container).setOnClickListener { tabSelectedListener(
            NavigationBarTabType.TRADING
        ) }
        findViewById<View>(R.id.navigation_deals_container).setOnClickListener { tabSelectedListener(
            NavigationBarTabType.DEALS
        ) }
        findViewById<View>(R.id.navigation_help_container).setOnClickListener { tabSelectedListener(
            NavigationBarTabType.HELP
        ) }
        marketplaceContainerView.setOnClickListener { tabSelectedListener(NavigationBarTabType.MARKETPLACE) }
        findViewById<View>(R.id.navigation_more_container).setOnClickListener { tabSelectedListener(
            NavigationBarTabType.MORE
        ) }

        dealsCountTextView.visibility = View.GONE
        helpCountTextView.visibility = View.GONE
        moreCountTextView.visibility = View.GONE
    }

    fun setTabSelectedListener(listener: (selectedTab: NavigationBarTabType) -> Unit) {
        tabSelectedListener = listener
    }

    @ColorInt
    private val mainColor = ContextCompat.getColor(context, R.color.ui_core_contrast_gamma)

    @ColorInt
    private val selectedColor = ContextCompat.getColor(context, R.color.ui_core_white)

    var selectedTab: NavigationBarTabType = NavigationBarTabType.TRADING
        set(value) {
            field = value

            tradingImageView.setColorFilter(mainColor, PorterDuff.Mode.SRC_IN)

            dealsImageView.setColorFilter(mainColor, PorterDuff.Mode.SRC_IN)

            helpImageView.setColorFilter(mainColor, PorterDuff.Mode.SRC_IN)

            marketplaceImageView.setColorFilter(mainColor, PorterDuff.Mode.SRC_IN)

            moreImageView.setColorFilter(mainColor, PorterDuff.Mode.SRC_IN)

            when (value) {
                NavigationBarTabType.TRADING -> {
                    tradingImageView.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN)
                }
                NavigationBarTabType.DEALS -> {
                    dealsImageView.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN)
                }
                NavigationBarTabType.HELP -> {
                    helpImageView.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN)
                }
                NavigationBarTabType.MARKETPLACE -> {
                    marketplaceImageView.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN)
                }
                NavigationBarTabType.MORE -> {
                    moreImageView.setColorFilter(selectedColor, PorterDuff.Mode.SRC_IN)
                }
            }
        }

    fun setDealsCount(dealsCount: Int) {
        dealsCountTextView.visibility = if (dealsCount > 0) View.VISIBLE else View.GONE
        dealsCountTextView.text = dealsCount.toString()
    }

    fun setHelpCount(helpCount: Int) {
        helpCountTextView.visibility = if (helpCount > 0) View.VISIBLE else View.GONE
        helpCountTextView.text = helpCount.toString()
    }

    fun setMoreCount(moreCount: Int) {
        moreCountTextView.visibility = if (moreCount > 0) View.VISIBLE else View.GONE
        moreCountTextView.text = moreCount.toString()
    }
}