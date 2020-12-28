package com.space307.navtest.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.space307.navtest.R
import com.space307.navtest.utils.startSystemViewActivity

class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        view.findViewById<Button>(R.id.promo_video_button).setOnClickListener {
            startSystemViewActivity(requireContext(), "https://www.youtube.com/watch?v=JFGq0asqSuA")
        }

        view.findViewById<Button>(R.id.traders_way_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToTradersWay)
        }

        view.findViewById<Button>(R.id.tasks_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToTasks)
        }

        view.findViewById<Button>(R.id.achievements_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToAchievements)
        }

        view.findViewById<Button>(R.id.profile_settings_button).setOnClickListener {
            findNavController().navigate(R.id.navigateToProfileSettings)
        }

        view.findViewById<Button>(R.id.log_out_button).setOnClickListener {
            //TODO Log out
        }

        return view
    }
}