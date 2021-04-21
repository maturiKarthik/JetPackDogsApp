package com.example.jetpackdogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.jetpackdogs.R
import com.example.jetpackdogs.Utils.NavigationUtil
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*


class DetailFragment : Fragment() {

    private var dogUUID = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Getting Arguments
        arguments?.let {
            dogUUID = DetailFragmentArgs.fromBundle(it).dogUuid
            text.text = dogUUID.toString()
        }

        detail_button.setOnClickListener {
            // Simple code to perform the Navigation The Directions class are generated from the navigation.xml file
            val actionToListView = DetailFragmentDirections.actionDetailFragmentToListFragment()
            NavigationUtil.navigationTransaction(it, actionToListView)
        }
    }
}