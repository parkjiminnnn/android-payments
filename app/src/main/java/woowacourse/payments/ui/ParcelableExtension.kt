package woowacourse.payments.ui

import android.content.Intent
import android.os.Build
import android.os.Parcelable
import java.io.Serializable

inline fun <reified T : Parcelable> Intent.getParcelableExtraCompat(key: String): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getParcelableExtra(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        getParcelableExtra(key) as? T
    }

inline fun <reified T : Serializable> Intent.getSerializableExtraCompat(key: String): T? =
    try {
        @Suppress("DEPRECATION")
        getSerializableExtra(key) as? T
    } catch (e: Exception) {
        null
    }
