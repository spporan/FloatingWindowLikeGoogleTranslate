package com.poran.floatingwindowlikegoogletranslate

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //extract intent data
        hasIntent()
    }

    private fun showAlertDialog(selectedText: String) {
        MaterialAlertDialogBuilder(this)
            .setIcon(R.drawable.ic_launcher_foreground)
            //.setView() you can set custom view here
            .setTitle("Floating Window")
            .setMessage(selectedText)
            .setOnDismissListener {
                onBackPressed()
            }
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
                onBackPressed()
            }
            .create().apply {
                window?.setGravity(Gravity.TOP)
                show()
            }
    }

    private fun hasIntent() {
        if (intent  != null) {
            var text: String? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // Intent.EXTRA_PROCESS_TEXT  support from 23 or above
                text = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)?.toString()
            }

            showAlertDialog(text ?: "Floating window like alert dialog...")
        } else {
            showAlertDialog("Floating window like alert dialog...")
        }
    }

}