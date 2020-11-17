package com.ministren.kinomaxi.ui.film.frames

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ministren.kinomaxi.databinding.ItemFilmFrameBinding
import com.ministren.kinomaxi.entity.FilmFrame

/**
 * Класс для отображения элемента списка кадров фильма
 */
class FilmFrameViewHolder(
    binding: ItemFilmFrameBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val imageView = binding.root

    /**
     * Загрузить изображение для предпросмотра кадра [frame]
     * и отобразить его в текущем элементе списка
     */
    fun setData(frame: FilmFrame) {
        Glide.with(imageView).load(frame.previewUrl).into(imageView)
    }
}