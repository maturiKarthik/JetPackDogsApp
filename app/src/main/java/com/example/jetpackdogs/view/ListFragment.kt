package com.example.jetpackdogs.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.jetpackdogs.R
import com.example.jetpackdogs.Utils.NavigationUtil
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list_button.setOnClickListener {
            val actionDetailDirection = ListFragmentDirections.actionListFragmentToDetailFragment()
            //After rebuilding the project.
            actionDetailDirection.dogUuid = 534 // Adding the argument

            NavigationUtil.navigationTransaction(it,actionDetailDirection)
        }
    }


}