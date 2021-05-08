package com.example.petproject.ui.detail

import android.Manifest
import android.app.Application
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL


class DetailViewModel(app: Application) : AndroidViewModel(app) {

    fun saveImage(link: String) = viewModelScope.launch(Dispatchers.IO){
        var imageurl : URL? = null
        try {
            imageurl = URL(link)
        } catch (e: MalformedURLException){
            e.printStackTrace()
        }
        try {
            val context = getApplication<Application>().applicationContext
            val bitmap = BitmapFactory.decodeStream(imageurl?.openConnection()?.getInputStream())
            MediaStore.Images.Media.insertImage(context.contentResolver, bitmap, "", "")
        } catch (e: IOException){
            e.printStackTrace()
        }
    }
}