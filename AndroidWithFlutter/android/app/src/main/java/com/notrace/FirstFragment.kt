package com.notrace

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {

            val map=HashMap<String,String>()
            map.put("content","这是测试内容")
            val jsonStr= Gson().toJson(map)
            val route="test"
            val intent=Intent(activity,FlutterMainActivity::class.java)
            intent.putExtra("_route_",route)
            intent.putExtra("_params_",jsonStr)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            FirstFragment()
    }
}