package com.betulesen.nutrientbook.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.betulesen.nutrientbook.adapter.NutrientRecyclerAdapter
import com.betulesen.nutrientbook.databinding.FragmentNutrientListBinding
import com.betulesen.nutrientbook.service.NutrientAPI
import com.betulesen.nutrientbook.viewmodel.NutrientListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class nutrientList : Fragment() {

    private var _binding: FragmentNutrientListBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : NutrientListViewModel
    private val nutrientRecyclerAdapter = NutrientRecyclerAdapter(arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNutrientListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[NutrientListViewModel::class.java]
        viewModel.refreshData()

        binding.nutrientRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.nutrientRecyclerView.adapter = nutrientRecyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.nutrientRecyclerView.visibility = View.GONE
            binding.nutrientErrorMessage.visibility = View.GONE
            binding.nutrientLoading.visibility = View.VISIBLE

            viewModel.refreshDataFromInternet()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()
    }

    private  fun observeLiveData() {
        viewModel.nutrients.observe(viewLifecycleOwner) {
            nutrientRecyclerAdapter.nutrientListUpdate(it)
            binding.nutrientRecyclerView.visibility = View.VISIBLE
        }

        viewModel.nutrientErrorMessage.observe(viewLifecycleOwner) {
            if(it) {
                binding.nutrientErrorMessage.visibility = View.VISIBLE
                binding.nutrientRecyclerView.visibility = View.GONE
            }else{
                binding.nutrientErrorMessage.visibility = View.GONE
            }
        }

        viewModel.nutrientLoading.observe(viewLifecycleOwner) {
            if(it) {
                binding.nutrientErrorMessage.visibility = View.GONE
                binding.nutrientRecyclerView.visibility = View.GONE
                binding.nutrientLoading.visibility = View.VISIBLE
            }else{
                binding.nutrientLoading.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}