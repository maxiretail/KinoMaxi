package com.ministren.kinomaxi.ui.main.top

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ministren.kinomaxi.databinding.ItemTopFilmBinding

/**
 * Класс для отображения элемента списка топ фильмов
 */
class TopFilmViewHolder(
    binding: ItemTopFilmBinding,
) : RecyclerView.ViewHolder(binding.root) {

    private val imageView = binding.filmPoster

    /**
     * Загрузить изображение постера по ссылке [posterUrl]
     * и отобразить его в текущем элементе списка
     */
    fun setData(posterUrl: String) {
        Glide.with(imageView).load(posterUrl).into(imageView)
    }
}