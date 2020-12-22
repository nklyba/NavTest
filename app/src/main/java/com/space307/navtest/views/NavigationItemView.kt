package com.space307.navtest.views

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.space307.navtest.R

class NavigationItemView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var iconImageView: ImageView
    private var titleTextView: TextView
    private var descTextView: TextView
    private var countTextView: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.item_navigation, this, true)

        iconImageView = findViewById(R.id.navigation_item_icon_imageview)
        titleTextView = findViewById(R.id.navigation_item_title_textview)
        descTextView = findViewById(R.id.navigation_item_desc_textview)
        countTextView = findViewById(R.id.navigation_item_count_textview)

        @SuppressLint("CustomViewStyleable")
        val a = context.obtainStyledAttributes(attrs, R.styleable.UiCoreNavigationItemView)

        val iconDrawableResId = a.getResourceId(R.styleable.UiCoreNavigationItemView_ui_core_nav_item_icon, -1)
        iconImageView.setImageDrawable(ContextCompat.getDrawable(context, iconDrawableResId))
        titleTextView.text = a.getString(R.styleable.UiCoreNavigationItemView_ui_core_nav_item_title)
        titleTextView.setTextColor(
            a.getColor(
                R.styleable.UiCoreNavigationItemView_ui_core_nav_item_color,
                ContextCompat.getColor(context, R.color.ui_core_contrast_alpha)
            )
        )

        val desc = a.getString(R.styleable.UiCoreNavigationItemView_ui_core_nav_item_desc)
        descTextView.visibility = if (desc != null) View.VISIBLE else View.GONE
        descTextView.text = desc

        a.recycle()

        countTextView.visibility = View.GONE

        setBackgroundResource(R.drawable.ui_core_button_base_alpha_rounded_background)
    }

    fun setCount(count: Int) {
        countTextView.visibility = if (count > 0) View.VISIBLE else View.GONE
        countTextView.text = count.toString()
    }
}