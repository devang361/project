package com.example.carconnect

import android.app.Application
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.carconnect.databinding.ActivitySellcarBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseDatabase.getInstance().setPersistenceEnabled(true)
    }
}

class sellcar : AppCompatActivity() {
    var sImage: String? = ""
    private lateinit var db: DatabaseReference
    private lateinit var binding: ActivitySellcarBinding
    private lateinit var imageLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySellcarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                val uri = result.data?.data
                try {
                    val inputStream = contentResolver.openInputStream(uri!!)
                    val myBitmap = BitmapFactory.decodeStream(inputStream)
                    val stream = ByteArrayOutputStream()
                    myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    val bytes = stream.toByteArray()
                    sImage = Base64.encodeToString(bytes, Base64.DEFAULT)
                    inputStream?.close()
                    Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT).show()
                } catch (ex: Exception) {
                    Toast.makeText(this, ex.message.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun et_post(view: View) {
        val title = binding.etTitle.text.toString()
        val condition = binding.etCondition.text.toString()
        val year = binding.etYear.text.toString()
        val brand = binding.etBrand.text.toString()
        val model = binding.etModel.text.toString()
        val features = binding.etFeatures.text.toString()
        val location = binding.etLocation.text.toString()
        val price = binding.etPrice.text.toString()
        val description = binding.etDescription.text.toString()

        db = FirebaseDatabase.getInstance().getReference("Carsdata")
        val item = itemDs(title, condition, year, brand, model, features, location, price, description)
        val databaseReference = FirebaseDatabase.getInstance().reference
        val id = databaseReference.push().key
        db.child(id.toString()).setValue(item).addOnSuccessListener {
            binding.etTitle.text.clear()
            binding.etCondition.text.clear()
            binding.etYear.text.clear()
            binding.etBrand.text.clear()
            binding.etModel.text.clear()
            binding.etFeatures.text.clear()
            binding.etLocation.text.clear()
            binding.etPrice.text.clear()
            binding.etDescription.text.clear()

            Toast.makeText(this, "Data inserted", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { e ->
            Toast.makeText(this, "Data insertion failed: ${e.message}", Toast.LENGTH_SHORT).show()
            Log.e("FirebaseInsertion", "Data insertion failed", e)
        }
    }

    fun et_upload(view: View) {
        val myfileintent = Intent(Intent.ACTION_GET_CONTENT)
        myfileintent.type = "image/*"
        imageLauncher.launch(myfileintent)
    }
}
