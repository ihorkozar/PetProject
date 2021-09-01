package com.example.petproject.ui.detail

import android.app.Application
import android.content.ContentResolver
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.MalformedURLException
import java.net.URL
import java.io.*


class DetailViewModel(app: Application) : AndroidViewModel(app) {

    fun downloadImage(link: String) = viewModelScope.launch(Dispatchers.IO) {
        var imageurl: URL? = null
        try {
            imageurl = URL(link)
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        val resolver = getApplication<Application>().applicationContext.contentResolver
        val bitmap = BitmapFactory.decodeStream(imageurl?.openConnection()?.getInputStream())
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) saveImageQ(resolver, bitmap)
        else saveImage(resolver, bitmap)
    }


    private fun saveImage(resolver: ContentResolver, bitmap: Bitmap) {
        try {
            MediaStore.Images.Media.insertImage(resolver, bitmap, "", "")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveImageQ(
        resolver: ContentResolver,
        bitmap: Bitmap
    ) {
        val stream: OutputStream?
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "filename")
            put(MediaStore.Files.FileColumns.MIME_TYPE, "image/png")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            put(MediaStore.MediaColumns.IS_PENDING, 1)
        }
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        uri?.let { resolver.openOutputStream(it) }.also { stream = it }
        stream?.use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }
        stream?.flush()
        stream?.close()
        contentValues.clear()
        contentValues.put(MediaStore.MediaColumns.IS_PENDING, 0)
        uri?.let {
            resolver.update(it, contentValues, null, null)
        }
    }
}