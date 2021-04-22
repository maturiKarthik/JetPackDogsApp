package com.example.jetpackdogs.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.jetpackdogs.R
import com.example.jetpackdogs.model.DogBreed
import com.example.jetpackdogs.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        //Fetching Data From ListFragment
        arguments?.let {
            val dogBreed: DogBreed? = DetailFragmentArgs.fromBundle(it).dog
            detailViewModel.uploadData(dogBreed)
        }

        //Create Observe
        observeData()
    }

    private fun observeData() {
        detailViewModel.dogDetail.observe(viewLifecycleOwner, Observer { dog ->
            dog?.let {
                context?.let { it1 -> Glide.with(it1).load(it.url).into(dogImage) }
                dogName.text = it.name
                dogPurpose.text = it.bred_for
                dogLifeSpan.text = it.life_span
                dogTemparment.text = it.temperament
                dogOrigin.text = it.origin
            }
        })
    }
}