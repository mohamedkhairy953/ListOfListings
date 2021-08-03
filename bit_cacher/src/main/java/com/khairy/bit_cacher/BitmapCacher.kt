package com.khairy.bit_cacher

import android.graphics.Bitmap
import android.os.AsyncTask
import android.util.LruCache

object BitmapCacher {
    private lateinit var memoryCache: LruCache<String, Bitmap>
    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
    private val cacheSize = maxMemory / 8

    init {
        memoryCache =
            object : LruCache<String, Bitmap>(cacheSize) {

                override fun sizeOf(key: String, bitmap: Bitmap): Int {
                    // The cache size will be measured in kilobytes rather than
                    // number of items.
                    return bitmap.byteCount / 1024
                }
            }
    }

    fun getBitmap(imageKey: String?): Bitmap? {
        imageKey?.let {
            return memoryCache[it]
        }
        return null
    }

    fun saveBitmap(imageKey: String, bitmap: Bitmap) {
        memoryCache.put(imageKey,bitmap)
    }


}