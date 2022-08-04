package com.example.fullapp.remote.presentation.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fullapp.databinding.MovieItemBinding
import com.example.fullapp.remote.Constants.IMAGE_BASE_URL
import com.example.fullapp.remote.domain.model.Result

class MoviesAdapter(
    private val clickListener: OnClickListener) : ListAdapter<Result, MoviesAdapter.MoviesViewHolder>(MoviesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MoviesViewHolder(private val binding : MovieItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Result){
            Glide.with(binding.moviePoster)
                .load(IMAGE_BASE_URL + movie.poster_path)
                .into(binding.moviePoster)

            binding.moviePoster.setOnClickListener {
                clickListener.onClickListener(movie)
            }

        }
    }

    class MoviesDiff : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }
    }
}

interface OnClickListener{
    fun onClickListener(movie: Result)
}