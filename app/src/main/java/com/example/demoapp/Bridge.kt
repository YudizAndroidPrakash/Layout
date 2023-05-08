package com.example.demoapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.result.contract.ActivityResultContract

class Bridge : ActivityResultContract<String, Int>() {
    override fun createIntent(context: Context, input: String): Intent {
        var intent = Intent(context,RtPermissionReturnResult2::class.java)
        intent.putExtra("Heading",input)
        return intent
     }
    override fun parseResult(resultCode: Int, intent: Intent?): Int {
        return 1
    }
}