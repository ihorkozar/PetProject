package com.example.petproject.ui.detail

import android.Manifest
import android.app.AlertDialog
import android.app.Application
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.petproject.databinding.DetailFragmentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class DetailFragment : Fragment() {
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var binding: DetailFragmentBinding
    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater)
        url = DetailFragmentArgs.fromBundle(requireArguments()).selected
        Glide.with(binding.imageView.context).load(url).into(binding.imageView)
        binding.button.setOnClickListener {
            viewModel.saveImage(url)
            checkForPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                "storage",
                STORAGE_RQ,
            )
        }
        return binding.root
    }

    private fun checkForPermission(
        permission: String,
        name: String,
        requestCode: Int
    ) = when {
        ContextCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED -> {
            Toast.makeText(requireContext(), "$name permission is granted", Toast.LENGTH_LONG)
                .show()
            //viewModel.saveImage(url)
            //saveImage(url)
        }
        shouldShowRequestPermissionRationale(permission) -> showDialog(
            permission,
            name,
            requestCode
        )
        else -> requestPermissions(arrayOf(permission), requestCode)
    }

    private fun showDialog(permission: String, name: String, requestCode: Int) {
        val builder = AlertDialog.Builder(context)
        builder.apply {
            setMessage("Permission to access your $name is required to use this app")
            setTitle("Permission required")
            setPositiveButton("OK") { _, _ ->
                requestPermissions(arrayOf(permission), requestCode)
            }
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        fun innerCheck(name: String) {
            if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(requireContext(), "$name permission refused", Toast.LENGTH_LONG)
                    .show()
            } else {
                Toast.makeText(requireContext(), "$name permission granted", Toast.LENGTH_LONG)
                    .show()
                //saveImage(url)
            }
        }

        when (requestCode) {
            STORAGE_RQ -> innerCheck("storage")
        }
    }

    fun saveImage(link: String){
        var imageurl : URL? = null
        try {
            imageurl = URL(link)
        } catch (e: MalformedURLException){
            e.printStackTrace()
        }
        try {
            val bitmap = BitmapFactory.decodeStream(imageurl?.openConnection()?.getInputStream())
            MediaStore.Images.Media.insertImage(context?.contentResolver, bitmap, "", "")
        } catch (e: IOException){
            e.printStackTrace()
        }
    }

    companion object {
        private const val STORAGE_RQ = 101
    }
}