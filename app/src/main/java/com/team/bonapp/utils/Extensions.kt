package com.team.bonapp.utils

import android.widget.ImageView
import androidx.lifecycle.LifecycleCoroutineScope
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun ImageView.setImage(image: String) {
    Glide.with(this.context).asBitmap().load(image).into(this)
}

fun <T> Flow<T>.launchWhenStarted(lifeCycleScope: LifecycleCoroutineScope) = lifeCycleScope
    .launchWhenStarted {
        this@launchWhenStarted.collect()
    }