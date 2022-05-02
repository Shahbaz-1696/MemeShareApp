package com.example.memeshare

import android.content.Context
import android.os.strictmode.InstanceCountViolation
import com.android.volley.Request
import com.android.volley.toolbox.Volley
import java.security.AccessControlContext

class MySingleton constructor(context: Context) {


    companion object{
        @Volatile
        private var INSTANCE: MySingleton? = null
        fun getInstance(context: Context)=
            INSTANCE?: synchronized(this){
                INSTANCE?: MySingleton(context).also {
                    INSTANCE=it
                }
            }
    }

    private val requestQueue by lazy{
        //applicationContext  is key, it keeps you from looking the
        //Activity or BroadcastReceiver if someone passes one in
        Volley.newRequestQueue(context.applicationContext)
    }
    fun <T> addToRequestQueue(req: Request<T>){
        requestQueue.add(req)
    }



}