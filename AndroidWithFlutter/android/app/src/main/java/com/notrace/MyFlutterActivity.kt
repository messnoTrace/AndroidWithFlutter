package com.notrace

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.flutter.facade.Flutter
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.view.FlutterView
import org.json.JSONObject

class MyFlutterActivity: AppCompatActivity(),MethodChannel.MethodCallHandler {
    private val TOAST_CHANNEL = "com.test.native_flutter/toast"

    var flutterView:FlutterView?=null
    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when(call.method){
            "showToast"->{
                var content = call.argument<String>("content")
                Toast.makeText(this, content, Toast.LENGTH_SHORT).show()
            }
            else->{result.notImplemented()}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val route=intent.getStringExtra("_route_")
        val params=intent.getStringExtra("_params_")
        val jsonObject=JSONObject()
        try {
            jsonObject.put("pageParam",params)


        }catch (e:Exception){

        }

         flutterView=Flutter.createView(this,lifecycle,"${route}?${jsonObject.toString()}")
        setContentView(flutterView)

        registerMethodChannel()

    }


    fun registerMethodChannel(){
        MethodChannel(flutterView,TOAST_CHANNEL).setMethodCallHandler(this)
    }

    override fun onBackPressed() {

        if (flutterView!=null){
            flutterView!!.popRoute()
        }else{
            super.onBackPressed()
        }
    }
}