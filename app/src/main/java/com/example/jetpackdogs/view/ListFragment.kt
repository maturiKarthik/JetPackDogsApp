package com.example.jetpackdogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpackdogs.R
import com.example.jetpackdogs.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogListAdapter = DogListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Init the ViewModel
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.setContext(requireContext())
        viewModel.retrieveData()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogListAdapter
        }

        refreshLayout.setOnRefreshListener {
            /**
             * Logic For Refresh Layout
             */
            errorMessage.visibility = View.GONE
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.GONE
            viewModel.loadDataFromApi()
            refreshLayout.isRefreshing = false
        }
        observeViewModel()

    }


    // Observing the values
    private fun observeViewModel() {

        viewModel.dogs.observe(viewLifecycleOwner, Observer {
            it?.let {
                recyclerView.visibility = View.VISIBLE
                dogListAdapter.updateDogList(it)
                viewModel.storeDataLocally(it)
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer {
            it?.let {
                errorMessage.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        viewModel.dogsLoadError.observe(viewLifecycleOwner, Observer {
            it?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        })
    }
}