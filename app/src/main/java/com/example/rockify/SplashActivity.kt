package com.example.rockify

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    var permissionString = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.MODIFY_AUDIO_SETTINGS,
        Manifest.permission.READ_PHONE_STATE,
        Manifest.permission.PROCESS_OUTGOING_CALLS,
        Manifest.permission.RECORD_AUDIO
        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        if(!hasPermission(this@SplashActivity, *permissionString)){
            // Ask for the permission
        }
        else{
            Handler().postDelayed( {
                val startAct = Intent(this@SplashActivity, MainActivity::class.java);
                startActivity(startAct)
                this.finish()
            }, 1000)
        }
    }

    fun hasPermission(context: Context, vararg permissions: String): Boolean{
        var hasAllPermission = true
        for(permission in permissions){
           val res = context.checkCallingOrSelfPermission(permission)
            if(res != PackageManager.PERMISSION_GRANTED) {
                hasAllPermission = false
            }
        }
        return hasAllPermission
    }
}