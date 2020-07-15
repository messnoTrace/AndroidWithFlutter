package com.notrace

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugins.GeneratedPluginRegistrant
import io.flutter.view.FlutterMain
import io.flutter.view.FlutterView
import org.json.JSONObject
import java.lang.Exception

class FlutterMainActivity : FlutterActivity(), MethodChannel.MethodCallHandler {

    var routerStr = ""

    private val TOAST_CHANNEL = "com.test.native_flutter/toast"

    override fun onCreate(savedInstanceState: Bundle?) {
        FlutterMain.startInitialization(applicationContext)

        super.onCreate(savedInstanceState)

        GeneratedPluginRegistrant.registerWith(this)
        registerCustomPlugin(this)
    }


    override fun createFlutterView(context: Context?): FlutterView {
        getIntentData()

        val mathcParams = WindowManager.LayoutParams(-1, -1)
        val flutterNativeView = this.createFlutterNativeView()
        val flutterView = FlutterView(this, null, flutterNativeView)
        flutterView.setInitialRoute(routerStr)
        flutterView.layoutParams = mathcParams
        setContentView(flutterView)

        return flutterView
    }

    override fun onMethodCall(call: MethodCall, result: MethodChannel.Result) {
        when(call.method){
            "showToast"->{
                val content=call.argument<String>("content")
                Toast.makeText(this,content,Toast.LENGTH_SHORT).show()
            }
            else->{
                result.notImplemented()
            }
        }
    }

    fun registerCustomPlugin(registry: PluginRegistry) {
        registerMethodChannel()
    }

    fun registerMethodChannel() {
        MethodChannel(
            this.registrarFor(TOAST_CHANNEL).messenger(),
            TOAST_CHANNEL
        ).setMethodCallHandler(this)
    }

    fun getIntentData() {

        var route = intent.getStringExtra("_route_")
        var params = intent.getStringExtra("_params_")
        var jsonObject = JSONObject()
        try {
            jsonObject.put("pageParams", params)

        } catch (e: Exception) {
        }
        routerStr = "${route}?${jsonObject.toString()}"
    }
}