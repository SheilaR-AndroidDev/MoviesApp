package com.example.fullapp.remote.presentation.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.fullapp.R
import com.example.fullapp.databinding.FragmentDetailsBinding
import com.example.fullapp.local.domain.model.Movie
import com.example.fullapp.local.domain.model.MovieDTO
import com.example.fullapp.remote.Constants.IMAGE_BASE_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding : FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    private val args : DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieDetails = args.movie
        initializeViews(movieDetails)
    }

    private fun initializeViews(movieDto: MovieDTO) = with(binding){

        val movie = Movie(movieDto.id, movieDto.title, movieDto.poster_path, movieDto.release_date, movieDto.overview)

        titleTextView.text = movie.title
        overviewTextView.text = movie.overview
        releaseTextView.text = movie.release_date

        Glide.with(moviePoster)
            .load(IMAGE_BASE_URL + movie.poster_path)
            .into(moviePoster)

        setBorderedIcon()

        favorite.setOnClickListener {
            addToFavorites(movie)
        }
    }

    private fun addToFavorites(movie: Movie) = with(binding){
        if(favorite.tag == 2131165286){
            addFavorite(movie)
        }else{
            removeFavorite(movie.id)
        }
    }

    private fun addFavorite(movie: Movie){
        try {
            viewModel.saveFavorite(movie)
            setFilledIcon()
            viewModel.getFavorites()
        }catch (e: Exception){
            Log.i("tag", "Exception on save ${e.message}")
        }
    }

    private fun removeFavorite(movieId: Int){
        try{
            viewModel.deleteFavorite(movieId)
            setBorderedIcon()
            viewModel.getFavorites()

        }catch (e: Exception){
            Log.i("tag", "Exception on remove ${e.message}")
        }
    }

    private fun setBorderedIcon() = with(binding) {
        favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        favorite.tag = R.drawable.ic_baseline_favorite_border_24
    }

    private fun setFilledIcon() = with(binding) {
        favorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        favorite.tag = R.drawable.ic_baseline_favorite_24
    }

}