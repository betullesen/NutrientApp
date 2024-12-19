package com.betulesen.nutrientbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.betulesen.nutrientbook.databinding.FragmentNutrientDetailBinding
import com.betulesen.nutrientbook.util.downloadImages
import com.betulesen.nutrientbook.util.placeHolderCreate
import com.betulesen.nutrientbook.viewmodel.NutrientDetailViewModel

class nutrientDetail : Fragment() {

    private var _binding: FragmentNutrientDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : NutrientDetailViewModel
    var nutrientID = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutrientDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NutrientDetailViewModel::class.java]

        arguments?.let {

            nutrientID = nutrientDetailArgs.fromBundle(it).nutrientID
        }

        viewModel.takeRoomData(nutrientID)

        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.nutrientLiveData.observe(viewLifecycleOwner){
            binding.nutrientName.text = it.nutrientName
            binding.nutrienCalorie.text = it.nutrientCalorie
            binding.nutrientProtein.text = it.nutrientProtein
            binding.nutrientCarbohydrate.text = it.nutrientCarbohydrate
            binding.nutrientFat.text = it.nutrientFat
            binding.nutrientImageView.downloadImages(it.nutrientImage, placeHolderCreate(requireContext()))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}