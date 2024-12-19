package com.betulesen.nutrientbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.betulesen.nutrientbook.databinding.NutrientRecyclerRowBinding
import com.betulesen.nutrientbook.model.nutrient
import com.betulesen.nutrientbook.util.downloadImages
import com.betulesen.nutrientbook.util.placeHolderCreate
import com.betulesen.nutrientbook.view.nutrientList
import com.betulesen.nutrientbook.view.nutrientListDirections

class NutrientRecyclerAdapter(val nutrientList : ArrayList<nutrient>) : RecyclerView.Adapter<NutrientRecyclerAdapter.NutrientViewHolder>() {

    class NutrientViewHolder(val binding : NutrientRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutrientViewHolder {
        val binding = NutrientRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NutrientViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nutrientList.size
    }

    fun nutrientListUpdate(nevNutrientList : List<nutrient>) {
        nutrientList.clear()
        nutrientList.addAll(nevNutrientList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: NutrientViewHolder, position: Int) {
        holder.binding.name.text = nutrientList[position].nutrientName
        holder.binding.calorie.text = nutrientList[position].nutrientCalorie

        holder.itemView.setOnClickListener {
            val action = nutrientListDirections.actionNutrientListToNutrientDetail(nutrientList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.binding.imageView.downloadImages(nutrientList[position].nutrientImage,
            placeHolderCreate(holder.itemView.context)
        )
    }
}