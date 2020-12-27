package com.space307.navtest.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class OpTradingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_op_trading, container, false)

        view.findViewById<Button>(R.id.assets_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToAssets)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        Log.d("Fragments", "OpTradingFragment - onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Fragments", "OpTradingFragment - onCreate")
    }

    override fun onStart() {
        super.onStart()

        Log.d("Fragments", "OpTradingFragment - onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("Fragments", "OpTradingFragment - onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d("Fragments", "OpTradingFragment - onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d("Fragments", "OpTradingFragment - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("Fragments", "OpTradingFragment - onDestroy")
    }

    override fun onDetach() {
        super.onDetach()

        Log.d("Fragments", "OpTradingFragment - onDetach")
    }
}