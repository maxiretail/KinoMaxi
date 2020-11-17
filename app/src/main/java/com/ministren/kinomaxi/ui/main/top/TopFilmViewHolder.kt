package com.ministren.kinomaxi.ui.main.top

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ministren.kinomaxi.databinding.ItemTopFilmBinding
import com.ministren.kinomaxi.entity.Film

/**
 * Класс для отображения элемента списка топ фильмов
 */
class TopFilmViewHolder(
    binding: ItemTopFilmBinding,
    private val onFilmClick: (filmId: Long) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {

    private val rootView = binding.root
    private val imageView = binding.filmPoster

    /**
     * Загрузить изображение постера по ссылке [posterUrl]
     * и отобразить его в текущем элементе списка
     */
    fun setData(film: Film) {
        Glide.with(imageView).load(film.posterUrl).into(imageView)
        rootView.setOnClickListener { onFilmClick(film.id) }
    }
}