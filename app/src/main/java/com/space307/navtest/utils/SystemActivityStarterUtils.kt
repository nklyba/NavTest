package com.space307.navtest.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

/**
 * Предлагает открыть web-ссылку в достпуном браузере.
 * В случае отсутствия приложения будет `toast` с текстом.
 */
@SuppressLint("QueryPermissionsNeeded")
fun startSystemViewActivity(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(url)

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "There is no browser on your device", Toast.LENGTH_SHORT).show()
    }
}