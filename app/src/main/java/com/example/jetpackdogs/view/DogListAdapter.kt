package com.example.jetpackdogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jetpackdogs.R
import com.example.jetpackdogs.Utils.NavigationUtil
import com.example.jetpackdogs.model.DogBreed
import kotlinx.android.synthetic.main.item_dog.view.*

/**
 * Recycler Adapter
 * https://developer.android.com/guide/topics/ui/layout/recyclerview
 */
class DogListAdapter(val dogBreedList: ArrayList<DogBreed>) :
    RecyclerView.Adapter<DogListAdapter.ViewHolder>() {

    fun updateDogList(newdogBreedList: List<DogBreed>) {
        dogBreedList.clear()
        dogBreedList.addAll(newdogBreedList)
        notifyDataSetChanged()
    }

    class ViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_dog, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.view.dogName.text = dogBreedList[position].name
        viewHolder.view.dogLifeSpan.text = dogBreedList[position].life_span
        Glide.with(viewHolder.view).load(dogBreedList[position].url).into(viewHolder.view.imageView)
        viewHolder.view.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment()
            action.dog = dogBreedList[position]
            // click on the event
            NavigationUtil.navigationTransaction(it, action)
            //Navigation.findNavController(it)
            //    .navigate(ListFragmentDirections.actionListFragmentToDetailFragment())
        }
    }

    override fun getItemCount() = dogBreedList.size
}
