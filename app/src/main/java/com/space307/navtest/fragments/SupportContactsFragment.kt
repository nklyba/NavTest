package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R

class SupportContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_support_contacts, container, false)

        view.findViewById<Button>(R.id.support_livetex_chat_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToSupportLiveTexChat)
        }

        return view
    }
}