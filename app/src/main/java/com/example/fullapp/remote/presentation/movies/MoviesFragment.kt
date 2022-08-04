package com.example.fullapp.remote.presentation.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.fullapp.R
import com.example.fullapp.databinding.FragmentMoviesBinding
import com.example.fullapp.remote.domain.model.Result
import com.example.fullapp.remote.domain.model.toMovieDTO
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(), OnClickListener {

    private var _binding : FragmentMoviesBinding? = null
    private val binding : FragmentMoviesBinding get() = _binding!!

    private val viewModel: MoviesViewModel by viewModels()

    private val adapter by lazy{
        MoviesAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeObservers()
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        val rv = binding.moviesRecyclerView
        rv.adapter = adapter
    }

    private fun initializeObservers(){
        viewModel.allMovies.observe(viewLifecycleOwner){
            Log.i("tag","movies $it")
            adapter.submitList(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClickListener(movie: Result) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToDetailsFragment(movie.toMovieDTO())
        findNavController().navigate(action)
    }
}