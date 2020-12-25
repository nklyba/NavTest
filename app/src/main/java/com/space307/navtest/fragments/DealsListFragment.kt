package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class DealsListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_deals_list, container, false)

        view.findViewById<Button>(R.id.active_op_deal_button).setOnClickListener {
            Toast.makeText(requireContext(), "TODO Navigate to op trading", Toast.LENGTH_SHORT)
                .show()
        }

        view.findViewById<Button>(R.id.active_fx_deal_button).setOnClickListener {
            Toast.makeText(requireContext(), "TODO Navigate to fx trading", Toast.LENGTH_SHORT)
                .show()
        }

        view.findViewById<Button>(R.id.closed_op_deal_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToOpClosedDealBottomSheet)
        }

        view.findViewById<Button>(R.id.closed_fx_deal_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToFxClosedDealBottomSheet)
        }

        return view
    }
}