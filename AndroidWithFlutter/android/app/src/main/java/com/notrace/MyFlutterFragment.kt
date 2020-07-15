package com.notrace

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.flutter.facade.Flutter
import io.flutter.view.FlutterView
import org.json.JSONObject
import java.lang.Exception

val TAG = MyFlutterFragment.javaClass.simpleName.toString()
val ARG_ROUTE = "_route_"
val PARAMS = "_params_"


class MyFlutterFragment : Fragment() {

    var mRoute = "/";
    var mParams = ""
    var mFlutterView: FlutterView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mRoute = it.getString(ARG_ROUTE, "")
            mParams = it.getString(PARAMS, "")
            val jsonObject = JSONObject()

            var paramsObject: JSONObject? = null
            if (mParams.isNotEmpty()) {
                try {
                    paramsObject= JSONObject(mParams)


                    jsonObject.put("pageParams",paramsObject)
                    mRoute="${mRoute}?${jsonObject.toString()}"
                    print("------${mRoute}")
                }catch (e:Exception){
                    e.printStackTrace()
                }


            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mFlutterView=Flutter.createView(activity as Activity,lifecycle,mRoute)

        mFlutterView!!.setZOrderOnTop(true);
        mFlutterView!!.setZOrderMediaOverlay(false);
        mFlutterView!!.getHolder().setFormat(Color.parseColor("#00000000"));

        //注册channel
        // GeneratedPluginRegistrant.registerWith(mFlutterView.getPluginRegistry());
        return mFlutterView;
    }

    companion object {

        @JvmStatic
        fun newInstance(route: String, params: String) =
            MyFlutterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ROUTE, route)
                    putString(PARAMS, params)
                }
            }
    }
}